public class JumpGame {

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3,2,1,0,4}));
    }

    public static boolean canJump(int[] nums) {
        return jump(nums, 0, nums.length - 1);
    }

    public static boolean jump(int[] nums, int currentIndex, int target) {
        boolean output = false;
        if(currentIndex == target) {
            return true;
        }
        for(int i = 1; i <= nums[currentIndex]; i++) {
            if(currentIndex + i <= target) {
                output = jump(nums, currentIndex + i, target);
                if(output) {
                    break;
                }
            } else {
                break;
            }
        }
        return output;
    }

}
