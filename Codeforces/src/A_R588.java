/**
 * Created by user on 11/19/2019.
 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class A_R588 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner scan = new Scanner(System.in);
        int[] a = {scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()};
        Arrays.sort(a);
        boolean ans = a[0]+a[3]==a[1]+a[2];
        boolean oth = a[3]==a[2]+a[1]+a[0];
        System.out.println(ans||oth?"YES":"NO");

    }
}
