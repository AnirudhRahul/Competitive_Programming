import java.math.BigInteger;

/**
 * Created by user on 10/28/2018.
 */
public class stringHashing {
    static final int MOD=Integer.MAX_VALUE;
    private long[] modPows;
    private long[] inversePows;
    private long[] hash;
    //Hashes the string as a base 26 number
    public stringHashing(String in){
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
    }

    //Hashes the substring str.substring(l,r)
    public long hashSubstring(int l, int r){
        long val=hash[r]-hash[l];
        return Math.floorMod(val*inversePows[l],MOD);
    }

}
