import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class stringCompletion {
public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    int len=Integer.parseInt(br.readLine());
    while(len-->0){
        String s=br.readLine();
        String t=br.readLine();
        int[] freq1=new int[100];
        int[] freq2=new int[100];
        for(int i=0;i<s.length();i++){
            freq1[s.charAt(i)-'A']++;
        }
        for(int i=0;i<t.length();i++){
            freq2[t.charAt(i)-'A']++;
        }
        int moves=0;
        for(int i=0;i<100;i++){
            moves+=Math.abs(freq1[i]-freq2[i]);
        }
        System.out.println(moves);
//        System.out.println(Arrays.toString(freq2));


    }
}

}
