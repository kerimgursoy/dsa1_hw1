class Queue<Item> {
    private Node<Item> front;
    private Node<Item> rear;
    // Method: enqueue
    //--------------------------------------------------------
    // Summary: enqueues an item.
    // Precondition: item is initialized.
    // Postcondition: adds item to the queue.
    //--------------------------------------------------------
    public void enqueue(Item item) {
        Node<Item> temp = new Node<>(item);
        if (rear == null) {
            front = rear = temp;
        } else {
            rear.next = temp;
            rear = temp;
        }
    }
    // Method: enqueue
    //--------------------------------------------------------
    // Summary: denqueues an item.
    // Precondition: none.
    // Postcondition: removes item from the queue and returns the
    // removed item if it exists.
    //--------------------------------------------------------
    public Item dequeue() {
        if (front == null){
            return null;
        }
        Item data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public Node<Item> getFrontNode() {
        return front;
    }
}