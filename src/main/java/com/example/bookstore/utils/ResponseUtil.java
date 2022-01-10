package com.example.bookstore.utils;

import com.example.bookstore.model.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

@Component
public class ResponseUtil {
    @Autowired
    private MessageSource messageSource;

    private Locale getLocale(String locale) {
        return locale != null ? new Locale(locale) : Locale.UK;
    }

    /**
     * Prepare success response response dto.
     *
     * @param data         the data
     * @param responseCode the response code
     * @param locale       the locale
     * @return the response dto
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ResponseDTO prepareSuccessResponse(Object data, int responseCode, String locale) {
        Locale localeName = getLocale(locale);
        String message = messageSource.getMessage(String.valueOf(responseCode), null, getLocale(locale));
        return new ResponseDTO(0, message, data);
    }

    /**
     * Exception response dto.
     *
     * @param code the code
     * @return the response dto
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ResponseDTO exception(int code) {
        String message = messageSource.getMessage(String.valueOf(code), null, getLocale(null));
        return new ResponseDTO(code, message, new HashMap<>());
    }
    /**
     * Exception response dto.
     *
     * @param code    the code
     * @param message the message
     * @return the response dto
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ResponseDTO exception(int code, String message) {
        if (Objects.isNull(message) || message.trim().isEmpty()) {
            message = messageSource.getMessage(String.valueOf(code), null, getLocale(null));
        } else {
            message = messageSource.getMessage(String.valueOf(message), null, getLocale(null));
        }
        return new ResponseDTO(code, message, new HashMap<>());
    }

    public ResponseDTO prepareSuccessResponseAndroidStick(Object data, int responseCode, String locale) {
        String message = messageSource.getMessage(String.valueOf(responseCode), null, getLocale(locale));
        return new ResponseDTO(0, message, data);
    }

    /**
     * @param code
     * @param message
     * @param data
     * @return
     */
    public ResponseDTO exception(int code, String message, Object data) {
        if (Objects.isNull(message) || message.trim().isEmpty()) {
            message = messageSource.getMessage(String.valueOf(code), null, getLocale(null));
        } else {
            message = messageSource.getMessage(String.valueOf(message), null, getLocale(null));
        }
        return new ResponseDTO(code, message, data);
    }

    public ResponseDTO prepareSuccessResponseWithCode(Object data, int responseCode, String locale) {
        String message = messageSource.getMessage(String.valueOf(responseCode), null, getLocale(locale));
        return new ResponseDTO(responseCode, message, data);
    }
}
