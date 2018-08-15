import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class circularBarnRevisited {
    public static int len;
    public static long minDist(ArrayList<Integer> entrances,int i){
        long min=Integer.MAX_VALUE;
        for(int e:entrances){
            min=Math.min(min,dist(e,i));
        }
        return min;
    }

    public static long dist(int e, int i){
        return i-e>=0?i-e:len-e+i;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        len=Integer.parseInt(tokenizer.nextToken());
        int openings=Integer.parseInt(tokenizer.nextToken());
        int[] cows=new int[len];
        for(int i=0;i<len;i++)
            cows[i]=Integer.parseInt(br.readLine());

        ArrayList<Integer> doors=new ArrayList<>(openings);
        while (openings>0) {
            ArrayList<Long> vals = new ArrayList<>(len);
            for (int test = 0; test < len; test++) {
                long sum = 0;
                for (int i = 0; i < len; i++) {
                    sum += cows[i] * Math.min(dist(test, i), minDist(doors, i));

                }
                vals.add(sum);
            }

            long min = Long.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < len; i++) {
                if (doors.contains(i))
                    continue;
                if (min >= vals.get(i)) {
                    min = vals.get(i);
                    minIndex = i;
                }
            }
            ArrayList<Integer> possibleIndices = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (vals.get(i) == min)
                    possibleIndices.add(i);
            }
            for (int i = 0; i < possibleIndices.size()&&openings>0; i += 3) {
                doors.add(possibleIndices.get(i));
                openings--;
            }
        }
        long sum=0;
        for(int i=0;i<len;i++) {
            sum += cows[i] * minDist(doors, i);
//            System.out.println("MinDist "+minDist(doors,i)+" i "+i);
        }
//        System.out.println(doors);
        System.out.println(sum);


    }
}
