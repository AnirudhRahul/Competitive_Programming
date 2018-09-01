import java.io.*;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 8/28/2018.
 */
public class bovineGenomics {
    final static int mod=20000003;
    //need mod inverses too
    static int[] modPows=new int[500];
//    static int[] modPowsInverse=new int[500];
    static class DNA{
        int[] hashValues;
        public DNA(char[] in){
            hashValues=new int[in.length];
            for(int i=0;i<in.length;i++){
                char cur=in[i];
                int val=0;
                if(cur=='A')
                    val=0;
                else if(cur=='C')
                    val=1;
                else if(cur=='G')
                    val=2;
                else if(cur=='T')
                    val=3;
                if(i==0)
                    hashValues[i]=val;
                else
                    hashValues[i]=(hashValues[i-1]+modPows[i]*val)%mod;
            }
        }
        //inclusive l to r
        private int hash(int l, int r){
            if(l==0)
                return hashValues[r];
            return (hashValues[r]-hashValues[l-1]+mod)%mod;
        }
    }
    static DNA[] spottedCows;
    static DNA[] normalCows;
    static int curID=2;
    public static boolean checkRange(int l,int r){
        curID++;
        for(DNA cow:spottedCows){
            seenHash[cow.hash(l,r)]=curID;
        }
        for(DNA cow:normalCows){

            if(seenHash[cow.hash(l, r)]==curID)
                return false;
        }
        return true;
    }
    static int[] seenHash=new int[mod];
    public static void main(String args[]) throws IOException {
        modPows[0]=1;
//        BigInteger bigInteger=BigInteger.valueOf(4);
//        int inverse=bigInteger.modInverse(BigInteger.valueOf(mod)).intValueExact();
//        modPowsInverse[0]=1;
        for(int i=1;i<modPows.length;i++)
            modPows[i]=(modPows[i-1]*4)%mod;

        BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int cows=Integer.parseInt(tokenizer.nextToken());
        int len=Integer.parseInt(tokenizer.nextToken());
        spottedCows=new DNA[cows];
        normalCows=new DNA[cows];
        for(int i=0;i<cows;i++)
            spottedCows[i]=new DNA(br.readLine().toCharArray());
        for(int i=0;i<cows;i++)
            normalCows[i]=new DNA(br.readLine().toCharArray());
        int[] minStart=new int[len];
        for(int start=0;start<len;start++){
            int low=0;
            int high=len-start;
            if(!checkRange(start,len-1)) {
                minStart[start] = -1;
                continue;
            }
            while(high-low>3){
                int mid=(low+high)/2;
                if(checkRange(start,start+mid)){
                    high=mid;
                }
                else
                    low=mid;
            }
            for(int i=low;i<=high;i++)
                if(checkRange(start,start+i)) {
                    minStart[start] = i;
                    break; }

        }
        System.out.println(Arrays.toString(minStart));
        int min=Integer.MAX_VALUE;
        for(int e:minStart){
            if(e>=0)
            min=Math.min(min,e);
        }
//        System.out.println(min+1);
        pw.println(min+1);
        pw.close();
    }
}
