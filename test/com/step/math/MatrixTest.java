package com.step.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class MatrixTest {

  @Test
  public void init_shouldInitializeValidMatrix() {
    Matrix matrix = secondOrderMatrix(1, 2, 3, 4);
    assertEquals(true,matrix instanceof Matrix);
    assertEquals(true,matrix.hasRow(new int[] { 1, 2 }));
    assertEquals(true, matrix.hasRow(new int[] { 3, 4 }));
  }

  @Test
  public void init_shouldNotInitializeInvalidMatrix() {
    int[][] list = { { 1, 1 }, { 2 } };
    assertEquals(Matrix.init(list), null);
  }

  @Test
  public void add_shouldAddMatricesHavingSameDimensions() {
    Matrix matrix1 = secondOrderMatrix(1, 2, 3, 4);
    Matrix matrix2 = secondOrderMatrix(1, 2, 3, 4);
    Matrix expectedSum = secondOrderMatrix(2, 4, 6, 8);

    assertEquals(expectedSum, matrix1.add(matrix2));
  }

  @Test
  public void add_shouldNotAddMatricesHavingDifferentDimensions() {
    Matrix matrix1 = secondOrderMatrix(1, 2, 3, 4);
    Matrix matrix2 = matrix_2x3(1, 2, 3, 4, 5, 6);
    assertEquals(null, matrix1.add(matrix2));
  }

  @Test
  public void sub_shouldSubtractMatricesHavingSameDimensions() {
    Matrix matrix1 = secondOrderMatrix(1, 2, 3, 4);
    Matrix matrix2 = secondOrderMatrix(1, 2, 3, 4);

    Matrix expectedDifference = secondOrderMatrix(0, 0, 0, 0);
    assertEquals(expectedDifference,matrix1.sub(matrix2));
  }

  @Test
  public void sub_shouldNotSubtractMatricesHavingDifferentDimensions() {
    Matrix matrix1 = secondOrderMatrix(1, 2, 3, 4);
    Matrix matrix2 = matrix_2x3(1, 2, 3, 4, 5, 6);

    assertEquals(null, matrix1.sub(matrix2));
  }

  @Test
  public void multiply_shouldMultiplyMatricesHavingSameDimensions() {
    Matrix matrix1 = secondOrderMatrix(1, 2, 3, 4);
    Matrix matrix2 = secondOrderMatrix(1, 2, 3, 4);

    Matrix expectedProduct = secondOrderMatrix(7, 10, 15, 22);
    assertEquals(expectedProduct,matrix1.multiply(matrix2));
  }

  @Test
  public void multiply_shouldNotMultiplyMatricesHavingDifferentDimensions() {
    Matrix matrix1 = secondOrderMatrix(1, 2, 3, 4);
    Matrix matrix2 = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);

    assertEquals(null, matrix1.multiply(matrix2));
  }

  @Test
  public void determinant_shouldCalculateDeterminantForMatrixSize1() {
    assertEquals(1,firstOrderMatrix(1).determinant());
  }

  @Test
  public void determinant_shouldCalculateDeterminantForMatrixSize2() {
    Matrix matrix = secondOrderMatrix(1, 2, 3, 4);
    assertEquals(-2,matrix.determinant());
  }

  @Test
  public void determinant_shouldCalculateDeterminantForMatrixSize3() {
    Matrix matrix = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertEquals(0,matrix.determinant());
  }

  @Test
  public void transpose_shouldTransposeMatrix() {
    Matrix matrix = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Matrix transposedMatrix = matrix.transpose();

    Matrix expected = thirdOrderMatrix(1, 4, 7, 2, 5, 8, 3, 6, 9);
    assertEquals(expected, transposedMatrix);
  }

  @Test
  public void equals_shouldReturnTrueForTwoEqualMatrices() {
    Matrix matrix1 = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Matrix matrix2 = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);

    assertEquals(matrix1, matrix2);
  }

  @Test
  public void equals_shouldReturnTrueForMatricesHavingSameHashCode() {
    Matrix matrix1 = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Matrix matrix2 = matrix1;

    assertEquals(matrix1, matrix2);
  }

  @Test
  public void equals_shouldReturnFalseForMatricesHavingDifferentElements() {
    Matrix matrix1 = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Matrix matrix2 = thirdOrderMatrix(1, 3, 3, 4, 5, 6, 7, 8, 9);

    assertNotEquals(matrix1, matrix2);
  }

  @Test
  public void equals_shouldReturnFalseForObjectOfDifferentInstance() {
    Matrix matrix = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertNotEquals(matrix, new Object());
  }

  @Test
  public void equals_shouldReturnFalseForMatricesHavingDifferentDimensions() {
    Matrix matrix1 = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Matrix matrix2 = matrix_2x3(3, 3, 3, 4, 5, 6);

    assertNotEquals(matrix1, matrix2);
  }

  @Test
  public void hasElement_shouldReturnTrueWhenMatrixHasGivenElement() {
    Matrix matrix = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertEquals(true,matrix.hasElement(2));
  }

  @Test
  public void hasElement_shouldReturnFalseWhenMatrixDoesNotHasGivenElement() {
    Matrix matrix = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertEquals(false,matrix.hasElement(10));
  }

  @Test
  public void hasRow_shouldReturnTrueWhenMatrixHasGivenSubArray() {
    Matrix matrix = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertEquals(true,matrix.hasRow(new int[] { 1, 2, 3 }));
  }

  @Test
  public void hasRow_shouldReturnFalseWhenMatrixDoesNotHasGivenSubArray() {
    Matrix matrix = thirdOrderMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertEquals(false,matrix.hasRow(new int[] { 3, 3, 3 }));
  }

  @Test
  public void toString_shouldReturnStringRepresentationOfMatrixClass() {
    Matrix matrix = secondOrderMatrix(1, 1, 2, 2);
    String string = "Matrix :\n1 1 \n2 2 \n";
    assertEquals(string, matrix.toString());
  }

  private Matrix firstOrderMatrix(int num) {
    return Matrix.init(new int[][] { { num } });
  }

  private Matrix secondOrderMatrix(int a, int b, int c, int d) {
    return Matrix.init(new int[][] { { a, b }, { c, d } });
  }

  private Matrix matrix_2x3(int a, int b, int c, int d, int e, int f) {
    return Matrix.init(new int[][] { { a, b, c }, { d, e, f } });
  }

  private Matrix thirdOrderMatrix(
    int a,
    int b,
    int c,
    int d,
    int e,
    int f,
    int g,
    int h,
    int i
  ) {
    return Matrix.init(new int[][] { { a, b, c }, { d, e, f }, { g, h, i } });
  }
}
