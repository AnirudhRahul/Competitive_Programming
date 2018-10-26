import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class GCDsum{
    final static int MOD= 1000*1000*1000+7;
    static long curNum=0;

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
                sum%=MOD;
                endIndex-=Integer.lowestOneBit(endIndex);
            }
            return sum;
        }
        //sum from l to r inclusive
        public long rangeSum(int l, int r){
            return Math.floorMod(rangeSum(r)-rangeSum(l-1),MOD);
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
    static long[]  phiValues=new long[500001];
    static long[] fValues=new long[500001];

    public static void main(String[] args) throws IOException {
        //Init array
        for (int i = 1; i < phiValues.length; i++)
            phiValues[i] = i;

        for (int p = 2; p <phiValues.length; p++) {
            //p is prime
            if (phiValues[p] == p) {
                phiValues[p] = p - 1;
                // Update multiples of p
                for (int i = 2 * p; i < phiValues.length; i += p) {
                    phiValues[i] = (phiValues[i] / p) * (p - 1);
                }
            }
        }

        for(int i=1;i<fValues.length;i++){
            for(int j=i;j<fValues.length;j+=i){
                fValues[j]+=phiValues[i]*j/i;
                fValues[j]%=MOD;
            }
        }
//        System.out.println(Arrays.toString(fValues));

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int size=Integer.parseInt(br.readLine());
        int[] arr=new int[size];
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        for(int i=0;i<size;i++)
            arr[i]=Integer.parseInt(stringTokenizer.nextToken());

        int queries=Integer.parseInt(br.readLine());

        int[] newArr=new int[size];
        for(int i=0;i<newArr.length;i++)
            newArr[i]= (int) fValues[arr[i]];
//        System.out.println(Arrays.toString(newArr));
        BIT tree=new BIT(newArr);
        while (queries-->0){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            String type=tokenizer.nextToken();
            int x=Integer.parseInt(tokenizer.nextToken());
            int y=Integer.parseInt(tokenizer.nextToken());
            if(type.equals("C")){
                System.out.println(tree.rangeSum(x-1,y-1));
            }
            else{
                tree.updateVal(x-1, (int) fValues[y]);
            }

        }
    }
}
