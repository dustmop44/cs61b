public class ArrayDeque <generic> {
    public generic[] items;
    public int size;

    public ArrayDeque() {
        items = (generic[]) new Object[8];
        size = 0;
    }

    public void AddFirst(generic item) {
        if (size == items.length) {
            resize(size * 2);
        }
        System.arraycopy(items, 0, items, 1, items.length - 1);
        items[0] = item;
        size += 1;
    }

    public void AddLast(generic item) {
        if (size == items.length) {
            resize(size*2);
        }
        items[size] = item;
        size += 1;
    }

    public void resize(int item) {
        generic[] soontobeitems = (generic[]) new Object[size*2];
        System.arraycopy(items, 0, soontobeitems, 0, size);
        items = soontobeitems;
    }

    public boolean isEmpty() {
        if (items[0] != null) {
            return false;
        } else {
            return true;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = 0;
        while (i < size - 1) {
            System.out.print(items[i] + " ");
            i += 1;
        }
        System.out.println(items[i]);
    }

    public generic removeFirst() {
        generic first = items[0];
        System.arraycopy(items, 1, items, 0, size-1);
        items[size -1] = null;
        size -= 1;
        return first;
    }

    public generic removeLast() {
        generic last = items[size - 1];
        items[size-1] = null;
        size -= 1;
        return last;
    }

    public generic get(int index) {
        return items[index];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        System.out.println(test.size());
        System.out.println(test.isEmpty());
        test.AddFirst(3);
        test.AddFirst(2);
        test.AddLast(4);
        test.printDeque();
        System.out.println(test.size());
        test.AddFirst(1);
        test.AddLast(5);
        test.printDeque();
        System.out.println(test.removeFirst());
        System.out.println(test.removeLast());
        System.out.println(test.get(1));
        test.printDeque();
        System.out.println(test.isEmpty());
        return;
    }
}