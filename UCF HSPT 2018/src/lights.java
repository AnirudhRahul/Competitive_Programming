
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by user on 12/1/2018.
 */
public class lights {
    static int[] lps;
    //finds the longest proper prefix that is also a proper suffix
    //this is a part of the KMP algorithm
    public static int[] lps(String pat){
        lps = new int[pat.length()];
        int i=1, len=0, L = pat.length();
        while(i<L){
            if(pat.charAt(i)==pat.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }else{
                if(len!=0){
                    len = lps[len-1];
                }else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    //If the string has a proper prefix that is also a proper suffix
    //We know it is ok
    static boolean isRepeat(String str)
    {
        lps(str);
        int len = lps[str.length()-1];

        return len>0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        for(int c=1;c<=cases;c++){
            String in=br.readLine();
            System.out.println(isRepeat(in)?"OK":"MESSY");

        }

    }
}
