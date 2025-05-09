package sort;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] array = { 38, 27, 43, 3, 9, 82, 10 };
        System.out.println("Original array:");
        mergeSort.printArray(array);

        mergeSort.sort(array, 0, array.length - 1);

        System.out.println("Sorted array:");
        mergeSort.printArray(array);
    }

    private void sort(int[] array, int begin, int end) {
        int mid = (begin + end)  / 2;
        if(begin < end) {
            sort(array, begin, mid);
            sort(array, mid + 1, end);
            merge(array, begin, mid, end);
        }
    }

    private void merge(int[] array, int begin, int mid, int end) {
        int[] temp1 = new int[mid - begin + 1];
        int[] temp2 = new int[end - mid];
        for(int i = 0; i < temp1.length; i++) {
            temp1[i] = array[begin + i];
        }
        for(int i = 0; i < temp2.length; i++) {
            temp2[i] = array[i + mid + 1];
        }
        int temp1Index = 0;
        int temp2Index = 0;
        int index = begin;
        while(temp1Index < temp1.length && temp2Index < temp2.length) {
            if(temp1[temp1Index] <= temp2[temp2Index]) {
                array[index] = temp1[temp1Index];
                temp1Index++;
            } else {
                array[index] = temp2[temp2Index];
                temp2Index++;
            }
            index++;
        }
        while(temp1Index < temp1.length) {
            array[index] = temp1[temp1Index];
            temp1Index++;
            index++;
        }
        while(temp2Index < temp2.length) {
            array[index] = temp2[temp2Index];
            temp2Index++;
            index++;
        }
    }

    // Utility method to print the array
    public void printArray(int[] array) {
        for (int value : array)
            System.out.print(value + " ");
        System.out.println();
    }
}
