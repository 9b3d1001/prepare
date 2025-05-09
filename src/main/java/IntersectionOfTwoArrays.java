import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        IntersectionOfTwoArrays intersectionOfTwoArrays = new IntersectionOfTwoArrays();
        System.out.println(Arrays.toString(intersectionOfTwoArrays.intersect(new int[] {1, 2, 2, 1}, new int[] {2, 2})));
        System.out.println(Arrays.toString(intersectionOfTwoArrays.intersect(new int[]{4,9,5}, new int[]{9,4,9,8,4})));
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {
            return process(nums1, nums2);
        } else {
            return process(nums2, nums1);
        }
    }

    public int[] process(int[] largerArray, int[] smallerArray) {
        Map<Integer, Integer> countInSA = new HashMap<>(smallerArray.length);
        List<Integer> result = new ArrayList<>(smallerArray.length);
        for(int i = 0; i < smallerArray.length; i++) {
            int num = smallerArray[i];
            int count = countInSA.getOrDefault(num, 0);
            countInSA.put(num, ++count);
        }
        for(int i=0; i < largerArray.length; i++) {
            int num = largerArray[i];
            int count = countInSA.getOrDefault(num, 0);
            if(count > 0) {
                result.add(num);
                countInSA.put(num, --count);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
