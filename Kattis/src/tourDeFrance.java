import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by user on 10/2/2018.
 */
public class tourDeFrance {
    public static void main(String[] args) throws IOException {

        Scanner scan=new Scanner(System.in);
        while(true) {
            int front = scan.nextInt();
            if(front==0)
                return;
            int back = scan.nextInt();

            HashSet<Integer> frontSprockets = new HashSet<>(front);
            HashSet<Integer> backSprockets = new HashSet<>(back);
            for (int i = 0; i < front; i++)
                frontSprockets.add(scan.nextInt());
            for (int i = 0; i < back; i++)
                backSprockets.add(scan.nextInt());
            TreeSet<Double> drives = new TreeSet<>();
            for (int i : frontSprockets)
                for (int j : backSprockets)
                    drives.add(1.0 * j / i);

            ArrayList<Double> listDrives = new ArrayList<>(drives);
            ArrayList<Double> spread = new ArrayList<>(drives.size());
            for (int i = 0; i < listDrives.size() - 1; i++) {
                spread.add(listDrives.get(i + 1) / listDrives.get(i));

            }
            Double max = spread.size() == 0 ? 1.0 : Collections.max(spread);
            System.out.printf("%.2f\n", max);
        }
    }
}
