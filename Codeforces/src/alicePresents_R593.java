/**
 * Created by user on 11/15/2019.
 */

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class alicePresents_R593 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        BigInteger a = new BigInteger(tokenizer.nextToken());
        BigInteger b = new BigInteger(tokenizer.nextToken());
        BigInteger mod = BigInteger.valueOf((long) (1e9+7));
        System.out.println(BigInteger.valueOf(2).modPow(b,mod).subtract(BigInteger.ONE).modPow(a,mod));

    }
}
