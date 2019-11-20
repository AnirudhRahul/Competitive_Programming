/**
 * Created by user on 11/19/2019.
 */

import java.io.*;
import java.util.*;

public class D_R588 {

    static class Kid implements Comparable<Kid>{
        long skills;
        int weight;
        public Kid(long skills, int weight){
            this.weight=weight;
            this.skills=skills;
        }

        @Override
        public int compareTo(Kid o) {
            long diff = skills-o.skills;
            if(diff<0)
                return -1;
            else if(diff>0)
                return 1;
            else
                return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        if(len==1){
            System.out.println(0);
            return;
        }
        long[] skills = new long[len];
        int[] weight = new int[len];
        long maxWeight = 0;
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++)
            skills[i] = Long.parseLong(tokenizer.nextToken());
        tokenizer = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++)
            weight[i] = Integer.parseInt(tokenizer.nextToken());
        ArrayList<Kid> list = new ArrayList<>(len);
        for(int i=0;i<len;i++)
            list.add(new Kid(skills[i],weight[i]));
        Collections.sort(list);
        long sum = 0;
        long orCombo=0;
        long andCombo=0;
        HashSet<Long> repeats = new HashSet<>();
        for(int i=1;i<len;i++){
            if(list.get(i).skills==list.get(i-1).skills){
                repeats.add(list.get(i).skills);
            }
        }


//        System.out.println(combined);
        for(int i=0;i<len;i++){
            Kid cur = list.get(i);
            if(repeats.contains(cur.skills)){
                sum+=cur.weight;
            }
            else{
                boolean works=false;
                for(long skill:repeats){
                    if((cur.skills|skill)==skill){
                        works=true;
                        break;
                    }
                }
                if(works)
                    sum+=cur.weight;
            }

            if(cur.skills!=orCombo && (cur.skills|orCombo)==orCombo){
                sum+=cur.weight;
            }
        }
        System.out.println(sum);

    }
}
