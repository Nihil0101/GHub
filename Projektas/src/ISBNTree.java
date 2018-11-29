import java.util.ArrayList;
import java.util.List;

public class ISBNTree {

    public static class Tree { // public nes naudojama greitveika

        Node root;
        Node current;


        public Tree() {
            root = null;
        }

        public void add(int value, int data) {

            root = addRecursive(root, value, data);
        }


        private Node addRecursive(Node current, int value, int data) {
            if (current == null) {
                return new Node(value, data);
            }
            current.next = addRecursive(current.next, value, data);
            return current;
        }
        void clearTree() {
            root = null;
        }

        private static class Node {
            int value;
            int data;
            Node next;

            Node(int value, int data) {
                this.value = value;
                this.data = data;
                next = null;
            }
        }
    }


    public static void main(String [] args)
    {
        for(int y = 0; y<51; y++){
        Tree tree = new Tree();
        int [] n = ISBN.BrokenApartD();
        int [] m = new int[9];
       // Node<Integer> parentNode = new Node<Integer>(n[8]);
        //Node root = new Node(n[8]);
        m[8] = n[8];
        for(int i = 7; i>-1;i--) {
            m[i] = m[i+1]+ n[i];
           // root.addChild(new Node(m[i]));
            tree.add(m[i+1]+ n[i], n[i]);
        }
        String medis = "";
        int suma = 0;
        while(tree.root != null){
            suma += tree.root.value;
            //medis += tree.root.value + "-";
            tree.root = tree.root.next;
        }
      //  System.out.println("medis: " + medis);
        int ats = suma % 11;
        //System.out.println(suma);
        if (ats < 10) {
            System.out.println("Sumcheck : " + ats + " Generated numbers: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8] + ats);
        } else
            System.out.println("Sumcheck : " + ats + " Generated numbers: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8] + 'X');
    }
    }
}
