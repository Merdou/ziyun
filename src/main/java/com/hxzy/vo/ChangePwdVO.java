package com.hxzy.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 更新密码VO
 */
@Getter
@Setter
public class ChangePwdVO {
    /**
     * 短信码
     */
    private String code;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 新密码
     */
    private String password;

}
