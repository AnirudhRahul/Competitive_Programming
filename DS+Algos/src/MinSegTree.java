import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

/**
 * Created by user on 7/21/2018.
 */
abstract class SegTree{

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

    //Function being applied on the range examples include:
    // a+b, identity:0
    // gcd(a,b), identity:0
    // a*b, identity:1
    // max(a,b), identity: Integer.MIN
    // min(a,b) identity: Integer.MAX
    abstract int func(int a, int b);

}
