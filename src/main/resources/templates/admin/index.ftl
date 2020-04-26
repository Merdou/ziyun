<#assign ctx="${request.contextPath}">

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>智云作业后台管理平台</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    
    
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    
    <link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">
    <link href="${ctx }/static/css/style.css?v=4.1.0" rel="stylesheet">

 <body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
	 <div id="wrapper">
	   <!-- 左边导航 -->
         <#include  "common/letNav.ftl">

	   
	   <!--右侧部分开始-->
	    <div id="page-wrapper" class="gray-bg dashbard-1">
             <!-- 顶部隐藏或显示导航按钮 -->
             <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                       <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    </div>                    
                </nav>
            </div>
			
			<!--选项卡条插件-->
            <div class="row content-tabs">
			
                <button class="roll-nav roll-left J_tabLeft">
                   <i class="fa fa-backward"></i>
                </button>
				
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="home_jsp">首页</a>
                    </div>
                </nav>
				
                <button class="roll-nav roll-right J_tabRight">
				   <i class="fa fa-forward"></i>
                </button>
				
				
                <div class="btn-group roll-nav roll-right">
				
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>
                    </button>
					
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="${ctx}/admin/logout" class="roll-nav roll-right J_tabExit">
				  <i class="fa fa fa-sign-out"></i> 退出
				</a>
            </div>
			
			<!--中间内容区(选项卡)-->
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx}/admin/default" frameborder="0" data-id="home_jsp" seamless></iframe>
            </div>
            
            
            
            <!-- 底部 -->
	       <#include "common/footer.ftl">
        </div> 
	   
	    
	 </div>  
	 
	 
	 
	 
	  <!-- 全局js -->
    <script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx }/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    
    
    <script src="${ctx }/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
 
     
    <script src="${ctx }/static/js/plugins/layer/layer.min.js"></script>
	  <!-- 自定义js -->
    <script src="${ctx }/static/js/hplus.js?v=4.1.0"></script>
    <script src="${ctx }/static/js/contabs.js" type="text/javascript"></script>


 </body>
 
 </html>
 
 
    
    