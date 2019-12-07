package Contest2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by user on 12/1/2018.
 */
public class even {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        for(int c=1;c<=cases;c++){
            long in=Integer.parseInt(br.readLine());
            long big=1;
            while(big<=in)
                big*=2;
            System.out.println("Pokemon "+c+": "+(big-in));

        }

    }
}
