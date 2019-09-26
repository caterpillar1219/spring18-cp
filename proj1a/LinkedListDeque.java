public class LinkedListDeque<T> {
    public class TNode {
        public T item;
        public TNode prev;
        public TNode next;
        public TNode (T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private TNode sentinel;
    private int size = 0;

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        size ++;
        TNode temp = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
    }

    public void addLast(T item) {
        size ++;
        TNode temp = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
    }

    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item);
            System.out.println(" ");
            p = p.next;
        }
        System.out.print(p.item);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        TNode temp = sentinel.next;
        temp.next.prev = sentinel;
        sentinel.next = temp.next;
        return temp.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        TNode temp = sentinel.prev;
        temp.prev.next = sentinel;
        sentinel.prev = temp.prev;
        return temp.item;
    }

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

    private T getRecHelper (int index, TNode p) {
        if (p.next == sentinel) {
            return null;
        }
        if (index == 0) {
            return p.next.item;
        }
        return this.getRecHelper(index - 1, p.next);
    }

}