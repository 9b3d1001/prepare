public class Anagram {

    public static void main(String[] args) {
        Anagram anagram = new Anagram();
        System.out.println(anagram.isAnagram("anagram", "nagaram"));
        System.out.println(anagram.isAnagram("car", "rat"));
        System.out.println(anagram.isAnagram("aa", "bb"));
    }

    public boolean isAnagram(String s, String t) {
        boolean anagram = true;
        if(s.length() == t.length()) {
            int[] arrS = new int[26];
            int[] arrT = new int[26];
            for(int i = 0; i < s.length(); i++) {
                arrS[s.charAt(i) - 'a']++;
                arrT[t.charAt(i) - 'a']++;
            }
            for (int i = 0; i <  arrS.length; i++) {
                if (arrS[i]  != arrT[i]) {
                    return false;
                }
            }
        } else {
            anagram = false;
        }
        return anagram;
    }
}
