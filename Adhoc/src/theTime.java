/**
 * Created by user on 11/14/2019.
 */

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class theTime {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
        Date in = fmt.parse(br.readLine());
        int min = Integer.parseInt(br.readLine());
        Date aft = new Date(in.getTime()+min*60000L);
        System.out.println(fmt.format(aft));
    }
}
