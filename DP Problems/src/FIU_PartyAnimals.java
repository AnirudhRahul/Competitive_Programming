import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by user on 8/12/2019.
 */
public class FIU_PartyAnimals {
    static int[] boss;
    static HashMap<Integer, ArrayList<Integer>> workers;
    static int[] conv;
    static int[][] dp;

//    static ArrayList<Integer> order;

    static int recurse(int index, boolean bossGoing){
        int bIndex=bossGoing?0:1;
        if(dp[index][bIndex]!=0)
            return dp[index][bIndex];

        if(bossGoing){
            int sum = 0;
            for(int child:workers.get(index))
                sum+=recurse(child,false);

            return dp[index][bIndex]=sum;
        }
        else {
            int sum1 = conv[index];
            for(int child:workers.get(index))
                sum1+=recurse(child,true);

            int sum2 = 0;
            for(int child:workers.get(index))
                sum2+=recurse(child,false);

            return dp[index][bIndex]=Math.max(sum1,sum2);
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        boss = new int[size];
        conv = new int[size];
        dp = new int[size][2];
        workers = new HashMap<>();
        for(int i=0;i<size;i++){
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            boss[i] = Integer.parseInt(tokenizer.nextToken())-1;
            conv[i] = Integer.parseInt(tokenizer.nextToken());
            workers.put(i, new ArrayList<>());
        }

        for(int i=1;i<size;i++){
            if(boss[i]==-1)
                continue;
            workers.get(boss[i]).add(i);
        }
//        System.out.println(workers);
        System.out.println(recurse(0,false));

//        System.out.println(workers);
//        ArrayDeque<Integer> q = new ArrayDeque<>();
//        q.addFirst(0);
//        while (!q.isEmpty()){
//            int cur = q.pollFirst();
////            System.out.println(q);
//            for(int underling: workers.get(cur))
//                q.addFirst(underling);
//            order.add(cur);
//        }



    }
}
