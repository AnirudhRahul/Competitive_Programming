/**
 * Created by user on 11/12/2019.
 */

import java.io.*;
import java.util.ArrayList;

public class characterSwap1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int cases = Integer.parseInt(br.readLine());
        while (cases-->0){
            int len = Integer.parseInt(br.readLine());
            String a = br.readLine();
            String b = br.readLine();
            ArrayList<Character> mismatchA = new ArrayList<>(len);
            ArrayList<Character> mismatchB = new ArrayList<>(len);
            for(int i=0;i<len;i++){
                if(a.charAt(i)!=b.charAt(i)){
                    mismatchA.add(a.charAt(i));
                    mismatchB.add(b.charAt(i));
                }
            }
            if(mismatchA.size()==2){
                if(mismatchA.get(0)==mismatchA.get(1) &&  mismatchB.get(0)==mismatchB.get(1))
                    wr.write("Yes\n");
                else
                    wr.write("No\n");
            }
            else
                wr.write("No\n");


        }
        wr.flush();
    }
}
