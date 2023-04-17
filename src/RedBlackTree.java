public class RedBlackTree <V extends Comparable<V>> {
    private Node root;
    private enum ColorNode {
        RED, BLACK
    }
    class Node {
        Node left, right;
        V data;
        ColorNode color;

        Node(V data) {
            this.data = data;
            left = null;
            right = null;
            color = ColorNode.RED;
        }
    }

    private Node rotateLeft (Node node){
        Node child = node.right;
        Node childLeft = child.left;
        child.left = node;
        node.right = childLeft;
        return child;
    }

    private Node rotateRight (Node node){
        Node child = node.left;
        Node childRight = child.right;
        child.right = node;
        node.left = childRight;
        return child;
    }

    private boolean isRed (Node node){
        return node != null && node.color.equals(ColorNode.RED);
    }

    private void swapColors (Node node1, Node node2){
        ColorNode temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }

    public boolean insert (V data){
        Node node;
        if (root != null) {
            node = insert(root, data);
            if (node == null) {
                return false;
            }
        } else {
            node = new Node(data);
        }
        root = node;
        root.color = ColorNode.BLACK;
        return true;
    }

    private Node insert (Node node, V data){
        if (node == null) {
            return new Node(data);
        }
        if (node.data.compareTo(data) > 0) {
            node.left = insert(node.left, data);
        } else if (node.data.compareTo(data) < 0) {
            node.right = insert(node.right, data);
        } else
            return null;
        return balanced(node);
    }

    private Node balanced (Node node){
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
            swapColors(node, node.left);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
            swapColors(node, node.right);
        }

        if (isRed(node.left) && isRed(node.right)) {
            node.color = ColorNode.RED;
            node.left.color = ColorNode.BLACK;
            node.right.color = ColorNode.BLACK;
        }
        return node;
    }

    public void inorder () {
        inorder(root);
    }

    private void inorder (Node node){
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " " + node.color + "|");
            inorder(node.right);
        }
    }
}