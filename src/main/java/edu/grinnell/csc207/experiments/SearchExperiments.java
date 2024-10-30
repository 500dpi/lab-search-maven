package edu.grinnell.csc207.experiments;

import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.function.Predicate;

import edu.grinnell.csc207.util.SearchUtils;

/**
 * Assorted experiments for searching structures.
 *
 * @author Sara Jaljaa
 * @author Samuel A. Rebelsky (starter code)
 */
public class SearchExperiments {
  /**
   * Run our experimens.
   *
   * @param args
   *   Command-line arguments. Ignored.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    /* Cannot use search() with tmp, since the type String[] does not
    implement the Iterable interface required for search()'s parameters */
    String[] tmp =
        new String[] { "alpha", "bravo", "charlie", "delta", "echo",
                      "foxtrot", "golf", "hotel", "india",
                      "juliett", "kilo", "lima", "mike",
                      "november", "oscar", "papa", "quebec",
                      "romeo", "sierra", "tango", "uniform",
                      "victor", "whiskey", "xray", "yankee", "zulu" };
    ArrayList<String> strings = new ArrayList<String>(Arrays.asList(tmp));

    try {
      // Method 1: Lambda
      String ex1c = SearchUtils.search(strings, str -> (str.length() < 5));

      // Method 2: Anonymous inner class
      Predicate<String> lessThanFiveChars = new Predicate<String>() {
        public boolean test(String str) {
          return (str.length() < 5);
        } // test(String)
      };
      String ex2c = SearchUtils.search(strings, lessThanFiveChars);

      if (ex1c == ex2c) {
        pen.println("The first string of fewer than five letters is: " + ex1c);
      } // if
    } catch (Exception e) {
      pen.println("There are no strings that have less than five letters.");
    } // try/catch

    try {
      String ex1g = SearchUtils.search(strings, (s) -> s.length() == 6);
      pen.println("The first string of exactly six letters is " + ex1g);
    } catch (Exception e) {
      pen.println("There are no strings of exactly six letters.");
    } // try/catch

    try {
      String ex2i = SearchUtils.search(strings, str -> (str.contains("u")));
      pen.println("The first string with the letter \"u\" is " + ex2i);
    } catch (Exception e) {
      pen.println("There are no string that contain the letter \"u.\"");
    } // try/catch

    pen.close();
  } // main(String[])
} // class SearchUtils
