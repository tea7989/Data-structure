import java.util.LinkedList;
import java.util.Queue;

public class Tree {

    public static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static class BinaryTree {
        private static int index = -1;

        public static Node buildTree(int[] nodes) {
            index++;
            if (index >= nodes.length || nodes[index] == -1) {
                return null;
            }

            Node newNode = new Node(nodes[index]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }

        public static void preOrder(Node root) {
            if (root == null) {
                return;
            }
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        public static void inOrder(Node root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }

        public static void postOrder(Node root) {
            if (root == null) {
                return;
            }
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }

        public static int countNodes(Node root) {
            if (root == null) {
                return 0;
            }
            int leftNode = countNodes(root.left);
            int rightNode = countNodes(root.right);
            return leftNode + rightNode + 1;
        }

        public static int sumNodes(Node root) {
            if (root == null) {
                return 0;
            }
            int leftSum = sumNodes(root.left);
            int rightSum = sumNodes(root.right);
            return leftSum + rightSum + root.data;
        }

        static class TreeInfo {
            int ht;
            int dia;

            TreeInfo(int ht, int dia) {
                this.ht = ht;
                this.dia = dia;
            }
        }

        public static int height(Node root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            int myHeight = Math.max(leftHeight, rightHeight) + 1;
            return myHeight;
        }

        public static int diameter(Node root) {
            if (root == null) {
                return 0;
            }

            int dia1 = diameter(root.left);
            int dia2 = diameter(root.right);
            int dia3 = height(root.left) + height(root.right) + 1;

            return Math.max(Math.max(dia1, dia2), dia3);
        }

        public static TreeInfo diameter2(Node root) {
            if (root == null) {
                return new TreeInfo(0, 0);
            }

            TreeInfo left = diameter2(root.left);
            TreeInfo right = diameter2(root.right);

            int myHeight = Math.max(left.ht, right.ht) + 1;

            int dia1 = left.dia;
            int dia2 = right.dia;
            int dia3 = left.ht + right.ht + 1;

            int myDiameter = Math.max(Math.max(dia1, dia2), dia3);

            return new TreeInfo(myHeight, myDiameter);
        }

        public static void levelOrder(Node root) {
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            if (root == null) {
                return;
            }
            while (!q.isEmpty()) {
                Node currNode = q.remove();
                if (currNode == null) {
                    System.out.println();
                    if (q.isEmpty()) {
                        break;
                    } else {
                        q.add(null);
                    }
                } else {
                    System.out.print(currNode.data + " ");
                    if (currNode.left != null) {
                        q.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        q.add(currNode.right);
                    }
                }
            }
        }

        public static int kSum(Node root,int k) {
            if (root == null) {
                return 0;
            }
            Queue<Node> q = new LinkedList<>();
            int sum = 0; 
            int level=0;
            q.add(root);
            
            while (!q.isEmpty()) {
                int size=q.size();
                for (int i = 0; i < size; i++) {
                    Node currNode = q.remove();
                    if(level==k){
                        sum+=currNode.data;
                    }
                    if(currNode.left!=null){
                        q.add(currNode.left);
                    }
                    if(currNode.right!=null){
                        q.add(currNode.right);
                    }
                } 
                if(level==k){
                    break;
                    
                }
                level++;
            }
            
            return sum;
        }
    }

    public static void main(String[] args) {
        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        Node root = BinaryTree.buildTree(nodes);
        System.out.println("Root node: " + root.data);

        System.out.print("Pre-order traversal: ");
        BinaryTree.preOrder(root);

        System.out.println();
        System.out.print("In-order traversal: ");
        BinaryTree.inOrder(root);
        System.out.println();
        System.out.print("Postorder traversal: ");
        BinaryTree.postOrder(root);

        System.out.println();
        System.out.println("Level order traversal: ");
        BinaryTree.levelOrder(root);

        System.out.println("Number of Nodes: " + BinaryTree.countNodes(root));
        System.out.println("Sum of nodes = " + BinaryTree.sumNodes(root));

        int height = BinaryTree.height(root);
        int diameter = BinaryTree.diameter(root);

        System.out.println("Height of tree: " + height);
        System.out.println("Diameter of tree: " + diameter);

        BinaryTree.TreeInfo treeInfo = BinaryTree.diameter2(root);
        System.out.println("Diameter of tree (from diameter2 method): " + treeInfo.dia);
        //int k=5;
        System.out.println(BinaryTree.kSum(root,1));
    }
}