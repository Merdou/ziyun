<#assign ctx="${request.contextPath}">


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>老师带班列表</title>

    <link href="${ctx}/static/css/animate.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/student/style.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/js/plugins/artDialog/css/dialog.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/teacher/daipigai.css" />
</head>
<body data-project="${ctx}">
     <#include  "common/header.ftl">
     <!-- 中间内容-->
     <div class="tab">
         <div class="wrap box">

              <!-- 左边菜单-->
             <#include "common/tabs.ftl" >


             <!-- 右边-->
             <div class="right">
                <form method="get" action="${ctx}/teacher/teachClasses" id="searchForm">
                 <div class="tab_right">

                     <div class="ypq">
                         <div class="ypt">
                             <div class="yptrr">所有带级列表</div>
                         </div>

                         <!--列表 -->
                         <table border="1" cellpadding="0" cellspacing="0" class="yp">
                             <tr>
                                 <td>专业名称</td>
                                 <td>班级名称</td>
                                 <td>开班时间</td>
                                 <td>结课时间</td>
                                 <td>操作</td>
                             </tr>

                             <#list pageInfo.list as p>
                             <tr>
                                 <td>${p.majorName}</td>
                                 <td>${p.name}</td>
                                 <td>${p.openingDate?date}</td>
                                 <td>
                                     <#if p.endDate?? >
                                         ${p.endDate?date}
                                     </#if>
                                 </td>
                                 <td>
                                     <a href="${ctx}/teacher/work/notchecked?classesId=${p.id}" target="_blank"> 待批改</a>
                                     <a href="#" target="_blank"> 已批改</a>
                                 </td>
                             </tr>
                             </#list>
                         </table>

                     </div>

                     <!--分页条 -->
                      <#import "common/Pagination.ftl" as page/>
                      <@page.nav  pageInfo=pageInfo callFunName="gotoPage" jumpToCallback="jumpTo"></@page.nav>


                 </div>

                </form>
             </div>

         </div>
     </div>

     <#include "common/footer.ftl" >

     <!-- 全局js -->
     <script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
     <script src="${ctx}/static/js/plugins/artDialog/dialog-plus.js"></script>

     <!-- 分页的js-->
     <script src="${ctx}/static/js/student/Pagination.js"></script>
</body>
</html>