#include <iostream>
#include <cmath>
using namespace std;

struct Node {
    int data;
    Node* left;
    Node* right;

    Node(int data) {
        this->data = data;
        left = nullptr;
        right = nullptr;
    }
};

// Function declarations
Node* insert(Node* root, int data);
void inorder(Node* root);
bool search(Node* root, int data);
int height(Node* root);
void levelOrder(Node* root);
void printLevel(Node* root, int level);
Node* deleteNode(Node* root, int data);
Node* minValueNode(Node* node);
Node* mirror(Node* root);
void printTree(Node* root);
void printLevelStructure(Node* root, int level, int space);

Node* insert(Node* root, int data) {
    if (root == nullptr) {
        return new Node(data);
    }
    if (data < root->data) {
        root->left = insert(root->left, data);
    } else {
        root->right = insert(root->right, data);
    }
    return root;
}

void inorder(Node* root) {
    if (root == nullptr) return;
    inorder(root->left);
    cout << root->data << " ";
    inorder(root->right);
}

bool search(Node* root, int data) {
    if (root == nullptr) return false;
    if (root->data == data) return true;
    if (data < root->data) return search(root->left, data);
    return search(root->right, data);
}

int height(Node* root) {
    if (root == nullptr) return 0;
    int leftHeight = height(root->left);
    int rightHeight = height(root->right);
    return 1 + max(leftHeight, rightHeight);
}

void levelOrder(Node* root) {
    int h = height(root);
    for (int i = 1; i <= h; i++) {
        printLevel(root, i);
        cout << endl;
    }
}

void printLevel(Node* root, int level) {
    if (root == nullptr) return;
    if (level == 1) {
        cout << root->data << " ";
    } else if (level > 1) {
        printLevel(root->left, level - 1);
        printLevel(root->right, level - 1);
    }
}

Node* deleteNode(Node* root, int data) {
    if (root == nullptr) return root;

    if (data < root->data) {
        root->left = deleteNode(root->left, data);
    } else if (data > root->data) {
        root->right = deleteNode(root->right, data);
    } else {
        // Node to be deleted found
        if (root->left == nullptr) {
            Node* temp = root->right;
            delete root;
            return temp;
        } else if (root->right == nullptr) {
            Node* temp = root->left;
            delete root;
            return temp;
        }

        // Node with two children: get the inorder successor (smallest in the right subtree)
        Node* temp = minValueNode(root->right);

        // Copy the inorder successor's content to this node
        root->data = temp->data;

        // Delete the inorder successor
        root->right = deleteNode(root->right, temp->data);
    }
    return root;
}

Node* minValueNode(Node* node) {
    Node* current = node;
    while (current && current->left != nullptr) {
        current = current->left;
    }
    return current;
}

Node* mirror(Node* root) {
    if (root == nullptr) return root;
    swap(root->left, root->right);
    mirror(root->left);
    mirror(root->right);
    return root;
}

void printTree(Node* root) {
    if (root == nullptr) {
        cout << "Tree is empty." << endl;
        return;
    }

    int treeHeight = height(root);
    int space = pow(2, treeHeight) - 1;

    for (int level = 1; level <= treeHeight; level++) {
        printLevelStructure(root, level, space);
        cout << endl;
        space /= 2;
    }
}

void printLevelStructure(Node* root, int level, int space) {
    if (root == nullptr) {
        for (int i = 0; i < space * 2; i++) {
            cout << " "; 
        }
        return;
    }

    if (level == 1) {
        for (int i = 0; i < space; i++) {
            cout << " "; 
        }
        cout << root->data;
        for (int i = 0; i < space; i++) {
            cout << " "; 
        }
    } else {
        printLevelStructure(root->left, level - 1, space);
        printLevelStructure(root->right, level - 1, space);
    }
}

int main() {
    Node* root = nullptr;

    root = insert(root, 50);
    root = insert(root, 30);
    root = insert(root, 20);
    root = insert(root, 40);
    root = insert(root, 70);
    root = insert(root, 60);
   
    cout << "Tree Structure:" << endl;
    printTree(root);

    cout << "\nIn-order traversal: ";
    inorder(root);
    cout << endl;

    int searchValue = 40;
    if (search(root, searchValue)) {
        cout << "Node " << searchValue << " found!" << endl;
    } else {
        cout << "Node " << searchValue << " not found!" << endl;
    }

    cout << "\nHeight of the tree: " << height(root) << endl;

    cout << "\nLevel-order traversal:" << endl;
    levelOrder(root);

    int deleteValue = 20;
    cout << "\nDeleting node " << deleteValue << "..." << endl;
    root = deleteNode(root, deleteValue);

    cout << "\nTree Structure after deletion:" << endl;
    printTree(root);

    root = mirror(root);
    cout << "\nMirrored Tree Structure:" << endl;
    printTree(root);

    return 0;
}
