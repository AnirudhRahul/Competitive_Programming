import java.io.*;
import java.util.*;

public class lasersAndMirrors{
    static int endX,endY;
    static class Point{
        int x,y,dist;
        public Point(int x, int y, int dist){
            this.x=x;this.y=y;this.dist=dist;
        }
    }
    static class Line{
        int location;
        boolean vertical;
        int dist;
        public Line(int location, boolean vertical, int dist){
            this.location=location;
            this.vertical=vertical;
            this.dist=dist;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("lasers.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));

        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int posts=Integer.parseInt(stringTokenizer.nextToken());
        int startX=Integer.parseInt(stringTokenizer.nextToken());
        int startY=Integer.parseInt(stringTokenizer.nextToken());
        endX=Integer.parseInt(stringTokenizer.nextToken());
        endY=Integer.parseInt(stringTokenizer.nextToken());
        //Y coordinates to X coordinates
        HashMap<Integer,ArrayList<Integer>> horizontalMap=new HashMap<>();
        //X coordinates to Y coordinates
        HashMap<Integer,ArrayList<Integer>> verticalMap=new HashMap<>();

        HashMap<Integer,Integer> verticalLines=new HashMap<>();
        HashMap<Integer,Integer> horizontalLines=new HashMap<>();


        Point[] postList=new Point[posts+2];
        postList[0]=new Point(startX,startY,0);
        postList[1]=new Point(endX,endY,0);
        for(int i=2;i<posts+2;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            postList[i]=new Point(Integer.parseInt(tokenizer.nextToken()),Integer.parseInt(tokenizer.nextToken()),0);
        }

        for(int i=0;i<posts+2;i++){
            if(!horizontalMap.containsKey(postList[i].y))
                horizontalMap.put(postList[i].y,new ArrayList<>(100));
            if(!verticalMap.containsKey(postList[i].x))
                verticalMap.put(postList[i].x,new ArrayList<>(100));
            horizontalMap.get(postList[i].y).add(postList[i].x);
            verticalMap.get(postList[i].x).add(postList[i].y);

        }


        ArrayDeque<Line> arrayDequeLines=new ArrayDeque<>();
        arrayDequeLines.add(new Line(startX,true,1));
        arrayDequeLines.add(new Line(startY,false,1));

        int counter=0;
        int distFin=2;
        //Do a bfs with lines instead of points
        while(!arrayDequeLines.isEmpty()){
            if(counter>=verticalMap.size()+horizontalMap.size())
                break;

            Line cur=arrayDequeLines.pollFirst();
            if(cur.vertical&&cur.location==endX)
                distFin=cur.dist;
            else if(!cur.vertical&&cur.location==endY)
                distFin=cur.dist;

            if(cur.vertical&&verticalLines.containsKey(cur.location))
                continue;
            if(!cur.vertical&&horizontalLines.containsKey(cur.location))
                continue;

            if(cur.vertical) {
                verticalLines.put(cur.location, cur.dist);
                for(int e:verticalMap.get(cur.location))
                    arrayDequeLines.add(new Line(e,false,cur.dist+1));
            }
            if(!cur.vertical) {
                horizontalLines.put(cur.location, cur.dist);
                for(int e:horizontalMap.get(cur.location))
                    arrayDequeLines.add(new Line(e,true,cur.dist+1));
            }
            counter++;
        }

        pw.println(distFin-3);
        pw.close();
    }
}
