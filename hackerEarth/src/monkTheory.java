import javafx.scene.input.InputMethodTextRun;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by user on 11/26/2018.
 */
public class monkTheory{
    static ArrayList<Integer> sieveOfEratosthenes(int n)
    {
        ArrayList<Integer> primes=new ArrayList<>();
        boolean prime[] = new boolean[n+1];
        for(int i=0;i<n;i++)
            prime[i] = true;

        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
                primes.add(i);
        }
        return primes;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int len=Integer.parseInt(br.readLine());
        int[] nums=new int[len];
        StringTokenizer tokenizer=new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++){
            nums[i]=Integer.parseInt(tokenizer.nextToken());
        }
        int queries=Integer.parseInt(br.readLine());
        HashMap<Integer,Integer> cache=new HashMap<>();
        HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
        ArrayList<Integer> primes=sieveOfEratosthenes((int)2e5);
        for(int i=0;i<primes.size();i++){
            ArrayList<Integer> list=new ArrayList<>();
            for(int e:nums)
                if(e%primes.get(i)==0)
                    list.add(e);
            if(list.size()!=0)
            map.put(primes.get(i),list);
        }
        System.out.println(map);
//        System.out.println(primes);
        while(queries-->0){
            StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(stringTokenizer.nextToken());
            int b=Integer.parseInt(stringTokenizer.nextToken());
            int largePa=-1,largePb=-1;
            for(int i=primes.size()-1;i>=0;i--){
                if(a%primes.get(i)==0) {
                    largePa = primes.get(i);
                    break;
                }
            }
            for(int i=primes.size()-1;i>=0;i--){
                if(b%primes.get(i)==0) {
                    largePb = primes.get(i);
                    break;
                }
            }
            int hash= (int) (a*1e6+b);
            if(cache.containsKey(hash))
                System.out.println(cache.get(hash));
            else {
                int val=0;
                HashSet<Integer> divisible=new HashSet<>();
                for(int e:map.get(largePa))
                    if(e%a==0)
                        divisible.add(e);
                for(int e:map.get(largePb))
                    if(e%b==0)
                        divisible.add(e);
                val=divisible.size();
                cache.put(hash,val);
                System.out.println(val);
            }
        }
    }
}
