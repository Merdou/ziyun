package com.hxzy.service;

import com.hxzy.common.vo.PageSearch;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.entity.Student;
import com.hxzy.vo.ChangePwdVO;

public interface StudentService {

    boolean insert(Student record);

    Student selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(Student record);

    boolean updateByPrimaryKey(Student record);

    ResponseMessage search(PageSearch pageSearch);

    /**
     * 登录
     * @param student
     * @return
     */
    ResponseMessage login(Student student);

    /**
     * 发送验证码
     * @param mobile
     * @return
     */
    ResponseMessage sendFindPasswordSms(String mobile);

    /**
     * 检查输入短信码与手机是否匹配，以及短信码是否已经过期
     * @param changePwdVO
     * @return
     */
    ResponseMessage checkSmsCode(ChangePwdVO changePwdVO);

    /**
     * 重置密码
     * @param changePwdVO
     * @return
     */
    ResponseMessage updatePwd(ChangePwdVO changePwdVO);
}
