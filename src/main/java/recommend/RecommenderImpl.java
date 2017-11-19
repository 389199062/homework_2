package recommend;

import javafx.util.Pair;
import segmenter.ChineseSegmenterImpl;
import tf_idf.TF_IDF;
import tf_idf.TF_IDFImpl;
import vo.StockInfo;
import vo.UserInterest;

import java.util.*;

public class RecommenderImpl implements Recommender {

    private static TF_IDFImpl tf_idf = new TF_IDFImpl();
    private static ChineseSegmenterImpl chineseSegmenter = new ChineseSegmenterImpl();
    /**
     * this function need to calculate stocks' content similarity,and return the similarity matrix
     *
     * @param stocks stock info
     * @return similarity matrix
     */
    @Override
    public double[][] calculateMatrix(StockInfo[] stocks) {
        double[][] similarity = new double[stocks.length][stocks.length];
        for (int i = 0; i < stocks.length; i++) {
            for (int j = i+1; j <stocks.length ; j++) {
                Pair<String,Double>[] pairs1 = tf_idf.getResult(chineseSegmenter.getWordsFromInput(stocks[i]));
                Pair<String,Double>[] pairs2 = tf_idf.getResult(chineseSegmenter.getWordsFromInput(stocks[j]));

                Map<String,Double> map2 = pairsToMap(pairs2);

                double sum = 0;
                double vector1Module = 0;
                double vector2Module = 0;
                for (int k = 0; k < 20; k++) {
                    String key1 = null;
                    if(k>=pairs1.length||k>=map2.size()) {
                        break;
                    }
                    key1 = pairs1[k].getKey();
                    if(map2.containsKey(key1)) {
                        sum += pairs1[k].getValue()*map2.get(key1);
                    }
                    vector1Module += Math.pow(pairs1[k].getValue(),2);
                    vector2Module += Math.pow(pairs2[k].getValue(),2);
                }

                similarity[j][i] = similarity[i][j] = sum/(Math.sqrt(vector1Module)*Math.sqrt(vector2Module));

            }
        }

        return similarity;
    }

    private Map<String,Double> pairsToMap(Pair<String,Double>[] pairs) {
        Map<String,Double> map = new HashMap<>();
        for (Pair<String,Double> pair:pairs) {
            map.put(pair.getKey(),pair.getValue());
        }

        return map;
    }

    /**
     * this function need to recommend the most possibility stock number
     *
     * @param matrix       similarity matrix
     * @param userInterest user interest
     * @return commend stock number
     */
    @Override
    public double[][] recommend(double[][] matrix, UserInterest[] userInterest) {
        double[][] result = new double[userInterest.length][10];
        List<Map<Integer,Double>> list = new ArrayList<>();
        for (int i = 0; i < userInterest.length; i++) {
            UserInterest current = userInterest[i];
            Map<Integer,Double> map = new TreeMap<>();
            for (int j = 0; j < matrix.length; j++) {
                if(current.data[j]) {
                    continue;
                }
                double sum = 0;
                for (int k = 0; k < current.data.length; k++) {
                    if(current.data[k]) {
                        sum += matrix[j][k];
                    }
                }
                map.put(j,sum);
            }
            list.add(map);
        }
        for (int l = 0;l<list.size();l++) {
            List<Map.Entry<Integer,Double>> temp = new ArrayList<>(list.get(l).entrySet());
            Collections.sort(temp, new Comparator<Map.Entry<Integer,Double>>() {
                @Override
                public int compare (Map.Entry<Integer,Double> o1, Map.Entry<Integer,Double> o2) {
                    if(o1.getValue().doubleValue()>o2.getValue().doubleValue()) {
                        return -1;
                    }
                    else if(o1.getValue().doubleValue() == o2.getValue().doubleValue()) {
                        return 0;
                    }
                    return 1;
                }
            });
            for (int i = 0; i < 10; i++) {
                result[l][i] = temp.get(i).getKey()+1;
            }
        }

        return result;
    }
}
