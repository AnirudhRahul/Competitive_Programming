import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 9/22/2019.
 */
public class medianUpdates {

    static class Query{
        int value;
        boolean add;
        public Query(String line){
            StringTokenizer tokenizer = new StringTokenizer(line);
            add = tokenizer.nextToken().equals("a");
            value = Integer.parseInt(tokenizer.nextToken());
        }

    }

    static class BIT {
        int[] tree;
        int[] data;
        int size=0;
        public BIT(int[] in){
            data=in;
            tree=new int[data.length+1];
            for(int i=1;i<tree.length;i++){
                int index=i;
                int val=data[i-1];
                while(index<tree.length){
                    tree[index]+=val;
                    index+=Integer.lowestOneBit(index);
                }

            }
        }
        public boolean remove(int index){
            if(data[index]>0){
                updateDelta(index,-1);
                size--;
                return true;
            }
            else{
                return false;
            }
        }

        public void add(int index){
            updateDelta(index,1);
            size++;
        }

        public double median(){
            int med = (size+1)/2;

            int start = 5;
            int end = data.length-6;
            System.out.println("Data: "+Arrays.toString(data));

            if(size%2==1){
                int target = (size+1)/2;
                while(end-start>4) {
                    System.out.println(start+" "+end);
                    int mid = (start + end) / 2;
                    int cur = rangeSum(mid);
                    if (target > cur - data[mid] && cur <= target) {
                        return mid;
                    } else if (cur < target) {
                        start = mid;
                    } else if (cur > target) {
                        end = mid;
                    }
                }
                while (end-->start){
                    int cur = rangeSum(end);
                    if(cur<target)
                        return end+1;
                    if(cur==target)
                        return end;
                }


            }

            return 0;

        }

        //sum from 0 to endIndex inclusive
        public int rangeSum(int endIndex){
            endIndex++;
            int sum=0;
            while(endIndex>0){
                sum+=tree[endIndex];
                endIndex-=Integer.lowestOneBit(endIndex);
            }
            return sum;
        }
        //sum from l to r inclusive
        public int rangeSum(int l, int r){
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int queries = Integer.parseInt(br.readLine());
        Query[] queryList=new Query[queries];
        TreeSet<Integer> distinctValues = new TreeSet<>();

        for(int i=0;i<queries;i++) {
            queryList[i] = new Query(br.readLine());
            distinctValues.add(queryList[i].value);
        }

        HashMap<Integer,Long> mapping = new HashMap<>();
        HashMap<Integer,Integer> reverse = new HashMap<>();

        int startIndex = 1;
        long gap = 100000;
        for(int val: distinctValues){
            mapping.put(val,startIndex*gap);
            startIndex++;
        }
        for(int i=0;i<queries;i++){

        }

        //Add 5 padding on both sides
        int[] freq = new int[startIndex+5];
        BIT tree = new BIT(freq);
        for(Query q: queryList){
            int compressed = mapping.get(q.value);

            if(q.add){
                tree.add(compressed);
                System.out.println(tree.median());

            }
            else{
                if(!tree.remove(compressed))
                    System.out.println("Wrong!");
                else if(tree.size==0)
                    System.out.println("Wrong!");
                else
                    System.out.println(tree.median());
            }

        }



    }
}
