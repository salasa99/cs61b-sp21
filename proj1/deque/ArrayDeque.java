package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int initlCap = 8;
    private int size;
    private int head, tail;
    public ArrayDeque() {
        items = (T[]) new Object[initlCap];
        size = 0;
        head = 0;
        tail = 0;
    }
    private void resize(int capacity) {
        /*
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, head, a, 0, size - head);
        System.arraycopy(items, 0, a, size - head, head);
        items = a;
        head = 0;
        tail = size;
        */
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = items[(head + i) % items.length];
        }
        items = a;
        head = 0;
        tail = size;
    }
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[head = (head - 1) & (items.length - 1)] = item;
        size++;
    }
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size++;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        for (int i = head; i < size; i++) {
            System.out.println(items[i] + " ");
        }
        for (int j = 0; j < tail; j++) {
            System.out.println(items[j] + " ");
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T x = items[head];
        head = (head + 1) % items.length;
        size--;
        if (size < items.length / 4 && items.length > 15) {
            resize(items.length / 4);
        }
        return x;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T x = items[tail = (tail - 1) & (items.length - 1)];
        size--;
        if (size < items.length / 4 && items.length > 15) {
            resize(items.length / 4);
        }
        return x;
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(head + index) % items.length];
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        ArrayDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = items[(head + wizPos) % items.length];
            wizPos++;
            return returnItem;
        }
    }
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    /*
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (o == this) {
            return true;
        }
        if (o instanceof LinkedListDeque<?>) {
            LinkedListDeque<T> other = (LinkedListDeque<T>) o;
            if (this.size() != other.size()) {
                return false;
            }
            Iterator<T> thisIter = this.iterator();
            Iterator<T> otherIter = other.iterator();
            while (otherIter.hasNext() && thisIter.hasNext()) {
                if (otherIter.next() != thisIter.next()) {
                    return false;
                }
            }
        } else if (o instanceof ArrayDeque<?>) {
            ArrayDeque<T> other = (ArrayDeque<T>) o;
            if (this.size() != other.size()) {
                return false;
            }
            Iterator<T> thisIter = this.iterator();
            Iterator<T> otherIter = other.iterator();
            while (otherIter.hasNext() && thisIter.hasNext()) {
                if (otherIter.next() != thisIter.next()) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
    */
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
