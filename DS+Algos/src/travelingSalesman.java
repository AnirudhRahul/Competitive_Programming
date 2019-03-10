import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by user on 3/10/2019.
 */
public class travelingSalesman {
    static int start=0;
    static int end;
    static int vertices;
    static int[][] adj;
    static int[][] dp;
    static int[][] prev;
    static  int tsp(int prev, int state) {
        if(state==end)
            return adj[prev][start];
        int bestOption = Integer.MAX_VALUE;
        for(int i=0;i<vertices;i++){
            int mask=1<<i;
            if((mask&state)==0){
              int option=  adj[prev][i]+tsp(i,state+mask);
                bestOption=Math.min(bestOption,option);
            }
        }
        return bestOption;

    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int cases = Integer.parseInt(scan.next());
        for(int c=1;c<=cases;c++){
            vertices = Integer.parseInt(scan.next());
            dp = new int[vertices][1<<vertices];
            prev = new int[vertices][1<<vertices];
            end = (1<<vertices) - 1;
//            System.out.println(Integer.toBinaryString(end));
            adj = new int[vertices][vertices];
            for(int i=0;i<vertices;i++){
                for(int j=0;j<vertices;j++){
                    adj[i][j]=Integer.parseInt(scan.next());
                }
            }
//            for(int[] e:adj)
//                System.out.println(Arrays.toString(e));
            System.out.println(tsp(start,1<<start));

        }
    }
}
