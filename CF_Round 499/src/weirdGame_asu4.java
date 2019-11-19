/**
 * Created by user on 11/16/2019.
 */

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class weirdGame_asu4 {
    //Repeating code so use and object
    static class Game{
        long count;
        public Game(int[] in, int target){
            //Create frequency map
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int i=0;i<in.length;i++){
                if(map.containsKey(in[i]))
                    map.put(in[i],map.get(in[i])+1);
                else
                    map.put(in[i],1);
            }
            //Double Count
            for(int i=0;i<in.length;i++){
                int toGet=target-in[i];
                if(toGet==in[i]){
                    count+=map.get(toGet)-1;
                }
                else if(map.containsKey(toGet))
                    count+=map.get(toGet);
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(tokenizer.nextToken());
        int sum = Integer.parseInt(tokenizer.nextToken());
        int[] a = new int[len];
        tokenizer = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++){
            a[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int[] b = new int[len];
        tokenizer = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++){
            b[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Game mahm = new Game(a, sum);
        Game bashar = new Game(b, sum);

        if(bashar.count>mahm.count){
            System.out.println("BASHAR");
        }
        else if(mahm.count>bashar.count){
            System.out.println("MAHMOUD");
        }
        else
            System.out.println("DRAW");
    }
}
