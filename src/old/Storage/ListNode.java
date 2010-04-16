package Storage;

/**
 * A generic class for list nodes.
 * A list node consists of a data component and a link to the next list node
 */
public class ListNode<T> {
      /**
       * data component is of generic type T
       */
      private T _data;

      /**
       * next points to the next list node
       */
      private ListNode<T> _next;


      /**
       * Construct a list node containing a specified object
       * @param obj the object for the list node
       */
      public ListNode(T obj) {
	 _data = obj;
	 _next = null; // leave next uninitialized
      }

      /**
       * Return the data in this node.
       * @return the data of this node.
       */
      public T getData() {
	 return _data;
      }

      /**
       * Set the data of the node.
       * @param obj the data object for the node.
       */
      public void setData(T obj) {
	 _data = obj;
      }

      /**
       * Return the next node.
       * @return the reference to the next node.
       */
      public ListNode<T> getNext() {
	 return _next;
      }

      /**
       * Set the next node.
       * @param next the next node.
       */
      public void setNext(ListNode<T> next) {
	 _next = next;
      }
}