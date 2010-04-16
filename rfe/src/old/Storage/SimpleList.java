package Storage;

import java.util.NoSuchElementException;

/**
 * The <code>SimpleList</code> class implements a dynamically growable
 * list of objects. <code>SimpleList</code> administers a cursor to
 * point to the active list node and another one to point to the
 * node before the current node
 *
 * Each list is a collection of ListNodes along with two
 * implicit list cursors in the range 1..n+1, where n is
 * the current length of the list
 *
 * @see ListNode
 */
public class SimpleList<T> {

      /**
       * a list pointer to the first node
       */
      private ListNode<T>  _head;

      /**
       * a list pointer to the current node
       */
      private ListNode<T>  _curr;

      /**
       * a list pointer to node preceding the current node
       */
      private ListNode<T>  _prev;

      /**
       * Default Constructor, constructs an empty list.
       */
      public SimpleList() {
	 _head = _prev = _curr = null;
      }


      /**
       * Tests if this list has no entries.
       *
       * @return <code>true</code> if the list is empty; <code>false</code>
       * otherwise
       */
      public boolean isEmpty() {
	 return (_head == null);
      }

      /**
       * Set the list cursor to the first list element.
       */
      public void reset() {
	 _curr = _head;
	 _prev = null;
      }

      /**
       * Test if the list cursor stands behind the last element of the list.
       *
       * @return <code>true</code> if the cursor stands behind the last
       * element of the list; <code>false</code> otherwise
       */
      public boolean isAtEnd() {
	 return (_curr == null);
      }

      /**
       * increment the list cursor to the next list node
       * Throws <code>NoSuchElementException</code> if
       * <code>endOfList() == true</code>
       */
      public void increment() throws NoSuchElementException {
	 if(isAtEnd()) {
	    throw new NoSuchElementException("No further list node.");
	 }
	 _prev = _curr;
	 _curr = _curr.getNext();
      }

      /**
       * Return the value of the current node.
       * Throws <code>NoSuchElementException</code> if
       * there is no current node
       */
      public T currentData() throws NoSuchElementException {
	 if (_curr == null) {
	    throw new NoSuchElementException("No current list node.");
	 }
	 return _curr.getData();
      }

      /**
       * Inserts a new list node before the current node.
       * If the list is empty insert at front.
       * The cursor points to the new list node.
       *
       * @param someData the object to be added.
       */
      public void insertBefore(T someData) {
	 ListNode<T> newNode = new ListNode<T>(someData);
	 if (isEmpty()) {
	    _head = _curr = newNode;
	 } else {
	    newNode.setNext(_curr);
	    _curr = newNode;
	    if (_prev != null) {
	       _prev.setNext(newNode);
	    } else {
	       _head = newNode;
	    }
	 }
      }

      /**
       * Inserts a new list node after the current node. The cursor points
       * to the new list node.
       * Throws <code>NoSuchElementException</code> if
       * there is no current node
       *
       * @param someData the object to be added.
       */
      public void insertAfter(T someData) throws NoSuchElementException {
	 ListNode<T> newNode = new ListNode<T>(someData);
	 if (isEmpty()) {
	    _head = _curr = newNode;
	 } else {
	    if (_curr == null) {
	       throw new NoSuchElementException(
		  "Cursor not on a valid element.");
	    }
	    newNode.setNext(_curr.getNext());
	    _curr.setNext(newNode);
	    _prev = _curr;
	    _curr = newNode;
	 }
      }

      /**
       * Delete the current node from the list.
       * Throws <code>NoSuchElementException</code> if
       * there is no current node
       */
      public void delete() throws NoSuchElementException {
	 if (_curr == null) {
	    throw new NoSuchElementException("No element for deletion.");
	 }
	 if (_curr == _head) {
	    _head = _curr = _curr.getNext();
	 } else {
	    _curr = _curr.getNext();
	    _prev.setNext(_curr);
	 }
      }

      /**
       * Returns a string representation of this list.
       *
       * @return a String representing this list.
       */
      public String toString( ) {
	 StringBuilder strBui = new StringBuilder() ;

	 for (reset(); ! isAtEnd(); increment())
	    strBui.append(currentData().toString());

	 return strBui.toString() ;
      }
}