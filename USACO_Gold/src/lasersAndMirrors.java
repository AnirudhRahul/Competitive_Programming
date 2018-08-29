import java.io.*;
import java.util.*;

public class lasersAndMirrors{
    static int endX,endY;
    static class State{
        int x,y,dist;
        public State(int x, int y, int dist){
            this.x=x;this.y=y;this.dist=dist;
        }

        private boolean isEnd(){
            return x==endX&&y==endY;
        }
        public String toString(){return x+" "+y;}
    }

    static class AdjacencyList{
        HashMap<Integer,HashMap<Integer,Boolean>> vertical;
        HashMap<Integer,HashMap<Integer,Boolean>> horizontal;
        public AdjacencyList(State[] in){
            vertical=new HashMap<>();
            horizontal=new HashMap<>();
            for(State e:in){
                int x=e.x;
                int y=e.y;
                if(!vertical.containsKey(x))
                    vertical.put(x,new HashMap<>());
                vertical.get(x).put(y,false);
                if(!horizontal.containsKey(y))
                    horizontal.put(y,new HashMap<>());
                horizontal.get(y).put(x,false);
            }
        }

        public ArrayDeque<State> getAdjacent(State in){
            ArrayDeque<State> arrayDeque=new ArrayDeque<>();
            HashMap<Integer,Boolean> verticalMap=vertical.get(in.x);
            HashMap<Integer,Boolean> horizontalMap=horizontal.get(in.y);
            for(int e:verticalMap.keySet()){
                if(!verticalMap.get(e))
                    arrayDeque.addFirst(new State(in.x,e,in.dist+1));
            }
            for(int e:horizontalMap.keySet()){
                if(!horizontalMap.get(e))
                    arrayDeque.addFirst(new State(e,in.y,in.dist+1));
            }
            return arrayDeque;
        }

        public void visit(State in){
            vertical.get(in.x).put(in.y,true);
            horizontal.get(in.y).put(in.x,true);
        }

        public boolean isVisited(State in){
            return vertical.get(in.x).get(in.y)&&horizontal.get(in.y).get(in.x);
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

        HashMap<Integer,ArrayList<Integer>> horizontalMap=new HashMap<>();
        HashMap<Integer,ArrayList<Integer>> verticalMap=new HashMap<>();

        HashMap<Integer,Integer> verticalLines=new HashMap<>();
        HashMap<Integer,Integer> horizontalLines=new HashMap<>();


        State[] postList=new State[posts+2];
        postList[0]=new State(startX,startY,0);
        postList[1]=new State(endX,endY,0);
        for(int i=2;i<posts+2;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            postList[i]=new State(Integer.parseInt(tokenizer.nextToken()),Integer.parseInt(tokenizer.nextToken()),0);
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
//        horizontalLines.put(startY,1);
//        verticalLines.put(startX,1);
        int counter=0;
        int distFin=-1;
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
//        System.out.println(distFin);
//        AdjacencyList adjacencyList=new AdjacencyList(postList);
//        if(adjacencyList.horizontal.get(endY).size()==1&&adjacencyList.vertical.get(endX).size()==1){
//            System.out.println(-1);
//            return;
//        }
//        System.out.println(adjacencyList.horizontal);
//        System.out.println(adjacencyList.vertical);
//        System.out.println(adjacencyList.getAdjacent(new State(0,0,0)));
//        int distFin=-1;
//        while(!arrayDeque.isEmpty()){
//            State cur=arrayDeque.pollFirst();
//            if(cur.isEnd()){
//                distFin=cur.dist;
//                break;
//            }
//            if(cur.x==endX||cur.y==endY){
//                distFin=cur.dist+1;
//                break;
//            }
//            if(adjacencyList.isVisited(cur))
//                continue;
//            adjacencyList.visit(cur);
//            arrayDeque.addAll(adjacencyList.getAdjacent(cur));
//        }
        pw.println(distFin-3);
        pw.close();
//        System.out.println(distFin-1);
    }
}
