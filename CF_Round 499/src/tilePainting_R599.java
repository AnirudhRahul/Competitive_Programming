/**
 * Created by user on 11/10/2019.
 */

import java.io.*;
import java.util.ArrayList;

public class tilePainting_R599 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        long num = Long.parseLong(br.readLine());
        ArrayList<Long> primeList = new ArrayList<>(100);
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0){
                primeList.add((long) i);
                while(num%i==0)
                    num/=i;
            }
        }
        if(num!=1)
            primeList.add(num);

        if(primeList.size()>1)
            System.out.println(1);
        else if(primeList.size()==0)
            System.out.println(num);
        else
            System.out.println(primeList.get(0));


    }
}
