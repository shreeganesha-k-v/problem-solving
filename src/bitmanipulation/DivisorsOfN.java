package bitmanipulation;

import java.util.ArrayList;
import java.util.List;

public class DivisorsOfN {
    /*We can optimise the previous approach by using the property that for any non-negative integer n, if d is a divisor of n then n/d is also a divisor of n.
    This property is symmetric about the square root of N. Thus, by traversing just the first half we can avoid redundant iteration and computations improving the efficiency
    of the algorithm.*/
    public static void main(String[] args) {
        System.out.println(divisors(36));
    }

    private static List<Integer> divisors(int n){
        List<Integer> res = new ArrayList<>();

        for(int i=1;i*i <= n;i++){
            if(n % i ==0){
                res.add(i);
            }

            if(i != (n/i)){
                res.add(n/i);
            }
        }
        return res;
    }
}
