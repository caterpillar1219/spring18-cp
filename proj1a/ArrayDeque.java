public class ArrayDeque<T> {
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
        nextLast = (nextLast - nextFirst - 1 + arr.length) % arr.length;
        if (nextLast == 0) {
            nextLast = arr.length;
        }
        nextFirst = capacity - 1;
        for (int j = 0; j < nextLast; j ++ ){
            a[j] = arr[i];
            i = (i + 1) % arr.length;
        }
        arr = a;
    }

    public void addFirst(T item) {
        arr[nextFirst] = item;
        size ++;
        nextFirst = (nextFirst - 1 + arr.length) % arr.length;
        if (size == arr.length) {
            this.resize(2 * arr.length);
        }
    }

    public void addLast(T item) {
        arr[nextLast] = item;
        size ++;
        nextLast = (nextLast + 1) % arr.length;
        if (size == arr.length) {
            this.resize(2 * arr.length);
        }
    }

    public boolean isEmpty() {
        return (nextLast - nextFirst + arr.length - 1) % arr.length == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = (nextFirst + 1) % arr.length; i != nextLast; i = (i + 1) % arr.length) {
            System.out.print(arr[i]);
            if ((i + 1) % arr.length != nextLast) {
                System.out.print(" ");
            }
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T x = arr[(nextFirst + 1) % arr.length];
        size --;
        nextFirst = (nextFirst + 1) % arr.length;
        if (size >= 16 && (float)size / arr.length < 0.25){
            this.resize(arr.length / 2);
        }
        return x;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = arr[(nextLast - 1 + arr.length) % arr.length];
        size --;
        nextLast = (nextLast - 1 + arr.length) % arr.length;
        if (size >= 16 && (float)size / arr.length < 0.25){
            this.resize(arr.length / 2);
        }
        return x;
    }

    public T get(int index) {
        if ((nextLast - nextFirst + arr.length - 1) % arr.length <= index) {
            return null;
        }
        return arr[(nextFirst + 1 + index) % arr.length];
    }

}
