package strings;

public class ReverseWordInString {
    public static void main(String[] args) {
        String s = "  Hello World!  ";
        System.out.println(reverseWordInString(s));
    }

    private static String reverseWordInString(String str){
        int i = str.length() - 1;
        StringBuilder sb = new StringBuilder();

        while(i >= 0){
            //skip spaces
            while(i>=0 && str.charAt(i)==' '){
                i--;
            }

            if(i< 0)break;
            int end = i;

            //find the start of the word
            while(i >=0 && str.charAt(i)!=' '){
                i--;
            }

            int start = i+1;
            String s = str.substring(start, end+1);
            sb.append(s).append(" ");
        }
        return sb.toString();
    }
}
