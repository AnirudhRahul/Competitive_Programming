public class KMPstringMatch {

    public static boolean match(char[] total,char[] piece){
        int[] pi=new int[total.length];
        pi[0] = -1;
        int k = -1;
        for(int i = 1; i <= total.length; i++) {
            while(k >= 0 && total[k+1] != total[i])
                k = pi[k];
            pi[i] = ++k;
        }

        k = 0;
        for(int i = 1; i <= piece.length; i++) {
            while(k >= 0 && total[k+1] != piece[i])
                k = pi[k];
            k++;
            if(k == total.length) {
                // P matches T[i-m+1..i]
                k = pi[k];
            }
        }
        return false;
    }
}
