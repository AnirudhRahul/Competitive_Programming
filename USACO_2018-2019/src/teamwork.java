import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by user on 12/16/2018.
 */
public class teamwork {
    static int len,maxTeamLen;
    static int[] skill;
    static int[][] dp;
    public static int dp(int index, int teamLen, int maxSkill){
        if(index==len)
            return dp[index][teamLen]=0;
        if(teamLen==maxTeamLen){
            return dp[index][teamLen]=skill[index]+dp(index+1,1,skill[index]);
        }
        if(dp[index][teamLen]!=0)
            return dp[index][teamLen];

        int newTeam=skill[index]+dp(index+1,1,skill[index]);
        int stay=0;
        if(skill[index]>maxSkill){
            int diff=skill[index]-maxSkill;
            stay=skill[index]+diff*teamLen+dp(index+1,teamLen+1,skill[index]);
        }
        else
            stay=maxSkill+dp(index+1,teamLen+1,maxSkill);
        return dp[index][teamLen]=Math.max(stay,newTeam);
    }

    public static void main(String[] args) throws IOException {
        String name = "teamwork";
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(name + ".out")));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        len=Integer.parseInt(tokenizer.nextToken());
        maxTeamLen=Integer.parseInt(tokenizer.nextToken());
        skill=new int[len];
        for(int i=0;i<len;i++)
            skill[i]=Integer.parseInt(br.readLine());
        dp=new int[len+1][maxTeamLen+1];
        pw.println(dp(0,0,0));
        pw.close();
    }
}
