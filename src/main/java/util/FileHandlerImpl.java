package util;

import vo.StockInfo;
import vo.UserInterest;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerImpl implements FileHandler {


    /**
     * This func gets stock information from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return the Stock information array from the interfaces,or NULL
     */
    @Override
    public StockInfo[] getStockInfoFromFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"utf-8"));

            List<StockInfo> stockInfos = new ArrayList<>();
            while (reader.ready()) {
                String line = reader.readLine();
                String[] fields = line.split("\t");
                StockInfo info = new StockInfo();
                info.setID(fields[0]);
                info.setTitle(fields[1]);
                info.setAuthor(fields[2]);
                info.setDate(fields[3]);
                info.setLastUpdate(fields[4]);
                info.setContent(fields[5]);
                info.setAnswer(fields[6]);
                info.setAnswerAuthor(fields[7]);
                stockInfos.add(info);
            }
            return stockInfos.toArray(new StockInfo[stockInfos.size()]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This func gets user interesting from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return
     */
    @Override
    public UserInterest[] getUserInterestFromFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));

            List<UserInterest> userInterests = new ArrayList<>();
            while (reader.ready()) {
                String line = reader.readLine();
                String[] fields = line.split("");
                UserInterest interest = new UserInterest();
                interest.data = new boolean[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    interest.data[i] = fields[i].charAt(0) == '1'?true:false;
                }
                userInterests.add(interest);
            }
            return userInterests.toArray(new UserInterest[userInterests.size()]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This function need write matrix to files
     *
     * @param matrix the matrix you calculate
     */
    @Override
    public void setCloseMatrix2File(double[][] matrix) {
        write2File(matrix,"similarity.txt");
    }

    /**
     * This function need write recommend to files
     *
     * @param recommend the recommend you calculate
     */
    @Override
    public void setRecommend2File(double[][] recommend) {
        write2File(recommend,"recommend.txt");
    }

    private void write2File(double[][] matrix,String path) {
        try {
            File target = new File(path);
            if(!target.exists()) {
                target.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(target));
            for (int i = 0; i <matrix.length ; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.write(matrix[i][j]+"");
                    writer.write('\t');
                }
                writer.write('\n');
            }
            writer.flush();
        }
        catch(IOException e) {}
    }
}
