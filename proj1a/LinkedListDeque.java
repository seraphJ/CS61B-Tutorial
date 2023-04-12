public class LinkedListDeque<T> {
    private int size = 0;
    private Node dummyHead = new Node();
    private Node dummyTail = new Node();
    private class Node<T> {
        T val;
        Node pre;
        Node next;
    }
    public LinkedListDeque() {
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
    }

    public void addFirst(T item) {
        Node node = new Node();
        node.val = item;
        Node next = dummyHead.next;
        dummyHead.next = node;
        node.next = next;
        next.pre = node;
        node.pre = dummyHead;
        size++;
    }
    public void addLast(T item) {
        Node node = new Node();
        node.val = item;
        Node pre = dummyTail.pre;
        dummyTail.pre = node;
        node.pre = pre;
        pre.next = node;
        node.next = dummyTail;
        size++;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        Node node = dummyHead.next;
        while (node != dummyTail) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node node = dummyHead.next;
        Node next = node.next;
        dummyHead.next = next;
        next.pre = dummyHead;
        node.next = null;
        node.pre = null;
        size--;
        return (T) node.val;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node node = dummyTail.pre;
        Node pre = node.pre;
        dummyTail.pre = pre;
        pre.next = dummyTail;
        node.next = null;
        node.pre = null;
        size--;
        return (T) node.val;
    }
    public T get(int index) {
        if (index < 0) {
            return null;
        }
        Node node = dummyHead.next;
        for (int i = 0; i < index; i++) {
            if (node == dummyTail) {
                return null;
            }
            node = node.next;
        }
        return (T) node.val;
    }

    public T getRecursive(int index) {
        if (index < 0) {
            return null;
        }
        return (T) helpGetRecursive(index, dummyHead.next);
    }

    private T helpGetRecursive(int index, Node head) {
        if (head == dummyTail) {
            return null;
        }
        if (index == 0) {
            return (T) head.val;
        }
        return (T) helpGetRecursive(index - 1, head.next);
    }

}
