package dsa_implementations.tree;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {
    private TreeNode root;

    public TreeNode getRoot() {
        return this.root;
    }

    public AVLTree() {
        root = null;
    }

    public void preOrderTraversal(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public void inOrderTraversal(TreeNode root) {
        if (root == null) return;
        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }

    public void postOrderTraversal(TreeNode root) {
        if (root == null) return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val + " ");
    }

    public void levelOrderTraversal() {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            System.out.print(treeNode.val + " ");
            if (treeNode.left != null) queue.add(treeNode.left);
            if (treeNode.right != null) queue.add(treeNode.right);
        }
    }

    public TreeNode search(TreeNode root, int value) {
        if (root == null) return null;
        else if (root.val == value) return root;
        else if (root.val < value) return search(root.right, value);
        else return search(root.left, value);
    }

    public int getHeight(TreeNode node) {
        if (node == null) return 0;
        else return node.height;
    }

    private TreeNode rotateRight(TreeNode disbalancedNode) {
        TreeNode newRoot = disbalancedNode.left;
        disbalancedNode.left = disbalancedNode.left.right;
        newRoot.right = disbalancedNode;
        disbalancedNode.height = 1 + Math.max(getHeight(disbalancedNode.left), getHeight(disbalancedNode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    private TreeNode rotateLeft(TreeNode disbalancedNode) {
        TreeNode newRoot = disbalancedNode.right;
        disbalancedNode.right = disbalancedNode.right.left;
        newRoot.left = disbalancedNode;
        disbalancedNode.height = 1 + Math.max(getHeight(disbalancedNode.left), getHeight(disbalancedNode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    private int getBalanced(TreeNode node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // Insert a new value into the AVL tree and keep it balanced
    private TreeNode insertNode(TreeNode node, int value) {
        // ✅ 1. Standard BST insertion
        if (node == null) {
            TreeNode newNode = new TreeNode(value);
            newNode.height = 1; // new leaf node has height 1
            return newNode;
        }

        if (value < node.val) node.left = insertNode(node.left, value);
        else node.right = insertNode(node.right, value);

        // ✅ 2. Update height of current node
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // ✅ 3. Check balance factor to detect imbalance
        int balance = getBalanced(node);

        // ✅ 4. Fix 4 possible imbalance cases
        // Case 1: Left-Left (heavy on left subtree, and new value in left-left)
        if (balance > 1 && value < node.left.val) {
            return rotateRight(node);
        }
        // Case 2: Left-Right (heavy on left subtree, new value in left-right)
        if (balance > 1 && value > node.left.val) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // Case 3: Right-Right (heavy on right subtree, new value in right-right)
        if (balance < -1 && value > node.right.val) {
            return rotateLeft(node);
        }
        // Case 4: Right-Left (heavy on right subtree, new value in right-left)
        if (balance < -1 && value < node.right.val) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        // ✅ 5. If already balanced, return the node unchanged
        return node;
    }

    public void insert(int value) {
        root = insertNode(root, value);
    }

    // Minimum node
    public static TreeNode minimumNode(TreeNode root) {
        if (root.left == null) return root;
        return minimumNode(root.left);
    }


    // Delete a node with given value from AVL tree and keep it balanced
    public TreeNode deleteNode(TreeNode node, int value) {
        // Base case: reached null → value not found
        if (node == null) {
            System.out.println("Value not found in AVL");
            return null;
        }

        // 🔍 Step 1: Traverse to find the node to delete
        if (value < node.val) {
            node.left = deleteNode(node.left, value);   // go left
        } else if (value > node.val) {
            node.right = deleteNode(node.right, value); // go right
        } else {
            // ✅ Node found — handle 3 possible deletion cases
            // Case 1: Node has two children → replace with inorder successor (smallest in right subtree)
            if (node.left != null && node.right != null) {
                TreeNode successor = minimumNode(node.right);
                node.val = successor.val;  // copy successor's value into current node
                node.right = deleteNode(node.right, successor.val); // delete successor
            }
            // Case 2: Only left child → replace node with left child
            else if (node.left != null) {
                node = node.left;
            }
            // Case 3: Only right child → replace node with right child
            else if (node.right != null) {
                node = node.right;
            }
            // Case 4: Leaf node → simply remove it
            else {
                node = null;
            }
        }

        // If tree became empty after deletion, return
        if (node == null) return null;

        // ⚖️ Step 2: Check and fix balance to maintain AVL property
        int balance = getBalanced(node);

        // Left-Left case → rotate right
        if (balance > 1 && getBalanced(node.left) >= 0) {
            return rotateRight(node);
        }
        // Left-Right case → rotate left on left child, then right on current
        if (balance > 1 && getBalanced(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // Right-Right case → rotate left
        if (balance < -1 && getBalanced(node.right) <= 0) {
            return rotateLeft(node);
        }
        // Right-Left case → rotate right on right child, then left on current
        if (balance < -1 && getBalanced(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        // Return (possibly new) root of this subtree after balancing
        return node;
    }


}