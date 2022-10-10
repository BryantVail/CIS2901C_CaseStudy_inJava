package printing_shop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import printing_shop.utility.MySqlTypeConverter;

public class MySqlTypeConverterTests {

    @Test public void getLocalDateTime_given_mySqlDateTimeString_converts_without_exception(){
        // arrange
        // act
        var localDateTime =
                MySqlTypeConverter.getLocalDateTime(
                        "2022-09-21 21:19:07");

        // assert
        Assertions.assertNotNull(localDateTime);
    }

}
