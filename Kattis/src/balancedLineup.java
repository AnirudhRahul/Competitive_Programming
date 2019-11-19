import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by user on 9/21/2019.
 */
public class balancedLineup {
    static class SparseTable {
        int[][] table;
    public SparseTable(int[] in){
            int exp=0;
            while(1<<exp<in.length){
                exp++;
            }
            table=new int[in.length][exp];
            for(int i=0;i<in.length;i++)
                table[i][0]=in[i];
            for(int j=1;j<exp;j++)
                for (int i=0;i<in.length;i++){
                    if(i+(1<<(j-1))>=in.length)
                        table[i][j]=table[i][j-1];
                    else
                        table[i][j]=Math.min(table[i][j-1],table[i+(1<<(j-1))][j-1]);
                }
        }
        //minimum from l to r inclusive
    public int rmq(int left, int right){
        if(left==right)
            return table[left][0];
        if(left==right-1)
            return table[left][1];

        int diff=right-left+1;
        int exp=0;
        while((1<<exp)<diff)
            exp++;
        exp--;
        return Math.min(table[left][exp],table[right-(1<<exp)+1][exp]);
    }
}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(tokenizer.nextToken());
        int queries = Integer.parseInt(tokenizer.nextToken());
        int[] arr = new int[len];
        int[] neg = new int[len];
        for(int i=0;i<len;i++){
            arr[i]=Integer.parseInt(br.readLine());
            neg[i]= -arr[i];
        }
        SparseTable minTable = new SparseTable(arr);
        SparseTable maxTable = new SparseTable(neg);
        while(queries-->0){
            tokenizer = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(tokenizer.nextToken())-1;
            int r = Integer.parseInt(tokenizer.nextToken())-1;
            System.out.println(-maxTable.rmq(l,r)-minTable.rmq(l,r));
        }

    }
}
