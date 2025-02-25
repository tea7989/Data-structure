#include <iostream>
//#include "bits/std++.h"
#include <cstdint>
#include<vector>
#include <algorithm>
using namespace std;

const int N=1e5+6;
vector<int> parent(N);
vector<int> sz(N);

void make_set(int v){
    parent[v]=v;
    sz[v]=1;
}
int find_set(int v){
    if(v==parent[v]){
        return v;
    }
    return parent[v]=find_set(parent[v]);
}

void union_set(int a, int b){
    a=find_set(a);
    b=find_set(b);
    if(a!=b){
        if(sz[a]<sz[b]){
            swap(a,b);
        }
        parent[b]=a;
        sz[a]+=sz[b];
    }
}

int32_t main(){
    for (int i = 0; i < N; i++)
    {
        make_set(i);
    }
    cout<<"n for vertices and m for edges"<<endl;
    int n,m; 
    cin>>n>>m;
    vector<vector<int>> edges;
    cout<<"input the src then dest and its weight"<<endl;
    for (int i = 0; i < m; i++)
    {
        int u,v,w;cin>>u>>v>>w;
        edges.push_back({w,u,v});
    }
    cout<<" the included vertices, edges and their vertices are \n";
    int cost=0;
    sort(edges.begin(),edges.end());
    for(auto i:edges){
        int w=i[0];
        int u=i[1];
        int v=i[2];
        int x=find_set(u);
        int y=find_set(v);
        if(x==y)
           continue;
        else{
            
            cost+=w;
            cout<<u<<" "<<v<<" "<<w<<"\n";
            union_set(u,v);
        }
    }
    cout<<cost;
}