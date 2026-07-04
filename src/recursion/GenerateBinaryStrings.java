package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateBinaryStrings {
    // Problem Statement: Given an integer n, return all binary strings of length n that do not contain consecutive 1s. Return the result in lexicographically increasing order.
    public static void main(String[] args) {
        int n = 3;
        List<String> res = new ArrayList<>();
        generate(n, "", res);

        System.out.println(res);
    }

    private static void generate(int n , String curr , List<String> res){
        if(curr.length() == n){
            res.add(curr);
            return;
        }

        generate(n, curr + "0" , res);

        if(curr.isBlank() || curr.charAt(curr.length()-1) !='1'){
            generate(n, curr + "1", res);
        }
    }
}
