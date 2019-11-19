/**
 * Created by user on 11/14/2019.
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class booksExchangeHard_R595 {
    static class SimpleDisjointSet {
        int[] parent;
        public SimpleDisjointSet(int size){
            parent=new int[size];
            for(int i=0;i<size;i++)
                parent[i]=i;
        }

        public int find(int x){
            if(parent[x]==x)
                return x;
            else
                return parent[x]=find(parent[x]);
        }

        public void union(int x, int y){
            parent[find(x)]=find(y);
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int cases = Integer.parseInt(br.readLine());
        while(cases-->0){
            int len = Integer.parseInt(br.readLine());
            SimpleDisjointSet set = new SimpleDisjointSet(len);
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int[] arr = new int[len];
            for(int i=0;i<len;i++){
                arr[i]= Integer.parseInt(tokenizer.nextToken())-1;
            }
            for(int i=0;i<arr.length;i++){
                set.union(i,arr[i]);
            }
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int i=0;i<len;i++){
                int cur = set.find(i);
                if(map.containsKey(cur))
                    map.put(cur,map.get(cur)+1);
                else
                    map.put(cur,1);
            }

            for(int i=0;i<len;i++){
                wr.write(""+map.get(set.find(i)));
                if(i!=len-1)
                    wr.write(" ");
                else
                    wr.write("\n");
            }



        }
        wr.flush();

    }
}
