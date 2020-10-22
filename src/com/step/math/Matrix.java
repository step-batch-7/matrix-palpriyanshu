package com.step.math;

public class Matrix {
  private int[][] matrix;

  private Matrix(int[][] matrix) {
    this.matrix = matrix;
  }

  public static Matrix init(int[][] list) {
    int noOfRows = list.length;
    int noOfCols = list[0].length;
    int[][] matrix = new int[noOfRows][noOfCols];
    for (int rowId = 0; rowId < noOfRows; rowId++) {
      for (int colId = 0; colId < noOfCols; colId++) {
        matrix[rowId][colId] = list[rowId][colId];
      }
    }
    return new Matrix(matrix);
  }

  private boolean isMatrixSizeEqual(Matrix other) {
    return (
      this.matrix.length == other.matrix.length &&
      this.matrix[0].length == other.matrix[0].length
    );
  }

  public Matrix add(Matrix other) {
    if (!isMatrixSizeEqual(other)) {
      return null;
    }
    int noOfRows = other.matrix.length;
    int noOfCols = other.matrix[0].length;
    int[][] sumOfMatrix = new int[noOfRows][noOfCols];
    for (int rowId = 0; rowId < noOfRows; rowId++) {
      for (int colId = 0; colId < noOfCols; colId++) {
        sumOfMatrix[rowId][colId] =
          this.matrix[rowId][colId] + other.matrix[rowId][colId];
      }
    }
    return new Matrix(sumOfMatrix);
  }

  public Matrix sub(Matrix other) {
    if (!isMatrixSizeEqual(other)) {
      return null;
    }
    int noOfRows = other.matrix.length;
    int noOfCols = other.matrix[0].length;
    int[][] sumOfMatrix = new int[noOfRows][noOfCols];
    for (int rowId = 0; rowId < noOfRows; rowId++) {
      for (int colId = 0; colId < noOfCols; colId++) {
        sumOfMatrix[rowId][colId] =
          this.matrix[rowId][colId] - other.matrix[rowId][colId];
      }
    }
    return new Matrix(sumOfMatrix);
  }

  public Matrix multiply(Matrix other) {
    int noOfRows1 = this.matrix.length;
    int noOfCols1 = this.matrix[0].length;
    int noOfCols2 = other.matrix[0].length;
    int noOfRows2 = other.matrix.length;

    if (noOfCols1 != noOfRows2) {
      return null;
    }

    int[][] productOfMatrix = new int[noOfRows1][noOfCols2];

    for (int rowId1 = 0; rowId1 < noOfRows1; rowId1++) {
      for (int colId2 = 0; colId2 < noOfCols2; colId2++) {
        for (
          int rowId2 = 0;
          rowId2 < Math.min(noOfRows2, noOfCols1);
          rowId2++
        ) {
          productOfMatrix[rowId1][colId2] +=
            this.matrix[rowId1][rowId2] * other.matrix[rowId2][colId2];
        }
      }
    }
    return new Matrix(productOfMatrix);
  }

  private Matrix getCoFactors(int factorIdx) {
    int size = this.matrix.length;
    int[][] cofactor = new int[size - 1][size - 1];

    for (int rowId = 1; rowId < size; rowId++) {
      for (int colId = 0; colId < size; colId++) {
        if (colId < factorIdx) {
          cofactor[rowId - 1][colId] = matrix[rowId][colId];
        }
        if (colId > factorIdx) {
          cofactor[rowId - 1][colId - 1] = matrix[rowId][colId];
        }
      }
    }
    return new Matrix(cofactor);
  }

  public int determinant() {
    int size = this.matrix.length;
    if (size == 1) {
      return this.matrix[0][0];
    }

    if (size == 2) {
      return (
        this.matrix[0][0] *
        this.matrix[1][1] -
        this.matrix[0][1] *
        this.matrix[1][0]
      );
    }

    int determinant = 0;
    int sign = 1;
    for (int factorIdx = 0; factorIdx < size; factorIdx++) {
      Matrix coFactors = getCoFactors(factorIdx);
      determinant += sign * this.matrix[0][factorIdx] * coFactors.determinant();
      sign = -sign;
    }
    return determinant;
  }

  public Matrix transpose() {
    int noOfCols = this.matrix.length;
    int noOfRows = this.matrix[0].length;
    int[][] transposedMatrix = new int[noOfRows][noOfCols];
    for (int rowId = 0; rowId < noOfCols; rowId++) {
      for (int colId = 0; colId < noOfRows; colId++) {
        transposedMatrix[rowId][colId] = this.matrix[colId][rowId];
      }
    }
    return new Matrix(transposedMatrix);
  }

  public boolean isElementPresent(int element) {
    for (int rowId = 0; rowId < this.matrix.length; rowId++) {
      int[] row = this.matrix[rowId];
      if (Array.init(row).isPresent(element)) {
        return true;
      }
    }
    return false;
  }

  public boolean isSubArrayPresent(int[] array) {
    for (int rowId = 0; rowId < this.matrix.length; rowId++) {
      int[] subArray = this.matrix[rowId];
      if (Array.init(subArray).deepEqual(Array.init(array))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof Matrix)) {
      return false;
    }

    Matrix otherMatrix = (Matrix) other;

    if (!isMatrixSizeEqual(otherMatrix)) {
      return false;
    }

    for (int rowId = 0; rowId < this.matrix.length; rowId++) {
      Array rowOfMatrixA = Array.init(this.matrix[rowId]);
      Array rowOfMatrixB = Array.init(otherMatrix.matrix[rowId]);
      int[] subArray = this.matrix[rowId];
      if (!(rowOfMatrixA.deepEqual(rowOfMatrixB))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Matrix :\n");
    for (int[] row : this.matrix) {
      for (int value : row) {
        stringBuilder.append(value).append(" ");
      }
      stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }
}
