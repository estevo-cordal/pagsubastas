package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class ExpiredProductException extends InstanceException {

    public ExpiredProductException(String name, Object key) {
        super(name, key);
    }

}
