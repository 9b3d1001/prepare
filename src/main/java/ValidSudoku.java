import java.util.HashMap;
import java.util.Map;

public class ValidSudoku {


    public static void main(String[] args) {
        ValidSudoku validSudoku = new ValidSudoku();
        boolean isValid = validSudoku.isValidSudoku(new char[][]{{'5','3','.','.','7','.','.','.','.'}
            ,{'6','.','.','1','9','5','.','.','.'}
            ,{'.','9','8','.','.','.','.','6','.'}
            ,{'8','.','.','.','6','.','.','.','3'}
            ,{'4','.','.','8','.','3','.','.','1'}
            ,{'7','.','.','.','2','.','.','.','6'}
            ,{'.','6','.','.','.','.','2','8','.'}
            ,{'.','.','.','4','1','9','.','.','5'}
            ,{'.','.','.','.','8','.','.','7','9'}});
        System.out.println(isValid);
        isValid = validSudoku.isValidSudoku(new char[][]{{'8','3','.','.','7','.','.','.','.'}
            ,{'6','.','.','1','9','5','.','.','.'}
            ,{'.','9','8','.','.','.','.','6','.'}
            ,{'8','.','.','.','6','.','.','.','3'}
            ,{'4','.','.','8','.','3','.','.','1'}
            ,{'7','.','.','.','2','.','.','.','6'}
            ,{'.','6','.','.','.','.','2','8','.'}
            ,{'.','.','.','4','1','9','.','.','5'}
            ,{'.','.','.','.','8','.','.','7','9'}});
        System.out.println(isValid);
        isValid = validSudoku.isValidSudoku(new char[][]{{'.','.','.','.','5','.','.','1','.'},
            {'.','4','.','3','.','.','.','.','.'},
            {'.','.','.','.','.','3','.','.','1'},
            {'8','.','.','.','.','.','.','2','.'},
            {'.','.','2','.','7','.','.','.','.'},
            {'.','1','5','.','.','.','.','.','.'},
            {'.','.','.','.','.','2','.','.','.'},
            {'.','2','.','9','.','.','.','.','.'},
            {'.','.','4','.','.','.','.','.','.'}});
        System.out.println(isValid);
        isValid = validSudoku.isValidSudoku(new char[][]{{'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'}});
        System.out.println(isValid);
    }

    public boolean isValidSudoku(char[][] board) {
        Map<Character, Integer> charMapRow = new HashMap<>();
        Map<Character, Integer> charMapColumn = new HashMap<>();
        Map<Character, Integer> charMapBlock1 = new HashMap<>();
        Map<Character, Integer> charMapBlock2 = new HashMap<>();
        Map<Character, Integer> charMapBlock3 = new HashMap<>();
        for(int i = 0; i < board.length; i++) {
                charMapRow.clear();
                charMapColumn.clear();
                if (i % 3 == 0) {
                    charMapBlock1.clear();
                    charMapBlock2.clear();
                    charMapBlock3.clear();
                }
            for(int j = 0; j < board[i].length; j++) {
                boolean res = true;
                if(board[i][j] != '.') {
                    char key = board[i][j];
                    res = breakIfMapContainsKey(charMapRow, key);
                    if(res) {
                        if (j < 3) {
                            res = breakIfMapContainsKey(charMapBlock1, key);
                        } else if (j > 5) {
                            res = breakIfMapContainsKey(charMapBlock3, key);
                        } else {
                            res = breakIfMapContainsKey(charMapBlock2, key);
                        }
                    }
                }
                if(res && board[j][i] != '.') {
                    res = breakIfMapContainsKey(charMapColumn, board[j][i]);
                }
                if(!res) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean breakIfMapContainsKey(Map<Character, Integer> charMap, char key) {
        if(charMap.containsKey(key)) {
            return false;
        }
        charMap.put(key, 1);
        return true;
    }
}
