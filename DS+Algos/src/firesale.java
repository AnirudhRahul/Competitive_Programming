import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Created by user on 3/9/2019.
 */
public class firesale {
    static boolean canAdd(int value, int min, int max, int maxDiff){
        min=Math.min(value,min);
        max=Math.max(value,max);
        return (max-min)<=maxDiff;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\Competitive_Programming\\UCF HSPT 2018\\src\\firesale22in"));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(stringTokenizer.nextToken());
        int maxDiff = Integer.parseInt(stringTokenizer.nextToken());
        int[] arr = new int[len];
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++)
            arr[i]=Integer.parseInt(tokenizer.nextToken());
        ArrayDeque<Integer> curRange = new ArrayDeque<>();
        TreeMap<Integer, Integer> curElements = new TreeMap<>();
        curRange.addLast(arr[0]);
        curElements.put(arr[0],1);
        int maxLen=1;
        for(int i=1;i<len;i++){
            while(!curElements.isEmpty()&&!canAdd(arr[i],curElements.firstKey(),curElements.lastKey(),maxDiff)){
                int removed=curRange.pollFirst();
                if(curElements.get(removed)==1)
                    curElements.remove(removed);
                else
                    curElements.put(removed,curElements.remove(removed)-1);
            }
            curRange.addLast(arr[i]);
            if(curElements.containsKey(arr[i]))
                curElements.put(arr[i],curElements.remove(arr[i])+1);
            else
                curElements.put(arr[i],1);

            maxLen=Math.max(maxLen,curRange.size());

        }
        System.out.println(maxLen);

    }
}
