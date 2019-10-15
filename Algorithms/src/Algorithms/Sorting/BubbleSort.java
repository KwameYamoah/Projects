package Algorithms.Sorting;
import java.util.Arrays;
import java.util.List;

public class BubbleSort implements SortingAlgorithm {
    @Override
    public void sort(List<Integer> unsortedCollection) {
        if (unsortedCollection.size() == 0) return;
        int flag = 0;
        for (int i = 0; i < unsortedCollection.size() - 1; i++) {
            for (int j = 0; j < unsortedCollection.size() - (i + 1); j++) {
                if (unsortedCollection.get(j) > unsortedCollection.get(j + 1)) {
                    flag++;
                    int temp = unsortedCollection.get(j);
                    unsortedCollection.set(j, unsortedCollection.get(j + 1));
                    unsortedCollection.set(j + 1, temp);
                }
            }
            if(flag==0) return;
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

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        List<Integer> unsortedArray = Arrays.asList(7, 2, 10, 8, 6, 4, 1, 5, 9, 3);
        bubbleSort.sort(unsortedArray);
        System.out.println(unsortedArray);

    }


}
