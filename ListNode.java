package com.company;

public class ListNode<T> {
  private T data;
  private ListNode<T> next;
  private ListNode<T> prev;

  public ListNode() {}

  public ListNode(T data) {
    this.data = data;
  }

  private ListNode(ListNodeBuilder<T> builder) {
    this.data = builder.data;
    this.next = builder.next;
    this.prev = builder.prev;
  }

  public T getData(){
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public ListNode<T> getNext() {
    return next;
  }

  public void setNext(ListNode<T> next) {
    this.next = next;
  }

  public ListNode<T> getPrev() {
    return prev;
  }

  public void setPrev(ListNode<T> prev) {
    this.prev = prev;
  }

    public static class ListNodeBuilder<T> {
    private T data;
    private ListNode<T> next;
    private ListNode<T> prev;

    public ListNode<T> build() {
      return new ListNode<>(this);
    }

    public ListNodeBuilder<T> data(T data) {
      this.data = data;
      return this;
    }

    public ListNodeBuilder<T> next(ListNode<T> next) {
      this.next = next;
      return this;
    }

    public ListNodeBuilder<T> prev(ListNode<T> prev) {
      this.prev = prev;
      return this;
    }
  }
}
