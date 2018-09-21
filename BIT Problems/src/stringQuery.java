import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 9/10/2018.
 */
public class stringQuery{

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

    public static void main(String[] args) throws IOException {
        String letters="abcdefghijklmnopqrstuvwxyz";
        BIT[] fenwickList=new BIT[26];
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String in=br.readLine();
        for(int i=0;i<fenwickList.length;i++)
            fenwickList[i]=new BIT(new int[in.length()]);
        for(int i=0;i<in.length();i++){
            int curIndex=letters.indexOf(in.charAt(i));
            fenwickList[curIndex].updateDelta(i,1);
        }
        int queries=Integer.parseInt(br.readLine());
        while(queries-->0){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            int target=Integer.parseInt(tokenizer.nextToken());
            char curChar=tokenizer.nextToken().charAt(0);
            int charIndex=letters.indexOf(curChar);
            BIT cur=fenwickList[charIndex];
            int high=in.length();
            int low=0;
            while(high-low>3){
                int mid=(high+low)/2;
                if(cur.rangeSum(0,mid)<target)
                    low=mid;
                else
                    high=mid;
            }
            int removeIndex=-1;
            for(int i=low;i<in.length();i++){
                if(cur.rangeSum(0,i)==target) {
                    removeIndex = i;
                    break;
                }
            }
            cur.updateVal(removeIndex,0);

        }
        for(int i=0;i<in.length();i++)
            for(int j=0;j<26;j++){
                if(fenwickList[j].data[i]==1) {
                    System.out.print(letters.charAt(j));
                    break;
                }
            }


    }
}
