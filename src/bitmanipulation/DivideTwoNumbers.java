package bitmanipulation;

public class DivideTwoNumbers {
    public static void main(String[] args) {
        int dividend = 7;
        int divisor = -3;
        System.out.println(divide(dividend, divisor));
    }

    private static int divide(int a , int b) {
        if(a==b){
            return 1;
        }else if(a == Integer.MIN_VALUE && b == -1){
            return Integer.MAX_VALUE;
        }else if(a == Integer.MIN_VALUE && b == 1) {
            return Integer.MIN_VALUE;
        }
        boolean isPositive = (a >= 0 || b <= 0) && (a <= 0 || b >= 0);

        long dividend = Math.abs((long)a);
        long divisor = Math.abs((long)b);
        long sum = 0 , ans = 0;

        while(sum + divisor <= dividend){
            ans++;
            sum += divisor;
        }

        return isPositive ? (int)ans : (int)-ans;
    }
}
