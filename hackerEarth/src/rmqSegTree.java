import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 3/10/2019.
 */
public class rmqSegTree {

    static abstract class SegTree{
        // limit for array size
//        int N = 10000;

        int n; // array size
        int [] tree;
        //Value such that func(x,identity) returns x
        //0 for addition, 1 for multiplication, etc.
        int identity;
        public SegTree(int[] in, int identity){
            this.identity=identity;
            n=in.length;
            int maxSize=1;
            while (maxSize<n)
                maxSize<<=1;
            tree = new int[maxSize<<1];
            build(in);
        }

        // Max size of tree

        // function to build the tree
        void build( int []arr)
        {
            // insert leaf nodes in tree
            for (int i = 0; i < n; i++)
                tree[n + i] = arr[i];

            // build the tree by calculating
            // parents
            for (int i = n - 1; i > 0; --i)
                tree[i]= func(tree[i << 1],
                        tree[(i << 1) | 1]);
        }

        // function to update a tree node
        void updateNode(int p, int value)
        {
            // set value at position p
            tree[p + n] = value;
            p = p + n;
            // move upward and update parents
            for (int i = p; i > 1; i >>= 1)
                tree[i >> 1] = func(tree[i], tree[i^1]);
        }

        // function to get sum on
        // interval [l, r)
        int query(int l, int r) {
            int res = identity;
            // loop to find the sum in the range
            for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) > 0)
                    res = func(res, tree[l++]);
                if ((r & 1) > 0)
                    res = func(res, tree[--r]);
            }
            return res;
        }

        //Function you applying on the range examples include:
        // a+b
        // gcd(a,b)
        // a*b
        // max(a,b)
        // min(a,b)
        abstract int func(int a, int b);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(stringTokenizer.nextToken());
        int queries = Integer.parseInt(stringTokenizer.nextToken());
        int[] arr = new int[len];
        StringTokenizer tokenizer1 = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++){
            arr[i]=Integer.parseInt(tokenizer1.nextToken());
        }
//        System.out.println(Arrays.toString(arr));
        SegTree segTree = new SegTree(arr,Integer.MAX_VALUE) {
            @Override
            int func(int a, int b) {
                return Math.min(a,b);
            }
        };

        for(int i=0;i<queries;i++){
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            String type = tokenizer.nextToken();
            int l = Integer.parseInt(tokenizer.nextToken())-1;
            int r = Integer.parseInt(tokenizer.nextToken());
            if(type.equals("q")){
                System.out.println(segTree.query(l,r));
            }
            else{
                segTree.updateNode(l,r);
            }

        }
    }
}
