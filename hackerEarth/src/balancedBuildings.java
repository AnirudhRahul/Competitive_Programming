import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class balancedBuildings{
    static int[][][] dp;
    static int matchBonus;
    static int lowerCost;
    static int riseCost;
    static int[] in;
    public static int recurse(int index, int height,boolean match){
        int matchIndex=match?1:0;
        if(index==in.length-1)
            return 0;
        if(dp[index][height][matchIndex]!=0)
            return dp[index][height][matchIndex];
        if(in[index]==in[index+1])
            return matchBonus+recurse(index+1,in[index],true);
        if(in[index]<in[index+1]){
            int diff=in[index+1]-in[index];
            return Math.max(recurse(index+1,in[index],false),matchBonus-diff*riseCost+recurse(index+1,in[index+1],true));
        }
        if(in[index]>in[index+1]){
            int diff=in[index+1]-in[index];
            return Math.max(recurse(index+1,in[index],false),matchBonus-diff*lowerCost+recurse(index+1,in[index+1],true));
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int len=Integer.parseInt(tokenizer.nextToken());
        matchBonus=Integer.parseInt(tokenizer.nextToken());
        lowerCost=Integer.parseInt(tokenizer.nextToken());
        riseCost=Integer.parseInt(tokenizer.nextToken());
        in=new int[len];
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++)
            in[i]=Integer.parseInt(stringTokenizer.nextToken());
        dp=new int[len][Arrays.stream(in).max().getAsInt()+1][2];
        System.out.println(recurse(in[0],0,false));
    }
}
