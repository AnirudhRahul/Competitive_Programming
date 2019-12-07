package Contest2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by user on 12/1/2018.
 */
public class slayer {
    //Reorder the numbers like this so we can sort the moves chronologically
    //block 2-->0
    //charge 3-->1
    //monster 4-->2
    //attack 1-->4
    static class Move implements Comparable<Move>{
        int type,power,cost;

        public Move(int type,int power, int cost){
            if(type==2)
                this.type=0;
            if(type==3)
                this.type=1;
            if(type==1)
                this.type=4;
            if(type==4)
                this.type=2;
            this.power=power;
            this.cost=cost;
        }

        public boolean isBlock(){return type==0;}
        public boolean isCharge(){return type==1;}
        public boolean isMonster(){return type==2;}
        public boolean isAttack(){return  type==4;}
        @Override
        public int compareTo(Move o) {
            return type-o.type;
        }
        public String toString(){return ""+type;}
    }
    /*
    Format is:
    dp[health][index][energy]
    Actual value in array is the maximum amount we can reduce monster health in the given state
    */
    static boolean killed;
    static ArrayList<Move> moveList;
    static int[][][] dp;
    //Returns max amount we can decrease monster health so we want to MAXIMIZE this
    //Essentially just simulates taking a move or not taking it
    //Uses tail recursion to see if we killed the monster before we died
    public static void recurse(int curHealth,int index, int energy, int monsterHP){
        //Checks if we killed the monster
        if(monsterHP<=0)
            killed=true;
        //Finished iterating through all the moves
        if(index==moveList.size())
            return;
        //No energy means no more moves possible
        if(energy==0)
            return;
        //Can kill all recursive instances if we already killed the monster
        if(killed)
            return;
        //Check if we already computed this state
        if(dp[curHealth][index][energy]>monsterHP)
            return;
        else
            dp[curHealth][index][energy]=monsterHP;

        Move cur=moveList.get(index);

        //Mark this state as visited

        int newEnergy=energy-cur.cost;
        //If we can't take this move just go onto the next move
        if(newEnergy<0)
            recurse(curHealth,index+1,energy,monsterHP);
        else if(cur.isBlock()){
            int newHealth=curHealth+cur.power;
            //if you have 101 or more health the monster can't kill you
            newHealth=Math.min(101,newHealth);
            //Tries taking the block or not taking it
            recurse(curHealth,index+1,energy,monsterHP);
            recurse(newHealth,index+1,newEnergy,monsterHP);
        }
        else if(cur.isMonster()){
            int newHealth=curHealth-cur.power;
            //We definitely want to exclude this path of options if it results in us dying
            if(newHealth>0)
            recurse(newHealth,index+1,energy,monsterHP);
        }
        else if(cur.isCharge()||cur.isAttack()){
            //Picks the better option out of taking the move or not taking it
            recurse(curHealth,index+1,energy,monsterHP);
            recurse(curHealth,index+1,newEnergy,monsterHP-cur.power);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());

        for(int c=1;c<=cases;c++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int startHealth=Integer.parseInt(tokenizer.nextToken());
            int moves=Integer.parseInt(tokenizer.nextToken());
            int startEnergy=Integer.parseInt(tokenizer.nextToken());
            int monsterHealth=Integer.parseInt(tokenizer.nextToken());
            int monsterAttack=Integer.parseInt(tokenizer.nextToken());
            //Make sure to re-initialize static variables in each case
            dp=new int[102][101][101];
            moveList=new ArrayList<>();
            killed=false;
            for(int i=0;i<moves;i++){
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(stringTokenizer.nextToken());
            int b=Integer.parseInt(stringTokenizer.nextToken());
            int cc=Integer.parseInt(stringTokenizer.nextToken());
            moveList.add(new Move(a,b,cc));
            }
            //Make the monsters attack a move
            moveList.add(new Move(4,monsterAttack,0));
            Collections.sort(moveList);

            recurse(startHealth,0,startEnergy,monsterHealth);
            System.out.println("Fight #"+c+": "+(killed?"Win":"Lose"));
        }

    }
}
