package main;

import matrix.ParallelMatrixProduct;
import matrix.UsualMatrix;
import java.util.Random;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(ParallelMatrixProduct.class.getName());
        try {
            int count = 1000;
            UsualMatrix mtx1 = new UsualMatrix(3, 4);
            UsualMatrix mtx2 = new UsualMatrix(4, 3);
            Random rand = new Random();
            for (int i = 0; i < mtx1.getRows(); i++) {
                for (int j = 0; j < mtx2.getColumns(); j++) {
                    mtx1.setElement(i, j, rand.nextInt(10));
                    mtx2.setElement(i, j, rand.nextInt(10));
                }
            }
            ParallelMatrixProduct prod = new ParallelMatrixProduct(4);
            UsualMatrix result1 = mtx1.prod(mtx2);
            UsualMatrix result2 = prod.product(mtx1, mtx2);
            String m1 = mtx1.toString();
            String m2 = mtx2.toString();
            String r1 = result1.toString();
            String r2 = result2.toString();
            log.info(m1);
            log.info(m2);
            log.info(r1);
            log.info(r2);
            UsualMatrix mtx3 = new UsualMatrix(count, count);
            UsualMatrix mtx4 = new UsualMatrix(count, count);
            for (int i = 0; i < mtx3.getRows(); i++) {
                for (int j = 0; j < mtx4.getColumns(); j++) {
                    mtx3.setElement(i, j, rand.nextInt(10));
                    mtx4.setElement(i, j, rand.nextInt(10));
                }
            }
            for (int i = 1; i <= 10; i++) {
                ParallelMatrixProduct tmp = new ParallelMatrixProduct(i);
                long begin = System.nanoTime();
                UsualMatrix result3 = tmp.product(mtx3, mtx4);
                String res = result3.toString();
                log.info(res);
                long end = System.nanoTime();
                String result = "Time on PARALLEL " + i +
                        " threads" +
                        ((float) (end - begin) / 1000000000) +
                        "\n";
                log.info(result);
            }
        } catch (InterruptedException e) {
            log.info("Threads interrupted");
        }
    }
}
