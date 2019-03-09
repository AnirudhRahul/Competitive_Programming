import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class smallFactorials{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len=Integer.parseInt(br.readLine());
        BigInteger fact = BigInteger.ONE;
        BigInteger[] list = new BigInteger[101];
        list[0]=fact;
        for(int i=1;i<101;i++){
            list[i]=list[i-1].multiply(BigInteger.valueOf(i));
        }
        while (len-->0){
            System.out.println(list[Integer.parseInt(br.readLine())]);
        }
    }
}
