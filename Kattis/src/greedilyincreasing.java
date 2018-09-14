import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Created by user on 9/13/2018.
 */
public class greedilyincreasing{
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int len=Integer.parseInt(br.readLine());
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int[] list=new int[len];
        for(int i=0;i<len;i++)
            list[i]=Integer.parseInt(tokenizer.nextToken());
        ArrayList<Integer> fin=new ArrayList<>(len);
        fin.add(list[0]);
        for(int i=1;i<len;i++){
            if(list[i]>fin.get(fin.size()-1))
                fin.add(list[i]);
        }
        System.out.println(fin.size());
        for(int i=0;i<fin.size()-1;i++)
            System.out.print(fin.get(i)+" ");
        System.out.print(fin.get(fin.size()-1));
    }
}
