import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 7/29/2018.
 */
public class circularBarn {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int len=Integer.parseInt(br.readLine());
        int[] cows=new int[len];
        int[] cumulative=new int[len];
        for(int i=0;i<len;i++){
            cows[i]=Integer.parseInt(br.readLine());
            if(i!=0)
                cumulative[i]+=cumulative[i-1]+cows[i]-1;
            else
                cumulative[i]+=cows[i]-1;
        }
        System.out.println(Arrays.toString(cumulative));

        int index=len;
        int sum=0;
        int min= Arrays.stream(cumulative).min().getAsInt();
        if(min!=0)
        while(sum<-min){
            index--;
            sum+=cows[index];
        }
        else
            index=0;
        System.out.println(Arrays.toString(cumulative));
        System.out.println((index));


    }
}
