#include <iostream>
#include <cstdint>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;

int n, m;
int N = 1e5 + 6;
int cost = 0;
vector<vector<pair<int, int>>> g(N);
vector<int> parent(N), dist(N);
const int inf = 1e9;
vector<bool> vis(N);

void primsMST(int source) {
    for (int i = 0; i < n; i++) {
        dist[i] = inf;
        parent[i] = -1;
        vis[i] = false;
    }

    set<pair<int, int>> s;
    dist[source] = 0;
    s.insert({0, source});

    while (!s.empty()) {
        auto x = *(s.begin());
        s.erase(x);

        int u = x.second;
        vis[u] = true;

        for (auto it : g[u]) {
            int v = it.first;
            int weight = it.second;

            if (!vis[v] && dist[v] > weight) {
                if (dist[v] != inf) {
                    s.erase({dist[v], v});
                }
                dist[v] = weight;
                parent[v] = u;
                s.insert({dist[v], v});
                cout<<"The included edges are: "<<u<<"  " <<v<< endl;

                cost += weight; 
            }
        }
    }
}

int32_t main() {
    cin >> n >> m;
    cout<<"give input in the form of source destination and weight associated with it:"<<endl;
    for (int i = 0; i < m; i++) {
        int u, v, w;

        cin >> u >> v >> w;
        
        g[u].push_back({v, w});
        g[v].push_back({u, w});
    }

    primsMST(1);

    cout << "The weight of the Minimum Spanning Tree is: " << cost << endl;
    return 0;
}
