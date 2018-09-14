import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by user on 9/12/2018.
 */
public class compoundWords {
    public static void main(String[] args){
        ArrayList<String> in=new ArrayList<>(20);
        Scanner scan=new Scanner(System.in);
        while(scan.hasNext()) {
            in.add(scan.next());

        }
        TreeSet<String> set=new TreeSet<>();
        for(int i=0;i<in.size();i++)
            for(int j=0;j<in.size();j++){
            if(i==j)
                continue;
                set.add(in.get(i)+in.get(j));
            }
        for(String e:set)
            System.out.println(e);

    }
}
