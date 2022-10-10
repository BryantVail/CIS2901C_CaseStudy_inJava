package printing_shop.domain.exceptions;

public class RecordDoesNotExistException extends Exception{


    public RecordDoesNotExistException(String identifier){
        super("" +
            "identifier: " +
            identifier +
            "was not found to be associated with a record");
        this.Identifier = identifier;
    }

    public String Identifier;


}
