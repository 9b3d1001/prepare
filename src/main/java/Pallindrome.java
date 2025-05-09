public class Pallindrome {

    public static void main(String[] args) {
        Pallindrome pallindrome = new Pallindrome();
        System.out.println(pallindrome.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(pallindrome.isPalindrome("race a car"));
        System.out.println(pallindrome.isPalindrome(" "));
        System.out.println(pallindrome.isPalindrome("0P"));
        System.out.println(pallindrome.isPalindrome("P"));
    }

    public boolean isPalindrome(String s) {
        boolean isPalindrome = true;
        s = s.toLowerCase().trim().replaceAll("[^a-z0-9]", "");
        int i = 0;
        int j = s.length() - 1;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return isPalindrome;
    }
}
