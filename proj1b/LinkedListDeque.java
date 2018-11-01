public class LinkedListDeque<Item> implements Deque<Item> {
    public LinkedList sentinel;
    public LinkedList last;
    public int size;

    public class LinkedList {
        public Item item;
        public LinkedList next;
        public LinkedList prev;

        public LinkedList(Item x, LinkedList L) {
            item = x;
            next = L;
        }
    }

    public LinkedListDeque(Item x) {
        LinkedList first = new LinkedList(x, sentinel);
        last = first;
        sentinel = new LinkedList((Item)"guard", first);
        first.prev = sentinel;
        sentinel.prev = first;
        size = 1;
    }

    public LinkedListDeque() {
        sentinel = new LinkedList((Item)"guard", null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        last = sentinel;
    }

    @Override
    public void addFirst(Item item) {
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

    @Override
   public void addLast(Item item) {
        LinkedList secondtolast = last;
        last = new LinkedList(item, sentinel);
        secondtolast.next = last;
        last.prev = secondtolast;
        size += 1;
   }

   @Override
   public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        } else {
            return false;
        }
   }

   @Override
   public int size() {
        return size;
   }

   @Override
   public void printDeque() {
        LinkedList p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item);
   }

   @Override
   public Item removeFirst() {
        Item firstitem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return firstitem;
   }

   @Override
   public Item removeLast() {
        Item lastitem = last.item;
        last = last.prev;
        last.next = sentinel;
        size -= 1;
        return lastitem;
   }

   @Override
   public Item get(int index){
        int n = 0;
        LinkedList p = sentinel.next;
        while (n < index) {
            p = p.next;
            n += 1;
        }
        return p.item;
   }

   private Item getRecursivehelper(LinkedList p, int i) {
        if (i == 0) {
            return p.item;
        } else {
            return getRecursivehelper(p.next, i - 1);
        }
   }

   public Item getRecursive(int index) {
        return getRecursivehelper(sentinel.next, index);
   }

}