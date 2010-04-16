package Storage;

import java.util.NoSuchElementException;

/**
 * The <code>SimpleQueue</code> class implements a queue
 * of objects of Type T.
 *
 * @see ListNode
 */
public class SimpleQueue<T>
{
      /**
       * a list pointer to the first node
       */
      private ListNode<T>  head, tail;


      /**
       * Constructs an empty queue.
       *
       */
      public SimpleQueue()
      {
			  head = null;
        tail = null;
      }

      /**
       * Tests if this queue has no entries.
       *
       * @return <code>true</code> if the queue is empty;
       * <code>false</code> otherwise
       */
      public boolean isEmpty()
      {
	 			return (head == null);
      }

      /**
       * Return the value of the head node.
       */
      public T currentData() throws NoSuchElementException
      {
	 			if (head == null)
        {
	    		throw new NoSuchElementException("Queue is empty and contains no data");
	 			}
	 			return head.getData();
      }

      /**
       * Inserts a new queue node at the end with <code>someData</code>
       * as data
       *
       * @param someData the data object of the new node
       */
      public void enqueue(T someData)
      {
	 			ListNode<T> newNode = new ListNode<T>(someData);
	 			if (isEmpty())
        {
	    		head = tail = newNode;
	 			}
        else
        {
	    		tail.setNext(newNode);
	    		tail = newNode;
	 			}
      }

      /**
       * Delete the head node from the queue.
       */
      public void dequeue() throws NoSuchElementException
      {
	 			if (head == null)
        {
	    		throw new NoSuchElementException("No element for deletion.");
	 			}
	 			head = head.getNext();
      }

      /**
       * Returns a string representation of this queue, head element first
       *
       * @return a String representing this queue.
       */
      public String toString( )
      {
				StringBuilder strBui = new StringBuilder();

	 			// use methods from class ListNode to traverse this queue
	 			for (ListNode<T> curr = head; curr != null; curr = curr.getNext())
	    		strBui.append(curr.getData().toString());

			  return strBui.toString() ;
      }
}