package Algorithms.Sorting;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class InsertionSort implements SortingAlgorithm {


    @Override
    public void sort(List<Integer> unsortedCollection) {
        sort(unsortedCollection, true);

    }

    @Override
    public void sort(List<Integer> unsortedCollection, boolean ascendingOder) {
        if (unsortedCollection.size() == 0) return;
        if (unsortedCollection.size() == 1) return;

        for (int i = 1; i < unsortedCollection.size(); i++) {
            Integer temp = unsortedCollection.get(i);
            int lastIndex = i;
            for (int j = i - 1; j >= 0; j--) {

                if(ascendingOder) {
                    if (unsortedCollection.get(j) > temp) {
                        unsortedCollection.set(j + 1, unsortedCollection.get(j));
                        lastIndex = j;
                    } else {
                        break;
                    }
                }
                else{
                    if (unsortedCollection.get(j) < temp) {
                        unsortedCollection.set(j + 1, unsortedCollection.get(j));
                        lastIndex = j;
                    } else {
                        break;
                    }
                }
            }
            unsortedCollection.set(lastIndex, temp);
        }
    }

    @Override
    public void timeComplexity() {
        System.out.println("Big O notation");
        System.out.println("Worst Case Time Complexity: O(n^2)");
        System.out.println("Average Case Time Complexity: O(n^2)");
        System.out.println("Best Case Time Complexity: O(n)");

    }

    @Override
    public void spaceComplexity() {
        System.out.println("Space Complexity: O(1)"); //Only needs one temp variable
    }
}
