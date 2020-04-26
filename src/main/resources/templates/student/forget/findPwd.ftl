<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>智云作业-账号服务中心</title>
    <link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">


    <!-- artdialog 模态框框 -->
    <link href="${ctx }/static/js/plugins/artDialog/css/dialog.css" rel="stylesheet">

    <link type="text/css" href="${ctx}/static/css/student/login.css " rel="stylesheet" />
</head>

<body   data-project="${ctx }" >
    <!-- 顶部-->
    <div class="container-fluid line">
        <div class="w1200 h88 clearfix">
            <a href="#" class="logo">
                <img src="${ctx}/static/img/biaozhi.jpg">
            </a>
            <span class="spanPwd">找回密码</span>
        </div>
    </div>

    <!--中间-->
    <div class="container-fluid content nobg">


        <div class="w1200 h687">

            <div class="row">
                <div class="form-wrap">

                    <form   method="post" id="forgetForm">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-8">
                                    <input type="text" class="form-control field" id="mobile" name="mobile" placeholder="已验证手机号">

                                </div>
                                <div class="col-md-4">
                                    <input type="button" id="btnSend" class="btn btn-info field" value="发送验证码">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control field" id="smsCode" name="smsCode"  maxlength="5" placeholder="短信验证码">
                        </div>
                        <input type="button" class="form-control  btn btn-primary field" id="btnNext" value="下一步" disable="disable">
                        <a  href="${ctx}/student/login" class="form-control  btn btn-success field" >返回登录</a>
                    </form>
                </div>
            </div>

        </div>

    </div>

    <!--底部-->
    <div class="container-fluid footer">
        <div class="w1200">
            <ul class="tm clearfix">
                <li>智云</li>
                <li>帮助中心</li>
                <li>学习中心</li>
                <li class="yq">友情链接</li>
            </ul>

            <h6>DT人才培训基地（重庆中心）&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;重庆互联网学院  &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;版权所有：华信智原</h6>

        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- argdialog -->
    <script src="${ctx }/static/js/plugins/artDialog/dialog-plus.js"></script>

    <script src="${ctx }/static/js/student/sms.js"></script>


</body>
</html>