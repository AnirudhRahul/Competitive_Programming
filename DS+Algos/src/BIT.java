import java.util.Arrays;

/**
 * Created by user on 7/21/2018.
 */
public class BIT {
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
        while(fenIndex<tree.length){
            tree[fenIndex]+=val;
            fenIndex+=Integer.lowestOneBit(index);
        }
    }

    public void updateVal(int index, int newVal){
        int fenIndex=index+1;
        int val=newVal-data[index];
        while(fenIndex<tree.length){
            tree[fenIndex]+=val;
            fenIndex+=Integer.lowestOneBit(index);
        }
    }

    public String toString(){
        return "Tree: " + Arrays.toString(tree)+"\n"+"Data:" + Arrays.toString(data);
    }

}
