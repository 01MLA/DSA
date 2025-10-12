package problem_solving_patterns;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Tree {

    static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " "); // process root
        preOrder(root.left);         // traverse left
        preOrder(root.right);        // traverse right
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;

        inOrder(root.left);               // traverse left
        System.out.print(root.val + " "); // process root
        inOrder(root.right);              // traverse right
    }

    public static void postOrder(TreeNode root) {
        if (root == null) return;

        postOrder(root.left);             // traverse left
        postOrder(root.right);            // traverse right
        System.out.print(root.val + " "); // process root
    }

    public static void levelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " "); // process node

            if (node.left != null) queue.offer(node.left);   // add left child
            if (node.right != null) queue.offer(node.right); // add right child
        }
    }


    public static void main(String[] args) {


    }
}

/**
 * Definition for a binary tree node.
 * (LeetCode usually provides this part.)
 */
class TreeNode {
    int val;            // value stored in the node
    TreeNode left;      // reference to left child
    TreeNode right;     // reference to right child

    TreeNode() {
    }       // empty constructor

    TreeNode(int val) { // constructor with value only
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) { // constructor with value and children
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    // Pointer to track current root index in preorder array
    private int preIndex = 0;

    // HashMap to store inorder value -> index for quick O(1) lookups
    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    /**
     * Main function to reconstruct the binary tree from preorder and inorder traversals.
     *
     * @param preorder preorder traversal of the tree (Root -> Left -> Right)
     * @param inorder  inorder traversal of the tree (Left -> Root -> Right)
     * @return root of the reconstructed binary tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Step 1: Store all values and their indices from inorder into a HashMap.
        //         This avoids scanning the array each time and reduces time complexity from O(n^2) to O(n).
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // Step 2: Start recursive tree construction from full inorder range (0 to n-1).
        return helper(preorder, 0, inorder.length - 1);
    }

    /**
     * Recursive helper function to build a subtree.
     *
     * @param preorder full preorder array
     * @param inStart  start index of current subtree in inorder array
     * @param inEnd    end index of current subtree in inorder array
     * @return root node of the constructed subtree
     */
    private TreeNode helper(int[] preorder, int inStart, int inEnd) {
        // ✅ Base Case: If there are no elements in this subtree range, return null.
        //    This happens when the start index goes beyond the end index.
        if (inStart > inEnd) {
            return null;
        }

        // 🪄 Step 1: Take the current root value from preorder array.
        // preorder traversal order is: Root -> Left -> Right,
        // so the first element not yet used is always the root of the current subtree.
        int rootVal = preorder[preIndex++]; // post-increment ensures preIndex moves forward for next call

        // 🌱 Step 2: Create a new tree node with this root value.
        TreeNode root = new TreeNode(rootVal);

        // 🔍 Step 3: Find the position of this root in the inorder array.
        // Elements to the left of this index belong to the left subtree.
        // Elements to the right belong to the right subtree.
        int inIndex = inorderIndexMap.get(rootVal);

        // 🌿 Step 4: Recursively construct the left subtree
        // using the part of inorder BEFORE the root index.
        root.left = helper(preorder, inStart, inIndex - 1);

        // 🌳 Step 5: Recursively construct the right subtree
        // using the part of inorder AFTER the root index.
        root.right = helper(preorder, inIndex + 1, inEnd);

        // 🔚 Step 6: Return the root node of this subtree.
        return root;
    }
}