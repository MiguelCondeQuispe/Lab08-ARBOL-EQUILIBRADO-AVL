package prueba;

import avltree.AVLTree;
import bstree.BSTree;
import exceptions.*;

public class TestAVL {

    public static void main(String[] args) {
        System.out.println("==== LABORATORIO 08 - ÁRBOLES AVL ====\n");

        AVLTree<Integer> avl = new AVLTree<>();

        try {

            avl.insert(30);
            avl.insert(15);
            avl.insert(20); 

            avl.insert(50);
            avl.insert(60); 

            avl.insert(10);
            avl.insert(5); 

            avl.insert(70);
            avl.insert(65); 

            System.out.println("\nBFS (recorrido por niveles):");
            avl.bfs();

            System.out.println("\nPreorden:");
            avl.printPreOrder();

            System.out.println("\n\n==== Comparación BST vs AVL ====");
            BSTree<Integer> bst = new BSTree<>();

            Integer[] valores = {30, 15, 20, 50, 60, 10, 5, 70, 65};

            for (int val : valores) {
                bst.insert(val);   // BST sin balance
            }

            System.out.println("Altura del BST (no balanceado): " + heightBST(bst));
            System.out.println("Altura del AVL (balanceado): " + heightBST(avl));


        } catch (ItemDuplicated e) {
            System.err.println("Duplicado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static int heightBST(BSTree<Integer> tree) {
        return height(tree.root);
    }

    private static int height(bstree.Node<Integer> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
}
