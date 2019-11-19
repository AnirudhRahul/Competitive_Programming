import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class cowpatibility {
    static final long MOD=Long.parseLong("29996224275833");
    private static long[] modPows;

    public static long hash(long[] in){
        long[] hash=new long[in.length+1];
        hash[0]=0;
        for(int i=1;i<in.length+1;i++)
            hash[i]=(hash[i-1]+modPows[i-1]*(in[i-1]))%MOD;
        return hash[in.length];
    }
    public static void main(String args[]) throws IOException {
        String name = "cowpatibility";
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        modPows=new long[10];
        modPows[0]=1;
        for(int i=1;i<modPows.length;i++)
            modPows[i]=(modPows[i-1]*(1000003))%MOD;
        BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(name + ".out")));
        int cows=Integer.parseInt(br.readLine());
//        int[] seenFlavors=new int[(int) 1e6];
        int flavs=5;
        int[][] flavors=new int[cows][flavs];
        for(int i=0;i<cows;i++){
            StringTokenizer tokenizer=new StringTokenizer(br.readLine());
            for(int k=0;k<flavs;k++)
                flavors[i][k]=Integer.parseInt(tokenizer.nextToken());

        }
        for(int i=0;i<cows;i++)
        Arrays.sort(flavors[i]);
//        for(int[] e:flavors)
//            System.out.println(Arrays.toString(e));


        int singles=0;
        HashMap<Integer,Integer> seenSingles=new HashMap<>();
        for(int i=0;i<cows;i++){
            for(int k=0;k<flavs;k++) {
                if(seenSingles.containsKey(flavors[i][k])) {
                    singles+=seenSingles.get(flavors[i][k]);
                    seenSingles.put(flavors[i][k], seenSingles.get(flavors[i][k]) + 1);
                }
                else
                    seenSingles.put(flavors[i][k],1);
            }

        }

        int seenCounter2=0;
        HashMap<Long,Integer> seen2=new HashMap<>();
        for(int i=0;i<cows;i++){
            for(int a=0;a<flavs;a++) {
                for(int b=a+1;b<flavs;b++) {
                    long hash=hash(new long[]{flavors[i][a],flavors[i][b]});
                    if (seen2.containsKey(hash)) {
                        seenCounter2 += seen2.get(hash);
                        seen2.put(hash,seen2.get(hash)+1);
                    }
                    else
                        seen2.put(hash,1);
                }
            }
        }

        int seenCounter3=0;
        HashMap<Long,Integer> seen3=new HashMap<>();
        for(int i=0;i<cows;i++){
            for(int a=0;a<flavs;a++) {
                for(int b=a+1;b<flavs;b++) {
                    for(int c=b+1;c<flavs;c++) {
                        long hash = hash(new long[]{flavors[i][a], flavors[i][b],flavors[i][c]});
                        if (seen3.containsKey(hash)) {
                            seenCounter3 += seen3.get(hash);
                            seen3.put(hash,seen3.get(hash)+1);
                        }
                        else
                            seen3.put(hash,1);
                    }
                }
            }
        }

        int seenCounter4=0;
        HashMap<Long,Integer> seen4=new HashMap<>();
        for(int i=0;i<cows;i++){
            for(int a=0;a<flavs;a++) {
                for(int b=a+1;b<flavs;b++) {
                    for(int c=b+1;c<flavs;c++) {
                        for(int d=c+1;d<flavs;d++){
                            long hash = hash(new long[]{flavors[i][a], flavors[i][b], flavors[i][c], flavors[i][d]});
                            if (seen4.containsKey(hash)) {
                                seenCounter4 += seen4.get(hash);
                                seen4.put(hash,seen4.get(hash)+1);
                            }
                            else
                                seen4.put(hash,1);
                        }
                    }
                }
            }
        }

        int seenCounter5=0;
        HashMap<Long,Integer> seen5=new HashMap<>();
        for(int i=0;i<cows;i++){
            for(int a=0;a<flavs;a++) {
                for(int b=a+1;b<flavs;b++) {
                    for(int c=b+1;c<flavs;c++) {
                        for(int d=c+1;d<flavs;d++){
                            for(int e=d+1;e<flavs;e++) {
                                long hash = hash(new long[]{flavors[i][a], flavors[i][b], flavors[i][c], flavors[i][d], flavors[i][e]});
                                if (seen5.containsKey(hash)) {
                                    seenCounter5 += seen5.get(hash);
                                    seen5.put(hash,seen5.get(hash)+1);
                                }
                                else
                                    seen5.put(hash,1);
                            }
                        }
                    }
                }
            }
        }


        int complement=singles-seenCounter2+seenCounter3-seenCounter4+seenCounter5;
        System.out.println(singles);
        System.out.println(seenCounter2);
        System.out.println(seenCounter3);
        System.out.println(seenCounter4);
        System.out.println(seenCounter5);

        System.out.println(cows*(cows-1)/2-complement);
//
//        if(seenCounter5==cows)
//            pw.println(0);
//        else
        pw.println(cows*(cows-1)/2-complement);
        pw.close();
    }
}