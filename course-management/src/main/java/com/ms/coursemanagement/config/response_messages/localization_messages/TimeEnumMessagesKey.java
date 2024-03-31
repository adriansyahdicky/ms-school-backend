package com.ms.coursemanagement.config.response_messages.localization_messages;

import com.ms.coursemanagement.base.constant.BaseEnumMessageKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TimeEnumMessagesKey implements BaseEnumMessageKey {
    // general
    ERROR_NOT_FOUND("TM-G-0000", HttpStatus.NOT_FOUND, "error.tm.not.found"),
    ERROR_ALREADY_EXISTS("TM-G-0001", HttpStatus.BAD_REQUEST, "error.tm.already.exists"),
    ERROR_LIST_CANNOT_BE_EMPTY("TM-G-0002", HttpStatus.BAD_REQUEST, "error.tm.list.cannot.be.empty"),
    ERROR_LIST_EMPTY_MIDDLE("TM-G-0003", HttpStatus.BAD_REQUEST, "error.tm.list.empty.middle"),

    // period
    ERROR_PERIOD_RANGE_DATE("TM-P-1000", HttpStatus.BAD_REQUEST, "error.tm.period.date.range"),
    ERROR_PERIOD_START_DATE_GT("TM-P-1001", HttpStatus.BAD_REQUEST, "error.tm.period.start_date.gt"),
    ERROR_PERIOD_TIME_GAP("TM-P-1002", HttpStatus.BAD_REQUEST, "error.tm.period.time.gap"),
    ERROR_PERIOD_EDIT_LATEST("TM-P-1003", HttpStatus.BAD_REQUEST, "error.tm.period.edit.latest"),
    ERROR_PERIOD_DELETE_LATEST("TM-P-1004", HttpStatus.BAD_REQUEST, "error.tm.period.delete.latest"),
    ERROR_PERIOD_NOT_FOUND("TM-P-1005", HttpStatus.NOT_FOUND, "error.tm.period.not.found"),
    ERROR_PERIOD_DURATION_RANGE("TM-P-1006", HttpStatus.BAD_REQUEST, "error.tm.period.duration"),
    ERROR_PERIOD_EARLIER_VALID_FROM("TM-P-1007", HttpStatus.BAD_REQUEST, "error.tm.period.earlier.valid_from"),
    ERROR_PERIOD_UPDATE_ACTIVATED("TM-P-1008", HttpStatus.BAD_REQUEST, "error.tm.period.cannot.edit.activated"),
    ERROR_PERIOD_DELETE_ACTIVATED("TM-P-1009", HttpStatus.BAD_REQUEST, "error.tm.period.cannot.delete.activated"),
    ERROR_PERIOD_PATTERN_FIRST_ROW_EMPTY("TM-P-1010", HttpStatus.BAD_REQUEST, "error.tm.period.pattern.first.row.empty"),
    ERROR_PERIOD_NORMAL_WORKING_TIME("TM-P-1011", HttpStatus.BAD_REQUEST, "error.tm.period.normal.working.time"),
    ERROR_PERIOD_CORE_WORKING_TIME("TM-P-1012", HttpStatus.BAD_REQUEST, "error.tm.period.core.working.time"),
    ERROR_ENOUGH_MONEY("TM-P-1013", HttpStatus.BAD_REQUEST, "error.tm.activated.fees"),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String messageKey;
}
