import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
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
//        BufferedReader br = new BufferedReader(new FileReader("Main.in.txt"));
        Scanner scan = new Scanner(System.in);
        int cases = scan.nextInt();
        for(int c=0;c<cases;c++){
            int m = scan.nextInt();
            ArrayList<Range> list = new ArrayList<>();
            while(true){
                int l = scan.nextInt();
                int r = scan.nextInt();
                if(l==0 && r==0)
                    break;
                if(l<0)
                    l=0;
                if(r<0)
                    r=0;

                if(l==0 && r==0)
                    continue;
                list.add(new Range(l,r));
            }
            Collections.sort(list);
            Range start=new Range(0,0);
            for(Range e:list){
                if(e.l!=0)
                    break;
                else{
                    if(e.r>start.r)
                        start=e;

                }
            }
            if(start.r==0){
                System.out.println(0);
                System.out.println();
                continue;
            }
            ArrayList<Range> goodList = new ArrayList<>();
            goodList.add(start);
            while(true) {
                int oldRight = start.r;
                int bestRight = start.r;
                Range best = null;
                for (Range cur : list) {
                    if (cur.l > start.r)
                        break;

                    if(bestRight<cur.r){
                        bestRight=cur.r;
                        best = cur;
                    }

                }
                if(best!=null)
                goodList.add(best);
                if(bestRight>=m) {
                    System.out.println(goodList.size());
                    for(Range e:goodList)
                        System.out.println(e);
                    System.out.println();
                    break;
                }

                if (oldRight == bestRight) {
                    System.out.println(0);
                    System.out.println();
                    break;
                }
                start = new Range(0,bestRight);
            }
        }
    }
}