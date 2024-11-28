class Stack<Item> {
    private Node<Item> top;
    // Method: push
    //--------------------------------------------------------
    // Summary: adds an item to the stack.
    // Precondition: item is initialized.
    // Postcondition: adds item to the stack.
    //--------------------------------------------------------
    public void push(Item item) {
        Node<Item> temp = new Node<>(item);
        temp.next = top;
        top = temp;
    }
    // Method: pop
    //--------------------------------------------------------
    // Summary: removes an item from the stack.
    // Precondition: none.
    // Postcondition: removes and item from the top of the stack and
    // returns it if it exists.
    //--------------------------------------------------------
    public Item pop() {
        if (top == null){
            return null;
        }
        Item data = top.data;
        top = top.next;
        return data;
    }
    // Method: getTopNode
    //--------------------------------------------------------
    // Summary: returns top node.
    // Precondition: none.
    // Postcondition: returns top node.
    //--------------------------------------------------------
    public Node<Item> getTopNode() {
        return top;
    }
    // Method: isEmpty
    //--------------------------------------------------------
    // Summary: returns if the stack is empty.
    // Precondition: none.
    // Postcondition: returns if the stack is empty.
    //--------------------------------------------------------
    public boolean isEmpty() {
        return top == null;
    }
}