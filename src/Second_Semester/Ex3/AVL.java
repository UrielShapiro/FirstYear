package Second_Semester.Ex3;

////////////////////////////////////////////////////////////////
class AVLStack {
    private int maxSize; // size of stack array
    private AVLNode[] stackArray;
    private int top; // top of stack
    //--------------------------------------------------------------
    // This is the constructor that takes as an argument the maximum size
    // s that it ever needs to be
    public AVLStack(int s) // constructor
    {
        maxSize = s; // set array size
        stackArray = new AVLNode[maxSize]; // create array
        top = -1; // no items yet
    }
    //--------------------------------------------------------------
    public void push(AVLNode j) // put item on top of stack
    {
        stackArray[++top] = j; // increment top, insert item
    }
    //--------------------------------------------------------------
    public AVLNode pop() // take item from top of stack
    {
        return stackArray[top--]; // access item, decrement top
    }
    //--------------------------------------------------------------
    public AVLNode peek() // peek at top of stack
    {
        return stackArray[top];
    }
    //--------------------------------------------------------------
    public boolean isEmpty() // true if stack is empty
    {
        return (top == -1);
    }
    //--------------------------------------------------------------
    public boolean isFull() // true if stack is full
    {
        return (top == maxSize - 1);
    }
    //--------------------------------------------------------------
} // end class myStack
////////////////////////////////////////////////////////////////


class AVLNode {
    public int iData; // data item (key)
    public AVLNode left; // this node's left child
    public AVLNode right; // this node's right child

    // Extra fields added to keep AVL related information
    public int balance;
    public int height;

    public AVLNode(int key) {
        iData = key; // data item (key)
        balance = 0;
        height = 0;
    }
    public void displayNode() // display ourself
    {
        System.out.print(this);
    }
    @Override
    public String toString() {

        return ("{" + iData + "|b " + balance + "|h " + height + "}");
    }
} // end class AVLNode


class AVLTree {
    private AVLNode root; // first node of tree
    private int size;
    // -------------------------------------------------------------
    public AVLTree() // constructor
    {
        root = null;
    } // no nodes in tree yet

    // -------------------------------------------------------------
    int height(AVLNode N) {
        if (N == null) return 0;
        return N.height;
    }
    //----------------------------------------

    public void insert(int id) {
        // Increase number of elements in tree
        System.out.println("Inserting  " + id);
        size++;
        AVLNode newNode = new AVLNode(id); // make new node
        if (root == null) // no node in root
            root = newNode;
        else // root occupied
        {
            AVLNode current = root; // start at root and walk down the tree
            AVLNode parent = null;

            // An AVLStack is a stack that stores AVLNodes. It will be used
            // to store the search path
            AVLStack S = new AVLStack(size);

            // Loop to walk down the tree, until current becomes null
            // The nodes visited in this walk are stored in AVLStack
            while (current != null) {
                S.push(current); // PUSH current node to stack
                parent = current;

                if (id < current.iData) // go left?
                    current = current.left;
                else // or go right?
                    current = current.right;
            } // end while


            // Insert the node
            if (id < parent.iData) parent.left = newNode;
            else parent.right = newNode;

            // Walk back up the tree, by popping items from the AVLStack S
            AVLNode pathNode, parentnode;
            while (!S.isEmpty()) {
                // This is the current node of the search path
                pathNode = S.pop();
                pathNode = CompHB(pathNode); // Compute its height and balance

                // Check if balance is out of bounds. If so, break out
                // of the loop because rotations are needed
                if ((pathNode.balance < -1) || (pathNode.balance > 1)) {

                    System.out.println("Tree is no longer AVL on " + pathNode);

                    // If this node becomes unbalanced, then  
                    // there are 4 cases Left Left Case  
                    if (pathNode.balance < -1 && id < pathNode.left.iData) {
                        System.out.println(" LL  ");
                        pathNode = rightRotate(pathNode);
                        if (S.isEmpty()) {
                            root = pathNode;
                        } else {
                            parentnode = S.pop();
                            parentnode.left = pathNode;
                        }
                    }

                    // Right Right Case  
                    if (pathNode.balance > 1 && id > pathNode.right.iData) {
                        System.out.println(" RR    ");
                        pathNode = leftRotate(pathNode);
                        pathNode = CompHB(pathNode);
                        if (S.isEmpty()) {
                            root = pathNode;
                        } else {
                            parentnode = S.pop();
                            parentnode.right = pathNode;
                        }
                    }

                    // Left Right Case  
                    if (pathNode.balance < -1 && id > pathNode.left.iData) {
                        System.out.println(" LR  ");
                        pathNode.left = leftRotate(pathNode.left);
                        pathNode = rightRotate(pathNode);
                        if (S.isEmpty()) {
                            root = pathNode;
                        } else {
                            parentnode = S.pop();
                            parentnode.left = pathNode;
                        }
                    }

                    // Right Left Case  
                    if (pathNode.balance > 1 && id < pathNode.right.iData) {
                        System.out.println(" RL  ");
                        pathNode.right = rightRotate(pathNode.right);
                        pathNode = leftRotate(pathNode);
                        if (S.isEmpty()) {
                            root = pathNode;
                        } else {
                            parentnode = S.pop();
                            parentnode.right = pathNode;
                        }
                    }

                    break;
                }
            } // end while stack is non-empty

        } // end else not root
    } // end insert()

    // A helper function that returns the maximum of two integers
    int max(int x, int y) {
        if (x > y) return x;
        else return y;
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation  
        x.right = y;
        y.left = T2;
        // Update heights  
        y = CompHB(y);
        x = CompHB(x);
        // Return new root  
        return x;
    }

    // A utility function to left rotate subtree rooted with x  
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation  
        y.left = x;
        x.right = T2;
        // Update heights  
        x = CompHB(x);
        y = CompHB(y);
        // Return new root  
        return y;
    }
    
    AVLNode CompHB(AVLNode pathNode) {
        // Compute node height and balance
        // First find the height of the left subtree
        int leftHeight, rightHeight;
        if (pathNode.left == null)            leftHeight = -1;
        else
            leftHeight = pathNode.left.height;

        // Then find the height of the right subtree
        if (pathNode.right == null)            rightHeight = -1;
        else
            rightHeight = pathNode.right.height;

        // Set the balance of the node
        pathNode.balance = rightHeight - leftHeight;
        // Set the height of the subtree rooted at the node
        pathNode.height = 1 + max(leftHeight, rightHeight);
        return pathNode;
    }
    // -------------------------------------------------------------
    public void displayTree() {
        AVLStack globalStack = new AVLStack(3 * (size + 1));
        globalStack.push(root);
        int nBlanks = 40;
        boolean isRowEmpty = false;
        System.out.println("..............................................................................");
        while (isRowEmpty == false) {
            AVLStack localStack = new AVLStack(3 * (size + 1));
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (globalStack.isEmpty() == false) {
                AVLNode temp = (AVLNode) globalStack.pop();
                if (temp != null) {
                    temp.displayNode();
                    localStack.push(temp.left);
                    localStack.push(temp.right);

                    if (temp.left != null ||
                        temp.right != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("-----");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 4; j++)
                    System.out.print(' ');
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        } // end while isRowEmpty is false
        System.out.println(
            "..............................................................................");
    } // end displayTree()

} // end class AVLTRee

//public class AVLTreeTester {
//
//    //Main method
//    public static void main(String[] args) {
//
//        AVLTree T = new AVLTree();
//        T.insert(20);
//        T.displayTree();
//        T.insert(15);
//        T.displayTree();
//        T.insert(18);
//        T.displayTree();
//        T.insert(10);
//        T.displayTree();
//        T.insert(12);
//        T.displayTree();
//        T.insert(80);
//        T.displayTree();
//        T.insert(85);
//        T.displayTree();
//    }
//}