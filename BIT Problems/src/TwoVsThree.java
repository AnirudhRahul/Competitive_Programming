import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 10/17/2018.
 */
public class TwoVsThree {
    static class BIT {
        long[] tree;
        int[] data;
        int[] weights;
        public BIT(int[] in, int[] weights){
            data=in;
            tree=new long[data.length+1];
            this.weights=weights;
            for(int i=1;i<tree.length;i++){
                int index=i;
                int val=data[i-1]*weights[i-1];
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
                tree[fenIndex]+=val*weights[index];
                fenIndex+=Integer.lowestOneBit(fenIndex);
            }
        }

        public void updateVal(int index, int newVal){
            int fenIndex=index+1;
            int val=newVal-data[index];
            data[index]=newVal;
            while(fenIndex<tree.length){
                tree[fenIndex]+=val*weights[index];
                fenIndex+=Integer.lowestOneBit(fenIndex);
            }
        }

        public String toString(){
            return "Tree: " + Arrays.toString(tree)+"\n"+"Data:" + Arrays.toString(data);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int len=Integer.parseInt(br.readLine());
        int[] binString=new int[len];
        int[] alternating=new int[len];
        for(int i=0;i<len;i++)
            binString[i]=br.read()-'0';
        br.readLine();
        for(int i=0;i<len;i++)
            alternating[i]=(i%2==0)?1:-1;
//        System.out.println(Arrays.toString(binString));
        int queries=Integer.parseInt(br.readLine());
        BIT tree=new BIT(binString,alternating);
        while (queries-->0){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int type=Integer.parseInt(tokenizer.nextToken());
            if(type==1){
                int index=Integer.parseInt(tokenizer.nextToken());
                if(tree.data[index]==0)
                    tree.updateVal(index,1);
//                System.out.println(tree);

            }
            else if(type==0){
                int l=Integer.parseInt(tokenizer.nextToken());
                int r=Integer.parseInt(tokenizer.nextToken());
                long sum=tree.rangeSum(l,r);
                while (sum<0)
                    sum+=3;
                sum%=3;
                //adjust for parity
                if((r)%2==1)
                    sum=3-sum;
                sum%=3;
                System.out.println(sum);
            }
        }
    }
}
