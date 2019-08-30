package com.company;

import javafx.util.Pair;

public interface MergeableHeapADT<P> {

  /**Checking, empty or not*/
  boolean isEmpty();

  /**
   * Function which removing max element from binomial heap
   */
  Pair removeMax();

  /**
   * Function for merging heaps
   * @param heap
   */
  void merge(BinomialHeap<P> heap);

  /**
   * Inserting node with @param key and @param value
   * */
  void insert(int key, P val);

  /**
   * Search a pair (priority,value) with maximum priority
   * */
  Pair max();
}
