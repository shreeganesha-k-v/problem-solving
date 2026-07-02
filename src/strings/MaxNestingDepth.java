package strings;

public class MaxNestingDepth {
    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
        System.out.println(maxNestingDepth(s));
    }
    private static int maxNestingDepth(String s){
        int res = 0;
        int count = 0;

        for(char c : s.toCharArray()){
            if(c=='('){
                count++;
            }else if(c == ')'){
                res = Math.max(res,count);
                count--;
            }
        }
        return res;
    }
}
