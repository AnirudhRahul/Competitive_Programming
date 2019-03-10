import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Created by user on 3/10/2019.
 */
public class cyclicPermutations {

    static class HashWindow{
        long curHash;
        long[] modPows;
        long MOD= (long) (1e9+7);
        long INVERSE;
        String text;
        int l,r;
        public HashWindow(String in){
            l=0;r=0;
            text=in;
            INVERSE = BigInteger.valueOf(3).modInverse(BigInteger.valueOf(MOD)).longValueExact();
//            System.out.println(INVERSE);
            modPows = new long[in.length()];
            modPows[0]=1;
            for(int i=1;i<modPows.length;i++)
                modPows[i]=(modPows[i-1]*3)%MOD;

            curHash=0;
        }

        public long getHash(){return curHash;}

        public void addRight(){
            curHash+= (text.charAt(r)-'0'+1) * modPows[r-l];
            curHash%=MOD;
            r++;
        }

        public void removeLeft(){
            curHash-=(text.charAt(l)-'0'+1);
            curHash=Math.floorMod(curHash, MOD);
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=1;c<=cases;c++){
            String a = br.readLine();
            String b = br.readLine();
            HashWindow windowA = new HashWindow(a);
            windowA.addRight(a.length());
            long checkHash = windowA.getHash();
            int matches = 0;
            HashWindow windowB = new HashWindow(b+b);
            windowB.addRight(b.length());
            long hash = windowB.getHash();
            if(hash==checkHash)
                matches++;

            for(int i=1;i<a.length();i++){
                windowB.addRight();
                windowB.removeLeft();
                if(windowB.getHash()==checkHash)
                    matches++;
//                System.out.println(windowB.getHash());
            }
            System.out.println(matches);
        }
    }
}
