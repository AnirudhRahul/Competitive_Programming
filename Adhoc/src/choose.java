import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class choose {
    static long[][] dp=new long[200][200];
    static long MOD= 10000007;
    public static long choose(int n, int r){
        if(n==r)
            return 1;
        if(r==0)
            return 1;
        if(r==1)
            return n;
        if(dp[n][r]!=0)
            return dp[n][r];

        return dp[n][r]=(choose(n-1,r)+choose(n-1,r-1))%MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int n=Integer.parseInt(tokenizer.nextToken())-1;
        int r=Integer.parseInt(tokenizer.nextToken());
        HashMap<Integer,Integer> map;

        if(n<r-1){
            System.out.println(-1);
            return;
        }
//        long mult=1;
//        for(long i=n;i>n-r;i--){
////            System.out.println("mult "+i);
//            mult*=i;
//            mult%=MOD;
//        }
//        for(int i=2;i<=r;i++){
////            System.out.println("divide "+i);
//            mult*=BigInteger.valueOf(i).modInverse(BigInteger.valueOf(MOD)).longValueExact();
//            mult%=MOD;
//        }
        System.out.println(choose(n,r-1));
    }
}
