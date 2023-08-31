import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size;
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    public Deque() {
        size = 0;
        first = new Node();
        last = first;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item should not be null");
        }
        if (first.item == null) {
            first.item = item;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            first.previous = null;
            oldFirst.previous = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item should not be null");
        }
        if (last.item == null) {
            last.item = item;
        }
        else {
            Node oldLast = last;
            last = new Node();
            oldLast.next = last;
            last.item = item;
            last.next = null;
            last.previous = oldLast;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is empty");
        }
        Item item = first.item;
        if (first == last) {
            first.item = null;
        }
        else {
            first = first.next;
            first.previous = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is empty");
        }
        Item item = last.item;
        if (first == last) {
            last.item = null;
        }
        else {
            last = last.previous;
            last.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return (current != null) && (current.item != null); }
        public void remove() { throw new UnsupportedOperationException("No such operation"); }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No element left");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        System.out.println(deque.isEmpty());
        deque.addFirst("111");
        deque.addLast("222");

        String s1 = deque.removeFirst();
        for (String s: deque) {
            System.out.println(s);
        }
        System.out.println(s1);
        String s2 = deque.removeLast();
        System.out.println(s2);
        System.out.println(deque.size());
        for (String s: deque) {
            System.out.println(s);
        }
        deque.addLast("111");
        deque.addFirst("222");
        for (String s: deque) {
            System.out.println(s);
        }
    }
}
