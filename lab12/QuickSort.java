import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        for (Item i : unsorted) {
            if (i.compareTo(pivot) < 0) {
                less.enqueue(i);
            } else if (i.compareTo(pivot) > 0) {
                greater.enqueue(i);
            } else {
                equal.enqueue(i);
            }
        }
    }

    private static <Item extends Comparable> Queue<Item> comparetwo(Queue<Item> items) {
        Item itemone = items.dequeue();
        Item itemtwo = items.dequeue();
        if (itemone.compareTo(itemtwo) < 0) {
            items.enqueue(itemone);
            items.enqueue(itemtwo);
        } else {
            items.enqueue(itemtwo);
            items.enqueue(itemone);
        }
        return items;
    }

    public static <Item extends Comparable> Queue<Item> quickSortHelper(Queue<Item> items) {
        if (items.size() <= 1) {
            return items;
        } else if (items.size() == 2) {
            return comparetwo(items);
        } else {
            Queue<Item> less = new Queue<>();
            Queue<Item> equal = new Queue<>();
            Queue<Item> greater = new Queue<>();
            Item pivot = getRandomItem(items);
            partition(items, pivot, less, equal, greater);
            return catenate(catenate(quickSortHelper(less), equal), quickSortHelper(greater));
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        return quickSortHelper(items);
    }

    public static void main(String[] args) {
        Queue<String> test = new Queue<>();
        test.enqueue("apple");
        test.enqueue("cherry");
        test.enqueue("dragonfruit");
        test.enqueue("edamame");
        test.enqueue("blueberry");
        System.out.println(test);
        Queue<String> test2 = QuickSort.quickSort(test);
        System.out.println(test);
        System.out.println(test2);
    }
}
