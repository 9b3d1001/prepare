package collections;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public int[] getMaximumForSlidingWindow(int[] input, int k) {
        if(null == input || input.length == 0 || k == 0) {
            return new int[0];
        }
        int n = input.length;
        k = Math.min(k, n);
        int resultLength = n - k + 1;
        int result[] = new int[resultLength];

        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < n; i++) {

            if(!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            while(!deque.isEmpty() && input[deque.peekLast()] <= input[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            if(i >= k - 1) {
                result[i - k + 1] = input[deque.peekFirst()];
            }

        }
        return  result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        int[] result = slidingWindowMaximum.getMaximumForSlidingWindow(nums, k);

        // Output the result
        System.out.print("Sliding window maximums: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
