/**
 * Created by user on 11/13/2019.
 */

import java.io.*;
import java.util.StringTokenizer;

public class waterLily {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(tokenizer.nextToken());
        int l = Integer.parseInt(tokenizer.nextToken());
        System.out.println((Math.pow(l,2)-Math.pow(h,2))/(2.0*h));
    }
}
