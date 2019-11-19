/**
 * Created by user on 11/14/2019.
 */

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateProblem {
    public static void main(String[] args) throws IOException, ParseException {

        SimpleDateFormat input = new SimpleDateFormat("MM/dd/yy");
        Date in = input.parse("12/13/14");

        //Use getTime to add and subtract days
        //86400000 time units in a day
        //Gets Date for tommorow
        Date tm = new Date(in.getTime()+86400000L);
        SimpleDateFormat output = new SimpleDateFormat("EEEEEEE, MMMMMM d'th'");
        System.out.println(output.format(in));
        System.out.println(output.format(tm));

    }
}
