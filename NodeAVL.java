package avltree;

import bstree.Node;

public class NodeAVL<E> extends Node<E> {
    protected int bf;

    public NodeAVL(E data) {
        super(data);
        this.bf = 0;
    }

    @Override
    public String toString() {
        return data + " (bf=" + bf + ")";
    }
}
