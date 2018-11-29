import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 10/30/2018.
 */
public class towerOfHanoi{
    //max height some for each radius
    static long[][][] dp;
    static ArrayList<Disk> list;
    public static long recurse(int index,int radius, int lasth){
        if(radius==0||index==list.size())
            return 0;

        if(dp[index][radius][lasth]!=0)
            return dp[index][radius][lasth];

        if(radius>list.get(index).r&&lasth>list.get(index).compressedH){
            return dp[index][radius][lasth]=Math.max(recurse(index+1,radius,lasth),list.get(index).h+recurse(index+1,list.get(index).r,list.get(index).compressedH));
        }
        else
            return dp[index][radius][lasth]=recurse(index+1,radius,lasth);

    }
    static class Disk implements Comparable<Disk>{
        int r, h,compressedH;
        public Disk(int r, int h, int compressedH){
            this.r = r;
            this.h=h;
            this.compressedH=compressedH;
        }

        public String toString(){return "height:"+h;}

        @Override
        public int compareTo(Disk o) {
            return o.h-h;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        while(cases-->0){
            int len=Integer.parseInt(br.readLine());
            int[] radii=new int[len];
            int[] sortedRadii=new int[len];
            int[] heights=new int[len];
            int[] sortedHeights=new int[len];
            dp=new long[300][300][300];
            list=new ArrayList<>();
            HashMap<Integer,Integer> compressR=new HashMap<>();
            HashMap<Integer,Integer> compressH=new HashMap<>();
            for(int i=0;i<len;i++){
                StringTokenizer tokenizer=new StringTokenizer(br.readLine());
                radii[i]=Integer.parseInt(tokenizer.nextToken());
                heights[i]=Integer.parseInt(tokenizer.nextToken());
                sortedRadii[i]=radii[i];
                sortedHeights[i]=heights[i];
            }
            Arrays.sort(sortedRadii);
            Arrays.sort(sortedHeights);
            for(int i=0;i<sortedRadii.length;i++) {
                compressR.put(sortedRadii[i], i);
                compressH.put(sortedHeights[i],i);
            }


            for(int i=0;i<radii.length;i++)
                radii[i]=compressR.get(radii[i]);



            for(int i=0;i<len;i++)
                list.add(new Disk(radii[i],heights[i],compressH.get(heights[i])));
            Collections.sort(list);




            System.out.println(recurse(0,299,299));
        }
    }
}
