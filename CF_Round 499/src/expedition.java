import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 7/28/2018.
 */
public class expedition {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int people=Integer.parseInt(tokenizer.nextToken());
        int food=Integer.parseInt(tokenizer.nextToken());
        if(food<people) {
            System.out.println(0);
            return;
        }
        if(food==people){
            System.out.println(1);
            return;
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        tokenizer=new StringTokenizer(br.readLine());
        for(int i=0;i<food;i++){
            int cur=Integer.parseInt(tokenizer.nextToken());
            if(map.containsKey(cur))
                map.put(cur,map.remove(cur)+1);
            else
                map.put(cur,1);
        }
        ArrayList<Integer> days=new ArrayList<>(food/people);
        int testMax=food/people;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int e : map.values())
            q.add(-e);
        while(testMax>0) {
            int curMin=testMax;
            PriorityQueue<Integer> foodleft=new PriorityQueue<>(q);
            for(int i=0;i<people;i++){
                int cur=-foodleft.poll();
                if(cur>=testMax)
                    cur-=testMax;
                else{
                    curMin=Math.min(curMin,cur);
                    cur=0;
                }
                foodleft.add(-cur);
            }
            days.add(curMin);
            testMax--;

        }

        System.out.println(Collections.max(days));

    }
}
