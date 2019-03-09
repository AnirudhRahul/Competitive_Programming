import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by user on 10/28/2018.
 */
public class censorGold{
    static final int MOD=Integer.MAX_VALUE;
    static private long[] modPows;
    static private long[] inversePows;
    static private long[] hash;
    static long hashSubstring(int l, int r){
        long val=hash[r]-hash[l];
        return Math.floorMod(val*inversePows[l],MOD);
    }
    ArrayList<Character> list=new ArrayList<>();
    ArrayList<Integer> listHash=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String in=br.readLine();
        int len=Integer.parseInt(br.readLine());

        modPows=new long[in.length()];
        inversePows=new long[in.length()];
        hash=new long[in.length()+1];
        modPows[0]=1;
        for(int i=1;i<modPows.length;i++)
            modPows[i]=(modPows[i-1]*26)%MOD;
        inversePows[0]=1;
        inversePows[1]= BigInteger.valueOf(26).modInverse(BigInteger.valueOf(MOD)).longValueExact();
        for(int i=2;i<modPows.length;i++)
            inversePows[i]=(inversePows[i-1]*26)%MOD;

        hash[0]=0;
        for(int i=1;i<in.length()+1;i++)
            hash[i]=(hash[i-1]+modPows[i-1]*(in.charAt(i-1)-'a'))%MOD;

        HashMap<Integer, HashSet<Long>> lenHash=new HashMap<>();
        while(len-->0){
            String censor=br.readLine();
            if(lenHash.containsKey(censor.length()))
                lenHash.get(censor.length()).add(hash(censor))

        }


    }
}
