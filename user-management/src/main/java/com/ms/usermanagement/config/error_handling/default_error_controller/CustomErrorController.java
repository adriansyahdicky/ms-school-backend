package com.ms.usermanagement.config.error_handling.default_error_controller;

import com.google.gson.Gson;
import com.ms.usermanagement.config.response_messages.BaseResponseMessage;
import com.ms.usermanagement.config.response_messages.localization_messages.EnumMessagesKey;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CustomErrorController extends AbstractErrorController {

    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @GetMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        var gson = new Gson();

        String responseMessageKey = EnumMessagesKey.ERROR_DEFAULT.getMessageKey();
        if ("404".equalsIgnoreCase(String.valueOf(errorAttributes.get("status")))) {
            responseMessageKey = EnumMessagesKey.ERROR_NOT_FOUND.getMessageKey();
        }
        BaseResponseMessage responseMessage = BaseResponseMessage.baseResponseBuilder()
                .errorCode(String.valueOf(errorAttributes.get("status")))
                .errorMessage(new BaseResponseMessage.ErrorMessage(responseMessageKey))
                .build();
        return gson.toJson(responseMessage);
    }
}
