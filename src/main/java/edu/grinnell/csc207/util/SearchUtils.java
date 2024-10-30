package edu.grinnell.csc207.util;

import java.util.function.Predicate;
import java.util.Arrays;

/**
 * Assorted utilities for searching structures.
 *
 * @author Sara Jaljaa
 * @author Samuel A. Rebelsky (starter code)
 */
public class SearchUtils {
  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <=
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static int iterativeBinarySearch(int[] vals, int val) throws Exception {
    int upper = vals.length - 1;
    int lower = 0;
    int length = vals.length;

    /* Start at the center value of vals[] and iterate until the
      length of the search reaches 0 */
    for (int i = vals.length / 2; length >= 0;) {

      /* If the value matches, return the index it is found at */
      if (vals[i] == val) {
        return i;

      /* If the value is less than val, set the lower bound to the
        next index over & keep the upper bound */
      } else if (vals[i] < val) {
        lower = i + 1;

      /* If the value is greater than val, set the upper bound to the
        index back one & keep the lower bound */
      } else if (vals[i] > val) {
        upper = i - 1;
      } // elif

      /* Ensure the length is nonnegative */
      length = upper - lower;

      /* Find the new starting index from the average of the upper &
        lower bounds */
      i = (upper + lower) / 2;
    } // for
    throw new Exception("Value " + val + " not found!");
  } // iterativeBinarySearch

  /* Exercise 5: Midpoint Check */
    // When you leave the +/-1 modifier out of the binary search for ub/lb,
    // the loop/recursive call will not terminate and instead continue
    // forever.

  /* Example: */
    // arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 }
    // val = 8

    // ub = 9
    // lb = 1

    /*  1st iteration:   */
    /* 1 2 3 4 5 6 7 8 9 */
    /*      ¯¯¯          */
    /*     lb = 4        */

    /*  2nd iteration:   */
    /*    4 5 6 7 8 9    */
    /*       ¯¯¯         */
    /*     lb = 6        */

    /*  3rd iteration:   */
    /*     6 7 8 9       */
    /*      ¯¯¯          */
    /*     lb = 7        */

    /*  4th iteration:   */
    /*      7 8 9        */
    /*     ¯¯¯           */
    /*     lb = 7        */

    // Since will always be the first number (3/2 = 1 -- truncated),
    // the bound forever stays stuck at lb = 7, ub = 9. Had lb been
    // incremented after each search & discard, the iteration would
    // have progressed since the bound would continue to change,
    // eventually reaching 8.

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <=
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static int recursiveBinarySearch(int[] vals, int val) throws Exception {
    int ub = vals.length - 1;
    int lb = 0;
    int mid = (lb + ub) / 2;

    if (mid >= 0) {
      if (vals[mid] == val) {
        return mid;
      } else if (vals[mid] < val) {
        lb = mid + 1;
      } else if (vals[mid] > val) {
        ub = mid - 1;
      } // elif
      return rbsHelper(vals, lb, ub, val);
    } // if

    // if (lb <= ub) {
    //   if (vals[mid] == val) {
    //     return mid;
    //   } else if (vals[mid] < val) {
    //     lb = mid + 1;
    //   } else if (vals[mid] > val) {
    //     ub = mid - 1;
    //   } // elif
    //   return rbsHelper(vals, lb, ub, val);
    // } // if
    throw new Exception("Value " + val + " not found!");
  } // recursiveBinarySearch

  /**
   * Search for val in a subarray of values, return the index of an
   * instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param lb
   *   The lower bound of the area of interest (inclusive)
   * @param ub
   *   The upper bound of the area of interest (exclusive)
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i between lb and ub s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <=
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static int rbsHelper(int[] vals, int lb, int ub, int val) throws Exception {
    for (int i = lb; i < ub; i++) {
      if (vals[i] == val) {
        return i;
      } // if
    } // for
    throw new Exception("Value " + val + " does not exist in array!");
  } // rbsHelper

  // +----------------+----------------------------------------------
  // | Public methods |
  // +----------------+

  /**
   * Search values for the first value for which pred holds.
   *
   * @param <T>
   *   The type of values we're examining
   * @param values
   *   The iterable we're searching
   * @param pred
   *   The predicate used to determine whether or not the value is
   *   acceptable
   *
   * @return the first matching element.
   *
   * @throws Exception
   *   If no matching value is found.
   */
  public static <T> T search(Iterable<T> values, Predicate<? super T> pred)
      throws Exception {
    for (T value : values) {
      if (pred.test(value)) {
        return (T) value;
      } // if
    } // for
    throw new Exception("No matching value found!");
  } // search(Iterable<T>, Predicate<? super T>)

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <=
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  public static int binarySearch(int[] vals, int val) throws Exception {
    return iterativeBinarySearch(vals, val);
    // return recursiveBinarySearch(vals, val);
  } // binarySearch
} // class SearchUtils
