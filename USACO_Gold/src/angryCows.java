import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Created by user on 7/25/2018.
 */
public class angryCows {
    static TreeSet<Long> locations;
    //Check if the explosion can propagate all the way to the left,
    // given:a specific power and starting point
    public static boolean checkPowerLeft(long power, long start){
        long min=locations.first();
        long bot=start;
        while(bot!=min){
            long newBot=locations.ceiling(bot-power);
            if(bot==newBot)
                break;
            bot=newBot;
            //Subtract 2 since we scaled everything by 2
            power-=2;
        }
        return bot==min;
    }
    //Check if the explosion can propagate all the way to the right,
    // given:a specific power and starting point
    public static boolean checkPowerRight(long power, long start){
        long max=locations.last();
        long top=start;
        while(top!=max){
            long newTop=locations.floor(top+power);
            if(top==newTop)
                break;
            //Subtract 2 since we scaled everything by 2
            top=newTop;
            power-=2;
        }

        return top==max;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        long cows=Integer.parseInt(br.readLine());

        locations=new TreeSet<>();
        //multiply by 2 so we dont have to account for having decimals and stuff
        //Use the cheeky 2L for implicit casting
        for(long i=0;i<cows;i++) {
            long temp=2L*(Integer.parseInt(br.readLine()));
            locations.add(temp);
        }

        long maxPower=locations.last()-locations.first();
        maxPower/=2;
        long minPower=1;

        //Loop labeled out binary searches for the minimum power
        //needed to blow up all the haybales
        out:while(minPower!=maxPower){
            //mid is the power we are going to test to see if it is sufficient to blow up all the hay bales
            long mid=(maxPower+minPower)/2;
            long left=locations.first();
            long right=locations.last();
            //binary search to find the rightmost point such that
            //the explosion propagates all the way to the left given
            //that the power we throw it with is 'mid'

            while (right-left>1){
                long m=(left+right)/2;
                if(checkPowerLeft(mid,m))
                    left=m;
                else
                    right=m;

            }
            //finish with a loop to avoid OBOEs and the like
            for(long i=right;i>=left;i--)
                if(checkPowerLeft(mid,i)){
                //Now that we have found the rightmost point that propagates all the way to the left
                //we check to see if it propagates to the right. If it doesn't propagate to the right we know
                //we need to increase the power, if it does propagate to the right then we can decrease the power
                if(checkPowerRight(mid,i))
                    maxPower=mid;
                else
                    minPower=mid+1;

                //Make sures we stop after we find the rightmost point
                continue out;
                }

        }
        //dividing because we previously scaled up by 2
        pw.println(minPower/2+"."+(minPower%2==0?"0":"5"));
        pw.close();

    }
}
