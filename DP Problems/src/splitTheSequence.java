import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 3/2/2019.
 */
public class splitTheSequence{
    static int[] list;
    static int[][][] dp;
    static boolean[][][] optionTaken;
    static int len;
    static int[] suffixSums;
    public static int recurse(int index, int k, int sum, int partLen){
//        System.out.println("Index:"+index+"\tk:"+k+"\tSum:"+"\tPrev:"+prev);
        if(index==len)
            return k==0?0:Integer.MIN_VALUE/2;
        if(k==0||index==len-1)
            return dp[index][k][partLen]=recurse(index+1,k,sum+list[index],partLen+1);
        if(dp[index][k][partLen]!=0)
            return dp[index][k][partLen];
        sum+=list[index];
        int option1=(suffixSums[index+1])*sum+recurse(index+1,k-1,0,0);
        int option2=recurse(index+1,k,sum,partLen+1);
        optionTaken[index][k][partLen]=option1>option2;
        return dp[index][k][partLen]=Math.max(option1,option2);
    }
    static ArrayList<Integer> splitLocations= new ArrayList<>();
    public static void constructList(int index,int k, int partLen){
    if(k==0)
        return;
    if(optionTaken[index][k][partLen]){
        splitLocations.add(index);
        constructList(index+1,k-1,0);
    }
    else
        constructList(index+1,k,partLen+1);
        //        int option1=(suffixSums[index+1])*sum+recurse(index+1,k-1,0,0);
//        int option2=recurse(index+1,k,sum,partLen+1);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        len = Integer.parseInt(stringTokenizer.nextToken());
        int maxSplits = Integer.parseInt(stringTokenizer.nextToken());
        list = new int[len];
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++)
            list[i]=Integer.parseInt(tokenizer.nextToken());
        suffixSums = new int[len];
        suffixSums[len-1]=list[len-1];
        for(int i=len-2;i>=0;i--){
            suffixSums[i]=list[i]+suffixSums[i+1];
        }
//        System.out.println(Arrays.toString(list));
//        System.out.println(Arrays.toString(suffixSums));
        dp = new int[len][maxSplits+1][len];
        optionTaken = new boolean[len][maxSplits+1][len];

        System.out.println(recurse(0,maxSplits,0,0));
        constructList(0,maxSplits,0);
        for(int i=0;i<splitLocations.size();i++){
            System.out.print((splitLocations.get(i)+1)+(i==splitLocations.size()-1?"":" "));
        }
//        System.out.println(splitLocations);
    }
}
