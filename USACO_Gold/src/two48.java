import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class two48{
    static int mod1=10007;
    static int mod2=1;
    static int[] modPows=new int[248];
    public static int hash(int[] in,int mod){
        int hash=0;
        for(int i=0;i<in.length;i++){
            hash+=in[i]*modPows[i];
            hash%=mod;
        }
        return hash;
    }

    public static short maxVal(int[] in){
        ArrayList<Integer> locs=new ArrayList<Integer>();
        int hashVal=hash(in,mod1);
        if(dp[hashVal]!=0)
            return dp[hashVal];
        for(int i=0;i<in.length-1;i++){
            if(in[i]==in[i+1])
                locs.add(i);
        }
        if(locs.size()==0){
            return dp[hashVal]= (short) Arrays.stream(in).max().getAsInt();
        }
        else{
            int max=0;
            for(int cur:locs){
                int[] newArr=new int[in.length-1];
                int index=0;
                for(int i=0;i<in.length;i++){
                    newArr[index]=in[i];
                    if(i==cur){
                        newArr[index]++;
                        i++;
                    }
                    index++;
                }
                max=Math.max(max,maxVal(newArr));
            }
            return dp[hashVal]=(short)max;



        }

//        return 0;
    }
    static short[] dp;
    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        modPows[0]=1;
        for(int i=1;i<modPows.length;i++){
            modPows[i]=(modPows[i-1]*40)%mod1;
        }
        BufferedReader br = new BufferedReader(new FileReader("248.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
        int len=Integer.parseInt(br.readLine());
        int[] list=new int[len];
        for(int i=0;i<len;i++)
            list[i]=Integer.parseInt(br.readLine());
        dp=new short[mod1];

        pw.println(maxVal(list));
        pw.close();
    }
}
