package segmenter;


import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import vo.StockInfo;

import java.util.*;

public class ChineseSegmenterImpl implements ChineseSegmenter {
    private static Set<String> expectedNature = new HashSet<String>() {{
        add("n");add("v");add("vd");add("vn");add("vf");
        add("vx");add("vi");add("vl");add("vg");
        add("nt");add("nz");add("nw");add("nl");
        add("ng");add("userDefine");add("wh");
    }};

    public static Map<String,Integer> articleNumContainsWord = new HashMap<>();
    /**
     * this func will get chinese word from a list of stocks. You need analysis stocks' answer and get answer word.
     * And implement this interface in the class : ChineseSegmenterImpl
     * Example: 我今天特别开心 result : 我 今天 特别 开心
     *
     * @param stocks stocks info
     * @return chinese word
     * @see ChineseSegmenterImpl
     */
    @Override
    public List<String> getWordsFromInput(StockInfo[] stocks) {
        List<String> stringList = new ArrayList<>();
        for (StockInfo stock:stocks) {
            List<String> temp = getWordsFromInput(stock);
            HashSet<String> set = new HashSet<>();
            for (String word:temp) {
                set.add(word);
            }
            for (String word:set) {
                if(!articleNumContainsWord.containsKey(word)) {
                    articleNumContainsWord.put(word,1);
                }
                else {
                    articleNumContainsWord.put(word,articleNumContainsWord.get(word)+1);
                }
            }
            stringList.addAll(getWordsFromInput(stock));
        }
        return stringList;
    }

    public List<String> getWordsFromInput(StockInfo stockInfo) {
        List<String> stringList = new ArrayList<>();
        Result result = ToAnalysis.parse(stockInfo.getAnswer()); //分词结果的一个封装，主要是一个List<Term>的terms
        List<Term> terms = result.getTerms(); //拿到terms
        for (int i = 0; i < terms.size(); i++) {
            String word = terms.get(i).getName(); //拿到词
            String natureStr = terms.get(i).getNatureStr(); //拿到词性
            if (expectedNature.contains(natureStr)) {
                stringList.add(word);
            }
        }

        return stringList;
    }
}
