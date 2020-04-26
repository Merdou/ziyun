var projectName;

$(function(){

    projectName=$("body").attr("data-project");

    //禁止使用右键
    $("body").bind("contextmenu", function(event) {
        return false;
    });

    //禁止使用F5
    $("body").bind("keydown",function(e){
        e=window.event||e;

        //屏蔽F5刷新键
        if(event.keyCode==116){
            e.keyCode = 0; //IE下需要设置为keyCode为false
            return false;
        }

        //屏蔽 Alt+ 方向键 ←
        //屏蔽 Alt+ 方向键 →
        if ((event.ctrlKey)&&(event.keyCode==116))
        {
            event.returnValue=false;
            return false;
        }
    });

    $("#btnSend").click(sendSMS);

    //监听手机和短信输入长度
    $("#mobile,#smsCode").keyup(codeNext);

    //下一步
    $("#btnNext").click(nextStep);

});

//点击发送短信
function sendSMS(){
    //清空已输入的短信码
    $("#smsCode").val("");
    //下一步操作禁用
    $("#btnNext").attr("disabled","disabled");

    var mobile=$("#mobile");
    var mobileValue=mobile.val().trim();
    if(mobileValue.length==0){
        artAlert("警告","手机号码不能为空!").showModal();
        return;
    }

    //验证手机格式
    var regTel=/^1\d{10}$/
    if(!regTel.test(mobileValue)){
        artAlert("警告","手机号码格式错误!").showModal();
        return;
    }

    //发送短信(ajax)
    $.ajax({
        type:"post",
        url: projectName+"/student/sendsms",
        data: {"mobile" : $("#mobile").val()},
        dataType: "json",
        success:function(resp){
           if(resp.code===0){
               //返回时间秒
               let seconds=resp.data;

               countDown(seconds);
           }  else{
               artAlert("警告",resp.message).showModal();
           }
        }
    });


}


function countDown(seconds){
    //禁用发送按钮
    var btnSend=$("#btnSend");
    btnSend.attr("disabled","disabled");

    let down=seconds;
    btnSend.val("("+down+"s)重新发送");

    var itDown=setInterval(function(){
        down--;
        //倒计时结束
        if(down==0){
            btnSend.removeAttr("disabled");
            //结束操作
            clearInterval(itDown);
            btnSend.val("发送验证码");
            return;
        }
        btnSend.val("("+down+"s)重新发送");

    },1000);

}


//监听用户输入短信值的长度
function codeNext(){
    var smsCode=$("#smsCode");
    //验证码的值
    var smsCodeValue=smsCode.val().trim();
    //手机号码
    var mobileValue=$("#mobile").val().trim();

    var regTel=/^1\d{10}$/;

    //三个条件全部成立，下一步才成立
    if(smsCodeValue.length==5 && mobileValue.length==11 && regTel.test(mobileValue)){
        $("#btnNext").removeAttr("disabled");
    }else{
        $("#btnNext").attr("disabled","disabled");
    }
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

//下一步操作
function nextStep(){
    //把手机和验证码一同发送到后台，进行查询
    var dataObj={};
    dataObj["mobile"]= $("#mobile").val().trim();
    dataObj["code"]=$("#smsCode").val().trim();

    //ajax
    $.ajax({
        type:"post",
        url:projectName+"/student/checkcode",
        data: dataObj,
        dataType:"json",
        success:function(resp){
            if(resp.code===0){
                let url=`${projectName}/student/changepwd?mobile=${dataObj.mobile}&code=${dataObj.code}`;
                window.location.href= url;
            }else{
                artAlert("警告",resp.message).showModal();
                //清空验证码
                $("#smsCode").val("");
                $("#btnNext").attr("disabled","disabled");
            }
        }
    });

}