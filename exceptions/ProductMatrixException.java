package exceptions;

public class ProductMatrixException extends MatrixException {
    private int firstLenght;
    private int secondHeight;

    public ProductMatrixException (String errorForm, String message, int fLenght, int sHeight) {
        super(errorForm, message);
        firstLenght = fLenght;
        secondHeight = sHeight;
    }

    public String getErrorParameters() {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("\n Sizes of matrices: \n first columns: ").append(firstLenght).append(" and second rows: ").append(secondHeight);
        return errorMessage.toString();
    }
}