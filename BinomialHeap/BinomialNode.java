package com.company;

import java.util.ArrayList;

class BinomialNode<P> {

    public int priority; /**Variable for Priority of entry*/

    public P value; /**Generic type for value*/

    public BinomialNode parent; /**Pointer to parent*/

    public ArrayList<BinomialNode<P>> children; /**Array list of pointers to children*/

    public int degree; /**For degree of tree*/

    /**Constructor*/
    BinomialNode(int key, P v) {
        this.priority = key;
        this.value = v;
        parent = null;
        children = new ArrayList<>();
        degree = 0;
    }
}