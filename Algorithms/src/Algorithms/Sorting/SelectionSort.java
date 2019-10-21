package Algorithms.Sorting;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SelectionSort implements SortingAlgorithm {


    @Override
    public void sort(List<Integer> unsortedCollection) {
        sort(unsortedCollection,true);
    }

    @Override
    public void sort(List<Integer> unsortedCollection, boolean ascendingOder) {
        int sorted = 0;
        for(int i = 0; i < unsortedCollection.size(); i++)
        {
            List<Integer> unsorted = unsortedCollection.subList(sorted,unsortedCollection.size());
            int count = sorted;
            int min = unsortedCollection.get(sorted);
            int minIndex = sorted;

            for(Integer n : unsorted){
                if(ascendingOder) {
                    if (n < min) {
                        min = n;
                        minIndex = count;
                    }
                }
                else{
                    if (n > min) {
                        min = n;
                        minIndex = count;
                    }

                }
                count++;
            }

            if(sorted!=minIndex){
                int temp = unsortedCollection.get(sorted);
                unsortedCollection.set(sorted,unsortedCollection.get(minIndex));
                unsortedCollection.set(minIndex,temp);
            }
            sorted++;

        }

    }
    @Override
    public void timeComplexity() {
        System.out.println("Big O notation");
        System.out.println("Worst Case Time Complexity: O(n^2)");
        System.out.println("Average Case Time Complexity: O(n^2)");
        System.out.println("Best Case Time Complexity: O(n^2)");

    }

    @Override
    public void spaceComplexity() {
        System.out.println("Space Complexity: O(1)"); //sub arrays

    }


}
