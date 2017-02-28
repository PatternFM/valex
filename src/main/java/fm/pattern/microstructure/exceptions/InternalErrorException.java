package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Reportable;

public class InternalErrorException extends ReportableException {

    private static final long serialVersionUID = -7093595345324626648L;

    public InternalErrorException() {

    }

    public InternalErrorException(List<Reportable> errors) {
        super(errors);
    }

    public InternalErrorException(String message, List<Reportable> errors) {
        super(message, errors);
    }

}
