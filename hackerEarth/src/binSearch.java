
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by user on 11/26/2018.
 */
public class binSearch {
    static long a,b,c,d;
    public static long functionVal(long in){
        return (long) (a*Math.pow(in,3)+b*Math.pow(in,2)+c*in+d);
    }
    public static boolean inRange(long in){
        return (a*Math.pow(in,3)+b*Math.pow(in,2)+c*in+d)<=10e18;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        while (cases-->0){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            a=Long.parseLong(tokenizer.nextToken());
            b=Long.parseLong(tokenizer.nextToken());
            c=Long.parseLong(tokenizer.nextToken());
            d=Long.parseLong(tokenizer.nextToken())-Long.parseLong(tokenizer.nextToken());
            long min=1;
            long max= 1;
            while(inRange(max))
                max*=2;
            max/=2;
            while (max-min>3){
                long mid=(min+max)/2;
                if(functionVal(mid)<0){
                    min=mid;
                }
                else
                    max=mid;

            }
            for(long i=min;i<Long.MAX_VALUE;i++){
                if(functionVal(i)>0) {
                        System.out.println(i - 1);
                    break;
                }
            }

        }
    }
}
