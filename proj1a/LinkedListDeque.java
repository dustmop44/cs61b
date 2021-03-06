public class LinkedListDeque<generic> {
    public LinkedList sentinel;
    public LinkedList last;
    public int size;

    public class LinkedList {
        public generic item;
        public LinkedList next;
        public LinkedList prev;

        public LinkedList(generic x, LinkedList L) {
            item = x;
            next = L;
        }
    }

    public LinkedListDeque(generic x) {
        LinkedList first = new LinkedList(x, sentinel);
        last = first;
        sentinel = new LinkedList((generic)"guard", first);
        first.prev = sentinel;
        sentinel.prev = first;
        size = 1;
    }

    public LinkedListDeque() {
        sentinel = new LinkedList((generic)"guard", null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        last = sentinel;
    }

    public void addFirst(generic item) {
        LinkedList second = sentinel.next;
        LinkedList first = new LinkedList(item, second);
        second.prev = first;
        sentinel.next = first;
        first.prev = sentinel;
        size += 1;
        if (last == sentinel) {
            last = first;
        }
    }

   public void addLast(generic item) {
        LinkedList secondtolast = last;
        last = new LinkedList(item, sentinel);
        secondtolast.next = last;
        last.prev = secondtolast;
        size += 1;
   }

   public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        } else {
            return false;
        }
   }

   public int size() {
        return size;
   }

   public void printDeque() {
        LinkedList p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item);
   }

   public generic removeFirst() {
        generic firstitem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return firstitem;
   }

   public generic removeLast() {
        generic lastitem = last.item;
        last = last.prev;
        last.next = sentinel;
        size -= 1;
        return lastitem;
   }

   public generic get(int index){
        int n = 0;
        LinkedList p = sentinel.next;
        while (n < index) {
            p = p.next;
            n += 1;
        }
        return p.item;
   }

   private generic getRecursivehelper(LinkedList p, int i) {
        if (i == 0) {
            return p.item;
        } else {
            return getRecursivehelper(p.next, i - 1);
        }
   }

   public generic getRecursive(int index) {
        return getRecursivehelper(sentinel.next, index);
   }

   public static void main(String[] args) {
        /** LinkedListDeque<Integer> test = new LinkedListDeque<>(3);
        test.addFirst(2);
        test.addLast(5);
        System.out.println(test.get(1));
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        test.printDeque();
        test.removeFirst();
        System.out.println(test.size());
        test.printDeque();
        test.removeLast();
        test.printDeque(); */
        LinkedListDeque<Integer> test2 = new LinkedListDeque<>();
        test2.addFirst(3);
        test2.printDeque();
        test2.addLast(4);
        test2.printDeque();
        test2.addLast(5);
        test2.addFirst(2);
        test2.printDeque();
        System.out.println(test2.get(2));
        System.out.println(test2.getRecursive(2));
        return;
   }




}