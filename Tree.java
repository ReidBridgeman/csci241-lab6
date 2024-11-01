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
        } else {
            int hl = height(t.left);
            int hr = height(t.right);
            if (hl > hr) {
                return hl + 1;
            } else {
                return hr +1;
            }
        }
    }
    // retrun max(height(t.left), height(t.right))  + 1;

    public static boolean isFull(Tree t){
        // base cases
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

        //if ((t.left != null) && (t.right != null)) {  //node has two children
        System.out.println("might be full...");
        boolean isleftFull = isFull(t.left);
        boolean isrightFull = isFull(t.right);
        return isleftFull && isrightFull;
    }
    /**
    * subtrees +- 1 difference in height
    * cant have a node with only a right children
    * get left child height, get right child height
    *
    */
    // Boris's assistance
    // public static boolean isComplete(Tree t){
    //     int h = height(t);
    //     boolean isc = countFull(t, h-1);
    //
    //     // check breadth of level height
    // }
    // // lltc = levelslefttocheck
    // public static boolean countFull (Tree t, int levelsLeftToCheck){
    //     if (isFull(t)){
    //         countFull(t, levelsLeftToCheck-1);
    //     }
    //     return isFull(t);
    // }

    public static boolean isComplete(Tree t){
        return false;
    }

    public static int levelCounter(Tree t){
        int level = height(t);
        return -1;

    }



    public static void main(String[] args){
        Tree trivia = null;
        //System.out.println(trivia.value);
        //  4
        //  /\
        // 3  5
        //   / \
        //  4   30
        Tree t3 = new Tree(4, null, null);
        t3.add(5);
        t3.add(3);
        // t3.add(4);
        // t3.add(30);
        System.out.println("isFull(t3): "+isFull(t3));

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
        //tree.add(12);
        System.out.println("isIdentical(t3, t3): " + isIdentical(t3, t3));
        System.out.println("tree height = " + height(tree.left));
        System.out.println("isFull(tree): "+isFull(tree));

        // t2
        //             15
        //            /
        //           10
        //          / \
        //         2  12
        //        / \
        //       1  3
        // t2 has tree as a subtree
        Tree t2 = new Tree(15, null, null);
        t2.add(10);
        t2.add(2);
        t2.add(3);
        t2.add(1);
        t2.add(12);
        System.out.println("\nt2 height = " + height(t2));
        System.out.println("isSubtree(t2, trivia): " + isSubtree(t2, trivia));
        System.out.println("isSubTree(t2, tree): " + isSubtree(t2, tree));
        System.out.println("isSubTree(t2, t3): " + isSubtree(t2, t3));

        // checking t3.right returns null if node has no value.
        if (t3.right.right != null){
            System.out.println("t3.right.right.value: " + t3.right.right.value);
        } else{
            System.out.println("t3.right.right: " + t3.right.right);
        }

    }
}
