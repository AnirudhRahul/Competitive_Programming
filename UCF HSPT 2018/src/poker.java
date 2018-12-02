import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by user on 12/1/2018.
 */
public class poker {
    //Hashmap copy paste
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        String[] hands={"Straight flush",
                "Four of a kind",
                "Full house",
                "Flush",
                "Straight",
                "Three of a kind",
                "Two pair",
                "Pair",
                "High card"};
        HashMap<String,Integer> map=new HashMap<>();
        for(int i=0;i<hands.length;i++){
            map.put(hands[i],i);
        }
        for(int c=1;c<=cases;c++){
            int ryan=map.get(br.readLine());
            int tyler=map.get(br.readLine());
            System.out.println("Game #"+c+": "+(ryan<tyler?"Ryan":"Tyler"));

        }

    }
}
