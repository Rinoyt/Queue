package queue;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;

public abstract class AbstractQueue<T> implements Queue<T> {
    protected int capacity;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T t) {
        if (!offer(t)) {
            throw new IllegalStateException("The queue is full");
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return c.stream().map(this::add).reduce(false, (a, b) -> a || b);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        size = 0;
        clearImpl();
    }

    protected abstract void clearImpl();

    @Override
    public boolean offer(T t) {
        size++;
        if (size == capacity) {
            return false;
        }
        return offerImpl(Objects.requireNonNull(t));
    }

    protected abstract boolean offerImpl(T t);

    @Override
    public T remove() {
        T item = poll();
        if (item == null) {
            throw new NoSuchElementException("The queue is empty");
        }
        return item;
    }

    @Override
    public T poll() {
        if (size == 0) {
            return null;
        }
        size--;
        return pollImpl();
    }

    protected abstract T pollImpl();

    @Override
    public T element() {
        T item = peek();
        if (item == null) {
            throw new NoSuchElementException("The queue is empty");
        }
        return item;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return peekImpl();
    }

    protected abstract T peekImpl();
}
