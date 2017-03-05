package fm.pattern.validation;

import java.util.List;

public class AuthorizationException extends ReportableException {

    private static final long serialVersionUID = -7022235229824655548L;

    public AuthorizationException() {

    }

    public AuthorizationException(List<Reportable> errors) {
        super(errors);
    }

    public AuthorizationException(String message, List<Reportable> errors) {
        super(message, errors);
    }

    public AuthorizationException(Reportable error) {
        super(error);
    }

    public AuthorizationException(String message, Reportable error) {
        super(message, error);
    }
    
}