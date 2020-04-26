<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>教师登录</title>
    <link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">

    <!-- bootstrap-validator验证 -->
    <link href="${ctx }/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">

    <!-- bootstrap-dialog 模态框框 -->
    <link href="${ctx }/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">

    <link type="text/css" href="${ctx}/static/css/student/login.css " rel="stylesheet" />
</head>

<body   data-project="${ctx }" >
    <!-- 顶部-->
    <div class="container-fluid">
        <div class="w1200 h88 clearfix">
            <a href="#" class="logo">
                <img src="${ctx}/static/img/biaozhi.jpg">
            </a>

            <ul class="logintype">
                <li><a href="${ctx}/student/login">学生入口</a></li>
                <li class="active">教师入口</li>
            </ul>
        </div>
    </div>

    <!--中间-->
    <div class="container-fluid content">
        <div class="rightbg">
            <img src="${ctx}/static/img/beij.png">
        </div>

        <div class="w1200 h687">

            <div class="row">
                <div class="col-md-8 whitecolor">
                    <h2 class="ziyun">来智云</h2>
                    <h1>作业提交更智能</h1>
                    <p></p>
                    <h3>方便学生查看作业的批改情况，以便于更好地学习方便学生和老师更好的互动式的学习与教学</h3>

                </div>

                <div class="col-md-4">
                    <div class="panel panel-default login">
                        <div class="panel-heading">
                            <h3 class="panel-title">教师登录</h3>
                        </div>
                        <div class="panel-body">

                            <form method="post" id="loginForm">

                                <div class="form-group">
                                    <input type="text" class="form-control" id="mobile" name="mobile" placeholder="请输入手机号">
                                </div>

                                <div class="form-group">
                                    <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                                </div>

                                <input type="button" id="btnLogin" class="btn btn-primary" value="登录">

                                <div class="form-group text-right forget">
                                    <a  href="#">忘记密码</a>
                                </div>

                            </form>
                        </div>
                    </div>

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
    <!-- bootstrap-dialog模态框 -->
    <script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>

    <script src="${ctx }/static/js/teacher/login.js"></script>

</body>
</html>