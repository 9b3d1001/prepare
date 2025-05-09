import java.util.Arrays;

public class RotateArray {

    public static void main(String[] args) {
        rotate(new int[]{1,2,3}, 5);
    }

    public static void rotate(int[] nums, int k) {
        int length = nums.length;
        if(length > 1) {
            if(k > length) {
                k = (k - length) % length;
            }
            for(int i = 0; i < k; i++) {
                int temp = nums[length - 1];
                for(int j = 1; j < length; j++) {
                    nums[j] = nums[j - 1];
                }
                nums[length - 1] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
