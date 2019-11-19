/**
 * Created by user on 11/14/2019.
 */

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class subtractHours {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        SimpleDateFormat input = new SimpleDateFormat("hh:mm aa");
        Date start = input.parse(br.readLine());
        Date end = input.parse(br.readLine());
        long diff =  end.getTime()-start.getTime();
        double division = Math.abs(diff/60000.0);
        System.out.println(start);
        System.out.println(end);
        System.out.println(Math.round(division));
    }
}
