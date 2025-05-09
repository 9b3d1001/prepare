public class PlusOne {

    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        plusOne.plusOne(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0});
        plusOne.plusOne(new int[]{9, 9, 9, 9});
        plusOne.plusOne(new int[]{4, 3, 2, 1});
        plusOne.plusOne(new int[]{1, 2, 3, 4});
    }

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int newLen = len + 1;
        int[] result = new int[newLen];
        int add = 1;
        for(int i = len - 1; i >= 0; i--) {
            if(digits[i] + add == 10) {
                result[i + 1] = 0;
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + add;
                result[i + 1] = digits[i];
                add = 0;
            }
        }
        if(add > 0) {
            result[0] += add;
            return result;
        }
        return digits;
    }

}
