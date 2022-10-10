package printing_shop.domain.exceptions;

public class NoCustomersReturnedException extends Exception{

    public NoCustomersReturnedException(String message){
        super(message);
    }

}