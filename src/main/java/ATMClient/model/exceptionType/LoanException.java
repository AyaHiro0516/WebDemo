package ATMClient.model.exceptionType;

public class LoanException extends ATMException {
    public LoanException() {
        super();
    }

    public LoanException(String message) {
        super(message);
    }

    public LoanException(String message, Throwable cause) {
        super(message, cause);
    }
}
