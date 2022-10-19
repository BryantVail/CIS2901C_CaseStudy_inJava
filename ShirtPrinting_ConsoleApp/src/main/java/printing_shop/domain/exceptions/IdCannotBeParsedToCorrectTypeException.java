package printing_shop.domain.exceptions;

public class IdCannotBeParsedToCorrectTypeException extends RuntimeException {


    public IdCannotBeParsedToCorrectTypeException(
        String message,
        String id ){

        super(message+"\n\nid: "+id+" could not be parsed appropriately");
        this.Id = id;
    }

    public String Id;
}
