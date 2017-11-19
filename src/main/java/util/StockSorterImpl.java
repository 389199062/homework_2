package util;

import vo.StockInfo;

public class StockSorterImpl implements StockSorter {
    /**
     * Accepting series of stock info, sorting them ascending according to their comment length.
     * List.sort() or Arrays.sort() are not allowed.
     * You have to write your own algorithms,Bubble sort、quick sort、merge sort、select sort,etc.
     *
     * @param infos stock information
     * @return sorted stock
     */
    @Override
    public StockInfo[] sort(StockInfo[] infos) {
        //选择排序
        for (int i = 0; i <infos.length; i++) {
            StockInfo temp = infos[i];
            int index = 0;
            for (int j = i; j <infos.length ; j++) {
                if(infos[j].getAnswer().length()<=temp.getAnswer().length()) {
                    temp = infos[j];
                    index = j;
                }
            }
            StockInfo temp2 = infos[i];
            infos[i] = temp;
            infos[index] = temp2;
        }

        return null;
    }

    /**
     * Accepting series of stock info, sorting them ascending, descending order.
     *
     * @param info  stock information
     * @param order true:ascending,false:descending
     * @return sorted stock
     */
    @Override
    public StockInfo[] sort(StockInfo[] info, boolean order) {

        return null;
    }
}
