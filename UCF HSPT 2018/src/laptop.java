import javax.swing.text.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by user on 12/1/2018.
 */
public class laptop {
    //Just goes around and makes the assumption that we dont skip a light of the same color because that would be inefficient
    static double walkDistance(int startR,int startG,int startB){
        double walkDist=0;
        int rCurIndex=reds.get(startR);
        for(int i=1;i<reds.size();i++){
            int finIndex=reds.get((startR+i)%redLen);
            walkDist+=length(dist(rCurIndex,finIndex));
            rCurIndex=finIndex;
        }

        int gCurIndex=greens.get(startG);
        walkDist+=length(dist(gCurIndex,rCurIndex));

        for(int i=1;i<greens.size();i++){
            int finIndex=greens.get((startG+i)%greenLen);
            walkDist+=length(dist(gCurIndex,finIndex));
            gCurIndex=finIndex;
        }



        int bCurIndex=blues.get(startB);
        walkDist+=length(dist(gCurIndex,bCurIndex));


        for(int i=1;i<blues.size();i++){
            int finIndex=blues.get((startB+i)%blueLen);
            walkDist+=length(dist(bCurIndex,finIndex));
            bCurIndex=finIndex;
        }

        return walkDist;
    }
    //Geometry
    static double length(int in){return Math.sin((Math.PI*in)/len)*scale*2;}
    static int dist(int a,int b){return Math.floorMod(a-b,len);}
    static int len, scale;
    static ArrayList<Integer> reds,greens,blues;
    static int redLen,greenLen,blueLen;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        for(int c=1;c<=cases;c++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            char[] in=br.readLine().toCharArray();

            len=Integer.parseInt(tokenizer.nextToken());
            scale=Integer.parseInt(tokenizer.nextToken());
            reds=new ArrayList<>();
            greens=new ArrayList<>();
            blues=new ArrayList<>();
            for(int i=0;i<in.length;i++){
                if(in[i]=='R')
                    reds.add(i);
                else if(in[i]=='G')
                    greens.add(i);
                else
                    blues.add(i);
            }
            redLen= reds.size();
            greenLen= greens.size();
            blueLen= blues.size();
            double min= Double.MAX_VALUE;
            //You honestly just do this part because
            //you're not really sure what order to go
            //around in and this accounts for all of them
            //And the for loop has a max of 125 iterations so it's not that bad
            for(int i=0;i<redLen;i++)
                for(int j=0;j<greenLen;j++)
                    for(int k=0;k<blueLen;k++){
                        min=Math.min(min,walkDistance(i,j,k));
                    }
            System.out.printf("Scenario #%d: %.3f\n",c,min);

        }
    }
}
