import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 3/10/2019.
 */
public class deepuAndArray {
    static class Index implements Comparable<Index>{
        int index;
        long value;
        public Index(int index, long value){
            this.index=index;
            this.value=value;
        }

        @Override
        public int compareTo(Index o) {
            long diff = this.value-o.value;
            if(diff>0)
                return 1;
            if(diff<0)
                return -1;

            return 0;

        }
        public String toString(){return ""+value;}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        ArrayList<Index> list = new ArrayList<>(len);
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++)
            list.add(new Index(i,Long.parseLong(stringTokenizer.nextToken())));
        Collections.sort(list);
        long[] diff = new long[len];
        diff[0]= list.get(0).value;
        for(int i=1;i<diff.length;i++){
            diff[i]=list.get(i).value - list.get(i-1).value;
        }
//        System.out.println(Arrays.toString(diff));
        int queries = Integer.parseInt(br.readLine());
        while (queries-->0){
            long val = Long.parseLong(br.readLine());
            long cur = 0;
            for(int i=0;i<diff.length;i++){
                cur+=diff[i];
                if(cur>val) {
                    diff[i]--;
                    break;
                }
            }
        }
        long cur=0;
        for(int i=0;i<list.size();i++){
            cur+=diff[i];
            list.get(i).value=cur;
        }


        Collections.sort(list, new Comparator<Index>() {
            @Override
            public int compare(Index o1, Index o2) {
                return o1.index-o2.index;
            }
        });

        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i).value+(i==list.size()-1?"":" "));
        }

//        System.out.println(list);
    }
}
