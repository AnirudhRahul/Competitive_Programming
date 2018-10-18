import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by user on 10/2/2018.
 */
public class excellentEngineers {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int len=Integer.parseInt(br.readLine());
        int[][] list=new int[len][2];
        for(int i=0;i<len;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(tokenizer.nextToken());
            int b=Integer.parseInt(tokenizer.nextToken());
            int c=Integer.parseInt(tokenizer.nextToken());
            list[a-1][0]=b;
            list[a-1][1]=c;
        }
        int count=1;
        for(int i=1;i<len;i++){
            if(list[i-1][0]>list[i][0]&&list[i-1][1]>list[i][1])
                continue;
            else
                count++;
        }
        System.out.println(count);
    }
}
