package ValueControl;

import Exceptions.ValueException;
/**
 * functional interface for checking values
 */
@FunctionalInterface
public interface Checks {
    void check(Object value) throws ValueException;
}
