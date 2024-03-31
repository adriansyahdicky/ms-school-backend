package com.ms.coursemanagement.base.responses;

import com.ms.coursemanagement.base.constant.BaseEnumMessageKey;
import com.ms.coursemanagement.config.response_messages.BaseResponseMessage;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SingleRecordResp extends BaseResponseMessage {

    private OutputSchema outputSchema;

    @Builder(builderMethodName = "responseBuilder")
    public SingleRecordResp(
        BaseEnumMessageKey messageKey,
        Object result,
        Object ...args
    ) {
        super.buildCustomResponse(messageKey, args);
        outputSchema = new OutputSchema(result);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutputSchema {

        private Object result;

    }

}
