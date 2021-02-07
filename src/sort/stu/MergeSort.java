package sort.stu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Perform an out of place merge sort on a native array of integers.
 *
 * merge_sort (not in place):
 *     best=O(nlogn)
 *     worst=O(nlogn)
 *
 * @author RIT CS
 * @author Clinten Hopkins
 */
public class MergeSort {
    /**
     * Split the array on the left side.
     *
     * @param data the full array of data
     * @return the left half side of the data
     */
    private static ArrayList<Integer> splitLeft(ArrayList<Integer> data) {

        // take the items up until the midpoint (exclusive) and place them in a new array

        int midpoint = Math.floorDiv(data.size(), 2);

        ArrayList<Integer> left = new ArrayList<>();

        for (int i = 0; i < midpoint; i++) {
            left.add(data.get(i));
        }

        return left;
    }

    /**
     * Split the array on the right side.
     *
     * @param data the full array of data
     * @return the right half side of the data
     */
    private static ArrayList<Integer> splitRight(ArrayList<Integer> data) {

        // take the items from the midpoint (inclusive) to the end of the array and place them in a new array

        int midpoint = Math.floorDiv(data.size(), 2);

        ArrayList<Integer> left = new ArrayList<>();

        for (int i = midpoint; i < data.size(); i++) {
            left.add(data.get(i));
        }

        return left;
    }

    /**
     * Merges two sorted arrays, left and right, into a combined sorted array.
     *
     * @param left  a sorted array
     * @param right a sorted array
     * @return one combined sorted array
     */
    private static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> results = new ArrayList<>();

        int leftIndex = 0;
        int rightIndex = 0;

        // keep taking the smaller item until eithe rlist runs out of elements

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) <= right.get(rightIndex)) {
                results.add(left.get(leftIndex));
                leftIndex++;
            } else {
                results.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        // add the remaining elements from the rest of the list that didn't run out

        if (leftIndex < left.size()) {
            for (int i = leftIndex; i < left.size(); i++) {
                results.add(left.get(i));
            }
        } else if (rightIndex < right.size()) {
            for (int i = rightIndex; i < right.size(); i++) {
                results.add(right.get(i));
            }
        }

        return results;
    }

    /**
     * Performs a merge sort and returns a newly sorted array
     *
     * @param data the data to be sorted (a native array)
     * @return a sorted array
     */
    private static ArrayList<Integer> mergeSort(ArrayList<Integer> data) {

        if (data.size() < 2) {
            return data;
        } else {
            ArrayList<Integer> left = splitLeft(data);
            ArrayList<Integer> right = splitRight(data);

            return merge(mergeSort(left), mergeSort(right));
        }
    }

    /**
     * Test function for mergeSort.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        int[][] DATA = {
                {},
                {0},
                {0, 1},
                {1, 0},
                {0, 1, 2},
                {0, 2, 1},
                {1, 0, 2},
                {1, 2, 0},
                {2, 0, 1},
                {2, 1, 0},
                {9, 5, 2, 6, 3, 8, 1, 4, 0, 7}
        };

        for (int[] data : DATA) {
            // create two lists of the data
            List<Integer> sortData = Arrays.stream(data).boxed().collect(Collectors.toList());
            List<Integer> sorted = Arrays.stream(data).boxed().collect(Collectors.toList());
            // merge sort is not in place and returns a new sorted list
            sortData = mergeSort((ArrayList<Integer>) sortData);
            // use built in sort to compare against
            Collections.sort(sorted);
            // show the results of the comparison
            System.out.print("mergeSort: " + Arrays.stream(data).boxed().collect(Collectors.toList()) +
                    " result: " + sortData);
            System.out.println(sortData.equals(sorted) ? " OK" : " FAIL");
        }
    }
}
