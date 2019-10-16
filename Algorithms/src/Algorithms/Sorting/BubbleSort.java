package Algorithms.Sorting;

import java.util.List;

public class BubbleSort implements SortingAlgorithm {
    @Override
    public void sort(List<Integer> unsortedCollection) {
        sort(unsortedCollection,true);
    }

    @Override
    public void sort(List<Integer> unsortedCollection, boolean ascendingOrder) {
        if (unsortedCollection.size() == 0) return;
        if (unsortedCollection.size() == 1) return;

        int flag = 0;
        for (int i = 0; i < unsortedCollection.size() - 1; i++) {
            for (int j = 0; j < unsortedCollection.size() - (i + 1); j++) {
                if(ascendingOrder) {
                    if (unsortedCollection.get(j) > unsortedCollection.get(j + 1)) {
                        flag++;
                        int temp = unsortedCollection.get(j);
                        unsortedCollection.set(j, unsortedCollection.get(j + 1));
                        unsortedCollection.set(j + 1, temp);
                    }
                }
                else{
                    if (unsortedCollection.get(j) < unsortedCollection.get(j + 1)) {
                        flag++;
                        int temp = unsortedCollection.get(j);
                        unsortedCollection.set(j, unsortedCollection.get(j + 1));
                        unsortedCollection.set(j + 1, temp);
                    }
                }
            }
            if (flag == 0) return;
            else flag = 0;
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
