import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class cowChecklist {
    static class Point{
        int x,y;
        public Point(int x, int y){this.x=x;this.y=y;}
        public String toString(){return x+" "+y;}


        public boolean equals(Point obj) {
            return this.x==obj.x&&this.y==obj.y;
        }
    }
    public static int dist(Point a, Point b){
        return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
    }
    static ArrayList<Point> holsteins;
    static ArrayList<Point> gurnsies;
    static int[][][] dp=new int[2][1001][1001];
    public static int minimizeDistance(int index1,int index2, boolean left){
        Point cur;
        int val=left?1:0;
        if(dp[val][index1][index2]!=0)
            return dp[val][index1][index2];
        if(left)
            cur=holsteins.get(index1-1);
        else
            cur=gurnsies.get(index2-1);
        boolean end1=holsteins.size()==index1;
        boolean end2=gurnsies.size()==index2;
        if(end1&&end2)
            return dp[val][index1][index2]=left?0:dist(cur,holsteins.get(index1-1));
        else if(end1)
            return dp[val][index1][index2]=dist(cur,gurnsies.get(index2))+distToFinish_G[index2];
        else if(end2)
            return dp[val][index1][index2]=dist(cur,holsteins.get(index1))+distToFinish_H[index1];
        else
        return dp[val][index1][index2]=Math.min(dist(cur,gurnsies.get(index2))+minimizeDistance(index1,index2+1,false),dist(cur,holsteins.get(index1))+minimizeDistance(index1+1,index2,true));
    }
    static int[] distToFinish_H;
    static int[] distToFinish_G;
    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("checklist.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));

        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int hols=Integer.parseInt(tokenizer.nextToken());
        int gurns=Integer.parseInt(tokenizer.nextToken());
        holsteins=new ArrayList<>(hols);
        gurnsies=new ArrayList<>(gurns);
        distToFinish_H=new int[hols];
        distToFinish_G=new int[gurns];

        for(int i=0;i<hols;i++){
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            Point toAdd=new Point(Integer.parseInt(stringTokenizer.nextToken()),Integer.parseInt(stringTokenizer.nextToken()));
            if(i==0)
                holsteins.add(toAdd);
            else if(holsteins.get(holsteins.size()-1).equals(toAdd))
                continue;
            else
                holsteins.add(toAdd);
        }
        for(int i=0;i<gurns;i++){
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            Point toAdd=new Point(Integer.parseInt(stringTokenizer.nextToken()),Integer.parseInt(stringTokenizer.nextToken()));
            if(i==0)
                gurnsies.add(toAdd);
            else if(gurnsies.get(gurnsies.size()-1).equals(toAdd))
                continue;
            else
                gurnsies.add(toAdd);

        }

        distToFinish_H[holsteins.size()-1]=0;
        for(int i=holsteins.size()-2;i>=0;i--){
            distToFinish_H[i]=distToFinish_H[i+1]+dist(holsteins.get(i),holsteins.get(i+1));
        }
        distToFinish_G[gurnsies.size()-1]=dist(gurnsies.get(gurnsies.size()-1),holsteins.get(holsteins.size()-1));
        for(int i=gurnsies.size()-2;i>=0;i--){
            distToFinish_G[i]=distToFinish_G[i+1]+dist(gurnsies.get(i),gurnsies.get(i+1));
        }
//        System.out.println(Arrays.toString(distToFinish_H));
//        System.out.println(Arrays.toString(distToFinish_G));
        pw.println(minimizeDistance(1,0,true));
        pw.close();


    }
}
