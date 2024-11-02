/*
Reid Bridgeman
csci241, wwu Fall 24'25, Dr. Tan
Lab 6: Tree Cardio
Due Nov 5th
*/

public class Tree{
    public int value;
    Tree left;
    Tree right;

    public Tree(int value, Tree left, Tree right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void add(int value){
        if(this.value > value){
            //add left.
            if(left == null){
                left = new Tree(value, null, null);
            } else {
                left.add(value);
            }
        } else {
            if(right == null){
                right = new Tree(value, null, null);
            } else {
                right.add(value);
            }
        }
    }
    /**
     * check if t2 is a subtree of t1
     */
    public static boolean isSubtree(Tree t1, Tree t2){
        if ((t1 == null) && (t2 != null)){
            return false;
        }
        if (t2 == null){
            return true;
        }
        return isSubHelper(t1, t2);
    }

    /**
     * resursively check for identical subtree
     */
    public static boolean isSubHelper(Tree t1, Tree t2){
        //base case
        if (t1 == null){
            return false;
        }
        //recurse
        if(t1.value != t2.value){
            boolean left = isSubHelper(t1.left, t2);
            boolean right = isSubHelper(t1.right, t2);
            return left || right;
        }
        else if(isIdentical(t1, t2)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * return true if two trees are identical
     */
    public static boolean isIdentical(Tree t1, Tree t2){
        //base cases
        boolean result;
        if((t1 == null) && (t2 == null)){
            return true;
        }
        // if only one tree is null, then not identical.
        if((t1 == null) || (t2 == null)){
            return false;
        }
        // must be after null checks
        if(t1.value != t2.value){
            return false;
        }

        // recurse when roots match
        if(t1.value == t2.value){
            boolean isleft = isIdentical(t1.left, t2.left);
            boolean isright = isIdentical(t1.right, t2.right);
            return isleft && isright;
        } else {
            return false;
        }
    }


    public static int height(Tree t){
        if(t == null){
            return 0;
        } 
        // else {
        //     int hl = height(t.left);
        //     int hr = height(t.right);
        //     if (hl > hr) {
        //         return hl + 1;
        //     } else {
        //         return hr +1;
        //     }
        // }
        return Math.max(height(t.left), height(t.right)) + 1;
    }
    // retrun max(height(t.left), height(t.right))  + 1;

    public static boolean isFull(Tree t){
    // BASE CASES
        if(t == null){
            return true;
        }
        if(height(t.left) != height(t.right)){      //different heights for left and right is not a full tree.
            return false;
        }
        //node has no children.
        if((t.left == null) && (t.right == null)){
            return true;
        }
        // only one child is null, therefore different heights
        if(((t.left == null) && (t.right != null)) ||
           ((t.left != null) && (t.right == null))) {
            //System.out.println("one child missing.");
            return false;
        }
        
        // node has two children, so recurse to check if left and right
        boolean isleftFull = isFull(t.left);
        boolean isrightFull = isFull(t.right);
        return isleftFull && isrightFull;
    }

    /**
     * true when tree height -1 is a full tree and all children in tree height fill from left to right.
     * a null tree is also a complete tree (for the recursion to work) 
     */
    public static boolean isComplete(Tree t){
    // BASE CASES
        if(t == null){                      // define null tree as complete
            //System.out.println("null node");
            return true;
        }
        // only a right child, therefor not complete
        if((t.left == null) && (t.right != null)){
            return false;
        }
        // get left and right child's height
        int lh = height(t.left);
        int rh = height(t.right);

        // comparing left and right heights
                    // height(t.right) cannot be greater than height(t.left) --> (lh - rh) < 0 
                    // left height can be at most 1 greater than right height --> (lh - rh) > 1
        if(((lh-rh) < 0) || ((lh-rh) > 1)){
            return false;
        }
    // RECURSION STEP
        // recursively call isComplete on t.left and t.right
        //System.out.println("recurse check");
        boolean lef =  isComplete(t.left);
        boolean rig = isComplete(t.right);
        return lef && rig;                  //left tree and right tree are complete.
    }

    public static void main(String[] args){
        Tree trivia = null;
        System.out.println("height(trivia) = " + height(trivia));
        System.out.println("isFull(trivia): "+isFull(trivia)); // true
        //  4
        //  /\
        // 3  5
        //   / \
        //  4   30
        Tree t3 = new Tree(4, null, null);
        System.out.println("isComplete(t3): " + isComplete(t3)); //true -> checking if tree with one entry is complete
        t3.add(5);
        t3.add(3);
        System.out.println("isFull(t3): "+isFull(t3)); // true
        t3.add(4);
        t3.add(30);
        System.out.println("isFull(t3): "+isFull(t3)); // false

        // tree
        //          10
        //          /\
        //         2  12
        //        / \
        //       1  3
        //tree is a subtree of t2
        Tree tree = new Tree(10, null, null);
        tree.add(2);
        tree.add(3);
        tree.add(1);
        tree.add(12);
        //tree.add(13);
        // System.out.println("isIdentical(t3, t3): " + isIdentical(t3, t3));
        System.out.println("height(tree) = " + height(tree));
        System.out.println("isFull(tree): "+isFull(tree));

        // t2
        //             15
        //            /  \
        //           10   20
        //          / \
        //         2  12
        //        / \
        //       1  3
        // t2 has tree as a subtree
        Tree t2 = new Tree(15, null, null);
        t2.add(10);
        t2.add(20);
        t2.add(2);
        t2.add(3);
        t2.add(1);
        t2.add(12);
        // System.out.println("\nt2 height = " + height(t2));
        // System.out.println("isSubtree(t2, trivia): " + isSubtree(t2, trivia));
        System.out.println("isFull(t2): " + isFull(t2));
        System.out.println("isSubTree(t2, tree): " + isSubtree(t2, tree));
        System.out.println("isSubTree(t2, t3): " + isSubtree(t2, t3));

        // checking t3.right returns null if node has no value.
        // if (t3.right.right != null){
        //     System.out.println("t3.right.right.value: " + t3.right.right.value);
        // } else{
        //     System.out.println("t3.right.right: " + t3.right.right);
        // }


        System.out.println("isComplete(t3): " + isComplete(tree));

    }
}