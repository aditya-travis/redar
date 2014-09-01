package design.patterns.adapter;

/**
 * Created by Feng on 1/9/14.
 */
public class ArraySorterApp {

    public static void main(String[] args){

        Integer[] arrayToSort = new Integer[]{2,1,3};

        new ListSorterAdapter().sortArray(arrayToSort);
    }
}
