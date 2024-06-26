package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
    }
    private class Node {
        private Node prev, next;
        private T item;
        Node() {
            this.prev = this;
            this.next = this;
            this.item = null;
        }
        Node(Node prev, Node next, T item) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }
    private int size;
    private Node sentinel;
    @Override
    public int size() {
        return size;
    }
    @Override
    public void addFirst(T item) {
        Node L = new Node();
        L.item = item;
        if (sentinel.next != null) {
            L.prev = sentinel.next.prev;
            L.next = sentinel.next;
            L.item = item;
        }
        sentinel.next = L;
        L.prev.next = L;
        L.next.prev = L;
        size++;
    }
    @Override
    public void addLast(T item) {
        if (sentinel.next == null) {
            addFirst(item);
            return;
        }
        Node last = sentinel.next.prev;
        Node L = new Node(last, sentinel.next, item);
        last.next = L;
        sentinel.next.prev = L;
        size++;
    };
    @Override
    public T removeLast() {
        T x;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            x = sentinel.next.item;
            sentinel.next = null;
        } else {
            Node p = sentinel.next.prev.prev;
            x = p.next.item;
            p.next = sentinel.next;
            sentinel.next.prev = p;
        }
        size--;
        return x;
    }
    @Override
    public T removeFirst() {
        T x;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            x = sentinel.next.item;
            sentinel.next = null;
        } else {
            x = sentinel.next.item;
            Node last = sentinel.next.prev;
            last.next = sentinel.next.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = last;
        }
        size--;
        return x;
    }
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int count = 0;
        Node p = sentinel.next;
        while (p.next != null) {
            if (count == index) {
                break;
            }
            p = p.next;
            count++;
        }
        return p.item;
    }
    public T getRecursive(int index) {
        if (index < 0 || size <= index) {
            return null;
        }
        return get(sentinel.next, index).item;
    }
    private Node get(Node p, int index) {
        if (p.next == null || index == 0) {
            return p;
        }
        return get(p.next, --index);
    }
    @Override
    public void printDeque() {
        Node p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.println(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;
        LinkedListDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = get(wizPos);
            wizPos++;
            return returnItem;
        }
    }
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }

        if (!(o instanceof Deque)) {
            return  false;
        }
        Deque<T> other = (Deque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!other.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }
}
