import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by user on 8/5/2019.
 */
public class DoubleHelix {
    static int[][] dp;
    static int[][] list;
    static int len1,len2;
    static int recurse(int index,int track){
        if(track == 0 && index == len1)
            return 0;
        if(track == 1 && index == len2)
            return 0;
        if(dp[index][track]!=0)
            return dp[index][track];


        if(list[index][track]==list[index][1-track]&&index<len1&&index<len2){
            return dp[index][track]=Math.max(
                    list[index][track] + recurse(index+1,track),
                    list[index][track] + recurse(index+1,1-track)
                    );
        }

        return dp[index][track]=list[index][track] + recurse(index+1,track);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String line = br.readLine();
            if(line.startsWith("0"))
                break;
            StringTokenizer tokenizer1 = new StringTokenizer(line);
            len1 = Integer.parseInt(tokenizer1.nextToken());

            StringTokenizer tokenizer2 = new StringTokenizer(br.readLine());
            len2 = Integer.parseInt(tokenizer2.nextToken());
            int maxLen = Math.max(len1,len2);

            list = new int[maxLen][2];

            for(int i=0;i<len1;i++)
                list[i][0]=Integer.parseInt(tokenizer1.nextToken());
            for(int i=0;i<len2;i++)
                list[i][1]=Integer.parseInt(tokenizer2.nextToken());
            dp = new int[maxLen][2];

            System.out.println(Math.max(recurse(0,0),recurse(0,1)));

        }
    }
}
