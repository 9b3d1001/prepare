public class LinkedListDeletion {

    public static void main(String[] args) {

    }

    public void deleteNode(ListNode node) {
        while(node.next != null) {
            node.val = node.next.val;
            node = node.next;
        }
        node = null;
    }
}
