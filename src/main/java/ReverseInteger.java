public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println(reverseInteger.reverse(123));
        System.out.println(reverseInteger.reverse(-123));
        System.out.println(reverseInteger.reverse(120));
        System.out.println(reverseInteger.reverse(-120));
        System.out.println(reverseInteger.reverse(1201));
        System.out.println(reverseInteger.reverse(-1201));
        System.out.println(reverseInteger.reverse(1200));
        System.out.println(reverseInteger.reverse(-1200));
        System.out.println(reverseInteger.reverse(-0000));
        System.out.println(reverseInteger.reverse(0000));
        System.out.println(reverseInteger.reverse(1534236469));

    }

    public int reverse2(int x) {
        int r = x;
        char[] chars = String.valueOf(x).toCharArray();
        int i = 0;
        int j = chars.length - 1;
        char temp;
        while (i < j) {
            if (chars[i] == '-') {
                i++;
            } else {
                temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        try {
            r = Integer.parseInt(new String(chars));
        } catch (NumberFormatException e) {
            r = 0;
        }
        return r;
    }

    public int reverse(int x) {
        int r = x;
        String s = String.valueOf(x);
        char c = '+';
        if(x < 0) {
           c = '-';
           s = s.substring(1);
        }
        try {
            r = Integer.parseInt(new StringBuilder(s).append(c).reverse().toString());
        } catch (NumberFormatException e) {
            r = 0;
        }
        return r;
    }
}
