package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class InvalidPriceException extends InstanceException {

    public InvalidPriceException(String name, Object key) {
        super(name, key);
    }

}
