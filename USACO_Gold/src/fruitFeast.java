import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 7/23/2018.
 */
public class fruitFeast {
    public static void main(String[] args) throws IOException{
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("feast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int maxHunger=Integer.parseInt(tokenizer.nextToken());
        int move1=Integer.parseInt(tokenizer.nextToken());
        int move2=Integer.parseInt(tokenizer.nextToken());
        int c=Math.max(move1,move2);
        move1=Math.min(move1,move2);
        move2=c;
        short[] dp=new short[maxHunger+1];
        dp[move1]=1;
        dp[move2]=1;
        for(int i=move1;i<dp.length;i++){
            if(dp[i]==1){
                int dub=i/2;
                int next1=i+move1;
                int next2=i+move2;
                if(dp[dub]==0) {
                    dp[dub] = 2;
                    i=dub-1;
                }
                if(next1<dp.length)
                    dp[next1]=1;
                if(next2<dp.length)
                    dp[next2]=1;
            }
            else if(dp[i]==2){
                int next1=i+move1;
                int next2=i+move2;
                if(next1<dp.length&&dp[next1]==0)
                    dp[next1]=2;
                if(next2<dp.length&&dp[next2]==0)
                    dp[next2]=2;
            }
        }

        for(int i=dp.length-1;i>=0;i--)
            if(dp[i]!=0) {
                pw.print(i);
                pw.close();
                break;
        }
    }
}
