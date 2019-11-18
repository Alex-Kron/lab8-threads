package matrix;

public interface IMatrix {
    public void setElement(int i, int j, int value);
    public int getElement(int i, int j);

    public int getColumns();
    public int getRows();

    public IMatrix sum(IMatrix o);
    public IMatrix prod(IMatrix o);
}