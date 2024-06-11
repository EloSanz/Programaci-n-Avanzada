import java.util.Iterator;

public class PriorityQ<T extends Comparable<T>> implements Iterable<T> {
    Heap<T> heap;

    public PriorityQ() {
        this.heap = new Heap<T>();
    }

    public void add(T elem) {
        this.heap.add(elem);
    }

    @SuppressWarnings("unchecked")
    public T poll() {
        return (T) this.heap.remove();
    }

    public Object peek() {
        return this.heap.peek();
    }

    public void clear() {
        this.heap.clear();
    }

    public boolean contains(T elem) {
        for (Comparable<T> o : this.heap.toArray()) {
            if (o.compareTo(elem) == 0) {
                return true;
            }
        }
        return false;
    }

    public Object[] toArray() {
        return this.heap.toArray();
    }

    public Iterator<T> iterator() {
        return new PriorityQIterator<T>(this);
    }

    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

}

class PriorityQIterator<T extends Comparable<T>> implements Iterator<T> {
    Object[] arr;
    int i;

    public PriorityQIterator(PriorityQ<T> pq) {
        this.arr = pq.toArray();
        this.i = 0;
    }

    public boolean hasNext() {
        return i < this.arr.length;
    }

    @SuppressWarnings("unchecked")
    public T next() {
        T aux = (T) arr[i];
        i++;
        return aux;
    }
}
