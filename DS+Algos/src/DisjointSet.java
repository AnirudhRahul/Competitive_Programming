/**
 * Created by user on 7/27/2018.
 */
//Uses path compression and union by rank
class DisjointSet {
    int[] parent;
    int[] rank;
    public DisjointSet(int size){
        parent=new int[size];
        rank=new int[size];
        for(int i=0;i<size;i++)
            parent[i]=i;
    }

    public int find(int x){
        if(parent[x]==x)
            return x;
        else
            return parent[x]=find(parent[x]);
    }

    public void union(int x, int y){
        int xRoot=find(x);
        int yRoot=find(y);
        if (rank[xRoot] < rank[yRoot])
            parent[xRoot]=yRoot;
        else if(rank[yRoot] < rank[xRoot])
            parent[yRoot]=xRoot;
        else {
            parent[yRoot]=xRoot;
            rank[xRoot]++;
        }

    }


}



