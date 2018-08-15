import java.io.*;
import java.util.*;

/**
 * Created by user on 7/29/2018.
 */
public class circularBarn {
    static class Cow implements Comparable<Cow>{
        int index, freq;
        public Cow(int index,int freq){
            this.freq=freq;
            this.index=index;
        }


        @Override
        public int compareTo(Cow o) {
            return index-o.index;
        }
        public String toString(){
            return "Index: "+index+" Freq: "+freq;
        }
    }

    public static int[] cumSum(int start){
        int[] cumulative=new int[len];
        cumulative[0]=cows[start]-1;
        for(int i=1;i<len;i++){
            cumulative[i]=cumulative[i-1]+cows[(start+i)%len]-1;
        }
        int[] diff=new int[len-1];
        for(int i=0;i<len-1;i++){
            diff[i]=cumulative[i+1]-cumulative[i];
        }
        return cumulative;
    }

    public static int len;
    public static int[] cows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        len=Integer.parseInt(br.readLine());
        cows=new int[len];

        for(int i=0;i<len;i++){
            cows[i]=Integer.parseInt(br.readLine());
        }
        int[] cumulative=cumSum(0);

        int index=0;
        int min= Arrays.stream(cumulative).min().getAsInt();
        if(min!=0) {
            while (cumulative[index] != min)
                index++;
        }
        index++;
//        System.out.println(index);
        int[] rotatedCows=new int[len];
        for(int i=0;i<len;i++){
            rotatedCows[i]=cows[(i+index)%len];
        }
        ArrayDeque<Cow> runningTotal=new ArrayDeque<>();
        long cost=0;
        for(int i=0;i<len;i++){
            if(rotatedCows[i]!=0)
            runningTotal.addLast(new Cow(i,rotatedCows[i]));
            Cow toAdd=runningTotal.pollFirst();
            if(toAdd.freq==0)
                toAdd=runningTotal.pollFirst();
            toAdd.freq--;
//            cost+=sumSquares(Math.abs(toAdd.index-i));
            cost+=(toAdd.index-i)*(toAdd.index-i);
            if(toAdd.freq!=0)
                runningTotal.addFirst(toAdd);
        }
//        System.out.println(runningTotal);

        pw.println(cost);
        pw.close();
//        System.out.println(cost);


//        System.out.println(Arrays.toString(rotatedCows));

    }
}
