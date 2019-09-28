public class LinkedListDeque<T> implements Deque<T> {

    private class TNode {
        private T item;
        private TNode prev;
        private TNode next;
        private TNode(T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        size++;
        TNode temp = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
    }

    @Override
    public void addLast(T item) {
        size++;
        TNode temp = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        TNode p = sentinel.next;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.print(p.item);
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        TNode temp = sentinel.next;
        temp.next.prev = sentinel;
        sentinel.next = temp.next;
        return temp.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        TNode temp = sentinel.prev;
        temp.prev.next = sentinel;
        sentinel.prev = temp.prev;
        return temp.item;
    }

    @Override
    public T get(int index) {
        TNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            if (p == sentinel) {
                return null;
            }
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        return this.getRecHelper(index, sentinel);
    }

    private T getRecHelper(int index, TNode p) {
        if (p.next == sentinel) {
            return null;
        }
        if (index == 0) {
            return p.next.item;
        }
        return this.getRecHelper(index - 1, p.next);
    }

}
