import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class birthday {
     public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int len = Integer.parseInt(br.readLine());
         ArrayList<Integer> list = new ArrayList<>(len);
         StringTokenizer tokenizer = new StringTokenizer(br.readLine());
         for(int i=0;i<len;i++){
             list.add(Integer.parseInt(tokenizer.nextToken()));
         }
         Collections.sort(list);
         Collections.reverse(list);
         ArrayDeque<Integer> q = new ArrayDeque<>();
//         ArrayDeque<Integer> circle = new ArrayDeque<Integer>();

         for(int i=0;i<list.size();i++)
             q.addLast(list.get(i));

         ArrayDeque<Integer> circle = new ArrayDeque<>(len);
         circle.add(q.pollFirst());
         while(q.size()>=2){
            circle.addLast(q.pollFirst());
            circle.addFirst(q.pollFirst());
         }
         if(!q.isEmpty())
         circle.addLast(q.pollFirst());
//         int maxDiff=0;
         while(!circle.isEmpty()){
             System.out.print(circle.pollFirst()+(circle.size()==0?"":" "));
         }
//         System.out.println(circle);



     }
}
