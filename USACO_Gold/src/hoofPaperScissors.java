import java.io.*;
import java.util.StringTokenizer;

public class hoofPaperScissors {
    static int[][][] dp;
    static short[] seq;
    public static int dp(short state,int flipsLeft,int index){
        if(index==seq.length)
            return 0;
        if(dp[state][flipsLeft][index]!=0)
            return dp[state][flipsLeft][index];

        if(seq[index]==state)
            return dp[state][flipsLeft][index]=1+dp(state,flipsLeft,index+1);
        else if(flipsLeft!=0){
            return dp[state][flipsLeft][index]=Math.max(dp(state,flipsLeft,index+1),1+dp(seq[index],flipsLeft-1,index+1));
        }
        else
            return dp[state][flipsLeft][index]=dp(state,flipsLeft,index+1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(tokenizer.nextToken());
        int k=Integer.parseInt(tokenizer.nextToken());
        dp=new int[3][k+1][n];
        seq=new short[n];
        for(int i=0;i<n;i++) {
            char cur=br.readLine().charAt(0);
            if(cur=='P')
                seq[i] = 0;
            if(cur=='H')
                seq[i] = 1;
            if(cur=='S')
                seq[i] = 2;
        }
        int min1=dp((short) 0,k,0);
        int min2=dp((short) 1,k,0);
        int min3=dp((short) 2,k,0);
        pw.println(Math.max(Math.max(min1,min2),min3));
        pw.close();
//        System.out.println(Math.max(Math.max(min1,min2),min3));
    }
}
