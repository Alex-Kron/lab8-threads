package matrix;

import java.util.concurrent.ArrayBlockingQueue;

public class ParallelMatrixProduct {
    private Thread[] threads;
    private ArrayBlockingQueue<Pair> tasks;
    private UsualMatrix result;
    private UsualMatrix matrix1;
    private UsualMatrix matrix2;

    public ParallelMatrixProduct(int count) {
        threads = new Thread[count];
        tasks = new ArrayBlockingQueue(1000000);
        for (int i = 0; i < count; i++)
            threads[i] = new Thread(new Runner(i));
    }

    public UsualMatrix product(UsualMatrix mtx1, UsualMatrix mtx2) throws InterruptedException{
        matrix1 = mtx1;
        matrix2 = mtx2;
        result = new UsualMatrix(matrix1.getRows(), matrix2.getColumns());
            for (int j = 0; j < matrix1.getRows(); j++)
                for (int i = 0; i < matrix2.getColumns(); i++) {
                    tasks.add(new Pair(i, j));
                }
        for (Thread thread : threads) {
            thread.start();
        }
            threads[threads.length - 1].join();
        return result;
    }

    class Runner implements Runnable {
        int threadNumber;

        Runner(int n) {
            threadNumber = n;
        }

        @Override
        public void run() {
            Pair index;
            int row;
            int column;

            while ((index = tasks.poll()) != null) {
                row = index.getR();
                column = index.getC();

                for (int k = 0; k < matrix2.getRows(); k++) {
                    result.setElement(row, column, result.getElement(row, column) + matrix1.getElement(row, k) * matrix2.getElement(k, column));
                }
            }
        }
    }
}
