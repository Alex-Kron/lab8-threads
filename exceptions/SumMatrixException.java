package exceptions;

public class SumMatrixException extends MatrixException {
    private int firstHeight;
    private int firstLenght;
    private int secondHeight;
    private int secondLenght;

    public SumMatrixException (String errorForm, String message, int fHeight, int fLenght, int sHeight, int sLenght) {
        super(errorForm, message);
        firstHeight = fHeight;
        firstLenght = fLenght;
        secondHeight = sHeight;
        secondLenght = sLenght;
    }

    public String getErrorParameters() {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("\n Sizes of matrices: \n rows: ").append(firstHeight).append(" and ").append(secondHeight).append("\n columns: ").append(firstLenght).append(" and ").append(secondLenght);
        return errorMessage.toString();
    }
}