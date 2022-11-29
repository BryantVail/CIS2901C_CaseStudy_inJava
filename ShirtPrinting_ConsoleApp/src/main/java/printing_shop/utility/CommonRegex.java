package printing_shop.utility;

import java.util.regex.Pattern;

public class CommonRegex {
	public static boolean isValidEmailAddress(String emailAddress){

		String emailAddressRegex = "^[A-Za-z0-9+_.-]+@(.+)\\.(.{2,})$";
		if (emailAddress.matches(emailAddressRegex) == true) {
			return true;
		}else{
			return false;
		}
	}
}
