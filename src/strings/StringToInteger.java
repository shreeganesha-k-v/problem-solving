package strings;

public class StringToInteger {
    public static void main(String[] args) {
        String s = "   -42";
        System.out.println(stringToInteger(s));
    }

    private static int stringToInteger(String s){
        int i = 0;
        int sign = 1;
        int res = 0;

        //skip spaces
        while(i < s.length() && s.charAt(i) == ' '){
            i++;
        }

        //check for sign
        if(i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '+' ? 1 : -1;
            i++;
        }

        //convert characters to integer
        while(i < s.length() && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            //check for overflow

            if (res > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + digit;
            i++;
        }

        return res * sign;
    }
}
