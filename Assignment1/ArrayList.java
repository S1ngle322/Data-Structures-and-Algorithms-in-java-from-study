package com.company;

import java.util.NoSuchElementException;

public class ArrayList<T> implements Methods<T>{
    private T[] data;
    private int size;

    public ArrayList(int initialCapacity){
        data = (T[]) new Object[initialCapacity];
    }

    /**
     * Initialization of capacity.
     */
    public ArrayList(){
        this(10);
    }

    /**
     *
     * @return current size of array.
     */
    @Override
    public int size(){
        return size;
    }

    /**
     * print current array.
     */
    @Override
    public void print() {
        if (isEmpty())
            System.out.println("null.");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Insert @param e on the particular @param index.
     * @param index
     * @param e
     */
    public void add(int index,T e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Index is not illegal");
        }
        /**
         Resize our array if index == length of array.
         */
        if (index == data.length){
            resize(2 * data.length);
        }
        System.arraycopy(data,index,data,index + 1,size - index);
        data[index] = e;
        size ++;
    }

    /**
     * Insert element at front of array.
     * @param e
     */
    @Override
    public void addFirst(T e){
        add(0,e);
    }

    /**
     * Insert element in end of array excluding empty cells with System.arraycopy.
     * @param e
     */
    @Override
    public void addLast(T e){
        add(size,e);
    }

    /**
     * Delete element with particular index and control the size of array by the condition.
     * Which i provided below.
     * @param index
     */
    @Override
    public void delete(int index){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index is illegal..");
        }

        if (isEmpty()){
            throw new NoSuchElementException("No elements");
        }

        System.arraycopy(data,index + 1,data,index,size - index - 1);
        data[size] = null;
        size--;

        if (size == data.length / 4){
            resize(data.length / 2);
        }
    }

    /**
     * Delete particular cell with particular element.
     * @param e
     */
    @Override
    public void deleteElement(T e) {
        for (int i = 0; i <= size; i++) {
            if (data[i] == e) {
                for (int j = i; j <= size - 1; j++) {
                    data[j] = data[j + 1];
                }
                size--;
            }
        }
    }

    /**
     * Delete first element.
     */
    @Override
    public void deleteFirst(){
        delete(0);
    }

    /**
     * Delete last element.
     */
    @Override
    public void deleteLast(){
        delete(size);
    }

    /**
     * Set an element with particular index.
     * @param index
     * @param e
     */
    @Override
    public void set(int index,T e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Index is illegal...");
        }
        data[index] = e;
    }

    /**
     * Get an element.
     * @param index
     * @return
     */
    @Override
    public T get(int index){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Index is illegal...");
        }
        return data[index];
    }

    /**
     * Private class which resize a capacity(Copy in new array).
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        T[] newData = (T [])new Object[newCapacity];

        System.arraycopy(data,0,newData,0,size);
        data = newData;
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
                Comparable  el = (Comparable) get(min);
                Comparable yui = (Comparable) get(j);
                if (yui.compareTo(el) < 0) {
                    min = j;
                }
            }
            T temp = get(i);
            set(i,get(min));
            set(min,temp);
        }

    }
}


