import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Node first, last;
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    public RandomizedQueue() {
        size = 0;
        first = new Node();
        last = first;
        first.item = null;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item should not be null");
        }
        if ((last.item == null)) {
            last.item = item;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            oldLast.next = last;
            last.previous = oldLast;
        }
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is empty");
        }
        int sz = size;
        size--;
        Item item;
        if (sz == 1) {
            item = first.item;
            first = new Node();
            first.item = null;
            last = first;
            return item;
        }
        int index = StdRandom.uniformInt(sz);

        if (index == 0) {
            item = first.item;
            first = first.next;
            first.previous = null;
            return item;
        } else if (index == sz - 1) {
            item = last.item;
            last = last.previous;
            last.next = null;
            return item;
        } else {
            Node current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            item = current.item;
            current.previous.next = current.next;
            current.next.previous = current.previous;
            return item;
        }

    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is empty");
        }
        int index = StdRandom.uniformInt(size());
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }


    private class RandomizedQueueIterator implements Iterator<Item> {
        private int sz;
        private Item[] itemsCopy;
        private Node current;
        private RandomizedQueueIterator() {
            sz = size;
            itemsCopy = (Item[]) new Object[sz];
            current = first;
            for (int i = 0; i < sz; i++) {
                itemsCopy[i] = current.item;
                current = current.next;
            }
        }

        public boolean hasNext() { return sz > 0; }
        public void remove() { throw new UnsupportedOperationException("No such operation"); }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No element left");
            }
            int index = StdRandom.uniform(sz);
            Item item = itemsCopy[index];

            // replace taken element with last one
            itemsCopy[index] = itemsCopy[sz - 1];
            itemsCopy[sz - 1] = null;
            sz--;
            return item;

        }
    }

    public static void main(String[] args) {

    }
}
