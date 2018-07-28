import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by user on 7/28/2018.
 */
public class fencedInPlat {
    static class Edges implements Comparable<Edges>{
        long length;
        //does the set of edges run vertically
        boolean isVertical;
        int index;
        public Edges(long length,int index, boolean isVertical){
            this.length=length;
            this.index=index;
            this.isVertical=isVertical;
        }

        @Override
        public int compareTo(Edges o) {
            long val=length-o.length;
            if(val>0)
                return 1;
            else if(val<0)
                return -1;
            else
                return 0;
        }
        public String toString(){return ""+length;}
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fencedin.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        long maxX=Long.parseLong(tokenizer.nextToken());
        long maxY=Long.parseLong(tokenizer.nextToken());
        int cols=Integer.parseInt(tokenizer.nextToken())+1;
        int rows=Integer.parseInt(tokenizer.nextToken())+1;
        ArrayList<Edges> list=new ArrayList<Edges>();
        ArrayList<Long> vertical=new ArrayList<>(cols+2);
        ArrayList<Long> horizontal=new ArrayList<>(rows+2);

        vertical.add(0L);
        vertical.add(maxX);
        horizontal.add(0L);
        horizontal.add(maxY);

        for(int i=0;i<cols-1;i++)
            vertical.add(Long.parseLong(br.readLine()));
        for(int i=0;i<rows-1;i++)
            horizontal.add(Long.parseLong(br.readLine()));
        Collections.sort(vertical);
        Collections.sort(horizontal);

        for(int i=1;i<vertical.size();i++){
            long len=vertical.get(i)-vertical.get(i-1);
            list.add(new Edges(len,i-1,true));

        }
        for(int i=1;i<horizontal.size();i++){
            long len=horizontal.get(i)-horizontal.get(i-1);
            list.add(new Edges(len,i-1,false));
        }
        Collections.sort(list);
//        System.out.println(list);
        ArrayList<Edges> mustAdd=new ArrayList<>();
        Edges first=list.remove(0);
        mustAdd.add(first);
        for(int i=0;i<list.size();i++)
            if(first.isVertical^list.get(i).isVertical){
                mustAdd.add(list.remove(i));
                break;
            }
//        System.out.println(list);

//        long edgesAdded=rows-1+cols-1;
        long dist=0;
        for(Edges e:mustAdd) {
            dist += (e.length * (e.isVertical ? (rows - 1) : (cols - 1)));
        }
//        System.out.println(dist);
        int vLines=1;
        int hLines=1;
        for(int i=0;i<list.size();i++){
            if(hLines==rows||vLines==cols)break;

            Edges cur=list.get(i);
            if(cur.isVertical){
                int edgesToAdd=rows-1-(hLines-1);
                dist+=edgesToAdd*cur.length;
                vLines++;
            }
            else{
                int edgesToAdd=cols-1-(vLines-1);
                dist+=edgesToAdd*cur.length;
                hLines++;
            }
//            System.out.println(dist);

        }
        pw.println(dist);
        pw.close();
//        System.out.println(dist);
    }
}
