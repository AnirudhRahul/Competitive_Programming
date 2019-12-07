package Contest2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 12/1/2018.
 */
public class fireworks {
    //Both the maximum height, the time till explosion are proportional to the velocity
    //So we can just find the indices of the largest and smallest velocities
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        for(int c=1;c<=cases;c++){
            int fireworks=Integer.parseInt(br.readLine());
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int[] in=new int[fireworks];
            for(int i=0;i<fireworks;i++)
                in[i]=Integer.parseInt(tokenizer.nextToken());
            int min= Arrays.stream(in).min().getAsInt();
            int max= Arrays.stream(in).max().getAsInt();
            int minIndex=0;
            int maxIndex=0;
            for(int i=0;i<fireworks;i++){
                if(in[i]==max)
                    maxIndex=i;
                if(in[i]==min)
                    minIndex=i;

            }
            minIndex++;
            maxIndex++;
            System.out.println("Scenario #"+c+":");
            System.out.println("Highest Firework: "+maxIndex);
            System.out.println("Earliest Firework: "+minIndex);
            System.out.println();

        }

    }
}
