<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>智云作业-账号服务中心</title>
    <link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">

    <!-- bootstrap-validator验证 -->
    <link href="${ctx }/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">

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
            <span class="spanPwd">更新密码</span>
        </div>
    </div>

    <!--中间-->
    <div class="container-fluid content nobg">


        <div class="w1200 h687">

            <div class="row">
                <div class="form-wrap">

                    <form   method="post" id="forgetForm">
                        <div class="form-group">
                            <h2>手机号：${vo.mobile}</h2>
                            <input type="hidden" id="mobile" name="mobile" value="${vo.mobile!}" >
                            <input type="hidden" id="code" name="code" value="${vo.code!}">
                        </div>

                        <div class="form-group">
                            <input type="password" class="form-control field" id="password" name="password" placeholder="新密码">
                        </div>

                        <div class="form-group">
                            <input type="password" class="form-control field" id="confirmPassword" name="confirmPassword" placeholder="确认新密码">
                        </div>

                        <input type="button" class="form-control  btn btn-danger field" id="btnNext" value="更新密码">

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
    <!-- bootstrap-validator验证 -->
    <script src="${ctx }/static/js/plugins/validator/js/bootstrapValidator.js"></script>
    <script src="${ctx }/static/js/plugins/validator/js/language/zh_CN.js"></script>

    <!-- argdialog -->
    <script src="${ctx }/static/js/plugins/artDialog/dialog-plus.js"></script>

    <script src="${ctx }/static/js/student/changepwd.js"></script>

</body>
</html>