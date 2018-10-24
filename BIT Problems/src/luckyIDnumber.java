import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class luckyIDnumber {
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

        public void updateDelta(int index, int delta){
            int fenIndex=index+1;
            int val=delta;
            data[index]+=delta;
            while(fenIndex<tree.length){
                tree[fenIndex]+=val;
                fenIndex+=Integer.lowestOneBit(fenIndex);
            }
        }

        public void updateVal(int index, int newVal){
            int fenIndex=index+1;
            int val=newVal-data[index];
            data[index]=newVal;
            while(fenIndex<tree.length){
                tree[fenIndex]+=val;
                fenIndex+=Integer.lowestOneBit(fenIndex);
            }
        }

        public String toString(){
            return "Tree: " + Arrays.toString(tree)+"\n"+"Data:" + Arrays.toString(data);
        }

    }
    public static int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; ++i) {
            if (((n >>> i) & 1) == 1) {
                ++count;
            }
        }
        return count;
    }
    public static void main(String[] arg) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        while (cases-->0){
        int guesses=Integer.parseInt(br.readLine());
        int[] list=new int[guesses];
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        for(int i=0;i<list.length;i++)
            list[i]=Integer.parseInt(stringTokenizer.nextToken());
        for(int i=0;i<list.length;i++)
            list[i]=(hammingWeight(list[i])==2?1:0);
        BIT tree= new BIT(list);
        int queries=Integer.parseInt(br.readLine());
        while (queries-->0){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int type=Integer.parseInt(tokenizer.nextToken());
            if(type==1){
                int index=Integer.parseInt(tokenizer.nextToken());
                int val=Integer.parseInt(tokenizer.nextToken());
                tree.updateVal(index-1,(hammingWeight(val)==2?1:0));
            }
            else{
                int l=Integer.parseInt(tokenizer.nextToken());
                int r=Integer.parseInt(tokenizer.nextToken());
                if(r<l)
                    System.out.println(0);
                else
                    System.out.println(tree.rangeSum(l-1,r-1));
            }
        }

        }
    }
}
