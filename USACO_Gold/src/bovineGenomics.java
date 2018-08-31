/**
 * Created by user on 8/28/2018.
 */
public class bovineGenomics {
    final static int mod=10007;
    //need mod inverses too
    static int[] modPows=new int[500];
    static class DNA{
        int[] hashValues;
        public DNA(String in){
            for(int i=0;i<in.length();i++){
                char cur=in.charAt(i);
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
            return (hashValues[r]-hashValues[l-1]);                                     
        }
    }
    public static void main(String args[]){
        modPows[0]=1;
        for(int i=1;i<modPows.length;i++)
            modPows[i]=(modPows[i-1]*4)%mod;

    }
}
