/**
 * Created by user on 11/19/2019.
 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class A_R586 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int len = Integer.parseInt(br.readLine());
        int z=0;
        int n=0;
        for(int i=0;i<len;i++){
            char in = (char) br.read();
            if(in=='n')
                n++;
            if(in=='z')
                z++;
        }
        while(n-->0)
            sb.append("1 ");
        while(z-->0)
            sb.append("0 ");
        System.out.println(sb.toString().trim());
    }
}
