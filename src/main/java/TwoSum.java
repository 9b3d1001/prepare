import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        System.out.println(Arrays.toString(twoSum.twoSum(new int[] {2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[] {3, 2, 4}, 6)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[] {3, 3}, 6)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[] {3, 2, 3}, 6)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[] {0, 4, 3, 0}, 0)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[] {-1, -2, -3, -4, -5}, -8)));
    }


    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for(int i=0; i < nums.length; i++) {
            int val = target - nums[i];
            for(int j=i+1; j < nums.length; j++) {
                if(val == nums[j]) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }
        return res;
    }
}
