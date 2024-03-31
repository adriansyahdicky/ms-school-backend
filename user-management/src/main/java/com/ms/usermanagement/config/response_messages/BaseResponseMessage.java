package com.ms.usermanagement.config.response_messages;

import com.ms.usermanagement.base.constant.BaseEnumMessageKey;
import com.ms.usermanagement.config.response_messages.localization_messages.EnumMessagesKey;
import com.ms.usermanagement.config.response_messages.localization_messages.LocalizedMessages;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseMessage {

    private ErrorSchema errorSchema = new ErrorSchema();

    @Builder(builderMethodName = "baseResponseBuilder")
    public BaseResponseMessage(String errorCode, ErrorMessage errorMessage) {
        this.errorSchema.errorCode = errorCode;
        this.errorSchema.errorMessage = errorMessage;
    }

    public void buildSuccessResponse() {
        errorSchema = ErrorSchema.builder()
            .errorCode(String.valueOf(HttpStatus.OK.value()))
            .errorMessage(new ErrorMessage(EnumMessagesKey.SUCCESS.getMessageKey()))
            .build();
    }

    public void buildUnprocessableEntityResponse() {
        errorSchema = ErrorSchema.builder()
                .errorCode(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .errorMessage(new ErrorMessage(EnumMessagesKey.ERROR_UNPROCESSABLE_ENTITY.getMessageKey()))
                .build();
    }

    public void buildCustomResponse(BaseEnumMessageKey messageKey, Object ...args) {
        errorSchema = ErrorSchema.builder()
            .errorCode(
                messageKey != null ? messageKey.getCode() : String.valueOf(HttpStatus.OK.value())
            )
            .errorMessage(
                new ErrorMessage(
                    messageKey != null ? messageKey.getMessageKey() : EnumMessagesKey.SUCCESS.getMessageKey(),
                    args
            ))
            .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorSchema {
        private String errorCode;
        private ErrorMessage errorMessage;
    }

    @Getter
    @NoArgsConstructor
    public static class ErrorMessage {
        private String indonesian;
        private String english;

        public ErrorMessage(String messageKey, Object ...values) {
            this.indonesian = MessageFormat.format(LocalizedMessages.getLocalizedMessageIndonesian(messageKey), values);
            this.english = MessageFormat.format(LocalizedMessages.getLocalizedMessageEnglish(messageKey), values);
        }
    }
}