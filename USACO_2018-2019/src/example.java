import java.io.*;

public class example {
    public static void main(String args[]) throws IOException {
        String name = "example";
        BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(name + ".out")));
    }
}