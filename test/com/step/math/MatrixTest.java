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
  public void shouldInitializeMatrix() {
    int[][] list1 = { { 1, 1 }, { 2, 2 } };
    Matrix matrix = Matrix.init(list1);
    assertTrue(matrix instanceof Matrix);

    int[] row1 = { 1, 1 };
    int[] row2 = { 2, 2 };
    assertTrue(matrix.hasSubArray(row1));
    assertTrue(matrix.hasSubArray(row2));
  }

  @Test
  public void shouldAddMatricesHavingSameDimensions() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);
    Matrix result1 = matrix1.add(matrix2);

    int[][] list3 = { { 2, 2 }, { 2, 2 } };
    Matrix expectedSum = Matrix.init(list3);
    assertEquals(expectedSum, result1);
  }

  @Test
  public void shouldNotAddMatricesHavingDifferentDimensions() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertNull(matrix1.add(matrix2));
  }

  @Test
  public void shouldSubtractMatricesHavingSameDimensions() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    Matrix result = matrix1.sub(matrix2);

    int[][] list3 = { { 0, 0 }, { 0, 0 } };
    Matrix expectedDifference = Matrix.init(list3);
    assertEquals(expectedDifference, result);
  }

  @Test
  public void shouldNotSubtractMatricesHavingDifferentDimensions() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertNull(matrix1.sub(matrix2));
  }

  @Test
  public void shouldMultiplyMatricesHavingSameDimensions() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    Matrix result = matrix1.sub(matrix2);

    int[][] list3 = { { 0, 0 }, { 0, 0 } };
    Matrix expectedDifference = Matrix.init(list3);
    assertEquals(expectedDifference, result);
  }

  @Test
  public void shouldNotMultiplyMatricesHavingDifferentDimensions() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertNull(matrix1.multiply(matrix2));
  }

  @Test
  public void shouldCalculateDeterminantForMatrixSize1() {
    int[][] list = { { 1 } };

    Matrix matrix = Matrix.init(list);
    assertThat(matrix.determinant(), is(1));
  }

  @Test
  public void shouldCalculateDeterminantForMatrixSize2() {
    int[][] list = { { 1, 1 }, { 1, 1 } };
    Matrix matrix = Matrix.init(list);
    assertThat(matrix.determinant(), is(0));
  }

  @Test
  public void shouldCalculateDeterminantForMatrixSize3() {
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
  public void shouldTransposeMatrix() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix = Matrix.init(list1);
    Matrix transposedMatrix = matrix.transpose();

    int[][] list2 = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
    Matrix expected = Matrix.init(list2);
    assertEquals(expected, transposedMatrix);
  }

  @Test
  public void shouldReturnTrueForTwoEqualMatrices() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] list2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertTrue(matrix1.equals(matrix2));
  }

  @Test
  public void shouldReturnTrueForMatricesHavingSameHashCode() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = matrix1;

    assertTrue(matrix1.equals(matrix2));
  }

  @Test
  public void shouldReturnFalseForMatricesHavingDifferentElements() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] list2 = { { 3, 3, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertFalse(matrix1.equals(matrix2));
  }

  @Test
  public void shouldReturnFalseForObjectOfDifferentInstance() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] list2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix = Matrix.init(list1);

    assertFalse(matrix.equals(list2));
  }

  @Test
  public void shouldReturnFalseForMatricesHavingDifferentDimensions() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] list2 = { { 3, 3, 3 }, { 4, 5, 6 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertFalse(matrix1.equals(matrix2));
  }

  @Test
  public void shouldReturnTrueWhenMatrixHasGivenElement() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix = Matrix.init(list1);

    assertTrue(matrix.hasElement(2));
  }

  @Test
  public void shouldReturnFalseWhenMatrixDoesNotHasGivenElement() {
    int[][] list1 = { { 1, 1, 1 }, { 1, 1, 1 } };
    Matrix matrix = Matrix.init(list1);

    assertFalse(matrix.hasElement(2));
  }

  @Test
  public void shouldReturnTrueWhenMatrixHasGivenSubArray() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[] list2 = { 1, 2, 3 };
    Matrix matrix = Matrix.init(list1);

    assertTrue(matrix.hasSubArray(list2));
  }

  @Test
  public void shouldReturnFalseWhenMatrixDoesNotHasGivenSubArray() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[] list2 = { 3, 3, 3 };
    Matrix matrix = Matrix.init(list1);

    assertFalse(matrix.hasSubArray(list2));
  }

  @Test
  public void shouldReturnStringRepresentationOfMatrixClass() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix = Matrix.init(list1);
    String string = "Matrix :\n1 1 \n1 1 \n";
    assertEquals(string, matrix.toString());
  }
}
