package printing_shop.domain.exceptions;

public class DatabaseInternalException extends RuntimeException {

    public DatabaseInternalException(String message){
        super(message);
    }
    
    public String getGenericMessage(){
        return "internal database exception, if this continues with subsequent attempts please get with your application administrator";
    }

}
