package com.step.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatrixTest {

  @Test
  public void shouldAddTwoMatrix() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    int[][] list3 = { { 2, 2 }, { 2, 2 } };
    Matrix expectedSum = Matrix.init(list3);

    Matrix result = matrix1.add(matrix2);
    assertEquals(expectedSum, result);
  }

  @Test
  public void shouldSubTwoMatrix() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    int[][] list3 = { { 0, 0 }, { 0, 0 } };
    Matrix expectedDifference = Matrix.init(list3);

    Matrix result = matrix1.sub(matrix2);
    assertEquals(expectedDifference, result);
  }
}
