<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>智云作业后台管理平台</title>
     <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    
    <link href="${ctx }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">
    <!-- bootstrap-validator验证 -->
     <link href="${ctx }/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">
    <!-- bootstrap-dialog 模态框框 -->
     <link href="${ctx }/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">


    
    <link href="${ctx }/static/css/style.css?v=4.1.0" rel="stylesheet">
    
    
    <link href="${ctx }/static/css/login.css" rel="stylesheet">
    
    <script>
       /*解决在iframe中显示登录界面的问题 */ 
       if (window.top !== window.self) {
            window.top.location = window.location;
        }
       
    </script>

</head>

<body class="signin" data-project="${ctx }" >
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>[ H+ ]</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>H+ 后台主题UI框架</strong></h4>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                    </ul>
                    <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
                </div>
            </div>
            <div class="col-sm-5">
                <form method="post"  id="editForm"  onsubmit="return false;">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到H+后台主题UI框架</p>
                    
	                  <div class="form-group">	
					         <div class="col-sm-12">
					            <input   type="text" class="form-control uname" id="mobile"  name="mobile" placeholder="手机号" autocomplete="off">
					       </div>
					 </div>
                    
                     <div class="form-group">	
					         <div class="col-sm-12">
					            <input   type="password" class="form-control pword m-b" id="password"  name="password" placeholder="密码">
					       </div>
					 </div>
					  
                    <a href="${ctx}/admin/findPwd" target="_blank">忘记密码了？</a>
                    <input type="button" id="btnLogin" class="btn btn-success btn-block" value="登录" >



               </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2019 All Rights Reserved. H+
            </div>
        </div>
    </div>
</body>

 <!-- 全局js -->
    <script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>
     
     <!-- bootstrap-validator验证 -->
     <script src="${ctx }/static/js/plugins/validator/js/bootstrapValidator.js"></script>
    <script src="${ctx }/static/js/plugins/validator/js/language/zh_CN.js"></script>
    
    <!-- bootstrap-dialog模态框 -->
     <script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>

    
    <script src="${ctx }/static/js/admin/login.js?v=1.0.0"></script>
</html>
