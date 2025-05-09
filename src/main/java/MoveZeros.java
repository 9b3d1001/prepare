import java.util.Arrays;

public class MoveZeros {

    public static void main(String[] args) {
        MoveZeros moveZeros = new MoveZeros();
        moveZeros.moveZeroes(new int[]{0,1,0,3,12});
        moveZeros.moveZeroes(new int[]{2,7,19,3,31});
        moveZeros.moveZeroes(new int[]{0});
    }

    public void moveZeroes(int[] nums) {
        int index = 0;
        int len = nums.length;
        int i = 0;
        while(i < len) {
            if(nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
            i++;
        }
        for(int j = index; j < len; j++) {
            nums[j] = 0;
        }
        System.out.println(Arrays.toString(nums));
    }
}
