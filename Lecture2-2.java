public class SLList {
	public class IntNode {
		public int item;
		public IntNode next;

		public IntNode(int i, IntNode n) {
			item = i;
			next = n;
		}

        public void addFirstIntNode(int i) {
            IntNode L = new IntNode(this.item, null);
            this.item = i;
            this.next = L;
        }
	}

	private IntNode first;

	public SLList(int x) {
		first = new IntNode(x, null);
	}

	public addFirst(int x) {
		first = new IntNode(x, first);
	}

	public getFirst() {
		return first.item;
	}


}