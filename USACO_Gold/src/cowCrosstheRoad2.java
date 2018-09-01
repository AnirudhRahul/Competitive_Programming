import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class cowCrosstheRoad2{
    static int[][] dp;
    static int[][] road;
    static int len;
    public static int maxRoads(int index1,int index2){
        if(dp[index1][index2]!=-1)
            return dp[index1][index2];
        boolean line=Math.abs(road[index1][0]-road[index2][1])<=4;

        if(index1==len-1&&index2==len-1)
            return dp[index1][index2]=line?1:0;
        else if(index1==len-1)
            return dp[index1][index2]=line?1:maxRoads(index1,index2+1);
        else if(index2==len-1)
            return dp[index1][index2]=line?1:maxRoads(index1+1,index2);

        if(line){
            return 1+maxRoads(index1+1,index2+1);
        }
        else
            return dp[index1][index2]=Math.max(maxRoads(index1+1,index2),maxRoads(index1,index2+1));
    }
    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("nocross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
        len=Integer.parseInt(br.readLine());
        road=new int[len][2];
        for(int j=0;j<2;j++)
        for(int i=0;i<road.length;i++){
                road[i][j]=Integer.parseInt(br.readLine());
            }
        for(int[] e:road){
            System.out.println(Arrays.toString(e));
        }
        dp=new int[len][len];
        for(int i=0;i<len;i++)
            for(int j=0;j<len;j++)
                dp[i][j]=-1;

        pw.println(maxRoads(0,0));
        pw.close();
    }
}
