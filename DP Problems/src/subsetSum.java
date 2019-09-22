import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by user on 8/5/2019.
 */
public class subsetSum {

    static ArrayList<Integer> list = new ArrayList<>();
    static Boolean[][] dp;
    public static boolean recurse(int target, int index){
        if(target == 0)
            return true;

        if(target<0 || index == list.size())
            return false;

        if(dp[target][index]!=null)
            return dp[target][index];

        return dp[target][index]=
                recurse(target,index+1) ||
                recurse(target-list.get(index),index+1);
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int len = scan.nextInt();
        int target = scan.nextInt();
        for(int i=0;i<len;i++)
            list.add(scan.nextInt());
        dp = new Boolean[target+1][len];
        System.out.println(recurse(target,0));
    }
}
