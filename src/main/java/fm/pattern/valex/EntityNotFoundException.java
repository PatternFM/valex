package fm.pattern.valex;

import java.util.List;

public class EntityNotFoundException extends ReportableException {

    private static final long serialVersionUID = -7099875229824655548L;

    public EntityNotFoundException(List<Reportable> errors) {
        super(errors);
    }

    public EntityNotFoundException(Reportable error) {
        super(error);
    }

}
