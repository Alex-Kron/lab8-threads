package matrix;

import exceptions.*;

public class UsualMatrix implements IMatrix {

    private int rows;
    private int columns;
    private int[][] mass;

    public UsualMatrix (int r, int c) throws MatrixException{
        if(r <= 0 || c <= 0){
            throw new MatrixException("Parameter error", "Can't create matrix with uncorrect parameters!");
        }
        rows = r;
        columns = c;
        mass = new int[rows][columns];
    }


    public void setElement(int r, int c, int value) throws SetGetException {
        if(r >= this.getRows() || c >= this.getColumns() || r < 0 || c < 0){
            throw new SetGetException("Parameter error", "Can't set element with parametrs (" + r + "; " + c + "). Out of range!");
        }
        mass[r][c] = value;
    }

    public int getElement(int r, int c) throws SetGetException {
        if(r >= this.getRows() || c >= this.getColumns() || r < 0 || c < 0){
            throw new SetGetException("Parameter error", "You can't get this element with parametrs (" + r + "; " + c + "). Out of range!");
        }
        return mass[r][c];
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    @Override
    public UsualMatrix sum (IMatrix mtx) throws SumMatrixException{
        if(this.getRows() != mtx.getRows() || this.getColumns() != mtx.getColumns()){
            throw new SumMatrixException("Size error", "You can't sum matrix with this parameters! ", this.getRows(), this.getColumns(), mtx.getRows(), mtx.getColumns());
        }
        UsualMatrix rezult = new UsualMatrix(this.getRows(), this.getColumns());
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getColumns(); j++){
                int value = this.getElement(i, j) + mtx.getElement(i, j);
                rezult.setElement(i, j, value);
            }
        }
        return rezult;
    }

    @Override
    public UsualMatrix prod(IMatrix mtx) throws ProductMatrixException {
        if(this.getColumns() != mtx.getRows() || this.getRows() != mtx.getColumns()){
            throw new ProductMatrixException("Size error", "You can't product matrix with this parameters!", this.getColumns(), mtx.getRows());
        }
        UsualMatrix rezult = new UsualMatrix(this.getRows(), mtx.getColumns());
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < mtx.getColumns(); j++){
                for(int k = 0; k < mtx.getRows(); k++){
                    rezult.setElement(i, j, rezult.getElement(i, j) + this.getElement(i, k) * mtx.getElement(k, j));
                }
            }
        }
        return rezult;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if (!(obj instanceof IMatrix)) {
            return false;
        }
        UsualMatrix mtx = (UsualMatrix) obj;
        if(this.getColumns() != mtx.getColumns() || this.getRows() != mtx.getRows()){
            return false;
        }
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getColumns(); j++){
                int e1 = this.getElement(i, j);
                int e2 = mtx.getElement(i, j);
                if(e1 != e2){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString(){
        StringBuilder build = new StringBuilder();
        for(int i = 0; i < this.getRows(); i++){
            for(int j =0; j < this.getColumns(); j++){
                build.append(getElement(i, j)).append(" ");
            }
            build.append("\r\n");
        }
        return build.toString();
    }


}
