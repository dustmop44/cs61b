public class ArrayDeque<Item> implements Deque<Item>{
    public Item[] items;
    public int size;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
    }

    @Override
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        System.arraycopy(items, 0, items, 1, items.length - 1);
        items[0] = item;
        size += 1;
    }

    @Override
    public void addLast(Item item) {
        if (size == items.length) {
            resize(size*2);
        }
        items[size] = item;
        size += 1;
    }

    public void resize(int item) {
        Item[] soontobeitems = (Item[]) new Object[size*2];
        System.arraycopy(items, 0, soontobeitems, 0, size);
        items = soontobeitems;
    }

    @Override
    public boolean isEmpty() {
        if (items[0] != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i = 0;
        while (i < size - 1) {
            System.out.print(items[i] + " ");
            i += 1;
        }
        System.out.println(items[i]);
    }

    @Override
    public Item removeFirst() {
        Item first = items[0];
        System.arraycopy(items, 1, items, 0, size-1);
        items[size -1] = null;
        size -= 1;
        return first;
    }

    @Override
    public Item removeLast() {
        Item last = items[size - 1];
        items[size-1] = null;
        size -= 1;
        return last;
    }

    @Override
    public Item get(int index) {
        return items[index];
    }

}