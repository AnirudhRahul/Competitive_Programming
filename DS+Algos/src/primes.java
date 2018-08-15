import java.io.BufferedReader;
import java.io.InputStreamReader;

public class primes{
    //find primes greater than start value to use as mod bases
    public static void main(String args[]){
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int start=10000;
        out:for(int i=start;i<Integer.MAX_VALUE;i++){
                for(int k=2;k<=Math.sqrt(start);k++){
                    if(i%k==0)
                        continue out;
                }
            System.out.println(i);
                break out;
        }
    }
}
