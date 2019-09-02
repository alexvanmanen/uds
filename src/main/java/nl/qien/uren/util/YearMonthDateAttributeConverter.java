package nl.qien.uren.util;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;

public class YearMonthDateAttributeConverter
        implements AttributeConverter<YearMonth, Date> {

    @Override
    public java.sql.Date convertToDatabaseColumn(
            YearMonth attribute) {
        return java.sql.Date.valueOf(attribute.atDay(1));
    }

    @Override
    public YearMonth convertToEntityAttribute(
            java.sql.Date dbData) {
        return YearMonth
                .from(Instant.ofEpochMilli(dbData.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());
    }
}
