import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by user on 3/10/2019.
 */
public class distinctIntegersRange {
    static class SegTree{

        int n; // array size
        BitSet[] tree;
        //Value such that func(x,identity) returns x
        //0 for addition, 1 for multiplication, etc.
        public SegTree(int[] in){
            n=in.length;
            int maxSize=1;
            while (maxSize<n)
                maxSize<<=1;
            tree = new BitSet[maxSize<<1];
            for(int i=0;i<tree.length;i++)
                tree[i]=new BitSet(5001);
            build(in);
        }

        // Max size of tree

        // function to build the tree
        void build( int []arr)
        {
            // insert leaf nodes in tree
            for (int i = 0; i < n; i++) {
                tree[n + i].set(arr[i],true);
            }

            // build the tree by calculating
            // parents
            for (int i = n - 1; i > 0; --i) {
                tree[i].or(tree[i<<1]);
                tree[i].or(tree[(i << 1) | 1]);
//                tree[i] = func(tree[i << 1],
//                        tree[(i << 1) | 1]);
            }
        }



        // function to get sum on
        // interval [l, r)
        BitSet query(int l, int r) {
            BitSet res = new BitSet(5001);
            // loop to find the sum in the range
            for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) > 0)
                    res.or(tree[l++]);
                if ((r & 1) > 0)
                    res.or(tree[--r]);
            }
            return res;
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[] a = new int[len];
        int[] b = new int[len];
        StringTokenizer tokenizer1 = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++)
            a[i]=Integer.parseInt(tokenizer1.nextToken());
        StringTokenizer tokenizer2 = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++)
            b[i]=Integer.parseInt(tokenizer2.nextToken());
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));

        SegTree segTreeA = new SegTree(a);
        SegTree segTreeB = new SegTree(b);
        int queries = Integer.parseInt(br.readLine());
        while (queries-->0){
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int la=Integer.parseInt(tokenizer.nextToken())-1;
            int ra=Integer.parseInt(tokenizer.nextToken());
            int lb=Integer.parseInt(tokenizer.nextToken())-1;
            int rb=Integer.parseInt(tokenizer.nextToken());
            BitSet set1 = segTreeA.query(la,ra);
            BitSet set2 = segTreeB.query(lb,rb);
            set1.or(set2);
            System.out.println(set1.cardinality());

        }

    }
}
