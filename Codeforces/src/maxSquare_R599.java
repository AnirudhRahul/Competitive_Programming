/**
 * Created by user on 11/12/2019.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class maxSquare_R599 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int cases = Integer.parseInt(br.readLine());
        while(cases-->0){
            int len = Integer.parseInt(br.readLine());
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            ArrayList<Integer> list  = new ArrayList<>(len);
            for(int i=0;i<len;i++)
                list.add(Integer.parseInt(tokenizer.nextToken()));
            Collections.sort(list);
            for(int size=1;size<=len;size++){
                if(list.get(list.size()-size)<size){
                    wr.write((size-1)+"\n");
                    break;
                }
                else if(size==len){
                    wr.write(len+"\n");
                    break;
                }

            }


        }


        wr.flush();

    }
}
