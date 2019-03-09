import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class divisor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        StringTokenizer tokenizer= new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++)
            list.add(Integer.parseInt(tokenizer.nextToken()));

        int max=Collections.max(list);
        ArrayList<Integer> filteredlist = new ArrayList<>();
        HashSet<Integer> seen = new HashSet<>();
        for(int i=0;i<list.size();i++){
            if(max%list.get(i)==0) {
                if(seen.contains(list.get(i)))
                    filteredlist.add(list.get(i));
                else
                    seen.add(list.get(i));
            }
            else
                filteredlist.add(list.get(i));
        }
        System.out.print(max+" "+Collections.max(filteredlist));
    }
}
