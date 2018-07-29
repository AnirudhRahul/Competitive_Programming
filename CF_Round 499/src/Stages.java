import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by user on 7/28/2018.
 */
public class Stages {
    public static void main(String args[]) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int len=Integer.parseInt(tokenizer.nextToken());
        int needed=Integer.parseInt(tokenizer.nextToken());
        ArrayList<Character> list=new ArrayList<>(len);
        for(int i=0;i<len;i++)
            list.add((char) br.read());
        Collections.sort(list);
//        System.out.println(list);
        ArrayList<Character> rocket=new ArrayList<>();
        int index=1;
        rocket.add(list.get(0));
        while(rocket.size()!=needed&&index<list.size()){
            char cur=list.get(index);
            if(cur-rocket.get(rocket.size()-1)>=2)
                rocket.add(cur);

            index++;
        }
//        System.out.println(rocket);
        if(rocket.size()==needed){
            int val=0;
            for(char e:rocket)
                val+=e-'a'+1;
            System.out.println(val);
        }
        else
            System.out.println(-1);


    }
}
