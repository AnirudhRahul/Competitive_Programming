class SimpleDisjointSet {
    int[] parent;
    public SimpleDisjointSet(int size){
        parent=new int[size];
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
        parent[find(x)]=find(y);
    }


}