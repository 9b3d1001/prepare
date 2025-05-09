import java.math.BigInteger;

public class Atoi {

    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi("42"));
        System.out.println(atoi.myAtoi("-042"));
        System.out.println(atoi.myAtoi("1337c0d3"));
        System.out.println(atoi.myAtoi("  0415902"));
        System.out.println(atoi.myAtoi("  -0415902 "));
        System.out.println(atoi.myAtoi("  2147483648 "));
        System.out.println(atoi.myAtoi("  -2147483649 "));
        System.out.println(atoi.myAtoi("0-1"));
        System.out.println(atoi.myAtoi("+-12"));
        System.out.println(atoi.myAtoi("20000000000000000000"));
        System.out.println(atoi.myAtoi("-2000000000000000000020000000000000000000"));
        System.out.println(atoi.myAtoi("ANCDE"));
        System.out.println(atoi.myAtoi("  +  413"));
    }

    public int myAtoi(String s) {
        int o = 0;
        String sign = "";
        char curChar;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            curChar = s.charAt(i);
            if(sb.isEmpty() && sign.isEmpty()) {
                if ((curChar == '+' || curChar == '-')) {
                    sign = String.valueOf(curChar);
                    continue;
                }
                if (curChar == ' ') {
                    continue;
                }
            }
            if(curChar < 48 || curChar > 57) {
                break;
            }
            sb.append(curChar);
        }
        if(!sb.isEmpty()) {
            BigInteger b = new BigInteger(sign.concat(sb.toString()));
            if(b.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
                o = Integer.MAX_VALUE;
            } else if(b.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
                o = Integer.MIN_VALUE;
            } else {
                o = b.intValue();
            }
        }
        return o;
    }
}
