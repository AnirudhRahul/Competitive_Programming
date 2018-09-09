import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by user on 9/8/2018.
 */
public class Inversions{
    int[] list;
    int count;
    public Inversions(int[] in){
        list=in.clone();
        Arrays.sort(in);
        HashMap<Integer,Integer> compressMap=new HashMap<>();
        for(int i=0;i<in.length;i++){
            compressMap.put(in[i],i);
        }
        for(int i=0;i<list.length;i++)
            list[i]=compressMap.get(list[i]);
        int[] empty=new int[in.length];
        BIT fenwick=new BIT(empty);
        for(int i=0;i<list.length;i++){
            count+=fenwick.rangeSum(list[i],list.length-1);
            fenwick.updateDelta(list[i],1);
        }
    }
}
