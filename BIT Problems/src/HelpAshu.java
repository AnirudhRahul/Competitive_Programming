import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 9/8/2018.
 */
public class HelpAshu {
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

    public static void main(String args[]) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int size=Integer.parseInt(br.readLine());
        int[] in=new int[size];
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        for(int i=0;i<size;i++){
            in[i]=Integer.parseInt(tokenizer.nextToken())%2;
        }
        BIT fenwick=new BIT(in);
//        System.out.println();
//        System.out.println(fenwick.rangeSum(3));
//        System.out.println(Arrays.toString(fenwick.data));
//        fenwick.updateDelta(1,1);
//        System.out.println(fenwick.rangeSum(2));
//        System.out.println(Arrays.toString(fenwick.data));
//
//        System.out.println();

        int queries=Integer.parseInt(br.readLine());
        for(int i=0;i<queries;i++){
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            int type=Integer.parseInt(stringTokenizer.nextToken());
            int a=Integer.parseInt(stringTokenizer.nextToken());
            int b=Integer.parseInt(stringTokenizer.nextToken());
//            System.out.println(Arrays.toString(fenwick.data));
//            System.out.println((a-1)+" "+(b-1)+" : "+fenwick.rangeSum(a-1,b-1));
            if(type==0){
                fenwick.updateVal(a-1,b%2);
            }
            if(type==1){
                System.out.println(b-a+1-fenwick.rangeSum(a-1,b-1));
            }
            if(type==2){
                System.out.println(fenwick.rangeSum(a-1,b-1));
            }
        }

    }
}
