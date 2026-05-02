/*
This is class for Merge Sort in descending order.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MergeSort
{
    public int[] mergeSort(int[] array)
    {
        int length = array.length;

        if(length == 1)
        {
            for(int integer : array) System.out.print(integer); // Print content of array at end of each recursive call to algorithm.
            System.out.println();

            return array;
        }

        int middleIndex = length / 2;

        int[] firstHalf = new int[middleIndex];
        int[] secondHalf = new int[length - middleIndex];

        for(int i = 0; i < middleIndex; i++) firstHalf[i] = array[i];
        for(int j = middleIndex; j < length; j++) secondHalf[j - middleIndex] = array[j];

        int[] sortedFirstHalf = mergeSort(firstHalf);
        int[] sortedSecondHalf = mergeSort(secondHalf);

        int[] result = merge(sortedFirstHalf, sortedSecondHalf);

        for(int integer : result) System.out.print(integer + " "); // Print content of array at end of each recursive call to algorithm.
        System.out.println();

        return merge(sortedFirstHalf, sortedSecondHalf);
    }

    private int[] merge(int[] a, int[] b) // Merge functions as helper function.
    {
        int lengthA = a.length;
        int lengthB = b.length;
        int lengthC = lengthA + lengthB;

        int[] c = new int[lengthC];

        int i = 0; // Index tracker for array a.
        int j = 0; // Index tracker for array b.
        int index = 0; // Index tracker for array c.

        while(i < lengthA && j < lengthB)
        {
            if(a[i] > b[j]) c[index++] = a[i++];
            else c[index++] = b[j++];
        }

        while(i < lengthA) c[index++] = a[i++];
        while(j < lengthB) c[index++] = b[j++];

        return c;
    }
}

/*
This is class for Quick Sort in descending order.
*/

class QuickSort
{
    public void quickSort(int[] a)
    {
        quickSortHelper(a, 0, a.length - 1);
    }

    private void quickSortHelper(int[] a, int low, int high)
    {
        if(low >= high) return;

        int correctPivotIndex = partition(a, low, high);

        quickSortHelper(a, low, correctPivotIndex - 1);
        quickSortHelper(a, correctPivotIndex + 1, high);

        return;
    }

    private int partition(int[] a, int low, int high)
    {
        int i = low, j = high + 1;

        while(true)
        {
            while(a[++i] > a[low])
            {
                if(i == high) break;
            }

            while(a[low] > a[--j])
            {
                if(j == low) break;
            }

            if(i >= j) break;

            int temporary = a[i]; // Swap to satisfy partition condition.
            a[i] = a[j];
            a[j] = temporary;
        }

        int temporary = a[low]; // Swap to put pivot in to correct position.
        a[low] = a[j];
        a[j] = temporary;

        return j; // Return partition index.
    }
}

class Q3
{
    public void negativeKeysPrecede(int[] a, int low, int high)
    {
        int i = low, j = high + 1;

        while(true)
        {
            while(a[++i] < 0)
            {
                if(i == high) break;
            }

            while(a[--j] >= 0)
            {
                if(j == low) break;
            }

            if(i >= j) break;

            int temporary = a[i];
            a[i] = a[j];
            a[j] = temporary;
        }

        int temporary = a[low];
        a[low] = a[j];
        a[j] = temporary;

        return;
    }
}

class Q12
{
    public int[][] merge(int[][] intervals)
    {
        if(intervals.length <= 1) return intervals;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // Sort array of intervals by intervals' start.

        List<int[]> result = new ArrayList<>();

        int index = 0;

        int[] updateInterval = intervals[0];

        result.add(updateInterval);

        for(int[] interval : intervals)
        {
            if(updateInterval[1] >= interval[0])
            {
                updateInterval[1] = Math.max(updateInterval[1], interval[1]);
                result.get(index)[1] = updateInterval[1];
            }
            else
            {
                updateInterval = interval;

                result.add(updateInterval);

                index++;
            }
        }

        return result.toArray(new int[result.size()][2]);
    }
}

public class HOA5
{
    public static void main(String[] args)
    {
        MergeSort msObject = new MergeSort();
        int[] msExample = { 4, 10, 9, 3, 14, 5, 11, 1, 8, 13, 6, 7, 2, 12 };
        msObject.mergeSort(msExample);

        QuickSort qsObject = new QuickSort();
        int[] qsExample = { 4, 10, 9, 3, 14, 5, 11, 1, 8, 13, 6, 7, 2, 12 };
        qsObject.quickSort(qsExample);
        for(int integer : qsExample)
        {
            System.out.println(integer);
        }

        Q3 q3 = new Q3();
        int[] q3Example = { 7, -3, -1, 11, 0, 5, -5 };
        q3.negativeKeysPrecede(q3Example, 0, q3Example.length - 1);
        for(int integer : q3Example)
        {
            System.out.println(integer);
        }

        Q12 q12 = new Q12();
        int[][] q12Example = { { 4, 7 }, { 1, 4 }, { 8, 10 }, { 15, 18 } };
        int[][] result = q12.merge(q12Example);
        for(int[] interval : result)
        {
            for(int i = 0; i < 2; i++)
            {
                System.out.print(interval[i] + "\t");
            }

            System.out.println();
        }
    }
}
