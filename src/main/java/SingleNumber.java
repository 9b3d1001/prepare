import java.util.HashMap;
import java.util.Map;

public class SingleNumber {

    public static void main(String[] args) {
        long l  = 4200000013L;
        System.out.println("Original Long " + l);
        int i = (int) l;
        System.out.println("Integer Value " + i);
        long l2 =  Integer.toUnsignedLong(i);
        System.out.println("Convert Again " + l2);
        SingleNumber singleNumber = new SingleNumber();
        singleNumber.singleNumber(new int[]{4,1,2,1,2});
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Boolean> cnt = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
           if(cnt.containsKey(nums[i])) {
               cnt.put(nums[i], false);
           } else {
               cnt.put(nums[i], true);
           }
        }
        return cnt.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).findFirst().get();
    }

}
