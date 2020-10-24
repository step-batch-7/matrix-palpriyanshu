package com.step.math;

public class Matrix {
  private int[][] matrix;
  private int noOfRows;
  private int noOfCols;

  public Matrix(int[][] matrix) {
    this.matrix = matrix;
    this.noOfRows = matrix.length;
    this.noOfCols = matrix[0].length;
  }

  public static Matrix init(int[][] list) {
    int noOfRows = list.length;
    int noOfCols = list[0].length;

    int[][] matrix = new int[noOfRows][noOfCols];

    for (int rowId = 0; rowId < noOfRows; rowId++) {
      if (noOfCols != list[rowId].length) {
        return null;
      }

      for (int colId = 0; colId < noOfCols; colId++) {
        matrix[rowId][colId] = list[rowId][colId];
      }
    }
    return new Matrix(matrix);
  }

  public Matrix add(Matrix other) {
    if (!(this.isMatricesOrderSame(other))) {
      return null;
    }

    int[][] sumOfMatrix = new int[this.noOfRows][this.noOfCols];
    int startingIndex = 0;

    for (int rowId = startingIndex; rowId < this.noOfRows; rowId++) {
      for (int colId = startingIndex; colId < this.noOfCols; colId++) {
        sumOfMatrix[rowId][colId] =
          this.matrix[rowId][colId] + other.matrix[rowId][colId];
      }
    }
    return new Matrix(sumOfMatrix);
  }

  public Matrix sub(Matrix other) {
    if (!(this.isMatricesOrderSame(other))) {
      return null;
    }

    int[][] sumOfMatrix = new int[this.noOfRows][this.noOfCols];
    int startingIndex = 0;

    for (int rowId = startingIndex; rowId < this.noOfRows; rowId++) {
      for (int colId = startingIndex; colId < this.noOfCols; colId++) {
        sumOfMatrix[rowId][colId] =
          this.matrix[rowId][colId] - other.matrix[rowId][colId];
      }
    }
    return new Matrix(sumOfMatrix);
  }

  public Matrix multiply(Matrix other) {
    if (this.noOfCols != other.noOfRows) {
      return null;
    }

    int[][] productOfMatrix = new int[this.noOfRows][other.noOfCols];
    int startingIndex = 0;

    for (int rowId1 = startingIndex; rowId1 < this.noOfRows; rowId1++) {
      for (int colId2 = startingIndex; colId2 < other.noOfCols; colId2++) {
        for (int rowId2 = startingIndex; rowId2 < other.noOfCols; rowId2++) {
          productOfMatrix[rowId1][colId2] +=
            this.matrix[rowId1][rowId2] * other.matrix[rowId2][colId2];
        }
      }
    }
    return new Matrix(productOfMatrix);
  }

  private Matrix getCoFactors(int factorIdx) {
    int unitValue = 1;
    int[][] coFactors = new int[this.noOfRows - unitValue][this.noOfCols -
    unitValue];

    for (int rowId = unitValue; rowId < this.noOfRows; rowId++) {
      for (int colId = 0; colId < this.noOfRows; colId++) {
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
    if (this.isFirstOrderMatrix()) {
      return this.firstOrderMatrixDeterminant();
    }

    if (this.isSecondOrderMatrix()) {
      return this.secondOrderMatrixDeterminant();
    }

    int determinant = 0;
    int sign = 1;
    for (int factorIdx = 0; factorIdx < this.noOfCols; factorIdx++) {
      Matrix coFactors = this.getCoFactors(factorIdx);
      determinant += sign * this.matrix[0][factorIdx] * coFactors.determinant();
      sign = -sign;
    }
    return determinant;
  }

  public Matrix transpose() {
    int[][] transposedMatrix = new int[this.noOfRows][this.noOfCols];
    int startingIndex = 0;

    for (int rowId = startingIndex; rowId < this.noOfCols; rowId++) {
      for (int colId = startingIndex; colId < this.noOfRows; colId++) {
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

  public boolean hasRow(int[] array) {
    Array subArray = Array.init(array);

    for (int rowId = 0; rowId < this.matrix.length; rowId++) {
      Array rowOfMatrix = Array.init(this.matrix[rowId]);

      if (rowOfMatrix.deepEqual(subArray)) {
        return true;
      }
    }
    return false;
  }

  private boolean isMatricesOrderSame(Matrix other) {
    return (this.noOfCols == other.noOfCols && this.noOfRows == other.noOfRows);
  }

  private boolean isFirstOrderMatrix() {
    return this.noOfCols == 1;
  }

  private int firstOrderMatrixDeterminant() {
    return this.matrix[0][0];
  }

  private boolean isSecondOrderMatrix() {
    return this.noOfCols == 2;
  }

  private int secondOrderMatrixDeterminant() {
    return (
      this.matrix[0][0] *
      this.matrix[1][1] -
      this.matrix[0][1] *
      this.matrix[1][0]
    );
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

    if (!(this.isMatricesOrderSame(otherMatrix))) {
      return false;
    }

    for (int rowId = 0; rowId < this.matrix.length; rowId++) {
      Array rowOfMatrixA = Array.init(this.matrix[rowId]);
      Array rowOfMatrixB = Array.init(otherMatrix.matrix[rowId]);

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
