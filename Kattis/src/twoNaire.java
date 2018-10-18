import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by user on 9/15/2018.
 */
public class twoNaire{
    public static double solve(int games, double prob, double curval){
        if(games==0)
            return curval;

        double probStop=(0.5-prob)/(1-prob);
        double probCont=(0.5)/(1-prob);
        return  curval*probStop+probCont*solve(games-1,prob, 1.5*curval);
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int game=Integer.parseInt(tokenizer.nextToken());
        double prob=Double.parseDouble(tokenizer.nextToken());
        System.out.println(solve(game,prob,1));
    }
}
