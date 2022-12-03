package printing_shop.domain.exceptions;

public class ExceptionMessages {
	public static String fieldCannotBeNullMessage(String fieldName){
		return "\""+fieldName +"\""+" cannot be empty/null";
	}
	
	public static String iterableFieldCannotBeNullMessage(String fieldName){
		return "none of the"+"\""+fieldName +"\""+" in the collection can be empty/null";
	}
}
