import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by user on 7/25/2018.
 */
//complete meme solution
public class lightsOut {
    public static int findDiscrepancy(int start, int fake){
        int dist=0;
        while(true){
            //Bessie can tell if she reached the exit
            if(fake%points==0||start%points==0)
                break;
            //Bessie can tell what angle she is at
            if(list[fake].angle!=list[start].angle)
                break;
            //edge after the point at fake
            int edgeFake=Math.abs(list[fake].y-list[(fake+1)%points].y)+Math.abs(list[fake].x-list[(fake+1)%points].x);
            //edge after the point at start
            int edgeStart=Math.abs(list[start].y-list[(start+1)%points].y)+Math.abs(list[start].x-list[(start+1)%points].x);
            dist+=edgeStart;
            //move pointers forward
            fake++;
            start++;

            if(edgeFake!=edgeStart)
                break;

        }
        //return distance traveled plus the distance you have left to travel
        return dist+shortestPath[start%points];
    }
    static class Point{
        int x,y;
        int angle=0;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        private int dist(Point a){
            return Math.abs(x-a.x)+Math.abs(y-a.y);
        }
        public String toString(){return ""+angle;}
    }
    static class Edge{
        int deltaX,deltaY;
        public Edge(Point a, Point b){
            deltaX=b.x-a.x;
            deltaY=b.y-a.y;
        }
        public int cross(Edge in){
            return deltaX*in.deltaY-deltaY*in.deltaX;
        }
    }
    static int points;
    static Point[] list;
    static int[] shortestPath;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lightsout.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightsout.out")));

        points=Integer.parseInt(br.readLine());
        list=new Point[points];
        //length of the shortest path from a point to the exit
        shortestPath=new int[points];

        for(int i=0;i<points;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            list[i]=new Point(Integer.parseInt(tokenizer.nextToken()),Integer.parseInt(tokenizer.nextToken()));
        }

        int perimeter=0;
        for(int i=0;i<points;i++){
            //point behind i
            int index0=(points+i-1)%points;
            //point at i
            int index1=i;
            //point ahead of i
            int index2=(i+1)%points;
            //edge behind i
            Edge edge1=new Edge(list[index0],list[index1]);
            //edge in front of i
            Edge edge2=new Edge(list[index1],list[index2]);

            //use a cross product to find the angle of the turn
            if(edge1.cross(edge2)<0)
                list[i].angle=90;
            else
                list[i].angle=270;

            //calculating perimeter
            perimeter+=list[index1].dist(list[index2]);
        }
        int sum=0;
        for(int i=0;i<points;i++){
            shortestPath[i]=Math.min(sum,perimeter-sum);
            sum+=list[i].dist(list[(i+1)%points]);
        }


        int worstCase=0;
        for(int index=0;index<points;index++){
            int start=index;
            for(int j=0;j<points;j++){
                if(j==start)continue;
                //test every point for where you could possibly be starting
                int result=findDiscrepancy(start,j);
                //find the difference in time it causes
                int delta=result-shortestPath[index];
                worstCase=Math.max(delta,worstCase);

            }

        }
        pw.println(worstCase);
        pw.close();
    }
}
