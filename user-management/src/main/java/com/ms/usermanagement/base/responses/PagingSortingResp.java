package com.ms.usermanagement.base.responses;

import com.ms.usermanagement.base.constant.BaseEnumMessageKey;
import com.ms.usermanagement.config.response_messages.BaseResponseMessage;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PagingSortingResp extends BaseResponseMessage {

    private OutputSchema outputSchema;

    @Builder(builderMethodName = "responseBuilder")
    public PagingSortingResp(
        BaseEnumMessageKey messageKey,
        List<?> records,
        Integer currentPage,
        Integer currentSize,
        Integer totalPage,
        Long totalSize,
        Object ...args
    ) {
        super.buildCustomResponse(messageKey, args);
        outputSchema = new OutputSchema(records, currentPage, currentSize, totalPage, totalSize);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutputSchema {

        private List<?> records;

        private Integer currentPage;

        private Integer currentSize;

        private Integer totalPage;

        private Long totalSize;

    }

}
