import jdk.nashorn.internal.runtime.regexp.joni.BitSet;

import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Created by user on 3/10/2019.
 */
public class stringHashWindow {
    //Good Primes:
    //10^9+7,10^9+9,10^9+23
    //10^12+39
    //2^64-19
    static class HashWindow{
        String in;
        long curHash;
        long[] modPows;
        long MOD= (long) (1e9+7);
        long INVERSE;
        String text;
        int l,r;
        public HashWindow(String in){
            l=0;r=0;
            text=in;
            INVERSE = BigInteger.valueOf(27).modInverse(BigInteger.valueOf(MOD)).longValueExact();
            modPows = new long[in.length()];
            modPows[0]=1;
            for(int i=1;i<modPows.length;i++)
                modPows[i]=(modPows[i-1]*27)%MOD;

            curHash=0;
        }

        public long getHash(){return curHash;}

        public void addRight(){
            curHash+=(in.charAt(r)-'A'+1)*modPows[r-l];
            curHash%=MOD;
            r++;
        }

        public void removeLeft(){
            curHash-=(in.charAt(l)-'A'+1)-MOD;
            curHash*=INVERSE;
            curHash%=MOD;
            l++;
        }

        public void addRight(int len){
            while (len-->0)
                addRight();
        }

        public void removeLeft(int len){
            while (len-->0)
                removeLeft();
        }

    }
}
