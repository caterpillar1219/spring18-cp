public class ArrayDeque<T> implements Deque<T> {
    private T[] arr;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int i = (nextFirst + 1) % arr.length;
        nextLast = size;
        nextFirst = capacity - 1;
        for (int j = 0; j < size; j++) {
            a[j] = arr[i];
            i = (i + 1) % arr.length;
        }
        arr = a;
    }

    @Override
    public void addFirst(T item) {
        arr[nextFirst] = item;
        size++;
        nextFirst = (nextFirst - 1 + arr.length) % arr.length;
        if (size == arr.length) {
            resize(2 * arr.length);
        }
    }

    @Override
    public void addLast(T item) {
        arr[nextLast] = item;
        size++;
        nextLast = (nextLast + 1) % arr.length;
        if (size == arr.length) {
            resize(2 * arr.length);
        }
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
        int i = (nextFirst + 1) % arr.length;
        for (int j = 0; j < size - 1; j++) {
            System.out.print(arr[i]);
            System.out.print(" ");
            i = (i + 1) % arr.length;
        }
        System.out.print(arr[i]);
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T x = arr[(nextFirst + 1) % arr.length];
        size--;
        nextFirst = (nextFirst + 1) % arr.length;
        if (arr.length >= 16 && (float) size / arr.length < 0.25) {
            this.resize(arr.length / 2);
        }
        return x;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = arr[(nextLast - 1 + arr.length) % arr.length];
        size--;
        nextLast = (nextLast - 1 + arr.length) % arr.length;
        if (arr.length >= 16 && (float) size / arr.length < 0.25) {
            this.resize(arr.length / 2);
        }
        return x;
    }

    @Override
    public T get(int index) {
        if (size <= index) {
            return null;
        }
        return arr[(nextFirst + 1 + index) % arr.length];
    }

}
