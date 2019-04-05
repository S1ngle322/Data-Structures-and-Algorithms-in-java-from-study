package com.company;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Methods<T>{

  private ListNode<T> head;
  private ListNode<T> tail;
  private int size;

  public DoublyLinkedList() {
    this.head = new ListNode();
    this.size = 0;
  }

  /**
   * Inserts data element at the front of the list.
   *
   * @param data data to insert.
   */

  public void addFirst(T data) {
    ListNode<T> newNode = new ListNode.ListNodeBuilder().data(data).build();
    if (isEmpty()) {
      this.head = newNode;
      this.tail = this.head;
    } else {
      this.head.setPrev(newNode);
      newNode.setNext(this.head);
      this.head = newNode;
    }
    this.size++;
  }

  /**
   * Inserts data element at the end of the list.
   *
   * @param data data to insert.
   */
  @Override
  public void addLast(T data) {
    ListNode<T> newNode = new ListNode.ListNodeBuilder().data(data).build();
    if (isEmpty()) {
      this.head = newNode;
      this.tail = this.head;
    } else {
      ListNode<T> temp = this.tail;
      newNode.setPrev(temp);
      temp.setNext(newNode);
      this.tail = temp.getNext();
    }
    this.size++;
  }

  /**
   * Inserts element at a specific index within the list.
   *
   * @param index position at which to insert.
   * @param data  element to insert.
   */
  @Override
  public void add(int index, T data) {
    ListNode<T> newNode = new ListNode.ListNodeBuilder().data(data).build();
    if (isEmpty()) {
      this.head = newNode;
      this.tail = this.head;
    } else if (index == 0) {
      addFirst(data);
    } else {
      ListNode<T> temp = this.head;
      for (int i = 1; i < index; i++) {
        temp = temp.getNext();
      }
      newNode.setPrev(temp);
      newNode.setNext(temp.getNext());
      temp.setNext(newNode);
    }
    this.size++;
  }

  /**
   * Removes head element of list.
   */
  @Override
  public void deleteFirst() {
    if (!isEmpty()) {
      if (this.head.getNext() != null) {
        this.head = this.head.getNext();
      } else {
        this.head = null;
        this.tail = null;
      }
      this.size--;
    }
  }

  /**
   * Removes tail element of list.
   */
  @Override
  public void deleteLast() {
    if (!isEmpty()) {
      if (this.tail.getPrev() != null) {
        this.tail = this.tail.getPrev();
      } else {
        this.tail = null;
        this.head = null;
      }
      this.size--;
    }
  }
  /**
   * Removes element at a specific index from list.
   *
   * @param index position at which to remove element.
   */
  @Override
  public void delete(int index) {
    if (!isEmpty()) {
      if (index == 0) {
        deleteFirst();
      } else {
        ListNode<T> temp = this.head;
        for (int i = 0; i < index; i++) {
          temp = temp.getNext();
        }
        temp.getPrev().setNext(temp.getNext());
        temp = temp.getPrev();
      }
      this.size--;
    }
  }

  /**
   *  Remove element with particular data.
   *  First condition means that, if our size = 0, then throwException.
   *  Second condition means that, we removing our element which we are found.
   *  3-rd cond. remove from head.
   *  4-th cond. remove from tail.
   * @param data
   */

  @Override
  public void deleteElement(T data) throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("No such element");
    }
    if (this.size == 1 && this.head.getData().equals(data)) {
      this.head = this.tail = null; // removing only data from list
      this.size--;
      return;
    }
    if (this.head.getData().equals(data)) { // removing head
      deleteFirst();
      return;
    }
    if (this.tail.getData().equals(data)) { // removing tail
      deleteLast();
      return;
    }

    for (ListNode<T> current = this.head; current != null; current = current.getNext()) {
      if (current.getData().equals(data)) {
        ListNode<T> previous = current.getPrev();
        ListNode<T> next = current.getNext();
        previous.setNext(next);
        next.setPrev(previous);
        this.size--;
        return;
      }
    }
    throw new NoSuchElementException("No such element");
  }


  /**
   * Returns the head element of the list.
   *
   * @return the element at the head of the list
   */

  public T getElementAtFront() {
    if (!isEmpty()) {
      return this.head.getData();
    }
    return null;
  }


  /**
   * Returns the tail element of the list.
   *
   * @return the tail element of the list.
   */

  public T getElementAtEnd() {
    if (!isEmpty()) {
      this.tail.getData();
    }
    return null;
  }

  /**
   * Returns the element at a specific index within the list.
   *
   * @param index position at which to search for / return element.
   * @return the element located at the given index.
   */
  @Override
  public T get(int index) {
    if (!isEmpty()) {
      if (index == 0) {
        return getElementAtFront();
      } else if (index == this.size) {
        return getElementAtEnd();
      } else {
        ListNode<T> temp = this.head;
        for (int i = 0; i < index; i++) {
          temp = temp.getNext();
        }
        return temp.getData();
      }
    }
    return null;
  }

    /**
     * Set data with particular index
     * @param index
     * @param data
     */
  @Override
  public void set(int index, T data) {
     ListNode element = head;

     for(int i = 0; i < size; i++){
         if (i == index){
             break;
         }
         element = element.getNext();
     }
     element.setData(data);
  }

  /**
   * Determines whether the underlying list structure is empty or not.
   *
   * @return true if list is empty, false if otherwise.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the current size of the list.
   *
   * @return integer representing current list size.
   */
  @Override
  public int size() {
    return this.size;
  }

    /**
   * Printing current list with data.
   */
  @Override
  public void print() {
    ListNode tmpNode = head;

    while (tmpNode != null) {
      System.out.print(tmpNode.getData() + " -> ");
      tmpNode = tmpNode.getNext();
    }

    System.out.println("null.");
  }

    /**
     * @param n which get a size of list.
     * With help of 2 loops(one of them is nest to other) we sorting our DLL.
     * By the comparator we can compare variables of several types.
     */
  @Override
  public void selectionSort(){
      int n = size;
      for (int i = 0; i < n - 1; i++){
            int min = i;
            for (int j = i + 1; j < n; j++) {
                Comparable element1 = (Comparable) get(min);
                Comparable element2 = (Comparable) get(j);
                if (element2.compareTo(element1) < 0) {
                    min = j;
                }
            }
            T temp = get(i);
            set(i,get(min));
            set(min,temp);
      }

    }

}

