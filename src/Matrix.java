import java.util.Arrays;

public class Matrix<T extends Number> {
    private final T[][] data;
    private final int rows;
    private final int cols;

    public Matrix(T[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Matrix<T> add(Matrix<T> other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковый размер для сложения");
        }

        T[][] result = (T[][]) new Number[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[i][j] = (T) Double.valueOf(this.data[i][j].doubleValue() + other.data[i][j].doubleValue());
            }
        }

        return new Matrix<>(result);
    }

    public Matrix<T> subtract(Matrix<T> other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковый размер для вычитания");
        }

        T[][] result = (T[][]) new Number[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[i][j] = (T) Double.valueOf(this.data[i][j].doubleValue() - other.data[i][j].doubleValue());
            }
        }

        return new Matrix<>(result);
    }

    public Matrix<T> multiply(Matrix<T> other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Количество столбцов матрицы А должно быть равно количеству строк матрицы В для умножения");
        }

        T[][] result = (T[][]) new Number[this.rows][other.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                result[i][j] = (T) Double.valueOf(0);
                for (int k = 0; k < this.cols; k++) {
                    result[i][j] = (T) Double.valueOf(result[i][j].doubleValue() + this.data[i][k].doubleValue() * other.data[k][j].doubleValue());
                }
            }
        }

        return new Matrix<>(result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T[] row : data) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[][] data1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Integer[][] data2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        Matrix<Integer> matrix1 = new Matrix<>(data1);
        Matrix<Integer> matrix2 = new Matrix<>(data2);

        System.out.println("Matrix 1:");
        System.out.println(matrix1);

        System.out.println("Matrix 2:");
        System.out.println(matrix2);

        Matrix<Integer> sum = matrix1.add(matrix2);
        System.out.println("Сумма:");
        System.out.println(sum);

        Matrix<Integer> difference = matrix1.subtract(matrix2);
        System.out.println("Разность:");
        System.out.println(difference);

        Matrix<Integer> product = matrix1.multiply(matrix2);
        System.out.println("Умножение:");
        System.out.println(product);
    }
}

