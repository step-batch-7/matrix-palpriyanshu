package com.step.math;

public class Matrix {
  private int[][] values;
  private int noOfRows;
  private int noOfCols;

  public Matrix(int noOfRows, int noOfCols) {
    this.values = new int[noOfRows][noOfCols];
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
        matrix.values[rowId][colId] = list[rowId][colId];
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
        sumOfMatrices.values[rowId][colId] =
          this.values[rowId][colId] + other.values[rowId][colId];
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
        differenceOfMatrices.values[rowId][colId] =
          this.values[rowId][colId] - other.values[rowId][colId];
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
          productOfMatrices.values[rowId1][colId2] +=
            this.values[rowId1][rowId2] * other.values[rowId2][colId2];
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
          coFactors.values[lastRowId][lastColId] = this.values[rowId][colId];
        }
      }
    }

    return coFactors;
  }

  public int determinant() {
    if (this.noOfCols == 1) {
      return this.values[0][0];
    }

    int determinant = 0;
    int sign = 1;
    for (int factorIdx = 0; factorIdx < this.noOfCols; factorIdx++) {
      Matrix coFactors = this.getCoFactors(factorIdx);
      determinant += sign * this.values[0][factorIdx] * coFactors.determinant();
      sign = -sign;
    }
    return determinant;
  }

  public Matrix transpose() {
    Matrix transposedMatrix = new Matrix(this.noOfRows, this.noOfCols);
    int startingIndex = 0;

    for (int rowId = startingIndex; rowId < this.noOfCols; rowId++) {
      for (int colId = startingIndex; colId < this.noOfRows; colId++) {
        transposedMatrix.values[rowId][colId] = this.values[colId][rowId];
      }
    }
    return transposedMatrix;
  }

  public boolean hasElement(int element) {
    for (int rowId = 0; rowId < this.values.length; rowId++) {
      Array rowOfMatrix = Array.init(this.values[rowId]);

      if (rowOfMatrix.has(element)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasRow(int[] array) {
    for (int rowId = 0; rowId < this.noOfRows; rowId++) {
      if (this.areRowsSame(array, this.values[rowId])) {
        return true;
      }
    }
    return false;
  }

  private boolean areMatricesOrderSame(Matrix other) {
    return (this.noOfCols == other.noOfCols && this.noOfRows == other.noOfRows);
  }

  private boolean areRowsSame(int[] row1, int[] row2) {
    if (row1.length != row2.length) {
      return false;
    }

    for (int index = 0; index < row1.length; index++) {
      if (row1[index] != row2[index]) {
        return false;
      }
    }
    return true;
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

    for (int rowId = 0; rowId < this.noOfRows; rowId++) {
      if (!(areRowsSame(this.values[rowId], otherMatrix.values[rowId]))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Matrix :\n");
    for (int[] row : this.values) {
      for (int value : row) {
        stringBuilder.append(value).append(" ");
      }
      stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }
}
