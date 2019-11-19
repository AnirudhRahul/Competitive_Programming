/**
 * Created by user on 11/13/2019.
 */

import java.io.*;
import java.text.DecimalFormat;

public class printF {
    public static void main(String[] args) throws IOException {

        Double num = 500.5;


        //Rounds to 2 decimal places with trailing zeros
        System.out.printf("%.2f%n",num);

        //Rounds to 2 decimal places no trailing zeros
        DecimalFormat format = new DecimalFormat("0.##");
        System.out.println(format.format(num));

        //Scientific notation with 2 decimal places
        String scientific = String.format("%.3e%n",num);
        System.out.println(scientific);

    }
}
