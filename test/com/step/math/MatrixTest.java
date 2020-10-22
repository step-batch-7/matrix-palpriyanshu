package com.step.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatrixTest {

  @Test
  public void testAddition() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);
    Matrix result1 = matrix1.add(matrix2);

    int[][] list3 = { { 2, 2 }, { 2, 2 } };
    Matrix expectedSum = Matrix.init(list3);
    assertEquals("Add: should be equal ", expectedSum, result1);
  }

  @Test
  public void testUnequalMatrixAddition() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertNull("Add: should be null ", matrix1.add(matrix2));
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
    assertEquals("Sub: should be equal ", expectedDifference, result);
  }

  @Test
  public void testUnequalMatrixSubtraction() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1 }, { 1, 1 }, { 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertNull("Sub: should be null ", matrix1.sub(matrix2));
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
    assertEquals("Multiply: should equal ", expectedDifference, result);

    int[][] list4 = { { 1, 1 }, { 1, 1 }, { 1, 1 } };
    Matrix matrix3 = Matrix.init(list4);
    Matrix result2 = matrix1.multiply(matrix3);
    assertNull("Multiply: should equal ", result2);
  }

  @Test
  public void testUnequalMatrixMultiplication() {
    int[][] list1 = { { 1, 1 }, { 1, 1 } };
    int[][] list2 = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertNull("Multiply: should be null ", matrix1.multiply(matrix2));
  }

  @Test
  public void testDeterminantForMatrixOfSize1() {
    int[][] list = { { 1 } };

    Matrix matrix = Matrix.init(list);
    assertEquals(1, matrix.determinant());
  }

  @Test
  public void testDeterminantForMatrixOfSize2() {
    int[][] list = { { 1, 1 }, { 1, 1 } };
    Matrix matrix = Matrix.init(list);
    assertEquals(0, matrix.determinant());
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
    assertEquals(-240, matrix.determinant());
  }

  @Test
  public void testTranspose() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix = Matrix.init(list1);
    Matrix transposedMatrix = matrix.transpose();

    int[][] list2 = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
    Matrix expected = Matrix.init(list2);
    assertEquals(expected, transposedMatrix);
  }

  @Test
  public void testEqualMatrices() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] list2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertTrue("Equal: should be true ", matrix1.equals(matrix2));
  }

  @Test
  public void testUnEqualMatrices() {
    int[][] list1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    int[][] list2 = { { 3, 3, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix1 = Matrix.init(list1);
    Matrix matrix2 = Matrix.init(list2);

    assertFalse("Equal: should be false ", matrix1.equals(matrix2));
  }
}
