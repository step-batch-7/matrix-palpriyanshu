package com.step.math;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatrixTest {

  @Test
  public void testInit() {
    int[][] list1 = { { 1, 1 }, { 2, 2 } };
    Matrix matrix = Matrix.init(list1);
    assertTrue(
      "init: should return instance of Matrix class ",
      matrix instanceof Matrix
    );

    int[] row1 = { 1, 1 };
    int[] row2 = { 2, 2 };
    assertTrue(matrix.hasSubArray(row1));
    assertTrue(matrix.hasSubArray(row2));
  }

  @Test
  public void testAddition() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);
    Matrix result1 = matrix1.add(matrix2);

    int[][] list3 = { { 2, 2 }, { 2, 2 } };
    Matrix expectedSum = Matrix.init(list3);
    assertEquals(
      "Add: should return sum of two matrices ",
      expectedSum,
      result1
    );
  }

  @Test
  public void testUnequalMatrixAddition() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertNull(
      "Add: should return null for given unequal matrices dimensions",
      matrix1.add(matrix2)
    );
  }

  @Test
  public void testSubtraction() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    Matrix result = matrix1.sub(matrix2);

    int[][] list3 = { { 0, 0 }, { 0, 0 } };
    Matrix expectedDifference = Matrix.init(list3);
    assertEquals(
      "Sub: should return difference of two matrices ",
      expectedDifference,
      result
    );
  }

  @Test
  public void testUnequalMatrixSubtraction() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertNull(
      "Sub: should return null for given unequal matrices dimensions ",
      matrix1.sub(matrix2)
    );
  }

  @Test
  public void testMultiplication() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    Matrix result = matrix1.sub(matrix2);

    int[][] list3 = { { 0, 0 }, { 0, 0 } };
    Matrix expectedDifference = Matrix.init(list3);
    assertEquals(
      "Multiply: should return product of two matrices",
      expectedDifference,
      result
    );
  }

  @Test
  public void testUnequalMatrixMultiplication() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertNull(
      "Multiply: should return null for matrix of invalid dimensions",
      matrix1.multiply(matrix2)
    );
  }

  @Test
  public void testDeterminantForMatrixOfSize1() {
    int[][] list = { { 1 } };

    Matrix matrix = Matrix.init(list);
    assertThat(matrix.determinant(), is(1));
  }

  @Test
  public void testDeterminantForMatrixOfSize2() {
    int[][] list = { { 1, 1 }, { 1, 1 } };
    Matrix matrix = Matrix.init(list);
    assertThat(matrix.determinant(), is(0));
  }

  @Test
  public void testDeterminantForMatrixOfSizeMoreThan2() {
    int[][] list = {
      { 4, 3, 2, 2 },
      { 0, 1, -3, 3 },
      { 0, -1, 3, 3 },
      { 0, 3, 1, 1 },
    };

    Matrix matrix = Matrix.init(list);
    assertThat(matrix.determinant(), is(-240));
  }

  @Test
  public void testTranspose() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix = Matrix.init(list1);
    Matrix transposedMatrix = matrix.transpose();

    int[][] list2 = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
    Matrix expected = Matrix.init(list2);
    assertEquals(
      "transpose: should return transpose of a matrix",
      expected,
      transposedMatrix
    );
  }

  @Test
  public void testEqualMatrices() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] list2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertTrue(
      "Equal: should return true for two equal matrices",
      matrix1.equals(matrix2)
    );
  }

  @Test
  public void testEqualityOfMatricesWithSameReference() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix = Matrix.init(list1);

    assertTrue(
      "Equal: should return true for two matrices having the same reference",
      matrix.equals(matrix)
    );
  }

  @Test
  public void testEqualityOfMatricesWithDifferentElements() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] list2 = { { 3, 3, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertFalse(
      "Equal: should return false for two matrices with different elements",
      matrix1.equals(matrix2)
    );
  }

  @Test
  public void testEqualityOfMatricesWithDifferentInstance() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] list2 = { { 3, 3, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix = Matrix.init(list1);

    assertFalse(
      "Equal: should return false for one matrix Object and one other Object",
      matrix.equals(list2)
    );
  }

  @Test
  public void testEqualityOfMatricesWithDifferentDimensions() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] list2 = { { 3, 3, 3 }, { 4, 5, 6 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertFalse(
      "Equal: should return false for two matrices having different dimensions",
      matrix1.equals(matrix2)
    );
  }

  @Test
  public void testIfElementPresent() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix = Matrix.init(list1);

    assertTrue(
      "hasElement: should return true when given element is present",
      matrix.hasElement(2)
    );
  }

  @Test
  public void testIfElementNotPresent() {
    int[][] list1 = { { 1, 1, 1 }, { 1, 1, 1 } };
    Matrix matrix = Matrix.init(list1);

    assertFalse(
      "hasElement: should return false when given element is absent",
      matrix.hasElement(2)
    );
  }

  @Test
  public void testIfSubArrayPresent() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[] list2 = { 1, 2, 3 };
    Matrix matrix = Matrix.init(list1);

    assertTrue(
      "hasSubArray: should return true when given subArray is present ",
      matrix.hasSubArray(list2)
    );
  }

  @Test
  public void testIfSubArrayNotPresent() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[] list2 = { 3, 3, 3 };
    Matrix matrix = Matrix.init(list1);

    assertFalse(
      "hasSubArray: should return false when given subArray is absent ",
      matrix.hasSubArray(list2)
    );
  }

  @Test
  public void testToString() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix = Matrix.init(list1);
    String string = "Matrix :\n1 1 \n1 1 \n";
    assertEquals(
      "toString : should return textual representation of matrix class",
      string,
      matrix.toString()
    );
  }
}
