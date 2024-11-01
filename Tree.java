public class Tree{
    public int value;
    Tree left;
    Tree right;

    public Tree(int value){
        this.value = value;
    }

    /**
     * check if t2 is a subtree of t2
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
        if (t1 == null){        //
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

    public static void main(String[] args){
        Tree trivia = null;
        Tree t3 = new Tree(1);
        t3.left = new Tree(5);
        t3.right = new Tree(3);
        t3.left.left = new Tree(4);

        // tree
        //          1
        //          /\
        //          2 3
        //        /
        //        4
        //tree is a subtree of t2
        Tree tree = new Tree(1);
        tree.left = new Tree(2);
        tree.right = new Tree(3);
        tree.left.left = new Tree(4);
        System.out.println(isIdentical(t3, tree));

        // t2
        //            2
        //           /
        //          1
        //          /\
        //          2 3
        //        /
        //        4
        // t2 has tree as a subtree
        Tree t2 = new Tree(2);
        t2.left = new Tree(1);
        t2.left.left = new Tree(2);
        t2.left.right = new Tree(3);
        t2.left.left.left = new Tree(4);
        System.out.println("t2, null: " + isSubtree(t2, trivia));
        System.out.println("t2, tree: " + isSubtree(t2, tree));
        System.out.println("t2, t3: " + isSubtree(t2, t3));

        System.out.println("t3.right.right: " + t3.right.right);

    }
}