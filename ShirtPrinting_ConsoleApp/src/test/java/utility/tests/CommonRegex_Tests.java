package utility.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import printing_shop.utility.CommonRegex;

public class CommonRegex_Tests {
	
	@Test
	public void isValidEmailAddress_given_a_non_null_string_when_address_has_special_chars_before_atSign_of_only_plus_underscore_period_or_hyphen_then_returns_true(){
		// arrange
		String[] validEmailAddresses = {
			"bryantvail@gmail.com",
			"bryant+vail@outlook.com",
			"-Bryant+vail@co.in",
			"-bryant+vail.@co.in.in"
		};
		// act
		// assert
		for(String emailAddress : validEmailAddresses){
			Assertions.assertTrue(CommonRegex.isValidEmailAddress(emailAddress) );
		}
	}
	
	@Test
	public void isValidEmailAddress_given_a_non_null_string_when_address_has_apostrophe_pipe_curly_or_square_brackets_then_returns_false(){
		// arrange
		String[] validEmailAddresses = {
			"'bryantvail@gmail.com",
			"|bryant+vail@outlook.com",
			"{-Bryant+vail@co.in",
			"}-bryant+vail.@co.in.in",
			"[bryant+vail@co.in.in",
			"]bryantvail@co.in"
		};
		// act
		// assert
		for(String emailAddress : validEmailAddresses){
			Assertions.assertFalse(CommonRegex.isValidEmailAddress(emailAddress) );
		}
	}
	
	@Test
	public void isValidEmailAddress_given_a_string_containing_the_aroba_character_when_substring_after_aroba_does_not_match_a_domain_pattern_of_string_period_followed_by_a_topLevelDomain_w_at_least_two_characters_then_returns_false(){
			String[] invalidEmailAddresses = {
				"char@char",
				"a@b",
				"a@b.",
				"a@.b",
				"a@a.b"
			};
			// act
			// assert
			for(String emailAddress : invalidEmailAddresses){
				Assertions.assertFalse(CommonRegex.isValidEmailAddress(emailAddress) );
		}
	}
}
