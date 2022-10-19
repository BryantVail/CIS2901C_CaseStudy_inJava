package printing_shop.domain.exceptions;

public class AttemptingChangeOfDeletedStatusToCurrentStatusException extends RuntimeException{
	public String getGenericMessage() {
		return "entity already has the active state you're attempting to change it to, no update to record";
	}
	
}
