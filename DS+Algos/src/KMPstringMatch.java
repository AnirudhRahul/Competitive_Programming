import java.util.Arrays;

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
        System.out.println(Arrays.toString(pi));
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
    static int[] calculateLPS(String pat) {
        int[] lps = new int[pat.length()];
        int len = 0;
        int i = 1;
        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if (len != 0) {
                    len = lps[len - 1];
                }
                else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        System.out.println(Arrays.toString(lps));
        return lps;
    }
    public static void main(String[] args){
        String pattern="abcabcabcabca";
        int[] lps= calculateLPS(pattern);
        //start at the end of the string
        int index=lps.length-1;
        while(lps[index]!=0){
            //shift back
            index-=lps[index];
        }
        System.out.println(pattern.substring(0,index+1));
    }
}
