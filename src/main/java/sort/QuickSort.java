package sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = new int[] {2, 4, 19, 6, 8, 3, 1};
        quickSort.sort(arr, 0, 6);
        System.out.println(Arrays.toString(arr));
    }

    private void sort(int[] arr, int begin, int end) {
        if(begin < end) {
            int pivot = findPivot(arr, begin, end);
            sort(arr, 0, pivot - 1);
            sort(arr, pivot + 1, end);
        }
    }

    private int findPivot(int[] arr, int begin, int end) {
        int x = begin + 1;
        int y = end;
        while(x <= y) {
            while(x <= end && arr[x] <= arr[begin]) {
                x++;
            }
            while(y >= begin && arr[y] > arr[begin]) {
                y--;
            }
            if(x < y) {
                int temp = arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
            }
        }
        int temp = arr[begin];
        arr[begin] = arr[y];
        arr[y] = temp;
        return y;
    }
}
