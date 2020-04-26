var projectName;

$(function(){
    projectName=$("body").attr("data-project");

    // 验证
    formValidator();

    //更新密码
    $("#btnNext").click(changepassword);

});

//表单验证
function formValidator(){
    $('#forgetForm').bootstrapValidator({
        message: 'This value is not valid',
        excluded: [':disabled'],  //在bootstrapValidator.js 第1779行有配置说明
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            password: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    },
                    stringLength: {
                        min:4,
                        max:20,
                        message:'长度必须在4-20个字符之间'
                    }
                }
            }, confirmPassword: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    },
                    identical: {  //比较是否相同
                        field: 'password',
                        message: '两次密码不一致'
                    }
                }
            },mobile: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    }
                }
            },code: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '短信验证码不能为空'
                    }
                }
            }

        }
    });
}


//保存
function changepassword(){
    //禁用更新密码
    $("#btnNext").attr("disabled","disabled");

    //验证表单是否通过 bootstrapValidator, 手动触发验证事件
    var bsv = $('#forgetForm').data('bootstrapValidator');
    //手动调用
    bsv.validate();
    //有时候有ajax验证，就是异步，会有延时，延迟200毫秒调用是否通过setTimeout(函数, 延迟时间);
    setTimeout(function(){
        //验证数据合性
        var result=bsv.isValid();
        if(result){
            ajaxSaveData(); 
        }else{
            //验证不成功
            $("#btnNext").removeAttr("disabled");
        }
    },200);
}

function ajaxSaveData(){
    
    var formData=$("#forgetForm").serialize();
    
    $.ajax({
        type:"post",
        url:projectName+"/student/updatepwd",
        data: formData,
        dataType: "json",
        success:function (resp) {
            //移出禁用状态
            $("#btnNext").removeAttr("disabled");
            if(resp.code===0){
                window.location.href=projectName+"/student/login";
            }else{
                artAlert("警告",resp.message).showModal();
                window.location.href=projectName+"/student/findpwd";
            }
        }
    });
    
}


function artAlert(title,message,okCallBack,cacneCallBack){
    if(!okCallBack){
        okCallBack=function(){};
    }

    var d = dialog({
        title: title,
        content:message,
        okValue: '确定',
        ok: okCallBack,
        cancelValue: '取消',
        cancel: cacneCallBack
    });
    return d;
}
