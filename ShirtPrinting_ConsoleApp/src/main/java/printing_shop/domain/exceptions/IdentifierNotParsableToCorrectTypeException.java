package printing_shop.domain.exceptions;

public class IdentifierNotParsableToCorrectTypeException extends Exception {


    public IdentifierNotParsableToCorrectTypeException(
            String id){
        super(
            "Identifier was not parsable to correct type, " +
            "string version of identifier passed is: " +
            "\""+id+"\"");
        this.Id = id;
    }

    String Id;

}
