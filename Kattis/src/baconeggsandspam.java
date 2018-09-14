import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by user on 9/13/2018.
 */
public class baconeggsandspam {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            TreeMap<String, TreeSet<String>> map = new TreeMap<>();
            int len = Integer.parseInt(br.readLine());
            if(len==0)
                break;
            for (int i = 0; i < len; i++) {
                StringTokenizer tokenizer=new StringTokenizer(br.readLine());
                String val=tokenizer.nextToken();
                while(tokenizer.hasMoreTokens()){
                    String cur=tokenizer.nextToken();
                    if(map.containsKey(cur))
                        map.get(cur).add(val);
                    else{
                        TreeSet<String> temp=new TreeSet<>();
                        temp.add(val);
                        map.put(cur,temp);
                    }
                }

            }
            for(String e:map.keySet()){
                System.out.print(e);
                for(String p:map.get(e))
                    System.out.print(" "+p);
                System.out.println();

            }

            System.out.println();

        }
    }
}
