import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

public class dates {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int len=Integer.parseInt(br.readLine());
        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        int[] dayMonth={31,28,31,30,31,30,31,31,30,31,30,31};
        while (len-->0){

            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int day=Integer.parseInt(tokenizer.nextToken());
            String monthName=tokenizer.nextToken();
            int monthNum=months.indexOf(monthName);
            int year=Integer.parseInt(tokenizer.nextToken());
            day--;
            if(day<=0){
                day+=dayMonth[(monthNum+11)%12];
                monthNum--;
                if(monthNum==1&&(year%4==0&&(year%100!=0||year%400==0)))
                    day--;
                if(monthNum<0){
                    year--;
                    monthNum+=12;
                }
            }
            System.out.println(day+" "+months.get(monthNum)+" "+year);

        }
    }
}
