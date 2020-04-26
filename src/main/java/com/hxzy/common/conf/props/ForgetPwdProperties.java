package com.hxzy.common.conf.props;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 忘记密码发短信模板类属性
 */
@Data
public class ForgetPwdProperties {
    //腾讯短信模板id
    private String templateId;

    //短信过期时间(分钟)
    private int expiredMinute;

    //重新发送时间(秒)
    private int retrySeconds;

    //短信长度 code_len
    private int codeLen=5;

    //redis存储验证码的前缀
    private String redisPrefix;



}
