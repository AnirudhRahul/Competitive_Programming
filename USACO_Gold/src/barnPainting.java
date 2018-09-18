import java.io.*;
import java.util.*;

/**
 * Created by user on 9/2/2018.
 */
public class barnPainting{


    static long[][] dp;
    final static long mod=(10*10*10)*(10*10*10)*(10*10*10)+7;
    public static long recurse(int vertex,int color){
        if(dp[vertex][color]!=-1)
            return dp[vertex][color];
        if(edges.get(vertex).size()==0)
            return dp[vertex][color]=1;
        long mult=1;
        HashSet<Integer> down=edges.get(vertex);
        for(Integer e:down){
            edges.get(e).remove(vertex);
        }
        for(int e:down){
            long sum=0;
            for(int k=0;k<3;k++)
                if(k!=color)
                    sum+=recurse(e,k);
            mult*=sum;
            mult%=mod;
        }


        return dp[vertex][color]=mult;
    }
    static HashMap<Integer,HashSet<Integer>> edges=new HashMap<>();
    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("barnpainting.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int barns=Integer.parseInt(stringTokenizer.nextToken());
        dp=new long[barns+1][3];
        for(long[] e:dp)
            Arrays.fill(e,-1);
        int colored=Integer.parseInt(stringTokenizer.nextToken());
        for(int i=1;i<=barns;i++)
            edges.put(i,new HashSet<>());
        for(int i=0;i<barns-1;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(tokenizer.nextToken());
            int end=Integer.parseInt(tokenizer.nextToken());
            edges.get(start).add(end);
            edges.get(end).add(start);
        }

        for(int i=0;i<colored;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int barn=Integer.parseInt(tokenizer.nextToken());
            int color=Integer.parseInt(tokenizer.nextToken())-1;
            for(int k=0;k<3;k++)
                if(color!=k)
                    dp[barn][k]=0;
        }
//        for(long[] e:dp)
//            System.out.println(Arrays.toString(e));
//        System.out.println(recurse(1,0)+recurse(1,1)+recurse(1,2));
//        for(long[] e:dp)
//            System.out.println(Arrays.toString(e));
        pw.println((recurse(1,0)+recurse(1,1)+recurse(1,2))%mod);
        pw.close();
    }
}
