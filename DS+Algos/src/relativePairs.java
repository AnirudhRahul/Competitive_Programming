import java.io.BufferedReader;
import java.io.InputStreamReader;

public class relativePairs {
    public static int gcd(int a, int b){
        if(a==0||b==0)
            return a+b;
        else
            return gcd(b%a,a);
    }
    public static void main(String[] args){
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int count=0;
        System.out.println(gcd(1,2));
        System.out.println(gcd(2,2));
        System.out.println(gcd(7,10));
        for(int i=1;i<=100;i++)
            for(int j=1;j<=100;j++){
                if(gcd(i,j)==1)
                    count++;
        }
        System.out.println(count);
    }
}
