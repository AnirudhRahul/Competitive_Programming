import java.io.*;

/**
 * Created by user on 9/1/2018.
 */
public class cowCrosstheRoad3{
    public static boolean contain(int l,int r, int a){
        return a>l&&a<r;
    }
    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("circlecross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
        int len=Integer.parseInt(br.readLine());
        int[] list=new int[2*len];
        for(int i=0;i<list.length;i++)
            list[i]=Integer.parseInt(br.readLine());
        int[][] indexMap=new int[len+1][2];
        for(int i=0;i<len+1;i++){
            indexMap[i][0]=-1;
            indexMap[i][1]=-1;
        }
        for(int i=0;i<list.length;i++){
            int cur=list[i];
            if(indexMap[cur][0]==-1)
                indexMap[cur][0]=i;
            else
                indexMap[cur][1]=i;
        }
//        for(int i=0;i<len;i++)
//            System.out.println(indexMap[i+1][0]+"\t"+indexMap[i+1][1]);
        int total=0;
        for(int i=1;i<=len;i++)
            for(int j=i+1;j<=len;j++){
                int start1=indexMap[i][0];
                int end1=indexMap[i][1];
                int start2=indexMap[j][0];
                int end2=indexMap[j][1];
                if(start2>end1||start1>end2)
                    continue;
                if(contain(start1,end1,start2)&&contain(start1,end1,end2))
                    continue;
                if(contain(start2,end2,start1)&&contain(start2,end2,end1))
                    continue;
                total++;

            }
        pw.println(total);
        pw.close();
    }
}
