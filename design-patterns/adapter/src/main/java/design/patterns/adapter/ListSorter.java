package design.patterns.adapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Feng on 1/9/14.
 */
public class ListSorter {

    public List<Integer> sortList(List<Integer> list){
        return Arrays.asList(new Integer[]{1,2,3});
    }
}
