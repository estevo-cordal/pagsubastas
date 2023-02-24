package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class InvalidDateException extends InstanceException {

    public InvalidDateException(String name, Object key) {
        super(name, key);
    }

}
