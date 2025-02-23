#include <iostream>
#include <list>
using namespace std;

void merge(list<int> &arr, list<int>::iterator st, list<int>::iterator mid, list<int>::iterator e) {
    list<int> temp;
    auto i = st, j = mid;
    
    while (i != mid && j != e) {
        if (*i < *j) {
            temp.push_back(*i);
            i++;
        } else {
            temp.push_back(*j);
            j++;
        }
    }

    while (i != mid) {
        temp.push_back(*i);
        i++;
    }
    
    while (j != e) {
        temp.push_back(*j);
        j++;
    }
    
    for (auto it = temp.begin(); it != temp.end(); ++it) {
        *st = *it;
        ++st;
    }
}

void mergeso(list<int> &arr, list<int>::iterator start, list<int>::iterator end) {
    if (distance(start, end) > 1) {
        auto mid = start;
        advance(mid, distance(start, end) / 2);

        mergeso(arr, start, mid);
        mergeso(arr, mid, end);
        merge(arr, start, mid, end);
    }
}

void print(const list<int> &arr) {
    for (auto it = arr.begin(); it != arr.end(); ++it) {
        cout << *it << " ";
    }
    cout << endl;
}

int main() {
    list<int> arr = {9, 5, 2, 6, 7, 8};
    
    mergeso(arr, arr.begin(), arr.end());
    
    cout << "Sorted array: ";
    print(arr);
    
    auto minElem = *arr.begin();
    auto maxElem = *arr.rbegin();

    cout << "Minimum element: " << minElem << endl;
    cout << "Maximum element: " << maxElem << endl;

    return 0;
}
