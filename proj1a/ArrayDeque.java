public class ArrayDeque<T> {
    private T[] arr;
    private int size = 0;
    private int start = 0;
    private static final double FACTOR = 0.25;
    public ArrayDeque() {
        arr = (T[]) new Object[8];
    }
    public void addFirst(T item) {
        if (size >= arr.length) {
            T[] newArr = (T[]) new Object[arr.length * 2];
            for (int i = start; i < start + size; i++) {
                newArr[i] = arr[i % arr.length];
            }
            arr = newArr;
        }
        start = (start - 1 + arr.length) % arr.length;
        arr[(start + arr.length) % arr.length] = item;
        size++;
    }
    public void addLast(T item) {
        if (size >= arr.length) {
            T[] newArr = (T[]) new Object[arr.length * 2];
            for (int i = start; i < start + size; i++) {
                newArr[i] = arr[i % arr.length];
            }
            arr = newArr;
        }
        arr[(start + size++) % arr.length] = item;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        for (int i = start; i < start + size; i++) {
            System.out.print(arr[i % arr.length] + " ");
        }
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (arr.length >= 16 && size <= arr.length * FACTOR) {
            T[] newArr = (T[]) new Object[arr.length / 2];
            int j = 0;
            for (int i = start; i < start + size; i++) {
                newArr[j++] = arr[i % arr.length];
            }
            arr = newArr;
            start = 0;
        }
        T ret = arr[start];
        arr[start] = null;
        start = (start + 1) % arr.length;
        size--;
        return ret;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (arr.length >= 16 && size <= arr.length * FACTOR) {
            T[] newArr = (T[]) new Object[arr.length / 2];
            int j = 0;
            for (int i = start; i < start + size; i++) {
                newArr[j++] = arr[i % arr.length];
            }
            arr = newArr;
            start = 0;
        }
        int index = (start + --size) % arr.length;
        T ret = arr[index];
        arr[index] = null;
        return ret;
    }
    public T get(int index) {
        if (index < 0 || index >= arr.length) {
            return null;
        }
        return arr[(start + index) % arr.length];
    }
}
