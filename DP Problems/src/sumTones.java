import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 1/9/2019.
 */
public class sumTones {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\Competitive_Programming\\DP Problems\\src\\tone.txt"));
        int cases = Integer.parseInt(br.readLine());
        for(int c=1; c<=cases ; c++){
            int len = Integer.parseInt(br.readLine());
            long[] arr = new long[len];
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for(int i=0;i<len;i++)
                arr[i] = Long.parseLong(tokenizer.nextToken());

            ArrayList<Long> pows = new ArrayList<>();
            for(int i = 0; i<63 ; i++){
                pows.add(1L<<i);
            }

            HashMap<Long, Integer> map = new HashMap<>();
            map.put(arr[0],1);
            for(int i=1; i<len; i++){
                long cur = arr[i];
                if(!map.containsKey(cur))
                    map.put(arr[i],1);
                for(int k=0;k<pows.size();k++){
                    long valNeeded = pows.get(k) - cur;
                    if(map.containsKey(valNeeded))
                        if(map.get(cur)<map.get(valNeeded) + 1)
                            map.put(cur, map.get(valNeeded) + 1);
                }
            }

            System.out.println("Song #"+c+": "+ Collections.max(map.values()));

        }
    }
}
