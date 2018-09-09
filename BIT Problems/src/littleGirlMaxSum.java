import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 9/8/2018.
 */
public class littleGirlMaxSum {
    static class Range implements Comparable<Range>{
        int l,r;
        public Range(int l, int r){
            this.l=l;
            this.r=r;
        }

        @Override
        public int compareTo(Range o) {
            return l-o.l;
        }
        public String toString(){return l+" "+r;}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int len=Integer.parseInt(tokenizer.nextToken());
        int queries=Integer.parseInt(tokenizer.nextToken());
        int[] list=new int[len];
        tokenizer=new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++){
            list[i]=Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(list);
        int[] freq=new int[len];
        PriorityQueue<Range> rangeList=new PriorityQueue<>();
        for(int i=0;i<queries;i++){
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            rangeList.add(new Range(Integer.parseInt(stringTokenizer.nextToken()),Integer.parseInt(stringTokenizer.nextToken())));
        }
        PriorityQueue<Integer> queueEnd=new PriorityQueue<>();
        int curval=0;
        for(int i=0;i<len;i++){
            while(!rangeList.isEmpty()&&rangeList.peek().l==i+1){
                queueEnd.add(rangeList.poll().r);
                curval++;
            }
            freq[i]=curval;
            while(!queueEnd.isEmpty()&&queueEnd.peek()==i+1){
                queueEnd.poll();
                curval--;
            }
        }
        Arrays.sort(freq);
        long sum=0;
        for(int i=0;i<len;i++){
            sum+=(long)freq[i]*list[i];
        }
        System.out.println(sum);


    }
}
