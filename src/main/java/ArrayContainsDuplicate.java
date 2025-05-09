import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayContainsDuplicate {

    public static void main(String[] args) {
        ArrayContainsDuplicate arrayContainsDuplicate = new ArrayContainsDuplicate();
    }

    /**
     * Sort and index approach.
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        boolean containsDuplicate = false;
        int arrayLength = nums.length;
        if(arrayLength > 1) {
            Arrays.sort(nums);
            for(int i = 1; i < arrayLength; i ++) {
                if(nums[i - 1] == nums[i]) {
                    containsDuplicate = true;
                    break;
                }
            }
        }
        return  containsDuplicate;
    }

    /**
     * Map and count approach
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        boolean cd = false;
        int len  = nums.length;
        Map<Integer, Integer> nc = new HashMap<>(len);
        if(len > 1) {
            for (int num : nums) {
                if (nc.containsKey(num)) {
                    return true;
                }
                nc.put(num, 1);
            }
        }
        return cd;
    }
}
