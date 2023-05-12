package org.arcone.lmsapi.util.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessRuleException extends Exception {
    public BusinessRuleException(String message) {
        super(message);
        log.warn(message);
    }
}
