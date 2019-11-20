/**
 * Created by user on 11/19/2019.
 */

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class B_R588 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());
        String in = br.readLine();
        if(c==0){
            System.out.println(in);
            return;
        }
        if(len==1 && c>0){
            System.out.println(0);
            return;
        }
        char[] num = in.toCharArray();

        if(num[0]>'1'){
            num[0]='1';
            c--;
        }
        for(int i=1;i<num.length;i++){
            if(c==0)
                break;
            if(num[i]!='0'){
                num[i]='0';
                c--;
            }

        }
        System.out.println(new String(num));


    }
}
