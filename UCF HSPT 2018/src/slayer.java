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
    //Lots and lots of DP
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
    //Health
    //Index
    //Energy
    //Actual value in array is monester health
    static boolean killed;
    static ArrayList<Move> moveList;
    static int[][][] dp;
    //Returns max amount we can decrease monster health
    //Used tail recursion to see if we killed the monster before we died
    public static int recurse(int curHealth,int index, int energy, int val){
        if(index==moveList.size())
            return 0;
        if(energy==0)
            return 0;
        if(val<=0)
            killed=true;
        if(killed)
            return 0;
        if(dp[curHealth][index][energy]!=0)
            return dp[curHealth][index][energy];

        Move cur=moveList.get(index);
        int newEnergy=energy-cur.cost;
        if(newEnergy<0)
            return dp[curHealth][index][energy]=recurse(curHealth,index+1,energy,val);
        if(cur.isBlock()){
            int newHealth=curHealth+cur.power;
            if(newHealth>101)
                newHealth=101;
            return dp[curHealth][index][energy]=Math.max(recurse(curHealth,index+1,energy,val),recurse(newHealth,index+1,newEnergy,val));
        }
        if(cur.isMonster()){
            int newHealth=curHealth-cur.power;
            if(newHealth<=0)
                return dp[curHealth][index][energy]=Integer.MIN_VALUE/200;
            else
                return dp[curHealth][index][energy]=recurse(newHealth,index+1,energy,val);
        }
        if(cur.isCharge()){
            return dp[curHealth][index][energy]=Math.max(recurse(curHealth,index+1,energy,val),cur.power+recurse(curHealth,index+1,newEnergy,val-cur.power));
        }
        if(cur.isAttack()){
            return dp[curHealth][index][energy]=Math.max(recurse(curHealth,index+1,energy,val),cur.power+recurse(curHealth,index+1,newEnergy,val-cur.power));
        }


        return 0;

    }
    //block 2-->0
    //charge 3-->1

    //attack 1-->4
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
            moveList.add(new Move(4,monsterAttack,0));
            Collections.sort(moveList);
//            System.out.println(moveList);
            int result=recurse(startHealth,0,startEnergy,monsterHealth);
            int delta=monsterHealth-result;
            System.out.println("Fight #"+c+": "+(delta<=0||killed?"Win":"Lose"));
        }

    }
}
