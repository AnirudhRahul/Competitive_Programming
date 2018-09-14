import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by user on 9/12/2018.
 */
public class inverseFactorial {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer,Integer> smallCases=new HashMap<>();
        int fact=1;
        for(int i=1;i<=10;i++){
            fact*=i;
            smallCases.put(fact,i);
        }
        String in=br.readLine();
        if(in.length()<=7){
            System.out.println(smallCases.get(Integer.parseInt(in)));
        }
        else{
            double curdigits=0;
            int curNum=1;
            while(curdigits<in.length()){
                curdigits+=Math.log10(curNum);
                curNum++;
            }
            System.out.println(curNum-2);

        }

    }
}
