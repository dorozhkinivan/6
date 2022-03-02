package ValueControl;

import Exceptions.ValueException;

@FunctionalInterface
public interface Checks {
    void check(Object value) throws ValueException;
}
