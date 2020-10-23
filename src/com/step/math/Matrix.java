package com.step.math;

public class Matrix {
  private int[][] matrix;

  public Matrix(int[][] matrix) {
    this.matrix = matrix;
  }

  private static int getFirstRowSize(int[][] list) {
    return list[0].length;
  }

  private static boolean sizeOfAllRowsEqual(int[][] list) {
    int sizeOfFirstRow = getFirstRowSize(list);
    for (int rowId = 1; rowId < list.length; rowId++) {
      if (sizeOfFirstRow != list[rowId].length) {
        return false;
      }
    }
    return true;
  }

  public static Matrix init(int[][] list) {
    if (!(sizeOfAllRowsEqual(list))) {
      return null;
    }
    int noOfRows = list.length;
    int noOfCols = getFirstRowSize(list);
    int startingIndex = 0;

    int[][] matrix = new int[noOfRows][noOfCols];

    for (int rowId = startingIndex; rowId < noOfRows; rowId++) {
      for (int colId = startingIndex; colId < noOfCols; colId++) {
        matrix[rowId][colId] = list[rowId][colId];
      }
    }
    return new Matrix(matrix);
  }

  private boolean isMatrixSizeEqual(Matrix other) {
    return (
      this.matrix.length == other.matrix.length &&
      getFirstRowSize(this.matrix) == getFirstRowSize(other.matrix)
    );
  }

  public Matrix add(Matrix other) {
    if (!isMatrixSizeEqual(other)) {
      return null;
    }

    int noOfRows = other.matrix.length;
    int noOfCols = getFirstRowSize(other.matrix);
    int[][] sumOfMatrix = new int[noOfRows][noOfCols];
    int startingIndex = 0;

    for (int rowId = startingIndex; rowId < noOfRows; rowId++) {
      for (int colId = startingIndex; colId < noOfCols; colId++) {
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
    int noOfCols = getFirstRowSize(other.matrix);
    int[][] sumOfMatrix = new int[noOfRows][noOfCols];
    int startingIndex = 0;

    for (int rowId = startingIndex; rowId < noOfRows; rowId++) {
      for (int colId = startingIndex; colId < noOfCols; colId++) {
        sumOfMatrix[rowId][colId] =
          this.matrix[rowId][colId] - other.matrix[rowId][colId];
      }
    }
    return new Matrix(sumOfMatrix);
  }

  public Matrix multiply(Matrix other) {
    int noOfRows1 = this.matrix.length;
    int noOfCols1 = getFirstRowSize(this.matrix);
    int noOfRows2 = other.matrix.length;
    int noOfCols2 = getFirstRowSize(other.matrix);

    if (noOfCols1 != noOfRows2) {
      return null;
    }

    int[][] productOfMatrix = new int[noOfRows1][noOfCols2];
    int startingIndex = 0;

    for (int rowId1 = startingIndex; rowId1 < noOfRows1; rowId1++) {
      for (int colId2 = startingIndex; colId2 < noOfCols2; colId2++) {
        for (int rowId2 = startingIndex; rowId2 < noOfRows2; rowId2++) {
          productOfMatrix[rowId1][colId2] +=
            this.matrix[rowId1][rowId2] * other.matrix[rowId2][colId2];
        }
      }
    }
    return new Matrix(productOfMatrix);
  }

  private Matrix getCoFactors(int factorIdx) {
    int size = this.matrix.length;
    int unitValue = 1;
    int[][] coFactors = new int[size - unitValue][size - unitValue];

    for (int rowId = unitValue; rowId < size; rowId++) {
      for (int colId = 0; colId < size; colId++) {
        int lastRowId = rowId - unitValue;
        if (colId != factorIdx) {
          int lastColId = colId < factorIdx ? colId : colId - unitValue;
          coFactors[lastRowId][lastColId] = matrix[rowId][colId];
        }
      }
    }

    return new Matrix(coFactors);
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
    int noOfRows = getFirstRowSize(this.matrix);
    int[][] transposedMatrix = new int[noOfRows][noOfCols];
    int startingIndex = 0;

    for (int rowId = startingIndex; rowId < noOfCols; rowId++) {
      for (int colId = startingIndex; colId < noOfRows; colId++) {
        transposedMatrix[rowId][colId] = this.matrix[colId][rowId];
      }
    }
    return new Matrix(transposedMatrix);
  }

  public boolean hasElement(int element) {
    for (int rowId = 0; rowId < this.matrix.length; rowId++) {
      Array rowOfMatrix = Array.init(this.matrix[rowId]);

      if (rowOfMatrix.has(element)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasSubArray(int[] array) {
    Array subArray = Array.init(array);

    for (int rowId = 0; rowId < this.matrix.length; rowId++) {
      Array rowOfMatrix = Array.init(this.matrix[rowId]);

      if (rowOfMatrix.deepEqual(subArray)) {
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
