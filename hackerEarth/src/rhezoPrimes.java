import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 11/22/2018.
 */
public class rhezoPrimes {
    static int[] dp;
    static int[] sums;
    static int len;
    static ArrayList<Integer> primes=new ArrayList<>();
    public static int recurse(int index){
        if(dp[index]!=0)
            return dp[index];
        int max=0;
        for(int i=0;i<primes.size();i++){
            int newIndex=index+primes.get(i)+1;
            if(newIndex>len+1)
                break;

            max=Math.max(max,rangeSum(index,newIndex-1)+recurse(newIndex));
        }
        return dp[index]=max;
    }

    public static int rangeSum(int l,int r){
        return sums[r]-sums[l];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        len=Integer.parseInt(br.readLine());
        dp=new int[len+10];
        sums=new int[len+1];
        int[] in=new int[len];
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++){
            in[i]=Integer.parseInt(tokenizer.nextToken());
        }
        sums[0]=0;
        for(int i=1;i<len+1;i++){
            sums[i]=sums[i-1]+in[i-1];
        }
//        System.out.println(Arrays.toString(sums));
        boolean[] sieve=new boolean[len+1];
        for(int k=2;k<sieve.length;k++){
            if(sieve[k])
                continue;
            primes.add(k);

            for(int j=2*k;j<sieve.length;j+=k){
                sieve[j]=true;
            }
        }
        System.out.println(recurse(0));

    }
}
