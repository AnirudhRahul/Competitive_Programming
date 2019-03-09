import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class garland1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len=Integer.parseInt(br.readLine());
        String in = br.readLine();
        String[] patterns={"RGB","RBG","BRG","BGR","GRB","GBR"};
        String best="";
        int minDiff=Integer.MAX_VALUE;
        for(String p:patterns){
            int diff=0;
            for(int i=0;i<in.length();i++)
                if(p.charAt(i%3)!=in.charAt(i))
                    diff++;
            if(diff<minDiff){
                minDiff=diff;
                best=p;
            }
        }
        System.out.println(minDiff);
        for(int i=0;i<in.length();i++)
            System.out.print(best.charAt(i%3));
        System.out.print("\n");
    }
}
