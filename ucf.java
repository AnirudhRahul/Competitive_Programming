import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by user on 9/17/2018.
 */
public class ucfHSPT2018_numbers{
    public static HashSet<Long> parse(ArrayList<Character> in){
        return new HashSet<>();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int puzzles=Integer.parseInt(br.readLine());
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int row=Integer.parseInt(stringTokenizer.nextToken());
        int col=Integer.parseInt(stringTokenizer.nextToken());
        char[][] map=new char[row][col];
        for(int i=0;i<row;i++)
            map[i]=br.readLine().toCharArray();
        ArrayList<ArrayList<Character>> streaks=new ArrayList<>(2*row*col);
        HashSet<Long> possibilities=new HashSet<>();
        //Horizontal
        for(int i=0;i<row;i++) {
            ArrayList<Character> cur=new ArrayList<>(col);
            for (int j = 0; j < col; j++) {
                cur.add(map[i][j]);
            }

            streaks.add(cur);
        }
        //Vertical
        for(int i=0;i<col;i++) {
            ArrayList<Character> cur=new ArrayList<>(col);
            for (int j = 0; j < row; j++) {
                cur.add(map[j][i]);
            }
            streaks.add(cur);
        }

        //Up right diagonals
        for(int i=0;i<col;i++){
            int startR=row-1;
            int startC=i;
            ArrayList<Character> cur=new ArrayList<>();
            while (startR>=0&&startC<col){
                cur.add(map[startR][startC]);
                startC++;
                startR--;
            }
            streaks.add(cur);
        }
        for(int i=0;i<row-1;i++){
            int startR=i;
            int startC=0;
            ArrayList<Character> cur=new ArrayList<>();
            while (startR>=0&&startC<col){
                cur.add(map[startR][startC]);
                startC++;
                startR--;
            }
            streaks.add(cur);
        }
        //Down Right diagonals
        for(int i=0;i<col;i++){
            int startR=0;
            int startC=i;
            ArrayList<Character> cur=new ArrayList<>();
            while (startR<row&&startC<col){
                cur.add(map[startR][startC]);
                startC++;
                startR++;
            }
            streaks.add(cur);
        }
        for(int i=1;i<row;i++){
            int startR=i;
            int startC=0;
            ArrayList<Character> cur=new ArrayList<>();
            while (startR<row&&startC<col){
                cur.add(map[startR][startC]);
                startC++;
                startR++;
            }
            streaks.add(cur);
        }

        System.out.println(streaks);


        for(ArrayList<Character> e:streaks)
            possibilities.addAll(parse(e));

    }
}
