package printing_shop.utility;

import java.time.LocalDateTime;

public class MySqlTypeConverter implements IDatabaseTypeConverter {

    public static LocalDateTime getLocalDateTime(String databaseStringValue) {


        String[] localDateTimeStringParts = databaseStringValue.split(" ");
        String javaLocalDateTimeString =
                localDateTimeStringParts[0] +
                "T" +
                localDateTimeStringParts[1];

        return LocalDateTime.parse(javaLocalDateTimeString);
    }
}
