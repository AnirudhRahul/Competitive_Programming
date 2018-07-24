import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 7/24/2018.
 */
public class radioContact {
    static class Point{
        long x,y;
        public Point(long x, long y){
            this.x=x;
            this.y=y;
        }

        private long dist(Point a){
            return (x-a.x)*(x-a.x)+(y-a.y)*(y-a.y);
        }
        private Point move(char in){
            if(in=='N')
                return new Point(x,y+1);
            if(in=='S')
                return new Point(x,y-1);
            if(in=='E')
                return new Point(x+1,y);
            if(in=='W')
                return new Point(x-1,y);
            return null;
        }
        public String toString(){
            return x+" "+y;
        }
    }
    public static long min(long a, long b, long c){return Math.min(Math.min(a,b),c);}
    public static long dist(int index1,int index2){return locations1[index1].dist(locations2[index2]);}
    static long[][] memo;
    public static long findMinPower(int index1, int index2){
        if(memo[index1][index2]!=-1)
            return memo[index1][index2];

        if(index1==locations1.length-1&&index2==locations2.length-1)
            return memo[index1][index2]=0;
        else if(index1==locations1.length-1){
            return memo[index1][index2]=findMinPower(index1,index2+1)+dist(index1,index2+1);
        }
        else if(index2==locations2.length-1){
            return memo[index1][index2]=findMinPower(index1+1,index2)+dist(index1+1,index2);
        }
        else{
            long a=findMinPower(index1+1,index2)+dist(index1+1,index2);
            long b=findMinPower(index1+1,index2+1)+dist(index1+1,index2+1);
            long c=findMinPower(index1,index2+1)+dist(index1,index2+1);
            return memo[index1][index2]=min(a,b,c);
        }
    }
    static Point[] locations1;
    static Point[] locations2;
    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("radio.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int len1=Integer.parseInt(tokenizer.nextToken());
        int len2=Integer.parseInt(tokenizer.nextToken());
        memo=new long[len1+1][len2+1];
        tokenizer=new StringTokenizer(br.readLine());
        Point start1=new Point(Integer.parseInt(tokenizer.nextToken()),Integer.parseInt(tokenizer.nextToken()));
        tokenizer=new StringTokenizer(br.readLine());
        Point start2=new Point(Integer.parseInt(tokenizer.nextToken()),Integer.parseInt(tokenizer.nextToken()));
        char[] moves1=br.readLine().toCharArray();
        char[] moves2=br.readLine().toCharArray();
        locations1=new Point[len1+1];
        locations2=new Point[len2+1];
        locations1[0]=start1;
        locations2[0]=start2;
        for(int i=1;i<locations1.length;i++)
            locations1[i]=locations1[i-1].move(moves1[i-1]);
        for(int i=1;i<locations2.length;i++)
            locations2[i]=locations2[i-1].move(moves2[i-1]);
//        System.out.println(Arrays.toString(locations1));
//        System.out.println(Arrays.toString(locations2));
        for(long[] e:memo)
            Arrays.fill(e,-1);
        pw.println(findMinPower(0,0));
        pw.close();
//        System.out.println(findMinPower(0,0));

    }
}
