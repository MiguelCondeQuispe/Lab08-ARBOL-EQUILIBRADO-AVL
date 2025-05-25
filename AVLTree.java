package avltree;

import bstree.BSTree;
import exceptions.*;
import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    public AVLTree() {
        root = null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertAVL((NodeAVL<E>) root, data);
    }

    private NodeAVL<E> insertAVL(NodeAVL<E> node, E data) throws ItemDuplicated {
        if (node == null) return new NodeAVL<>(data);

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            NodeAVL<E> left = insertAVL((NodeAVL<E>) node.left, data);
            node.left = left;
            node = balanceToRight(node);
        } else if (cmp > 0) {
            NodeAVL<E> right = insertAVL((NodeAVL<E>) node.right, data);
            node.right = right;
            node = balanceToLeft(node);
        } else {
            throw new ItemDuplicated("Elemento duplicado: " + data);
        }

        return node;
    }

    private NodeAVL<E> balanceToLeft(NodeAVL<E> node) {
        NodeAVL<E> hijo = (NodeAVL<E>) node.right;

        switch (hijo.bf) {
            case 1:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSL(node);
                break;
            case -1:
                NodeAVL<E> nieto = (NodeAVL<E>) hijo.left;
                switch (nieto.bf) {
                    case -1:
                        node.bf = 0;
                        hijo.bf = 1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.right = rotateSR(hijo);
                node = rotateSL(node);
        }

        return node;
    }

    private NodeAVL<E> balanceToRight(NodeAVL<E> node) {
        NodeAVL<E> hijo = (NodeAVL<E>) node.left;

        switch (hijo.bf) {
            case -1:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSR(node);
                break;
            case 1:
                NodeAVL<E> nieto = (NodeAVL<E>) hijo.right;
                switch (nieto.bf) {
                    case 1:
                        node.bf = 0;
                        hijo.bf = -1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case -1:
                        node.bf = -1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.left = rotateSL(hijo);
                node = rotateSR(node);
        }

        return node;
    }

    private NodeAVL<E> rotateSL(NodeAVL<E> node) {
        NodeAVL<E> newRoot = (NodeAVL<E>) node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    private NodeAVL<E> rotateSR(NodeAVL<E> node) {
        NodeAVL<E> newRoot = (NodeAVL<E>) node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    // BFS 
    public void bfs() {
        if (root == null) return;
        Queue<NodeAVL<E>> queue = new LinkedList<>();
        queue.add((NodeAVL<E>) root);

        while (!queue.isEmpty()) {
            NodeAVL<E> node = queue.poll();
            System.out.print(node.data + " ");

            if (node.left != null) queue.add((NodeAVL<E>) node.left);
            if (node.right != null) queue.add((NodeAVL<E>) node.right);
        }
        System.out.println();
    }

    // Preorden
    public void printPreOrder() {
        preOrder((NodeAVL<E>) root);
        System.out.println();
    }

    private void preOrder(NodeAVL<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder((NodeAVL<E>) node.left);
            preOrder((NodeAVL<E>) node.right);
        }
    }
}
