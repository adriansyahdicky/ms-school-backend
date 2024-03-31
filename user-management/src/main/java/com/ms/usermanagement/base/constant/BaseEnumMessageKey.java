package com.ms.usermanagement.base.constant;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public interface BaseEnumMessageKey extends Serializable {

    String getMessageKey();

    String getCode();

    HttpStatus getHttpStatus();

}
