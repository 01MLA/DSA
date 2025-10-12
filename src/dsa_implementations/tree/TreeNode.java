package dsa_implementations.tree;

public class TreeNode {
    public int val;
    public TreeNode left, right;
    public int height;

    TreeNode(int val) {
        this.val = val;
        this.height = 0;
    }

    TreeNode() {
    }
}
