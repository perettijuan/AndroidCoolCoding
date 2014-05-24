package com.jpp.foods.services;

import com.jpp.foods.R;

/**
 * General support enum that contains all the possible error codes of an HTTP
 * execution.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public enum ApiMessageEnum {

    ERROR_GENERAL(-1),
    UNSUPORTED_OPERATION(-1, R.string.error_unsupported, -1),
    ERROR_OPERATION_FAIL(-1),;


    public static final int ERROR_GENERAL_CODE = -1;
    public static final int GENERAL_SUCCESS_CODE = 200;
    public static final int GENERAL_SUCCESS_CREATION = 201;

    private int mErrorCode;
    private Integer mMessageRes;
    private Integer mTitleRes;

    private ApiMessageEnum(int errorCode) {
        mErrorCode = errorCode;
        mMessageRes = R.string.error_general;
        mTitleRes = R.string.error_general;
    }

    private ApiMessageEnum(int errorCode, int messageRes, int titleRes) {
        mErrorCode = errorCode;
        mMessageRes = messageRes;
        mTitleRes = titleRes;
    }

    public Integer getMessageRes() {
        return mMessageRes;
    }

    public int getTitleRes() {
        return mTitleRes;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public static ApiMessageEnum getEnum(int code, String serverCode) {
        if (serverCode != null) {
            for (ApiMessageEnum en : ApiMessageEnum.values()) {
                if (code == en.getErrorCode()) {
                    return en;
                }
            }
        }
        return null;
    }

    public static boolean isSuccessResponse(int response) {
        boolean successful = false;
        if (response == GENERAL_SUCCESS_CODE) {
            successful = true;
        } else if (response == GENERAL_SUCCESS_CREATION) {
            successful = true;
        }
        return successful;
    }

}
