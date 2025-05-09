public class RemoveDuplicatesFromArray {

    public static void main(String[] args) {
        RemoveDuplicatesFromArray e = new RemoveDuplicatesFromArray();
        e.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
    }
        public int removeDuplicates(int[] nums) {
            int idx = 1;
            int len = nums.length;
            for(int i = 1; i < len; i++) {
                if(nums[i] != nums[i - 1]) {
                    nums[idx] = nums[i];
                    idx++;
                }
            }
            return idx;
        }
}
