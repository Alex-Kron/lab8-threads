package exceptions;

public class MatrixException extends RuntimeException {
    private String errorForm;

    public MatrixException(String errorForm, String message) {
        super(message);
        this.errorForm = errorForm;
    }

    public String getErrorForm() {
        return errorForm;
    }
}