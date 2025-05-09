package Serialization;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class SingletonReadResolve  implements Serializable {

    private final String message;

    private static final SingletonReadResolve INSTANCE = new SingletonReadResolve(LocalDateTime.now().toString());

    private SingletonReadResolve(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static SingletonReadResolve getINSTANCE() {
        return INSTANCE;
    }

    @Serial
    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }
}
