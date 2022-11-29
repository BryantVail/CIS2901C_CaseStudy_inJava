package printing_shop.domain.exceptions;

public class RecordDoesNotExistException extends RuntimeException{


    public RecordDoesNotExistException(String identifier){
        super("" +
            "identifier: \"" +
            identifier +
            "\" has not resolved to a record");
        this.Identifier = identifier;
    }

    public String Identifier;


}
