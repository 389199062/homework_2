package tf_idf;

import javafx.util.Pair;
import segmenter.ChineseSegmenterImpl;
import util.StockSorter;
import vo.StockInfo;

import java.util.*;

public class TF_IDFImpl implements TF_IDF {
    /**
     * this func you need to calculate words frequency , and sort by frequency.
     * you maybe need to use the sorter written by yourself in example 1
     *
     * @param words the word after segment
     * @return a sorted words
     * @see StockSorter
     */
    @Override
    public Pair<String, Double>[] getResult(List<String> words, StockInfo[] stockInfos) {

        return getResult(words);
    }

    public Pair<String, Double>[] getResult(List<String> words) {
        Map<String,Integer> map = new HashMap<>();
        List<Pair<String,Double>> pairList = new ArrayList<>();
        for (String word:words) {
            if(!map.containsKey(word)) {
                map.put(word,1);
            }
            else {
                map.put(word,map.get(word)+1);
            }
        }
        int num = words.size();
        for (Map.Entry<String,Integer> entry:map.entrySet()) {
            double tf = entry.getValue()*1d/num;
            double idf = Math.log(60/(ChineseSegmenterImpl.articleNumContainsWord.get(entry.getKey())+1));
            Pair<String,Double> pair = new Pair<>(entry.getKey(),tf*idf);
            pairList.add(pair);
        }

        Collections.sort(pairList, new Comparator<Pair<String, Double>>() {
            @Override
            public int compare (Pair<String, Double> o1, Pair<String, Double> o2) {
                if(o1.getValue().doubleValue()>o2.getValue().doubleValue()){
                    return -1;
                }
                else if(o1.getValue().doubleValue() == o2.getValue().doubleValue()) {
                    return 0;
                }
                return 1;
            }
        });
        Pair<String,Double>[] result = new Pair[pairList.size()];

        return pairList.toArray(result);
    }



}
