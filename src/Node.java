public class Node<Item> {
    Item data;
    Node<Item> next;
    Node<Item> prev;

    public Node(Item data){
        this.data = data;
        next = null;
        prev = null;
    }
}
