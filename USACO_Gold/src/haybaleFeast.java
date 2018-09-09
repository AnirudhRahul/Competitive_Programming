import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 9/2/2018.
 */
public class haybaleFeast{
    static class SparseTable{
        int[][] table;
        ArrayList<Integer> pows=new ArrayList<>(25);
        public SparseTable(int[] in){
            int power=1;
            int exp=0;
            pows.add(power);
            while(power<in.length){
                power<<=1;
                exp++;
                pows.add(power);
            }
            table=new int[in.length][exp];
            for(int i=0;i<in.length;i++)
                table[i][0]=in[i];
            for(int j=1;j<exp;j++)
                for (int i=0;i<in.length;i++){
                    if(i+pows.get(j-1)>=in.length)
                        table[i][j]=table[i][j-1];
                    else
                        table[i][j]=Math.max(table[i][j-1],table[i+pows.get(j-1)][j-1]);
                }
        }
        //max from l to r inclusive
        public int rmq(int left, int right){
            if(left==right)
                return table[left][0];
            if(left==right-1)
                return table[left][1];

            int diff=right-left+1;
            int exp=0;
            while(pows.get(exp)<diff)
                exp++;
            exp--;
            return Math.max(table[left][exp],table[right-pows.get(exp)+1][exp]);
        }
    }

    static class BIT {
        long[] tree;
        int[] data;
        public BIT(int[] in){
            data=in;
            tree=new long[data.length+1];
            for(int i=1;i<tree.length;i++){
                int index=i;
                int val=data[i-1];
                while(index<tree.length){
                    tree[index]+=val;
                    index+=Integer.lowestOneBit(index);
                }

            }
        }
        //sum from 0 to endIndex inclusive
        public long rangeSum(int endIndex){
            endIndex++;
            long sum=0;
            while(endIndex>0){
                sum+=tree[endIndex];
                endIndex-=Integer.lowestOneBit(endIndex);
            }
            return sum;
        }
        //sum from l to r inclusive
        public long rangeSum(int l, int r){
            return rangeSum(r)-rangeSum(l-1);
        }



        public String toString(){
            return "Tree: " + Arrays.toString(tree)+"\n"+"Data:" + Arrays.toString(data);
        }

    }


    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("hayfeast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        int meals=Integer.parseInt(stringTokenizer.nextToken());
        long minSum=Long.parseLong(stringTokenizer.nextToken());
        int[] flavor=new int[meals];
        int[] spice=new int[meals];
        for(int i=0;i<meals;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            flavor[i]=Integer.parseInt(tokenizer.nextToken());
            spice[i]=Integer.parseInt(tokenizer.nextToken());
        }
        SparseTable table=new SparseTable(spice);
        BIT sum=new BIT(flavor);
//        int[] spicesFromStart=new int[meals];
        long finMin=Long.MAX_VALUE;
        for(int start=0;start<meals;start++){
            int low=start;
            int high=meals-1;
            if(sum.rangeSum(start,meals-1)<minSum)
                continue;
            while(high-low>3){
                int mid=(low+high)/2;
                if(sum.rangeSum(start,mid)>minSum)
                    high=mid;
                else
                    low=mid;
            }
            for(int i=low;i<meals;i++){
                if(sum.rangeSum(start,i)>=minSum){
                    finMin=Math.min(finMin,table.rmq(start,i));
                    break;
                }
            }
        }
//        System.out.println(Arrays.toString(spicesFromStart));
        pw.println(finMin);
        pw.close();
    }
}
