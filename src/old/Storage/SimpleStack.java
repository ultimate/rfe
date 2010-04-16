package Storage;

import java.util.NoSuchElementException;

/**
 * The <code>SimpleStack</code> class implements a Stack
 * of objects of Type T.
 *
 * @see ListNode
 */
public class SimpleStack<T> {
      /**
       * a list pointer to the first node
       */
      private ListNode<T>  top;


      /**
       * Constructs an empty stack.
       *
       */
      public SimpleStack() {
	 top = null;
      }

      /**
       * Tests if this stack has no entries.
       *
       * @return <code>true</code> if the stack is empty;
       * <code>false</code> otherwise
       */
      public boolean isEmpty() {
	 return (top == null);
      }

      /**
       * Return the value of the top node.
       */
      public T top() throws NoSuchElementException {
	 if (top == null) {
	    throw new NoSuchElementException("No top node.");
	 }
	 return top.getData();
      }

      /**
       * Inserts a new stack node at the top with <code>someData</code>
       * as data
       *
       * @param someData the data object of the new node
       */
      public void push(T someData) {
	 ListNode<T> newNode = new ListNode<T>(someData);
	 if (isEmpty()) {
	    top = newNode;
	 } else {
	    newNode.setNext(top);
	    top = newNode;
	 }
      }

      /**
       * Delete the top node from the stack.
       */
      public void pop() throws NoSuchElementException {
	 if (top == null) {
	    throw new NoSuchElementException("No element "
					     + "for deletion.");
	 }
	 top = top.getNext();
      }

      /**
       * Returns a string representation of this stack, top element first
       *
       * @return a String representing this stack.
       */
      public String toString( ) {
	 StringBuilder strBui = new StringBuilder();

	 // use methods from class ListNode to traverse this stack
	 for (ListNode<T> curr = top; curr != null; curr = curr.getNext())
	    strBui.append(curr.getData().toString());

	 return strBui.toString() ;
      }
}