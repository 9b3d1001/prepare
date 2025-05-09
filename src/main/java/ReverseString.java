public class ReverseString {

    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        reverseString.reverseString(new char[]{'h','e','l','l','o'});
        reverseString.reverseString(new char[]{'H','a','n','n','a', 'h'});
    }

    public void reverseString(char[] s) {
        if(s.length > 1) {
            char temp;
            int i = 0;
            int j = s.length - 1;
            while (i < j) {
                temp = s[i];
                s[i] = s[j];
                s[j] = temp;
                i++;
                j--;
            }
        }
        System.out.println(s);
    }
}
