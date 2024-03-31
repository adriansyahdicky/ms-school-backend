package com.ms.usermanagement.config.response_messages.localization_messages;

import com.ms.usermanagement.base.constant.BaseEnumMessageKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum EnumMessagesKey implements BaseEnumMessageKey {
    // 200
    SUCCESS("COMP-D-2001", HttpStatus.OK, "success.default"),

    //400
    ERROR_DEFAULT("COMP-D-4001", HttpStatus.BAD_REQUEST, "error.default"),
    ERROR_BAD_REQUEST("COMP-D-4002", HttpStatus.BAD_REQUEST, "error.badRequest"),
    ERROR_UNAUTHORIZED("COMP-D-4003", HttpStatus.UNAUTHORIZED,"error.unauthorized"),
    ERROR_FORBIDDEN("COMP-D-4004", HttpStatus.FORBIDDEN,"error.forbidden"),
    ERROR_NOT_FOUND("COMP-D-4005", HttpStatus.NOT_FOUND,"error.notFound"),
    ERROR_UNSUPPORTED_MEDIA_TYPE("COMP-D-4006", HttpStatus.UNSUPPORTED_MEDIA_TYPE,"error.unsupportedMediaType"),
    ERROR_FILE_SIZE_LIMIT_EXCEEDED("COMP-D-4007", HttpStatus.PAYLOAD_TOO_LARGE, "error.overPayload"),
    ERROR_UNPROCESSABLE_ENTITY("COMP-D-4008", HttpStatus.UNPROCESSABLE_ENTITY, "error.unprocessableEntity"),
    ERROR_ALREADY_EXISTS("COMP-D-4009", HttpStatus.BAD_REQUEST, "error.already.exists"),
    ERROR_BUSINESS("COMP-D-4010", HttpStatus.BAD_REQUEST, "error.business"),
    ERROR_NOT_FOUND_DATA("COMP-D-4011", HttpStatus.NOT_FOUND,"error.notFound.data"),
    // 500
    ERROR_INTERNAL_SERVER("COMP-D-5001", HttpStatus.INTERNAL_SERVER_ERROR, "error.internalServer"),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String messageKey;
}
