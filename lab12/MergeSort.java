import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> newqueue = new Queue<>();
        for (Item i : items) {
            Queue<Item> insert = new Queue<>();
            insert.enqueue(i);
            newqueue.enqueue(insert);
        }
        return newqueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> returnqueue = new Queue<>();
        if (q1.isEmpty()) {
            for (Item i : q2) {
                returnqueue.enqueue(i);
            }
        } else if (q2.isEmpty()) {
            for (Item i : q1) {
                returnqueue.enqueue(i);
            }
        } else if (q1.peek().compareTo(q2.peek()) <= 0) {
            returnqueue.enqueue(q1.dequeue());
            for (Item i : mergeSortedQueues(q1, q2)) {
                returnqueue.enqueue(i);
            }
        } else if (q1.peek().compareTo(q2.peek()) > 0) {
            returnqueue.enqueue(q2.dequeue());
            for (Item i : mergeSortedQueues(q1, q2)) {
                returnqueue.enqueue(i);
            }
        }
        return returnqueue;
    }

    private static <Item extends Comparable> Queue<Queue<Item>> MergeSortHelper(Queue<Queue<Item>> items) {
        if (items.size() == 1) {
            return items;
        } else if (items.size() == 2) {
            Queue<Item> q1 = items.dequeue();
            Queue<Item> q2 = items.dequeue();
            items.enqueue(mergeSortedQueues(q1, q2));
            return items;
        } else {
            int k = 0;
            int l = items.size();
            Queue<Queue<Item>> q1 = new Queue<Queue<Item>>();
            Queue<Queue<Item>> q2 = new Queue<Queue<Item>>();
            while (k <= l/2) {
                q1.enqueue(items.dequeue());
                k++;
            }
            while (k < l) {
                q2.enqueue(items.dequeue());
                k++;
            }
            q1 = MergeSortHelper(q1);
            q2 = MergeSortHelper(q2);
            q1.enqueue(q2.dequeue());
            return MergeSortHelper(q1);
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        Queue<Queue<Item>> startqueue = makeSingleItemQueues(items);
        return MergeSortHelper(startqueue).dequeue();
    }

    public static void main(String[] args) {
        Queue<String> test = new Queue<>();
        test.enqueue("apple");
        test.enqueue("cherry");
        test.enqueue("dragonfruit");
        test.enqueue("edamame");
        test.enqueue("blueberry");
        System.out.println(test);
        Queue<String> test2 = MergeSort.mergeSort(test);
        System.out.println(test);
        System.out.println(test2);
    }
}
