package com.hxzy.controller.student;

import com.hxzy.common.conf.props.ForgetPwdProperties;
import com.hxzy.common.vo.ResponseMessage;
import com.hxzy.service.StudentService;
import com.hxzy.vo.ChangePwdVO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 短信找回密码
 */
@Log4j2
@Controller
@RequestMapping(value = "/student")
public class StudentFindPwdController  {

    @Autowired
    private ForgetPwdProperties forgetPwdProperties;

    @Autowired
    private StudentService studentService;

    /**
     * 找回密码页
     * @param model
     * @return
     */
    @GetMapping(value = "/findpwd")
    public String findPwd(Model model){
        return "student/forget/findPwd";
    }

    /**
     * 发送验证码
     * @param mobile
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/sendsms")
    public ResponseMessage sendsms(String mobile){
        return this.studentService.sendFindPasswordSms(mobile);
    }

    /**
     * 显示更新密码页
     * @param model
     * @return
     */
    @GetMapping(value = "/changepwd")
    public String changePwd(ChangePwdVO  changePwdVO,Model model){
        //再次判断是否手机和验证码是否正确(跳转到登录页)
        if(StringUtils.isBlank(changePwdVO.getMobile()) ||  StringUtils.isBlank(changePwdVO.getCode())){
            log.error("手机号:{},验证码:{}为空", changePwdVO.getMobile(),changePwdVO.getCode());
            return "redirect:/student/login";
        }

        if(changePwdVO.getMobile().trim().length()!=11 ||  changePwdVO.getCode().length()!=this.forgetPwdProperties.getCodeLen()){
            log.error("手机号:{}长度不够,验证码:{}长度不够", changePwdVO.getMobile(),changePwdVO.getCode());
            return "redirect:/student/login";
        }

        //判断手机验证码是否正确
        ResponseMessage  rm=this.studentService.checkSmsCode(changePwdVO);
        if(rm.getCode()==0){
            model.addAttribute("vo",changePwdVO);
            return "student/forget/changePwd";

        }else{
            //验证过期了或者验证码错误
            log.error("手机号:{}或验证码:{}错误", changePwdVO.getMobile(),changePwdVO.getCode());
            return "redirect:/student/findpwd";
        }
    }

    /**
     * 检查输入短信码与手机是否匹配，以及短信码是否已经过期
     * @param changePwdVO
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/checkcode")
    public ResponseMessage checkSmsCode(ChangePwdVO  changePwdVO){
        return this.studentService.checkSmsCode(changePwdVO);
    }

    /**
     * 重置密码
     * @param changePwdVO
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/updatepwd")
    public ResponseMessage updatepwd(ChangePwdVO changePwdVO){
        return this.studentService.updatePwd(changePwdVO);
    }
}
