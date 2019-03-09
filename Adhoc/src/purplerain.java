import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class purplerain {

    static int subSetSum(ArrayList<Integer> in){
        int[] dp = new int[in.size()];
        dp[0]=Math.max(0,in.get(0));
        for(int i=1;i<dp.length;i++){
            dp[i]=Math.max(0,dp[i-1]+in.get(i));
        }
        int max = Arrays.stream(dp).max().getAsInt();

        return max;

    }
    static int l,r;
    static void getRange(ArrayList<Integer> in, int max){
        int[] dp = new int[in.size()];
        dp[0]=Math.max(0,in.get(0));
        for(int i=1;i<dp.length;i++){
            dp[i]=Math.max(0,dp[i-1]+in.get(i));
        }
        int index=0;
        for(int i=0;i<dp.length;i++){
            if(dp[i]==max){
                index=i;
                break;
            }
        }
        r=index;
//        System.out.println(in);
//        System.out.println(Arrays.toString(dp));
        while(index>0&&dp[index]>dp[index-1]){
            index--;
        }
        l=index;


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in = br.readLine();
        ArrayList<Integer> list1 = new ArrayList<>(in.length());
        ArrayList<Integer> list2 = new ArrayList<>(in.length());
        for(int i=0;i<in.length();i++){
            list1.add(in.charAt(i)=='B'?1:-1);
            list2.add(in.charAt(i)=='R'?1:-1);
        }
        int max1=subSetSum(list1);
        int max2=subSetSum(list2);

//        System.out.println(max1+"\t"+list1);
//        System.out.println(max2+"\t"+list2);


        if(max1>max2){
            getRange(list1,max1);
//            System.out.println(max1);
        }
        else {
            getRange(list2, max2);
//            System.out.println(max2);
        }
//        System.out.println(l+" "+r);
//        int sum1=0;
//        int sum2=0;
//        for(int i=0;i<l;i++)
//            sum1+=compressed.get(i);
//        for(int i=0;i<=r;i++)
//            sum2+=compressed.get(i);
//        System.out.println(compressed);

        System.out.println((l-1)+" "+(r+1));
//        System.out.println(sum1+" "+sum2);
    }
}
