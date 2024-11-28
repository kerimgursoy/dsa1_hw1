class DLinkedList<Item> {
    private Node<Item> head, tail;
    private int size;

    // Method: addFirst
    //--------------------------------------------------------
    // Summary: Adds an item to the beginning of the list.
    // Precondition: Item is initialized and valid.
    // Postcondition: The item is added as the new head of the list.
    //--------------------------------------------------------
    public void addFirst(Item item) {
        Node<Item> temp = new Node<>(item);
        if (head == null) {
            head = tail = temp;
        }
        else {
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
        size++;
    }
    // Method: addLast
    //--------------------------------------------------------
    // Summary: Adds an item to the end of the list.
    // Precondition: Item is initialized and valid.
    // Postcondition: The item is added as the new tail of the list.
    //--------------------------------------------------------
    public void addLast(Item item) {
        Node<Item> newNode = new Node<>(item);
        if (tail == null) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Method: removeFirst
    //--------------------------------------------------------
    // Summary: Removes and returns the item at the beginning of the list.
    // Precondition: The list is not empty.
    // Postcondition: The head item is removed, size decreases by 1,
    // and the next item becomes the new head.
    //--------------------------------------------------------
    public Item removeFirst() {
        if (head == null) {
            return null;
        }
        Item data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        else {
            head.prev = null;
        }
        size--;
        return data;
    }
    // Method: remove
    //--------------------------------------------------------
    // Summary: Removes and returns the item at the specified index.
    // Precondition: Index is within the bounds of
    // the list (0 <= index < size).
    // Postcondition: The item at the specified index is removed,
    // and the list size is reduced by 1.
    //--------------------------------------------------------
    public Item remove(int index) {
        // out of bounds case
        if (index < 0 || index >= size) {
            return null;
        }

        Node<Item> current;
        if (index == 0) {
            current = head;
            head = head.next; // Move head to the next node
            if (head != null) {
                head.prev = null; // Update the new head's previous to null
            } else {
                tail = null; // If list is now empty, update tail as well
            }
        }
        else if (index == size - 1) {
            current = tail;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
        }
        else {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        return current.data;
    }

    // Method: getSize
    //--------------------------------------------------------
    // Summary: Returns the number of items in the list.
    // Precondition: None.
    // Postcondition: The size of the list is returned as an integer.
    //--------------------------------------------------------
    public int getSize() {
        return size;
    }
    // Method: getHeadNode
    //--------------------------------------------------------
    // Summary: Returns the head node of the list.
    // Precondition: None.
    // Postcondition: Returns the head node.
    //--------------------------------------------------------
    public Node<Item> getHeadNode() {
        return head;
    }
}