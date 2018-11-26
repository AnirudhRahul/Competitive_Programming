import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class vanyaArray{
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
        public BIT(int len){
            data=new int[len];
            tree=new long[len];
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
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        int len=Integer.parseInt(tokenizer.nextToken());
        int sum=Integer.parseInt(tokenizer.nextToken());
        tokenizer=new StringTokenizer(br.readLine());
        int[] list=new int[len];
        int[] sorted=new int[len];
        for(int i=0;i<len;i++) {
            list[i] = Integer.parseInt(tokenizer.nextToken());
            sorted[i] = list[i];
        }
        Arrays.sort(sorted);
        HashMap<Integer,Integer> compress=new HashMap<>();
        for(int i=0;i<sorted.length;i++)
            compress.put(sorted[i],i);
        for(int i=0;i<sorted.length;i++)
            list[i]=compress.get(list[i])+1;

        BIT inversion=new BIT(len+10);
        int[] inversionCounts= new int[len];
        for(int i=len-1;i>=0;i--){
            inversion.updateDelta(list[i],1);
            inversionCounts[i]= (int) inversion.rangeSum(list[i]+1,len);
        }
//        System.out.println(Arrays.toString(inversionCounts));

        BIT pairSum=new BIT(len+10);
        for(int i=0;i<len;i++){
            pairSum.updateDelta(inversionCounts[i],1);
        }
//        System.out.println(Arrays.toString(pairSum.data));

        long combinations=0;
        for(int i=0;i<len;i++){
            int needed=sum-i;
            if(needed>len)
                continue;
//            System.out.println(i+" : "+needed+"\t"+pairSum.data[i]*pairSum.rangeSum(needed,len));
            if(needed<=i)
                combinations+=pairSum.data[i]*(pairSum.rangeSum(needed,len)-1);
            else
                combinations+=pairSum.data[i]*pairSum.rangeSum(needed,len);
        }
        System.out.println(combinations/2);


//        System.out.println(inversion);
//        System.out.println(Arrays.toString(inversionCounts));

    }
}
