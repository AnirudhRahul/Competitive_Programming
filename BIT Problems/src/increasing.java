import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.StringTokenizer;

public class increasing{
    static class BIT {
        long[] tree;
        int[] data;
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
    final static long mod=1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\Anirudh\\IdeaProjects\\Competitive_Programming\\BIT Problems\\src\\increasing.in"));
        int cases=Integer.parseInt(br.readLine());

        for(int d=1;d<=cases;d++){
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            int len=Integer.parseInt(stringTokenizer.nextToken());
            int choose=Integer.parseInt(stringTokenizer.nextToken());
            BIT tree=new BIT(len+10);
            int[] order=new int[len];
            int[] sorted=new int[len];
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            for(int i=0;i<len;i++) {
                order[i] = Integer.parseInt(tokenizer.nextToken());
                sorted[i] = order[i];
            }
            HashMap<Integer,Integer> compress=new HashMap<>();
            Arrays.sort(sorted);
            for(int i=0;i<sorted.length;i++)
                compress.put(sorted[i],i+1);
            for(int i=0;i<len;i++)
                order[i]=compress.get(order[i]);
            long ans=0;
            for (int i=0;i<order.length;i++){
                tree.updateDelta(order[i],1);
                long sum=tree.rangeSum(0,order[i]-1);
                BigInteger product=BigInteger.ONE;
                if(sum<choose-1)
                    continue;

                //sum choose (choose-1)
                for(int j=0;j<choose-1;j++){
                    product=product.multiply(BigInteger.valueOf(sum-j));
                }
                for(int j=1;j<=choose-1;j++) {
                    BigInteger integer=BigInteger.valueOf(j);

//                    integer=integer.modInverse(BigInteger.valueOf(mod));
                    product=product.divide(integer);
//                    product/=j;
                }
                product=product.mod(BigInteger.valueOf(mod));
//                System.out.println(order[i]+":"+product);
                if(choose-1==sum){
                    System.out.println("ans:"+product.longValueExact());
                }

                ans+=product.longValueExact();
                ans%=mod;
            }
            System.out.println("Class #"+d+": "+ans);

        }
    }
}
