import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 9/2/2018.
 */
public class barnPainting{
    static class ColorSet{
        HashSet<Integer> set=new HashSet<>(3);
        public ColorSet(){
            set.add(0);
            set.add(1);
            set.add(2);
        }
        public ColorSet(int in){
            set.add(in);
        }
        public String toString(){return set.toString();}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int barns=Integer.parseInt(stringTokenizer.nextToken());
        int colored=Integer.parseInt(stringTokenizer.nextToken());
        ArrayList<Integer>[] adjacencyList=new ArrayList[barns+1];
        for(int i=0;i<barns+1;i++)
            adjacencyList[i]=new ArrayList<>();
        for(int i=0;i<barns-1;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(tokenizer.nextToken());
            int end=Integer.parseInt(tokenizer.nextToken());
            adjacencyList[start].add(end);
            adjacencyList[end].add(start);
        }
        ColorSet[] colors=new ColorSet[barns+1];
        for(int i=0;i<colored;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int barn=Integer.parseInt(tokenizer.nextToken());
            int color=Integer.parseInt(tokenizer.nextToken());
            colors[barn]=new ColorSet(color);
        }
        for(int i=1;i<colors.length;i++)
            colors[i]=new ColorSet();

        System.out.println(Arrays.toString(colors));
    }
}
