package bstree;

import exceptions.*;

public class BSTree<E extends Comparable<E>> {
    public Node<E> root;

    public BSTree() {
        root = null;
    }

    public void insert(E data) throws ItemDuplicated {
        root = insert(root, data);
    }

    protected Node<E> insert(Node<E> node, E data) throws ItemDuplicated {
        if (node == null)
            return new Node<>(data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            node.left = insert(node.left, data);
        else if (cmp > 0)
            node.right = insert(node.right, data);
        else
            throw new ItemDuplicated("Duplicado: " + data);
        return node;
    }

    public E search(E data) throws ItemNotFound {
        Node<E> node = search(root, data);
        if (node == null)
            throw new ItemNotFound("No encontrado: " + data);
        return node.data;
    }

    protected Node<E> search(Node<E> node, E data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.data);
        if (cmp < 0) return search(node.left, data);
        else if (cmp > 0) return search(node.right, data);
        else return node;
    }
}
