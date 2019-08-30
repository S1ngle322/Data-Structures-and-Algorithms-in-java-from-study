/**
 * TUTORIAL HOW TO TEST MY PROGRAM:
 * 1) If you wanna to test methods separately, just comment lines in main method.
 * 2) For example: if you wanna to test 1-st task.
 * 3) You should to comment 2 other input methods, because without input methods, functions will not work, they are connected.
 * 4) I call particular functions in input method.
 * 5) Happy testing!
 */

package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{
    public static String invalid;                                   /** Global variable for method text Auto correction. */

    public static void main(String[] args) {
        inputEstimate();                                            /** Start point for function estimate, later we will pass arguments to estimate. */
        inputSpellChecker();                                        /** Start point for function spellChecker, later we will pass arguments to spellChecker. */
        inputTextAutocorrection();                  /** Start point for function text Auto correction, later we will pass arguments to text Auto correction. */
        System.out.println(invalid);                                /** Printing our new string for 3-rd task. */

    }

    /**
     * input function for "estimate"
     */
    public static void inputEstimate(){
        Scanner input = new Scanner(System.in);
        int amount = Integer.parseInt(input.nextLine());
        String[] string;                                           /** Array with strings where we will store our 2 words, 1-st is correct, 2-nd is incorrect. */
        for (int i = 0; i < amount; i++){
            string = input.next().split(" ");                    /**filling array according to space in regex. */
            System.out.println(estimate(string[0], string[1]));         /**calling function estimate where we are passing 2 arguments. */
        }
    }

    /**
     * input function for spell checker
     */
    public static void inputSpellChecker() {
        Scanner input = new Scanner(System.in);
        int amount = Integer.parseInt(input.nextLine());
        String[] dictionary;                                            /** Creating array "dictionary". */
        dictionary = input.nextLine().split(" ");                 /** filling an array according to space in regex. */
        String word = input.nextLine();
        spellChecker(dictionary, word);                                 /** Calling function "spellChecker" where we are passing 2 arguments:
                                                                        array of words and just word with which we are going to work. */
    }

    public static void inputTextAutocorrection(){
        Scanner input = new Scanner(System.in);
        String[] dictionary;                                            /** Array for correct words. */
        String[] words;                                                 /** Array for incorrect words. */
        dictionary = input.nextLine().split("[^a-z]+");          /** Here we are storing words of lowerCase only, we parse it by regex. */
        invalid = input.nextLine();  /** In @param invalid we are storing everything except of words in loweCase, like ". , 123 < > and etc.". */
        words = invalid.split("[^a-z]+");
        textAutocorrection(dictionary, words);
    }
    /**
     * Function retrieve 2 parameters from "inputEstimate" which are 2 strings.
     * Pseudo code was Retrieved from </https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance>.
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int estimate(String word1, String word2){
        int n = word1.length() + 1;                           /** be a 2-d array of integers, dimensions length(string1) + 1. */
        int m = word2.length() + 1;                           /** be a 2-d array of integers, dimensions length(string2) + 1. */
        int[][] new_matrix = new int[n][m];
        int cost = 0;
        for (int i = 0; i < n; i++) {                           /** entering our 1-st word in first row of our matrix. */
            new_matrix[i][0] = i;
        }
        for (int j = 0; j < m; j++) {                           /** entering our 2-nd word in first column of our matrix. */
            new_matrix[0][j] = j;
        }

        for (int i = 1; i < n; i++){                            /** note that matrix is zero-indexed, while string1 and string2 are one-indexed. */
            for (int j = 1; j < m; j++){
                /** Price for exchange. */
                if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                    cost = 0;
                } else {
                    cost = 1;
                }
                /**
                 * function min can take any amount of arguments because of her structure which i provide below.
                 */
                new_matrix[i][j] = minimumOutOfThree(new_matrix[i - 1][j] + 1,   /**deletion. */
                        new_matrix[i][j - 1] + 1,                                       /** insertion. */
                        new_matrix[i - 1][j - 1] + cost);                               /**substitution. */
                if (i > 1 && j > 1 && word1.charAt(i - 1) == word2.charAt(j - 2) && word1.charAt(i - 2) == word2.charAt(j - 1)) {
                    new_matrix[i][j] = Math.min(new_matrix[i][j], new_matrix[i - 2][j - 2] + cost);  /** transposition. */
                }
            }
        }
        return new_matrix[n - 1][m - 1];                                                /** Return result, which is amount of mistakes. */
    }


    /**
     * Function can take any amount of arguments and return minimum out of 3 arguments.
     * @param args
     * @return
     */
    public static int minimumOutOfThree(int... args){
        int min = args[0];
        for (int i = 0; i < args.length; i++) {
            if (args[i] < min) min = args[i];
        }
        return min;
    }
    public static void spellChecker(String[] dictionary, String word){
        Arrays.sort(dictionary);
        int minimumOfEstimate = -1;                                         /** Variable for statement in condition. */
        int amountOfElementsWithMinOfEstimate = 0;                          /** Counter for amount of estimates. */
        int[] array = new int[dictionary.length];
        for (int i = 0; i < array.length; i++){
            int c = estimate(dictionary[i], word);                          /** Variable for storing estimate. */
            array[i] = c;
            if (minimumOfEstimate == -1 || c < minimumOfEstimate){          /** Storing minimum of estimate. */
                minimumOfEstimate = c;
            }
        }
        for (int i = 0; i < dictionary.length; i++) {
            if (array[i] == minimumOfEstimate) {                            /** Counter for amount of total elements for estimate. */
                amountOfElementsWithMinOfEstimate++;
            }
        }
        for (int i = 0; i < dictionary.length; i++) {
            if ((array[i] == minimumOfEstimate) && (amountOfElementsWithMinOfEstimate != 1)){/** if our estimate not equal to minimum. */
                System.out.print(dictionary[i] + " ");                                       /** Output with spaces. */
                amountOfElementsWithMinOfEstimate--;
            }else{
                if (array[i] == minimumOfEstimate){                                          /**If it is last word, output without space. */
                    System.out.print(dictionary[i]);
                }
            }
        }
    }

    public static void textAutocorrection(String[] Correct, String[] Incorrect){
        int minimumOfEstimate;                                                              /** Variable where we are going to store our estimate.*/
        String bestChange = "";                 /** String where we are going to store our best case of changing words with minimum of estimate. .*/
        int c = -1;                                                                         /** Variable for statement in condition which i provide below.*/
        for (int i = 0; i < Incorrect.length; i++){                                         /** Loop for checking our array with incorrect words. */
            minimumOfEstimate = -1;                                                         /** For statement in condition which is every time TRUE. */
            for (int j = 0; j < Correct.length; j++) {                                      /** Loop for checking our array with correct words. */
                c = estimate(Incorrect[i], Correct[j]);                                     /** Storing here distance between 2 words. */
                if (minimumOfEstimate == -1) {
                    minimumOfEstimate = c;                                                  /** Storing estimate from variable "c" to other. */
                    bestChange = Correct[j];  /** Best change means that, our correct words in array at position "j" should be change later. */
                }else if (c <= minimumOfEstimate){                   /** Else if our estimate less or equal to minimum, then store it again. */
                    minimumOfEstimate = c;
                    bestChange = Correct[j];
                }
            }

            replacement(Incorrect[i], bestChange);/** Here we are calling function replacement which is going to change our word with mistake to words without it. */
                                                  /** Passing here array with incorrect word at position i, and word on which we will changing. */
        }
    }

    public static void replacement(String changeStart, String changeFinish){      /** Passed 2 arguments from function text Auto correction. */
        /**
         * Regex for taking Start point of our string and it is patter on which we are going to accord.
         * We take everything except of symbols(dots, comas, braces and etc.)
         */
        Pattern pattern = Pattern.compile(("(\\b|[^a-zA-Z]+)") + changeStart + "([^a-zA-Z]+|\\b)");
        Matcher matcher = pattern.matcher(invalid);
        while(matcher.find()){
            String string;                                                  /** String where we are going to store our new string without mistakes. */
            string = matcher.group().replaceAll(changeStart, changeFinish); /** matcher.group take string which match with regex. */
            invalid = invalid.replace(matcher.group(), string);             /** Replacing such string instead of our incorrect string. */
        }
    }


}
