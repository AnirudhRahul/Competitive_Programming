import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 7/21/2018.
 */
//Not working yet
public class familyDAG {
    static class Ancestor{
        String myName;
        HashMap<String,Integer> map;
        public Ancestor(String myName){
            this.myName=myName;
            map=new HashMap<String,Integer>();
        }
        public void add(String in){
            if(map.containsKey(in))
                map.put(in,map.remove(in)+1);
            else
                map.put(in,1);
        }
        public boolean isHillyBilly(){
            for(int e:map.values())
                if(e>=2)
                    return true;
            return false;
        }
        public boolean isParadox(){
            return map.containsKey(myName);
        }
        public String toString(){
            return map.toString();
        }

        public void add(Ancestor ancestor) {
            for(String e:ancestor.map.keySet()){
                if(map.containsKey(e)){
                    map.put(e,map.remove(e)+ancestor.map.get(e));
                }
                else{
                    map.put(e,ancestor.map.get(e));
                }
            }
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Input :[
        while (true) {
            HashMap<String, HashSet<String>> edges = new HashMap<>();
            HashMap<String, Integer> inDegree = new HashMap<>();
            while (true) {
                String in = br.readLine();
                if(in==null)
                    return;
                if (in.equals("done"))
                    break;
                StringTokenizer tokenizer = new StringTokenizer(in);
                String parent = tokenizer.nextToken();
                tokenizer.nextToken();
                String child = tokenizer.nextToken();
                if(edges.containsKey(parent)&&edges.get(parent).contains(child))
                    continue;
                if (edges.containsKey(parent)) {
                    edges.get(parent).add(child);
                } else {
                    HashSet<String> temp = new HashSet<>(2);
                    temp.add(child);
                    edges.put(parent, temp);
                }
                if (inDegree.containsKey(child))
                    inDegree.put(child, inDegree.get(child) + 1);
                else
                    inDegree.put(child, 1);
                if (!inDegree.containsKey(parent))
                    inDegree.put(parent, 0);

            }

            ArrayDeque<String> q = new ArrayDeque<String>();
            for (String e : inDegree.keySet())
                if (inDegree.get(e) == 0)
                    q.add(e);
//        System.out.println(q);
//        System.out.println(edges);
//        System.out.println("In: "+inDegree);

            HashMap<String, Ancestor> list = new HashMap<>();
            for (String e : inDegree.keySet())
                list.put(e, new Ancestor(e));

            while (!q.isEmpty()) {
                String cur = q.pop();
                if (edges.containsKey(cur))
                    for (String e : edges.get(cur)) {
                        list.get(e).add(cur);
                        list.get(e).add(list.get(cur));
                        inDegree.put(e, inDegree.remove(e) - 1);
                        if (inDegree.get(e) == 0)
                            q.add(e);
                    }
            }
//        System.out.println(list);
//        System.out.println(inDegree);
            ArrayList<String> names = new ArrayList<>();

            for (String e : inDegree.keySet()) {
                names.add(e);
            }
            Collections.sort(names);
            for (int i = 0; i < names.size(); i++) {
                String cur = names.get(i);
                if (inDegree.get(cur) != 0 || list.get(cur).isParadox()) {
                    System.out.println(cur + " paradox");
                } else if (list.get(cur).isHillyBilly()) {
                    System.out.println(cur + " hillbilly");
                }
            }
            System.out.println();
        }
    }

}
