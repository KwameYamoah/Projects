package Algorithms.Sorting;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class QuickSort implements SortingAlgorithm {


    @Override
    public void sort(List<Integer> unsortedCollection) {
        sort(unsortedCollection, 0, unsortedCollection.size() - 1, true);

    }


    @Override
    public void sort(List<Integer> unsortedCollection, boolean ascendingOder) {
        sort(unsortedCollection, 0, unsortedCollection.size() - 1, ascendingOder);
    }


    private void sort(List<Integer> unsortedCollection, int low, int high, boolean ascendingOrder) {

        if (low < high) {
            int partitionIndex = partition(unsortedCollection, low, high,ascendingOrder);
            sort(unsortedCollection, low, partitionIndex - 1, ascendingOrder);
            sort(unsortedCollection, partitionIndex + 1, high, ascendingOrder);
        }
    }

    private int partition(List<Integer> unsortedCollection, int low, int high, boolean ascendingOrder) {
        int pivot = getPivot(unsortedCollection, low, high);
        int temp = unsortedCollection.get(high);
        unsortedCollection.set(high, unsortedCollection.get(pivot));
        unsortedCollection.set(pivot, temp);
        pivot = high;
        int pivotValue = unsortedCollection.get(pivot);

        int leftPointer = low;
        int rightPointer = high - 1;
        while (leftPointer < rightPointer) {
            for (int i = leftPointer; i < high; i++) {
                if(ascendingOrder) {
                    if (unsortedCollection.get(i) > pivotValue) {
                        break;
                    }
                }
                else{
                    if (unsortedCollection.get(i) < pivotValue) {
                        break;
                    }
                }
                leftPointer++;
            }
            for (int i = rightPointer; i >= low; i--) {
                if(ascendingOrder) {
                    if (unsortedCollection.get(i) < pivotValue) {
                        break;
                    }
                }
                else{
                    if (unsortedCollection.get(i) > pivotValue) {
                        break;
                    }
                }
                rightPointer--;

            }
            if (leftPointer < rightPointer) {
                int t = unsortedCollection.get(rightPointer);
                unsortedCollection.set(rightPointer, unsortedCollection.get(leftPointer));
                unsortedCollection.set(leftPointer, t);

            }
        }


        int t = unsortedCollection.get(leftPointer);
        unsortedCollection.set(leftPointer, unsortedCollection.get(high));
        unsortedCollection.set(high, t);
        pivot = leftPointer;

        return pivot;
    }

    private int getPivot(List<Integer> unsortedCollection, int low, int high) {
        int startElement = unsortedCollection.get(low);
        int middleElement = unsortedCollection.get((high) / 2);
        int lastElement = unsortedCollection.get(high);
        int pivot = Math.max(Math.min(startElement, middleElement), Math.min(Math.max(startElement, middleElement), lastElement));
        return unsortedCollection.indexOf(pivot);
    }

    @Override
    public void timeComplexity() {
        System.out.println("Big O notation");
        System.out.println("Worst Case Time Complexity: O(n^2)");
        System.out.println("Average Case Time Complexity: O(nlogn)");
        System.out.println("Best Case Time Complexity: O(nlogn)");

    }

    @Override
    public void spaceComplexity() {
        System.out.println("Space Complexity: O(log(n))"); //sub arrays

    }


}
