/**
 * Created by user on 8/14/2018.
 */
public class KnapSack {
    static int[][] dp;
    static int[] weight={};
    static int[] vals={};
    static int maxWeight=11;
    public static int knapSack(int index, int curWeight){
        if(index>=vals.length)
            return 0;
        if(dp[index][curWeight]!=0)
            return dp[index][curWeight];
        if(curWeight+weight[index]>maxWeight)
            return dp[index][curWeight]=knapSack(index+1,curWeight);
        else
            return dp[index][curWeight]=Math.max(knapSack(index+1,curWeight),vals[index]+knapSack(index+1,curWeight+weight[index]));
    }
    public static void main(String args[]){
        dp=new int[weight.length][maxWeight+1];
        System.out.println(knapSack(0,0));
    }
}
