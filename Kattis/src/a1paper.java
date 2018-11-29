import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 9/12/2018.
 */
public class a1paper {
    final static double root2=Math.sqrt(2);
    final static double target=0.5;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int types=Integer.parseInt(br.readLine());
        double[] longSide=new double[types+1];
        longSide[2]=2*(Math.pow(2,-1.25)+Math.pow(2,-0.75));
        for(int i=3;i<longSide.length;i++)
            longSide[i]=longSide[i-1]/root2;
        int[] list=new int[types+1];
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        for(int i=2;i<list.length;i++)
            list[i]=Integer.parseInt(tokenizer.nextToken());
        double curValTape=0;
        int req=2;
        double curMin=Double.MAX_VALUE;
        for(int i=2;i<list.length;i++){
            int subtract=Math.min(req,list[i]);
            req-=subtract;
            curValTape+=subtract*longSide[i];

            if(req==0) {
                curMin = curValTape;
                break;
            }
            req*=2;


        }
        double extra=2*(Math.pow(2,-0.25)+Math.pow(2,-0.75));
        if(req!=0)
            System.out.println("impossible");
        else
            System.out.println((curMin-extra)/2);



    }
}
