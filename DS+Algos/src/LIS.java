import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by user on 3/9/2019.
 */
public class LIS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for(int c=1;c<=cases;c++){
            int len = Integer.parseInt(br.readLine());
            int[] arr = new int[len];
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for(int i=0;i<len;i++){
                arr[i]=Integer.parseInt(tokenizer.nextToken());
            }
            int[] dpLIS = new int[len+1];
            for(int i=0;i<dpLIS.length;i++)
                dpLIS[i]=Integer.MAX_VALUE;
            dpLIS[0]=Integer.MIN_VALUE;
            dpLIS[1]=arr[0];
            for(int i=1;i<arr.length;i++){
                int insertIndex = Arrays.binarySearch(dpLIS,arr[i]);
                if(insertIndex<0){
                    for (int j = -insertIndex-2; j < dpLIS.length - 1; j++) {
                        if (arr[i] > dpLIS[j]) {
                            dpLIS[j + 1] = Math.min(arr[i], dpLIS[j + 1]);
                            break;
                        }
                    }
                }
                else{
                    for (int j = insertIndex; j < dpLIS.length - 1; j++) {
                        if (arr[i] > dpLIS[j]) {
                            dpLIS[j + 1] = Math.min(arr[i], dpLIS[j + 1]);
                            break;
                        }
                    }
                }

            }
            int index=dpLIS.length-1;
            while(dpLIS[index]==Integer.MAX_VALUE)index--;
            System.out.println(index);

        }

    }
}
