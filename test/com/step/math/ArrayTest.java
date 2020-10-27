package com.step.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayTest {

  @Test
  public void shouldInitializeValidArray() {
    int[] list1 = { 1, 2, 3 };
    Array array = Array.init(list1);
    assertTrue(array instanceof Array);

    assertTrue(array.has(1));
    assertTrue(array.has(2));
    assertTrue(array.has(3));
  }

  @Test
  public void shouldReturnIndexOfElementIfPresent() {
    int[] list1 = { 1, 2, 3 };
    Array array = Array.init(list1);
    assertEquals(1,array.findIndex(2));
  }

  @Test
  public void shouldReturnNegative1IfElementAbsent() {
    int[] list1 = { 1, 2, 3 };
    Array array = Array.init(list1);
    assertEquals(-1,array.findIndex(4));
  }
}
