package com.example.auth.common;

import java.io.Serializable;

/**
 * @author zj
 */
public interface IResultCode extends Serializable {

    String getMessage();

    int getCode();
}
