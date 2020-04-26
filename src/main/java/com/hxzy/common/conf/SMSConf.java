package com.hxzy.common.conf;

import com.hxzy.common.conf.props.ForgetPwdProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信相关的配置
 */
@Configuration
public class SMSConf {

    /**
     * 忘记密码短信模板属性
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "sms.forgetpwd")
    public ForgetPwdProperties forgetPwdProperties(){
        return new ForgetPwdProperties();
    }



}
