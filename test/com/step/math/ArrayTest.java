package com.step.math;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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
    assertThat(array.findIndex(2), is(1));
  }

  @Test
  public void shouldReturnNegative1IfElementAbsent() {
    int[] list1 = { 1, 2, 3 };
    Array array = Array.init(list1);
    assertThat(array.findIndex(4), is(-1));
  }
}
