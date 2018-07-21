import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by user on 7/21/2018.
 */
public class CanonicalCoins {
    public static void main(String args[]) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int len=Integer.parseInt(br.readLine());
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int[] coinValues=new int[len];
        for(int i=0;i<len;i++) {
            coinValues[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int largest=coinValues[len-1];
        int nextLargest=coinValues[len-2];
        //Create an array to find the minimum amount of coins in the optimal case
        int[] dp=new int[largest+nextLargest+1];
        dp[1]=1;
        for(int i=2;i<=largest+nextLargest;i++){
            int minIndex=0;
            for(int j=0;j<len;j++){
                if(i-coinValues[j]>0){
                    minIndex=i-coinValues[j];
                    if(dp[i]==0)
                        dp[i]=dp[minIndex]+1;
                    else
                        dp[i]=Math.min(dp[i],dp[minIndex]+1);
                }
                else
                    break;
            }
            //If the optimal case does not take the largest valued coin out it can then it is not canonical
            if(dp[i]!=dp[minIndex]+1){
                System.out.println("non-canonical");
                return;
            }


        }
        System.out.println("canonical");

    }
}
