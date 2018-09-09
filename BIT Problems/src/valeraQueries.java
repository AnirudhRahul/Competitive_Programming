import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by user on 9/8/2018.
 */
public class valeraQueries {
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
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int ranges=Integer.parseInt(tokenizer.nextToken());
        int queries=Integer.parseInt(tokenizer.nextToken());
        ArrayList<Range> rangeList=new ArrayList<>(ranges);
        for(int i=0;i<ranges;i++){
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            rangeList.add(new Range(Integer.parseInt(stringTokenizer.nextToken()),Integer.parseInt(stringTokenizer.nextToken())));
        }


    }
}
