package com.step.math;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MatrixTest {

  @Test
  public void init_shouldInitializeValidMatrix() {
    Matrix matrix = secondOrderMatrix(row(1, 2), row(3, 4));
    assertThat(matrix instanceof Matrix, is(true));
    assertThat(matrix.hasRow(row(1, 2)), is(true));
    assertThat(matrix.hasRow(row(3, 4)), is(true));
  }

  @Test
  public void init_shouldNotInitializeInvalidMatrix() {
    int[][] list = { { 1, 1 }, { 2 } };
    assertEquals(Matrix.init(list), null);
  }

  @Test
  public void add_shouldAddMatricesHavingSameDimensions() {
    Matrix matrix1 = secondOrderMatrix(row(1, 2), row(3, 4));
    Matrix matrix2 = secondOrderMatrix(row(1, 2), row(3, 4));
    Matrix expectedSum = secondOrderMatrix(row(2, 4), row(6, 8));

    assertThat(matrix1.add(matrix2), is(expectedSum));
  }

  @Test
  public void add_shouldNotAddMatricesHavingDifferentDimensions() {
    Matrix matrix1 = secondOrderMatrix(row(1, 2), row(3, 4));
    Matrix matrix2 = matrix_2x3(row(1, 2, 3), row(4, 5, 6));
    assertEquals(null, matrix1.add(matrix2));
  }

  @Test
  public void sub_shouldSubtractMatricesHavingSameDimensions() {
    Matrix matrix1 = secondOrderMatrix(row(1, 2), row(3, 4));
    Matrix matrix2 = secondOrderMatrix(row(1, 2), row(3, 4));

    Matrix expectedDifference = secondOrderMatrix(row(0, 0), row(0, 0));
    assertThat(matrix1.sub(matrix2), is(expectedDifference));
  }

  @Test
  public void sub_shouldNotSubtractMatricesHavingDifferentDimensions() {
    Matrix matrix1 = secondOrderMatrix(row(1, 2), row(3, 4));
    Matrix matrix2 = matrix_2x3(row(1, 2, 3), row(4, 5, 6));

    assertEquals(null, matrix1.sub(matrix2));
  }

  @Test
  public void multiply_shouldMultiplyMatricesHavingSameDimensions() {
    Matrix matrix1 = secondOrderMatrix(row(1, 2), row(3, 4));
    Matrix matrix2 = secondOrderMatrix(row(1, 2), row(3, 4));

    Matrix expectedProduct = secondOrderMatrix(row(7, 10), row(15, 22));
    assertThat(matrix1.multiply(matrix2), is(expectedProduct));
  }

  @Test
  public void multiply_shouldNotMultiplyMatricesHavingDifferentDimensions() {
    Matrix matrix1 = secondOrderMatrix(row(1, 2), row(3, 4));
    Matrix matrix2 = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));

    assertEquals(null, matrix1.multiply(matrix2));
  }

  @Test
  public void determinant_shouldCalculateDeterminantForMatrixSize1() {
    assertThat(firstOrderMatrix(1).determinant(), is(1));
  }

  @Test
  public void determinant_shouldCalculateDeterminantForMatrixSize2() {
    Matrix matrix = secondOrderMatrix(row(1, 2), row(3, 4));
    assertThat(matrix.determinant(), is(-2));
  }

  @Test
  public void determinant_shouldCalculateDeterminantForMatrixSize3() {
    Matrix matrix = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    assertThat(matrix.determinant(), is(0));
  }

  @Test
  public void transpose_shouldTransposeMatrix() {
    Matrix matrix = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    Matrix transposedMatrix = matrix.transpose();

    int[][] list2 = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
    Matrix expected = thirdOrderMatrix(
      row(1, 4, 7),
      row(2, 5, 8),
      row(3, 6, 9)
    );
    assertEquals(expected, transposedMatrix);
  }

  @Test
  public void equals_shouldReturnTrueForTwoEqualMatrices() {
    Matrix matrix1 = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    Matrix matrix2 = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));

    assertEquals(matrix1, matrix2);
  }

  @Test
  public void equals_shouldReturnTrueForMatricesHavingSameHashCode() {
    Matrix matrix1 = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    Matrix matrix2 = matrix1;

    assertEquals(matrix1, matrix2);
  }

  @Test
  public void equals_shouldReturnFalseForMatricesHavingDifferentElements() {
    Matrix matrix1 = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    Matrix matrix2 = thirdOrderMatrix(row(1, 1, 1), row(4, 5, 6), row(7, 8, 9));

    assertNotEquals(matrix1, matrix2);
  }

  @Test
  public void equals_shouldReturnFalseForObjectOfDifferentInstance() {
    Matrix matrix = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    assertNotEquals(matrix, new Object());
  }

  @Test
  public void equals_shouldReturnFalseForMatricesHavingDifferentDimensions() {
    Matrix matrix1 = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    Matrix matrix2 = matrix_2x3(row(3, 3, 3), row(4, 5, 6));

    assertNotEquals(matrix1, matrix2);
  }

  @Test
  public void hasElement_shouldReturnTrueWhenMatrixHasGivenElement() {
    Matrix matrix = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    assertThat(matrix.hasElement(2), is(true));
  }

  @Test
  public void hasElement_shouldReturnFalseWhenMatrixDoesNotHasGivenElement() {
    Matrix matrix = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    assertThat(matrix.hasElement(10), is(false));
  }

  @Test
  public void hasRow_shouldReturnTrueWhenMatrixHasGivenSubArray() {
    Matrix matrix = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    assertThat(matrix.hasRow(row(1, 2, 3)), is(true));
  }

  @Test
  public void hasRow_shouldReturnFalseWhenMatrixDoesNotHasGivenSubArray() {
    Matrix matrix = thirdOrderMatrix(row(1, 2, 3), row(4, 5, 6), row(7, 8, 9));
    assertThat(matrix.hasRow(row(3, 3, 3)), is(false));
  }

  @Test
  public void toString_shouldReturnStringRepresentationOfMatrixClass() {
    Matrix matrix = secondOrderMatrix(row(1, 1), row(2, 2));
    String string = "Matrix :\n1 1 \n2 2 \n";
    assertEquals(string, matrix.toString());
  }

  private int[] row(int num1, int num2) {
    return new int[] { num1, num2 };
  }

  private int[] row(int num1, int num2, int num3) {
    return new int[] { num1, num2, num3 };
  }

  private Matrix firstOrderMatrix(int num) {
    return Matrix.init(new int[][] { { num } });
  }

  private Matrix secondOrderMatrix(int[] row1, int[] row2) {
    int[][] list = { { row1[0], row1[1] }, { row2[0], row2[1] } };
    return Matrix.init(list);
  }

  private Matrix matrix_2x3(int[] row1, int[] row2) {
    int[][] list = {
      { row1[0], row1[1], row1[2] },
      { row2[0], row2[1], row2[2] },
    };
    return Matrix.init(list);
  }

  private Matrix thirdOrderMatrix(int[] row1, int[] row2, int[] row3) {
    int[][] list = {
      { row1[0], row1[1], row1[2] },
      { row2[0], row2[1], row2[2] },
      { row3[0], row3[1], row3[2] },
    };
    return Matrix.init(list);
  }
}
