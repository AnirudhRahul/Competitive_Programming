/**
 * Created by user on 11/12/2019.
 */

import java.io.*;
import java.util.ArrayList;

public class characterSwap2 {
    static class Swap{
        int a,b;
        public  Swap(int a, int b){
            this.a=a;
            this.b=b;
        }

        public String toString(){return (a+1)+" "+(b+1);}
    }

    static void swap(int aIndex, int bIndex, char[] a, char[] b){
        char temp  = a[aIndex];
        a[aIndex]=b[bIndex];
        b[bIndex]=temp;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int cases = Integer.parseInt(br.readLine());
        out: while (cases-->0){
            int len = Integer.parseInt(br.readLine());
            char[] a = br.readLine().toCharArray();
            char[] b = br.readLine().toCharArray();
            ArrayList<Swap> list = new ArrayList<>();
            for(int i=0;i<len;i++){
                if(a[i]==b[i])
                    continue;
                boolean fixed = false;
                for(int j=i+1;j<len;j++){
                    if(b[i]==b[j]){
                        list.add(new Swap(i,j));
                        swap(i,j,a,b);
                        fixed=true;
                        break;
                    }
                    else if(a[i]==a[j]){
                        list.add(new Swap(j,i));
                        swap(j,i,a,b);
                        fixed=true;
                        break;
                    }
                    else if(a[i]==b[j]){
                        list.add(new Swap(len-1,j));
                        list.add(new Swap(len-1, i));
                        swap(len-1,j,a,b);
                        swap(len-1,i,a,b);
                        fixed = true;
                        break;
                    }
                    else if(a[j]==b[i]){
                        list.add(new Swap(j,len-1));
                        list.add(new Swap(i,len-1));
                        swap(j,len-1,a,b);
                        swap(i,len-1,a,b);
                        fixed = true;
                        break;
                    }
                }
                if(!fixed){
                    wr.write("No\n");
                    continue out;
                }

            }
            if(a[len-1]==b[len-1]) {

                wr.write("Yes\n");
                wr.write(list.size() + "\n");
                for (Swap s : list)
                    wr.write(s.toString() + "\n");
            }
            else
                wr.write("No\n");

        }
        wr.flush();
    }
}
