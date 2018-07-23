import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Created by user on 7/23/2018.
 */
public class cardgame {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
        int cards=2*Integer.parseInt(br.readLine());
        ArrayList<Integer> p1=new ArrayList<>(cards/2);
        ArrayList<Integer> p2=new ArrayList<>(cards/2);
        boolean[] arr=new boolean[cards+1];
        for(int i=0;i<cards/2;i++) {
            p1.add(Integer.parseInt(br.readLine()));
            arr[p1.get(i)]=true;
        }
        for(int i=1;i<arr.length;i++)
            if(!arr[i])
                p2.add(i);
        Collections.sort(p2);
        TreeSet<Integer> topHalf=new TreeSet<Integer>();
        TreeSet<Integer> botHalf=new TreeSet<Integer>();
        for(int i=0;i<p2.size();i++){
            if(i<p2.size()/2)
                botHalf.add(p2.get(i));
            else
                topHalf.add(p2.get(i));
        }
        int points=0;
        //first half
        for(int i=0;i<p1.size()/2;i++){
            int cur=p1.get(i);
            Integer result=topHalf.ceiling(cur);
            if(result==null)
                topHalf.pollFirst();
            else{
                points++;
                topHalf.remove(result);
            }

        }

        //second half
        for(int i=p1.size()/2;i<p1.size();i++){
            int cur=p1.get(i);
            Integer result=botHalf.floor(cur);
            if(result==null)
                botHalf.pollLast();
            else{
                points++;
                botHalf.remove(result);
            }

        }

        pw.println(points);
        pw.close();
    }
}
