import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FirstUniqueCharacter {

    public static void main(String[] args) {
        FirstUniqueCharacter firstUniqueCharacter = new FirstUniqueCharacter();
        System.out.println(firstUniqueCharacter.firstUniqChar("leetcode"));
        System.out.println(firstUniqueCharacter.firstUniqChar("loveleetcode"));
        System.out.println(firstUniqueCharacter.firstUniqChar("aabb"));

    }

    public int firstUniqChar(String s) {
        int output = -1;
        Map<Character, Integer> map = new HashMap<>(s.length());
        for(int i = 0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        for(int i = 0; i < s.length(); i++) {
            if(map.get(s.charAt(i)) == 1) {
                output = i;
                break;
            }
        }
        return output;
    }
}
