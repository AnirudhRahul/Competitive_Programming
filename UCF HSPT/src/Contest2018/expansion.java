package Contest2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by user on 12/1/2018.
 */
public class expansion {
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

    //Yeah using a BIT is a bit overkill
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        for(int c=1;c<=cases;c++){
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            int original=Integer.parseInt(stringTokenizer.nextToken());
            int toAdd=Integer.parseInt(stringTokenizer.nextToken());
            int[] og=new int[original];
            ArrayList<Integer> list=new ArrayList<>(toAdd);
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            for(int i=0;i<og.length;i++){
                og[i]=Integer.parseInt(tokenizer.nextToken());
            }
            tokenizer=new StringTokenizer(br.readLine());
            for(int i=0;i<toAdd;i++){
                list.add(Integer.parseInt(tokenizer.nextToken()));
            }
            Collections.sort(list);
            int[] temp=new int[10005];
            BIT tree=new BIT(temp);
            for(int e:og)
                tree.updateDelta(e,1);
            int added=0;
            for(int i=0;i<list.size();i++){
                int cur=list.get(i);
                if(cur==-1)
                    continue;
                if(tree.rangeSum(cur+1,10001)<tree.rangeSum(0,cur)){
                    added++;
                    tree.updateDelta(cur,1);
//                    System.out.println(cur);
                }
                list.set(i,-1);

            }
            System.out.println("Expansion #"+c+": "+added);

        }

    }
}
