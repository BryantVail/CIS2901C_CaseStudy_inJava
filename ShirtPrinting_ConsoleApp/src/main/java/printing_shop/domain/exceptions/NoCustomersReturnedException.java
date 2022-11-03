package printing_shop.domain.exceptions;

public class NoCustomersReturnedException extends RuntimeException{

    public NoCustomersReturnedException(String message){
        super(message);
    }

}