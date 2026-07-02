package strings;

public class LongestPallindrome {
    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPallindrome(s));
    }
    private static int longestPallindrome(String s){
        int res = 1;
        for(int i=0; i< s.length();i++){
            int len1 = expand(s, i, i); // odd length pallindrome
            int len2 = expand(s, i, i+1); // even length pallindrome
            int len = Math.max(len1, len2);
            res = Math.max(res, len);
        }
        return res;
    }
    private static int expand(String s , int left , int right){

        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left --;
            right ++;
        }
        return right - left - 1; // after while loop both left and right have moved one step further, so we need to subtract 1 from the length
    }
}
