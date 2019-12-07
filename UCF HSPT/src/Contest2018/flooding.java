package Contest2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.util.StringTokenizer;

/**
 * Created by user on 12/1/2018.
 */
public class flooding  {

    //If the point is inside the triangle, the areas 3 triangles
    // you form by connecting your point to the vertices of the
    // triangle, should add up to the area of the triangle

    //Shoelace
    static double area(int x1, int y1, int x2, int y2, int x3, int y3) {
        return Math.abs((x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2))/2.0);
    }

    static boolean isInside(int x1, int y1, int x2, int y2, int x3, int y3, int x, int y) {
        double totalA = area (x1, y1, x2, y2, x3, y3);
        double A1 = area (x, y, x2, y2, x3, y3);
        double A2 = area (x1, y1, x, y, x3, y3);
        double A3 = area (x1, y1, x2, y2, x, y);
        return totalA == A1 + A2 + A3;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        for(int c=1;c<=cases;c++){
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            int x1=Integer.parseInt(stringTokenizer.nextToken());
            int y1=Integer.parseInt(stringTokenizer.nextToken());
            int x2=Integer.parseInt(stringTokenizer.nextToken());
            int y2=Integer.parseInt(stringTokenizer.nextToken());
            int x3=Integer.parseInt(stringTokenizer.nextToken());
            int y3=Integer.parseInt(stringTokenizer.nextToken());
            int tunnels=Integer.parseInt(br.readLine());
            int counter=0;
            for(int i=0;i<tunnels;i++){
                StringTokenizer tokenizer=new StringTokenizer(br.readLine());
                int sx=Integer.parseInt(tokenizer.nextToken());
                int sy=Integer.parseInt(tokenizer.nextToken());
                int ex=Integer.parseInt(tokenizer.nextToken());
                int ey= Integer.parseInt(tokenizer.nextToken());
                boolean works1=isInside(x1,y1,x2,y2,x3,y3,sx,sy);
                boolean works2=isInside(x1,y1,x2,y2,x3,y3,ex,ey);
                if(works1^works2)
                    counter++;
            }
            System.out.println("Scenario "+c+": "+counter);
        }

    }
}
