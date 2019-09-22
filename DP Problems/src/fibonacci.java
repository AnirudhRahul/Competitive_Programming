/**
 * Created by user on 8/11/2019.
 */
public class fibonacci {

    static int fib(int n){
        if(n==0||n==1)
            return 1;
        return fib(n-1)+fib(n-2);
    }

//    static int[] list;

    static boolean subsetSum(int target, int index){
        if(target==0)
            return true;
        if(index==list.length)
            return false;

        return subsetSum(target,index+1)
                ||
                subsetSum(target-list[index],index+1);

    }

//    static int[] list;

//    static int recurse(int index){
//        if(index==list.length)
//            return 0;
//
//        return list[index]+Math.max(0,recurse(index-1));
//    }

    static int[] list;

    static int lis(int index, int lastTerm){
        if(index==list.length)
            return 0;

        int cur = list[index];

        if(cur>lastTerm)
            return Math.max(
                    1+lis(index+1,cur),
                    lis(index+1,lastTerm));
        else
            return lis(index+1,lastTerm);

    }

    static int[] values;
    static int[] weights;

    static String a;
    static String b;
    static String combo;
    static boolean recurse(int index1, int index2){
        int comboIndex = index1+index2;
        boolean match1 = combo.charAt(comboIndex)==a.charAt(index1);
        boolean match2 = combo.charAt(comboIndex)==b.charAt(index2);

        if(match1&&match2){
            return recurse(index1+1,index2) ||
                   recurse(index1,index2+1);
        }
        else if(match1){
            return recurse(index1+1,index2);
        }
        else if(match2){
            return recurse(index1,index2+1);
        }
        else{
            return false;
        }


    }

    public static void main(String[] args){

    }
}
