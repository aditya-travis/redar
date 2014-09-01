package design.patterns.adapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Feng on 1/9/14.
 */
public class ListSorterAdapter implements ArraySorter{

    private ListSorter listSorter = new ListSorter();

    @Override
    public Integer[] sortArray(Integer[] list) {
        return listSorter.sortList(Arrays.asList(list)).toArray(new Integer[]{});
    }

}
