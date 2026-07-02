package recursion;

public class PowerOfN {
    public static void main(String[] args) {
        System.out.println(powerOfN(2,10));
        System.out.printf("%.5f",powerOfN(2,-2));
    }

    private static double powerOfN(double x , int n){
        if(n==0){
            return 1;
        }

        if(n == 1){
            return x;
        }

        if( n < 0){
            return 1/powerOfN(x,-n);
        }

        if(n%2==0){
            return powerOfN(x*x,n/2);
        }
        return x * powerOfN(x,n-1);
    }
}
