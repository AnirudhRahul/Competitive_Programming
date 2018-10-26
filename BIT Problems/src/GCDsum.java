import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class GCDsum{
    final static int MOD= (int) (10e9+7);
    static int curNum=0;
    public static int eval(int x){
        ArrayList<primeFactor> factors=factor(x);
//        System.out.println(x+":"+factors);
        curNum=x;
        return recurse(factors,x);
    }
    static class Pair{int a,b;
        public Pair(int a,int b){this.a=a;this.b=b;}
    }
    static Pair[] dpdp=new Pair[500000];
    static int recurse(ArrayList<primeFactor> factors,int val){
        if(dpdp[val-1]!=null) {
//            System.out.println("CurNume:"+curNum+"\t"+val+"\t"+dpdp[val - 1].a * curNum / dpdp[val - 1].b);
            return dpdp[val - 1].b * curNum / dpdp[val - 1].b;
        }
        int sum=curNum/val*phi(factors,val)%MOD;
//        System.out.println("Called:"+factors+"\t"+sum);
        for(int i=0;i<factors.size();i++){
            if(factors.get(i).exp==0)
                continue;
            factors.get(i).exp--;
            sum+=recurse(factors,val/factors.get(i).prime);
            factors.get(i).exp++;

        }
        dpdp[val-1]=new Pair(sum,curNum);
        return sum;

    }
    static int[] dp=new int[500000];
    static int phi(ArrayList<primeFactor> factors,int val){
        int product=1;
        if(val==1)
            return 1;
        if(dp[val-1]!=0)
            return dp[val-1];
        for(int i=0;i<factors.size();i++){
            if(factors.get(i).exp==0)
                continue;

            product*=factors.get(i).prime-1;
//            for(int k=1;k<factors.get(i).exp;k++)
//                product*=factors.get(i).prime;
            product*=Math.pow(factors.get(i).prime,factors.get(i).exp-1);
        }
         return dp[val-1]=product;
    }

    static class primeFactor{
        int prime,exp;
        public primeFactor(int prime,int exp){
            this.prime=prime;
            this.exp=exp;
        }
        public String toString(){return prime+"^"+exp;}
    }

    public static ArrayList<primeFactor> factor(int val){
        ArrayList<primeFactor> list=new ArrayList<>();
        for(int i=2;i<=Math.sqrt(val);i++){
            int count=0;
            while(val%i==0) {
                val /= i;
                count++;
            }
            if(count!=0)
                list.add(new primeFactor(i,count));
        }
        if(val!=1)
            list.add(new primeFactor(val,1));
        return list;
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
//        Arrays.fill(dpdp,-1);
//        eval(2);
//        ArrayList<primeFactor> l=new ArrayList<>();
//        l.add(new primeFactor(2,0));
//        System.out.println(phi(l));

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int size=Integer.parseInt(br.readLine());
        int[] arr=new int[size];
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        for(int i=0;i<size;i++)
            arr[i]=Integer.parseInt(stringTokenizer.nextToken());

        int queries=Integer.parseInt(br.readLine());
        HashMap<Integer,Integer> values=new HashMap<>();
        for(int i=2;i<=10;i++){
            System.out.println(i+":"+eval(i));
//            values.put(i,eval(i));
        }
        int[] newArr=new int[size];
        for(int i=0;i<newArr.length;i++)
            newArr[i]=values.get(arr[i]);
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
                tree.updateVal(x-1,values.get(y));
            }

        }
    }
}
