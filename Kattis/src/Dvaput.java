import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by user on 10/23/2018.
 */
public class Dvaput{
    private static final long MOD=(2147483647);
    static long[] hashes,modPows,inverse;
    static int len;
    static String in;
    public static long rangeHash(int l,int r){
        if(l==0)
            return hashes[r];
        else
            return Math.floorMod(((hashes[r]-hashes[l-1])*inverse[l]),MOD);
    }

    static class Pair{
        int a,b;
        public Pair(int a, int b){this.a=a;this.b=b;}
    }

    //returns true if there is a collision
    public static boolean testLen(int mid){
        HashMap<Long,Pair> set=new HashMap<>();
        for(int i=0;i+mid-1<len;i++){
            long hashVal=rangeHash(i,i+mid-1);
            if(!set.containsKey(hashVal))
            set.put(hashVal,new Pair(i,i+mid-1));
            else{
                boolean equal=true;
                int start1=i;
                int start2=set.get(hashVal).a;
                for(int k=0;k<mid;k++)
                    if(in.charAt(start1+k)!=in.charAt(start2+k)){
                        equal=false;
                        break;
                    }
                if(equal)
                    return true;
            }
        }
        return false;
//        System.out.println();
//        System.out.println(mid);
//        System.out.println(set);
    }
    public static void main(String args[]) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        len=Integer.parseInt(br.readLine());
        in=br.readLine();
        hashes=new long[len];
        modPows=new long[200001];
        inverse=new long[200001];
        modPows[0]=1;
        for(int i=1;i<modPows.length;i++)
            modPows[i]= (modPows[i-1]*27)%MOD;
        inverse[0]=1;
        inverse[1]= BigInteger.valueOf(27).modInverse(BigInteger.valueOf(MOD)).longValueExact();
        for(int i=2;i<inverse.length;i++)
            inverse[i]=(inverse[i-1]*inverse[1])%MOD;

        hashes[0]=in.charAt(0)-'a'+1;
        for(int i=1;i<hashes.length;i++)
            hashes[i]= (hashes[i-1]+modPows[i]*(in.charAt(i)-'a'+1))%MOD;

        if(!testLen(1)){
            System.out.println(0);
            return;
        }
        int low=1;
        int high=len-1;
        while(high-low>3){
            int mid=(low+high)/2;

            if(testLen(mid))
                low=mid;
            else
                high=mid;
        }
        for(int i=high;i>=0;i--)
            if(testLen(i)) {
                System.out.println(i);
                break;
            }

//        System.out.println(Arrays.toString(hashes));
//        System.out.println(rangeHash(2,2));

    }
}
