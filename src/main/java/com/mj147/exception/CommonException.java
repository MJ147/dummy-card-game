package com.mj147.exception;

import com.mj147.common.ConstErrorMsg;

public class CommonException extends RuntimeException {

    private ConstErrorMsg constErrorMsg;

    public CommonException(ConstErrorMsg constErrorMsg) {
        this.constErrorMsg = constErrorMsg;
    }

    public ConstErrorMsg getConstErrorMsg() {
        return constErrorMsg;
    }
}
