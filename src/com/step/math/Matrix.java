package com.step.math;

public class Matrix {
  private int[][] matrix;
  private int noOfRows;
  private int noOfCols;

  public Matrix(int noOfRows, int noOfCols) {
    this.matrix = new int[noOfRows][noOfCols];
    this.noOfRows = noOfRows;
    this.noOfCols = noOfCols;
  }

  public static Matrix init(int[][] list) {
    int noOfRows = list.length;
    int noOfCols = list[0].length;

    Matrix matrix = new Matrix(noOfRows, noOfCols);

    for (int rowId = 0; rowId < noOfRows; rowId++) {
      if (noOfCols != list[rowId].length) {
        return null;
      }

      for (int colId = 0; colId < noOfCols; colId++) {
        matrix.matrix[rowId][colId] = list[rowId][colId];
      }
    }
    return matrix;
  }

  public Matrix add(Matrix other) {
    if (!(this.areMatricesOrderSame(other))) {
      return null;
    }

    Matrix sumOfMatrices = new Matrix(this.noOfRows, this.noOfCols);
    int startingIndex = 0;

    for (int rowId = startingIndex; rowId < this.noOfRows; rowId++) {
      for (int colId = startingIndex; colId < this.noOfCols; colId++) {
        sumOfMatrices.matrix[rowId][colId] =
          this.matrix[rowId][colId] + other.matrix[rowId][colId];
      }
    }
    return sumOfMatrices;
  }

  public Matrix sub(Matrix other) {
    if (!(this.areMatricesOrderSame(other))) {
      return null;
    }

    Matrix differenceOfMatrices = new Matrix(this.noOfRows, this.noOfCols);
    int startingIndex = 0;

    for (int rowId = startingIndex; rowId < this.noOfRows; rowId++) {
      for (int colId = startingIndex; colId < this.noOfCols; colId++) {
        differenceOfMatrices.matrix[rowId][colId] =
          this.matrix[rowId][colId] - other.matrix[rowId][colId];
      }
    }
    return differenceOfMatrices;
  }

  public Matrix multiply(Matrix other) {
    if (this.noOfCols != other.noOfRows) {
      return null;
    }

    Matrix productOfMatrices = new Matrix(this.noOfRows, this.noOfCols);
    int startingIndex = 0;

    for (int rowId1 = startingIndex; rowId1 < this.noOfRows; rowId1++) {
      for (int colId2 = startingIndex; colId2 < other.noOfCols; colId2++) {
        for (int rowId2 = startingIndex; rowId2 < other.noOfCols; rowId2++) {
          productOfMatrices.matrix[rowId1][colId2] +=
            this.matrix[rowId1][rowId2] * other.matrix[rowId2][colId2];
        }
      }
    }
    return productOfMatrices;
  }

  private Matrix getCoFactors(int factorIdx) {
    int unitValue = 1;
    Matrix coFactors = new Matrix(
      this.noOfRows - unitValue,
      this.noOfCols - unitValue
    );

    for (int rowId = unitValue; rowId < this.noOfRows; rowId++) {
      for (int colId = 0; colId < this.noOfRows; colId++) {
        int lastRowId = rowId - unitValue;
        if (colId != factorIdx) {
          int lastColId = colId < factorIdx ? colId : colId - unitValue;
          coFactors.matrix[lastRowId][lastColId] = this.matrix[rowId][colId];
        }
      }
    }

    return coFactors;
  }

  public int determinant() {
    if (this.noOfCols == 1) {
      return this.matrix[0][0];
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
    Matrix transposedMatrix = new Matrix(this.noOfRows, this.noOfCols);
    int startingIndex = 0;

    for (int rowId = startingIndex; rowId < this.noOfCols; rowId++) {
      for (int colId = startingIndex; colId < this.noOfRows; colId++) {
        transposedMatrix.matrix[rowId][colId] = this.matrix[colId][rowId];
      }
    }
    return transposedMatrix;
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

  private boolean areMatricesOrderSame(Matrix other) {
    return (this.noOfCols == other.noOfCols && this.noOfRows == other.noOfRows);
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

    if (!(this.areMatricesOrderSame(otherMatrix))) {
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
