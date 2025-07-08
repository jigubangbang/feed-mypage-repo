package com.jigubangbang.mypage_service.util;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Locale;

public class DateUtils {

    private static final DateTimeFormatter KOREAN_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.KOREAN);

    // For java.sql.Timestamp input
    public static String formatToKoreanDate(Timestamp timestamp) {
        if (timestamp == null) return null;
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        return dateTime.format(KOREAN_FORMATTER);
    }
}
