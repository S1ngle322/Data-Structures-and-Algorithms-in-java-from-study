package com.company;
/**https://codeforces.com/group/lk8Ud0ZeBu/contest/242929/submission/52919329 - submission*/
import javafx.util.Pair;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

class Main{
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        TodoList todoList = new TodoList();
        int N = in.nextInt(); /** Entering amount of fields*/
        in.nextLine();
        for (int i = 0; i < N; i++) todoList.insert_task(in.nextLine()); /**Inserting fields */
        todoList.printAllList();

    }
}

/**
 * Class TodoList where i implemented solution for task
 */

class TodoList {
    HashMap<Date, BinomialHeap<String>> heaps;  /**Different heaps, one heap for one date*/
    BinomialHeap<String> allTasks;              /**Heap-union of all heaps*/
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");/**Pattern for parsing dates*/
    PriorityQueue<Date> datesInRow;           /**Sorting all dates from least*/

    /**Constructor*/
    TodoList() {
        allTasks = new BinomialHeap<>();
        heaps = new HashMap<>();
        datesInRow = new PriorityQueue<>();
    }

    /**
     * Parsing string to insert values from there to our heaps
     * */
    public void insert_task(String str) throws ParseException {
        if (str.substring(0, 2).equals("DO")) {
            Date date = format.parse(str.substring(3, 11));
            if (heaps.containsKey(date)) {
                BinomialHeap<String> heap = heaps.get(date);
                heap.removeMax();
            } else datesInRow.add(date);
            return;
        }
        Date date = format.parse(str.substring(0, 8));
        int temp = 9;
        while (str.charAt(temp) != ' ') {
            temp++;
        }
        String task = str.substring(9, temp);
        int priority = Integer.parseInt(str.substring(temp + 1));
        if (heaps.containsKey(date)) {        /**If we had this date before - than we insert into heap associated with this node*/
            BinomialHeap<String> heap = heaps.get(date);
            heap.insert(priority, task);
        } else {
            datesInRow.add(date);             /**else we create new heap and insert values there*/
            BinomialHeap<String> heap = new BinomialHeap<>();
            heap.insert(priority, task);
            heaps.put(date, heap);
        }
    }

    /**
     * Just printing function
     * */
    void printAllList() {
        while (!datesInRow.isEmpty()) {        /**we go through all dates and place nodes from heaps to union-heap*/
            Date date = datesInRow.peek();
            datesInRow.remove(date);
            BinomialHeap<String> heap = heaps.get(date);
            System.out.println("TODOList " + format.format(date));
            while (!heap.isEmpty()) {
                Pair<Integer, String> maxNode = heap.max();
                System.out.println("   " + maxNode.getValue());
                allTasks.insert(maxNode.getKey(), maxNode.getValue());
                heap.removeMax();
            }
        }
        System.out.println("TODOList");
        while (!allTasks.isEmpty()) {        /**write all values from union-heap*/
            Pair<Integer, String> maxnode = allTasks.max();
            System.out.println("   " + maxnode.getValue());
            allTasks.removeMax();
        }
    }
}



