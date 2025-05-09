public class LinkedListReveral {

    public static void main(String[] args) {
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        ListNode result = reverseList(listNode1);
        System.out.println(result);

    }

    // 1, 2, 3, 4, 5
    //5.next = 4,
    public static ListNode reverseList(ListNode head) {
        ListNode listNode = null;
        if(head == null) {
            return head;
        }
        if(head.next != null) {
            listNode = reverseList(head.next);
            ListNode temp = listNode;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = head;
            temp.next.next = null;
        } else {
            listNode = new ListNode(head.val);
        }
        return listNode;
    }
}
