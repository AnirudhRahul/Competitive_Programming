/**
 * Created by user on 11/16/2019.
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class basharHard_asu4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(tokenizer.nextToken());
        int coinsNeeded = Integer.parseInt(tokenizer.nextToken());
        int[] arr = new int[len];
        long[] sums = new long[len];
        tokenizer = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
            if(i>0)
                sums[i]=sums[i-1];
            sums[i]+=arr[i];
        }
        if(sums[len-1]<coinsNeeded){
            System.out.println(-1);
            return;
        }
        int[] lenNeeded = new int[len];
        for(int i=0;i<len;i++)
            lenNeeded[i]=Integer.MAX_VALUE;
        for(int start=0;start<len;start++){
            int low = start;
            int high = len;
            while(high-low>4){
                int mid = (low+high)/2;
                long curSum = 0;
                if(start!=0)
                    curSum=sums[mid]-sums[start-1];
                else
                    curSum=sums[mid];
                if(curSum>coinsNeeded){
                    high=mid;
                }
                else if(curSum<coinsNeeded){
                    low=mid;
                }
                else{
                    high=mid;
                    low=mid;
                }
            }
            for(int i=low;i<len;i++){
                long curSum = 0;
                if(start!=0)
                    curSum=sums[i]-sums[start-1];
                else
                    curSum=sums[i];
                if(curSum>=coinsNeeded){
                    lenNeeded[start]=(i-start+1);
                    break;
                }
            }

        }
        System.out.println(Arrays.stream(lenNeeded).min().getAsInt());

    }
}
