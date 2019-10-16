package Algorithms.Sorting;

import java.util.Collection;
import java.util.List;

public  interface SortingAlgorithm {
    public void sort(List<Integer> unsortedCollection);
    public void sort(List<Integer> unsortedCollection,boolean ascendingOrder);
    public void timeComplexity();
    public void spaceComplexity();
}
