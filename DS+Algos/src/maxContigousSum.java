import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
        import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class maxContigousSum {
    static int maxSum(ArrayList<Integer> in){
        int[] dp = new int[in.size()];
        dp[0]=Math.max(0,in.get(0));
        for(int i=1;i<dp.length;i++){
            dp[i]=Math.max(0,dp[i-1]+in.get(i));
        }
        int max = Arrays.stream(dp).max().getAsInt();

        return max;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        for(int c=1;c<=cases;c++){
            int len = Integer.parseInt(br.readLine());
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            ArrayList<Integer> list = new ArrayList<>(len);
            for(int i=0;i<len;i++)
                list.add(Integer.parseInt(tokenizer.nextToken()));
            int max = maxSum(list);
            if(max==0)
                max=Collections.max(list);
            System.out.println(max);
        }
    }
}
