package edu.grinnell.csc207.experiments;

/**
 * Couldn't find an element.
 */
public class NotFoundException extends Exception {
  public NotFoundException() {
    super();
  } // NotFoundException()

  public NotFoundException(String msg) {
    super(msg);
  } // NotFoundException(String)
} // NotFoundException
