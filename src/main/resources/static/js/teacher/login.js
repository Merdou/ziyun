//项目名称
var projectName="";
//主键(0代表新增， 非空代表修改)
var primaryKey=0;

//1、初始化
$(function () {
    projectName=$("body").attr("data-project");
    //表单验证方法
    formValidator();

    //登录按钮
    $("#btnLogin").click(ajaxLogin);

    //阻止form表单提交
    $("#loginForm").submit(function(e){
        e.preventDefault();
    });

    $("#password").keydown(function(e) {
        //按下回车键
        if(e.keyCode==13){
            //自己触发一下登录事件
            $("#btnLogin").trigger("click");
        }

    });
});


//表单验证
function formValidator(){

    $('#loginForm').bootstrapValidator({
        message: 'This value is not valid',
        excluded: [':disabled'],  //在bootstrapValidator.js 第1779行有配置说明
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            mobile: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    },phone: {
                        country:'CN',
                        message:'请输入正确 的手机号'
                    }
                }
            }, password: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    },stringLength:{
                        min:4,
                        max:20,
                        message:'密码长度必须是4-20位之间'
                    }
                }
            }
        }
    });
}


//保存数据-ajax
function ajaxLogin() {
    //验证表单是否通过 bootstrapValidator, 手动触发验证事件
    var bsv = $('#loginForm').data('bootstrapValidator');
    //手动调用
    bsv.validate();

    //有时候有ajax验证，就是异步，会有延时，延迟200毫秒调用是否通过setTimeout(函数, 延迟时间);
    setTimeout(function(){
        //验证数据合性
        var result=bsv.isValid();
        if(result){
             submitLogin();
        }
    },200);
}

function submitLogin(){
    var formData=$("#loginForm").serialize();
    $.ajax({
        type:"post",
        url: projectName+"/teacher/login",
        data: formData,
        dataType:"json",
        success:function (resp) {
            if(resp.code ===0){
                //登录成功
                window.location.href= projectName+"/teacher/teachClasses"
            }else{
                BootstrapDialog.show({
                    type: BootstrapDialog.TYPE_DANGER,
                    title: '错误',
                    message: resp.message
                });
            }
        }
    });
}
