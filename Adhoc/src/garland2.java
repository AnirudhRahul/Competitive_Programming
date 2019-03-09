import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class garland2 {
    static int[][] dp;
    static String in;
    static String letters="RGB";
    public static int recurse(int index, int prevLetter){
        if(index==in.length())
            return 0;
        if(prevLetter>=0&&dp[index][prevLetter]!=-1)
            return dp[index][prevLetter];

        int bestOption=Integer.MAX_VALUE;
        int curLetter=letters.indexOf(in.charAt(index));
//        int bestColor=-1;
        for(int i=0;i<3;i++){
            if(prevLetter==i)
                continue;
            int option=recurse(index+1,i);
            if(curLetter!=i)
                option++;
            if(option<bestOption) {
                bestOption = option;
//                bestColor=i;
            }
        }

        if(prevLetter>=0)
            return dp[index][prevLetter]=bestOption;
        else
            return bestOption;
    }

    public static int bestColor(int index, int prevLetter){
        int bestColor=-1;
        int curLetter=letters.indexOf(in.charAt(index));
        int bestOption=Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            if(prevLetter==i)
                continue;
            int option=recurse(index+1,i);
            if(curLetter!=i)
                option++;
            if(option<bestOption) {
                bestOption = option;
                bestColor=i;
            }
        }
        return bestColor;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len=Integer.parseInt(br.readLine());
        in = br.readLine();
        dp = new int[in.length()][3];
        for(int i=0;i<dp.length;i++)
            for(int j=0;j<dp[0].length;j++)
                dp[i][j]=-1;
//        optimal = new int[in.length()];
        String best="";
        int minDiff=Integer.MAX_VALUE;

        System.out.println(recurse(0,-1));


        int prevColor=-1;
        ArrayList<Integer> optimal=new ArrayList<>();
        for(int i=0;i<in.length();i++){
            prevColor=bestColor(i,prevColor);
            optimal.add(prevColor);
        }

        for(int e:optimal)
        System.out.print(letters.charAt(e));
    }
}
