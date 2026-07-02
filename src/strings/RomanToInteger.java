package strings;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(romanToInt("LVIII")); // 58
        System.out.println(romanToInt("MCMXCIV")); // 1994
    }

    private static int romanToInt(String s){
        Map<Character, Integer> roman = new HashMap<>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);

        int res = 0;

        for(int i=0;i<s.length()-1;i++){
            if(roman.get(s.charAt(i)) < roman.get(s.charAt(i+1))){
                res -= roman.get(s.charAt(i));
            }else{
                res += roman.get(s.charAt(i));
            }
        }
        res += roman.get(s.charAt(s.length()-1));
        return res;
    }
}
