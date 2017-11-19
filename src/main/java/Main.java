import recommend.RecommenderImpl;
import segmenter.ChineseSegmenterImpl;
import util.FileHandlerImpl;
import vo.StockInfo;
import vo.UserInterest;

/**
 * Created by chenshaojie on 2017/11/9,20:44.
 */
public class Main {


    public static void main (String[] args) {
        FileHandlerImpl fileHandler = new FileHandlerImpl();
        ChineseSegmenterImpl chineseSegmenter = new ChineseSegmenterImpl();
        RecommenderImpl recommender = new RecommenderImpl();
        StockInfo[] infos = fileHandler.getStockInfoFromFile("src/main/resources/data.txt");
        chineseSegmenter.getWordsFromInput(infos);
        double[][] similarity = recommender.calculateMatrix(infos);
        UserInterest[] userInterests = fileHandler.getUserInterestFromFile("src/main/resources/interest.txt");
        fileHandler.setCloseMatrix2File(similarity);
        fileHandler.setRecommend2File(recommender.recommend(similarity,userInterests));
    }
}
