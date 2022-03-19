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
 */
public class MergeSort {
    /**
     * Split the array on the left side.
     *
     * @param data the full array of data
     * @return the left half side of the data
     */
    private static ArrayList<Integer> splitLeft(ArrayList<Integer> data) {
        ArrayList<Integer> left = new ArrayList<>(data.size() / 2);
        for (int i = 0; i < data.size()/2; ++i) {
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
        ArrayList<Integer> right = new ArrayList<>(data.size() / 2);
        for (int i = data.size()/2; i < data.size(); ++i) {
            right.add(data.get(i));
        }
        return right;
    }

    /**
     * Merges two sorted arrays, left and right, into a combined sorted array.
     *
     * @param left  a sorted array
     * @param right a sorted array
     * @return one combined sorted array
     */
    private static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        // the sorted left + right will be stored in result
        ArrayList<Integer> result = new ArrayList<>(left.size() + right.size());
        int leftIndex = 0;
        int rightIndex = 0;

        // loop through until either the left or right list is exhausted
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) <= right.get(rightIndex)) {
                result.add(left.get(leftIndex));
                leftIndex += 1;
            }
            else {
                result.add(right.get(rightIndex));
                rightIndex += 1;
            }
        }

        // take the un-exhausted list and extend the remainder onto the result
        if (leftIndex < left.size()) {
            for (int i = leftIndex; i < left.size(); ++i) {
                result.add(left.get(i));
            }
        }
        else if (rightIndex < right.size()) {
            for (int i = rightIndex; i < right.size(); ++i) {
                result.add(right.get(i));
            }
        }

        return result;
    }

    /**
     * Performs a merge sort and returns a newly sorted array
     *
     * @param data the data to be sorted (a native array)
     * @return a sorted array
     */
    private static ArrayList<Integer> mergeSort(ArrayList<Integer> data) {
        // an empty list, or list of 1 element is already sorted
        if (data.size() < 2) {
            return data;
        }
        else {
            // split the data into left and right halves
            ArrayList<Integer> left = splitLeft(data);
            ArrayList<Integer> right = splitRight(data);
            // return the merged recursive merge_sort of the halves
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
