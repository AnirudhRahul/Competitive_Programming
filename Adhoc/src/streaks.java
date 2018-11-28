import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class streaks {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int days=Integer.parseInt(br.readLine());
        String[] in=new String[days];
        for(int i=0;i<days;i++)
            in[i]=br.readLine();
        int[] vals=new int[days];
        int totalMax=0;
        for(int i=0;i<days;i++){
            int curIndex=0;
            int runSum=0;
            int max=0;
            while(curIndex<in[i].length()){
                if(in[i].charAt(curIndex)!='C') {
                    runSum=0;
                    curIndex++;
                }
                else{
                    runSum++;
                    curIndex++;
                    max=Math.max(runSum,max);
                }

            }
            totalMax=Math.max(max,totalMax);
        }
        int i=0;
        int runSum=0;
        int max=0;
        while(i<days){
            int curIndex=0;

            while(curIndex<in[i].length()){
                if(in[i].charAt(curIndex)!='C') {
                    runSum=0;
                    curIndex++;
                }
                else{
                    runSum++;
                    curIndex++;
                    max=Math.max(runSum,max);
                }

            }
            i++;
//            totalMax=Math.max(max,totalMax);

        }
        System.out.println(totalMax+" "+max);
//        System.out.println(Arrays.toString(vals));
    }
}
