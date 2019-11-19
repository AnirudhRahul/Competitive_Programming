import java.io.*;
import java.util.ArrayDeque;

/**
 * Created by user on 8/6/2019.
 */
public class backSpace {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayDeque<Character> q = new ArrayDeque<>(1<<20);
        int in;
        while(true){
            in = br.read();
            if(in==-1)
                break;
            if(in=='<')
                q.removeLast();
            else
                q.addLast((char) in);
        }
        for(char e:q)
            wr.write(e);
        wr.flush();
    }
}
