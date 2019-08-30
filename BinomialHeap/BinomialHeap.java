package com.company;
import javafx.util.Pair;
import java.util.*;

public class BinomialHeap<P> implements MergeableHeapADT<P>{

    private BinomialNode<P> head;
    private Map<Integer, BinomialNode<P>> roots;

    public BinomialHeap() {         /**Constructor for empty heap*/
        roots = new HashMap<>();
        head = null;
    }
    public BinomialHeap(Integer key, P priority) {      /**Constructor for heap with only one node*/
        roots = new HashMap<>();
        head = new BinomialNode<>(key, priority);
        roots.put(0, head);
    }
    @Override
    public boolean isEmpty() {              /**Checking, empty or not*/
        return roots.isEmpty();
    }


    /**
     * Function which removing max element from binomial heap
     * /Since each of ltakes O(lg n) time if H has n nodes, removeMax runs in O(lg n) time.(based on Cormen's book)
     */
    @Override
    public Pair<Integer, P> removeMax() {
        if (roots.isEmpty()) throw new NoSuchElementException();  /**Checking for empty nodes, if it is, then Exception*/
        BinomialNode<P> max = maxNode();                          /**Storing to variable <code>max</code> our max node*/
        this.roots.remove(max.degree);                            /**Removing node with links to their child's*/
        BinomialNode<P> child;
        while (!max.children.isEmpty()) {                         /**Adding all child's of node to heap, if they are not there yet*/
            child = max.children.get(0);
            max.children.remove(0);
            if (!roots.containsValue(child))                      /**check if child already in the heap, then we insert them to the heap*/
                mergeNode(child);
        }
        return new Pair<>(max.priority, max.value);
    }

    /**
     * Inserting node with @param key and @param value
     * /The function makes a one-node binomial heap,
     *  *  in O(1) time and unites
     *  it with the n-node binomial heap H in O(lg n) time.
     *  The call to union takes care of freeing the temporary binomial heap H(based on Cormens textbook) /
     * */
    @Override
    public void insert(int key, P value) {
        mergeNode(new BinomialNode<>(key, value));                  /**Using function mergeNode</code> to insert new node into heap*/

    }

    /**
     * Function for merging heaps
     * @param heap
     * We can see this as follows. Let H1 contain n1 nodes and H2 contain n2 nodes, so that n = n1+n2.
     * Then H1 contains at most lg n1 +1 roots and H2 contains at most  lg n2 +1 roots, and so H contains at
     * most  lg n1 + lg n2 +2 ≤ 2 lg n +2 = O(lg n) roots immediately after the call
     * of merge. The time to perform merge is
     * thus O(lg n). Each iteration of the while loop takes O(1) time, and there are at
     * most  lg n1  +  lg n2  + 2 iterations so each iteration either marches the
     * pointers one position down the root list of H or removes a root from the root list.
     * The total time is thus O(lg n). (based on the Cormen's book
     */

    @Override
    public void merge(BinomialHeap<P> heap) {
        Iterator<Map.Entry<Integer, BinomialNode<P>>> iterator = heap.roots.entrySet().iterator();
        while (iterator.hasNext()) {                            /**We insert all nodes that are in heap into our heap, using iterator*/
            BinomialNode<P> node = iterator.next().getValue();
            mergeNode(node);
        }
    }

    public BinomialNode<P> mergeNode(BinomialNode<P> newNode) {   /**Just insertion for node structure*/
        if (roots.isEmpty()) {                                    /**If empty, then we just put it in our list of roots*/
            roots.put(newNode.degree, newNode);
            return newNode;
        }
        /**while we have tree with same degree as we want to insert - then we merge them and increment the degree of tree*/
        while (roots.containsKey(newNode.degree)) {
            BinomialNode<P> node_to_link = roots.get(newNode.degree);
            if (newNode.priority < node_to_link.priority) {      /**remove from list of roots and then adding a merge result*/
                roots.remove(newNode.degree);
                node_to_link.children.add(newNode);
                newNode = node_to_link;
            } else {
                roots.remove(node_to_link.degree);
                newNode.children.add(node_to_link);
            }
            newNode.degree++;
        }
        roots.put(newNode.degree, newNode);                     /**adding of modified tree*/
        return newNode;
    }

    /**
     * Function wich searching a root with maximum priority
     * @return maxroot
     * /Since there are at most  lgn  + 1 roots to check, the running time of
     *      max is O(lg n).(based on Cormen's book)/
     */
    public BinomialNode<P> maxNode() {
        BinomialNode<P> maxRoot = null;
        Iterator<Map.Entry<Integer, BinomialNode<P>>> iterator = roots.entrySet().iterator();
        while (iterator.hasNext()) {                /**We are going through all roots of trees, which we have in the heap*/
            BinomialNode<P> node = iterator.next().getValue();
            if (maxRoot == null || maxRoot.priority < node.priority) maxRoot = node;
        }
        return maxRoot;
    }
    /**
     * Search a pair (priority,value) with maximum priority
     * */
    @Override
    public Pair<Integer, P> max() {
        Pair<Integer, P> maxroot = new Pair<>(null, null);
        Iterator<Map.Entry<Integer, BinomialNode<P>>> iterator = roots.entrySet().iterator();
        while (iterator.hasNext()) {        /**going through all roots of trees that we have in heap and find maximum priority*/
            BinomialNode<P> node = iterator.next().getValue();
            if (maxroot.getKey() == null || maxroot.getKey() < node.priority)
                maxroot = new Pair<>(node.priority, node.value);
        }
        return maxroot;
    }

    /**
     * We going through all trees and display their max-priority with degree of tree
     */
    public void display() {
        Iterator<Map.Entry<Integer, BinomialNode<P>>> iterator = roots.entrySet().iterator();
        while (iterator.hasNext()) {
            BinomialNode<P> node = iterator.next().getValue();
            System.out.printf("priority %d degree %d\n", node.priority, node.degree);
        }
    }
}



