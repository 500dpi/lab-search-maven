package edu.grinnell.csc207;

import java.util.Arrays;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.util.SearchUtils;

/**
 * Tests of our search methods.
 *
 * @author Sara Jaljaa
 * @author Samuel A. Rebelsky
 */
public class TestSearch {
  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * A string version of a call to binary search.
   *
   * @param values
   *   The array.
   * @param val
   *   The value we're searching for.
   *
   * @return
   *   The string "binarySearch(values, val)"
   */
  String bsCall(int[] values, int val) {
    return String.format("binarySearch(%s, %d)", Arrays.toString(values), val);
  } // bsCall

  /**
   * Assert that a search for a particular value finds the value at an
   * expected index.
   *
   * @param expected
   *   The expected index.
   * @param values
   *   The array we're searching.
   * @param val
   *   The value we're searching for.
   */
  void assertBinarySearchFinds(int expected, int[] values, int val)
      throws Exception {
    assertEquals(expected, SearchUtils.binarySearch(values, val),
        () -> bsCall(values, val));
  } // assertBinarySearchFinds(int, int[], int)

  /**
   * Assert that a search for a particular value finds the value.
   * @param values
   *   The array we're searching.
   * @param val
   *   The value we're searching for.
   */
  void assertBinarySearchFinds(int[] values, int val) throws Exception {
    assertEquals(val, values[SearchUtils.binarySearch(values, val)],
        () -> String.format("values[%s]", bsCall(values, val)));
  } // assertBinarySearchFinds(int[], int)

  /**
   * Assert that a search for a particular value fails (hopefully, because
   * the value * is not in the array).
   *
   * @param values
   *   The array we're searching.
   * @param val
   *   The value we're searching for.
   */
  void assertBinarySearchFails(int[] values, int val) throws Exception {
    assertThrows(Exception.class,
        () -> SearchUtils.binarySearch(values, val),
        () -> bsCall(values, val));
  } // assertBinarySearchFails()

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Searching the empty array should fail.
   */
  @Test
  void testBinarySearchEmpty() throws Exception {
    assertBinarySearchFails(new int[] { }, 0);
  } // testSearchEmpty()

  /**
   * Searching in a one-element array.
   */
  @Test
  void testBinarySearchOne() throws Exception {
    assertBinarySearchFinds(0, new int[] { 5 }, 5);
    assertBinarySearchFails(new int[] { 5 }, 0);
    assertBinarySearchFails(new int[] { 5 }, 10);
  } // testBinarySearchOne()

  /**
   * Searching in a two-element array.
   */
  @Test
  void testBinarySearchTwo() throws Exception {
    assertBinarySearchFinds(0, new int[] { 7, 11 }, 7);
    assertBinarySearchFinds(1, new int[] { 7, 11 }, 11);
    assertBinarySearchFails(new int[] { 7, 11 }, 0);
    assertBinarySearchFails(new int[] { 7, 11 }, 10);
    assertBinarySearchFails(new int[] { 7, 11 }, 20);
  } // testBinarySearchTwo()

  /**
   * Searching with duplicates. (Credit to SD, MM, JV.)
   */
  @Test
  void testBinarySearchDups() throws Exception {
    assertBinarySearchFinds(new int[] { 1, 1, 1, 2, 2, 3 }, 1);
    assertBinarySearchFinds(new int[] { 1, 1, 1, 2, 2, 3 }, 2);
    assertBinarySearchFinds(new int[] { 1, 1, 1, 2, 2, 3 }, 3);
  } // testBinarySearchDups()

  // +-------------+-------------------------------------------------
  // | Added Tests |
  // +-------------+

  /**
   * Searching with an array that is filled with the same one
   * value.
   *
   * @throws Exception
   *    When value is not found.
   */
  @Test
  void testBinarySearchSame() throws Exception {
    assertBinarySearchFinds(new int[] { 5, 5, 5, 5, 5 }, 5);
    assertBinarySearchFails(new int[] { 5, 5, 5, 5, 5 }, 6);
  } // testBinarySearchSame()

  /**
   * Searching with a long array that has the target element
   * near the end or beginning of the array.
   *
   * @throws Exception
   *    When value is not found.
   */
  @Test
  void testBinarySearchMaxMin() throws Exception {
    int[] longArray = new int[151];
    int[] longerArray = new int[301];

    for (int i = 0; i < longerArray.length; i++) {
      if (i < longArray.length) {
        longArray[i] = i + 1;
      } // if
      longerArray[i] = i + 1;
    } // for
    
    /* Find element at the end of the array */
    assertBinarySearchFinds(longArray, 150);
    assertBinarySearchFinds(longArray, 149);
    assertBinarySearchFinds(longArray, 148);

    /* Find element at the beginning of the array */
    assertBinarySearchFinds(longArray, 1);
    assertBinarySearchFinds(longArray, 2);
    assertBinarySearchFinds(longArray, 3);
  } // testBinarySearchMaxMin()

  /**
   * Implements tests based on Jon Bentley's Programming Pearls
   * column. Tests for searchable even values from 0-32 (exclusive).
   *
   * @throws Exception
   *    When value is not found.
   */
  @Test
  void testBinarySearchBentley() throws Exception {
    int[] arr = new int[32];

    /* Set each index to a multiple of two */
    for (int s = 0; s < arr.length; s++) {
      arr[s] = s * 2;
    } // for(s)

    /* Ensure that each index is set to an even value */
    for (int i = 0; i < arr.length; i++) {
      assertBinarySearchFinds(arr, 2 * i);
      assertBinarySearchFails(arr, (2 * i) + 1);
      assertBinarySearchFails(arr, -1);
    } // for(i)
  } // testBinarySearchBentley()
} // class TestSearch
