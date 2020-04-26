<#assign ctx="${request.contextPath}">


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>老师未批改</title>

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
                <form method="get" action="${ctx}/teacher/work/notchecked" id="searchForm">

                    <!-- 隐藏班级id -->
                    <input type="hidden" id="classesId" name="classesId" value="${classesId}">

                 <div class="tab_right">

                     <div class="ypq">
                         <div class="ypt">
                             <div class="yptrr">待批改</div>
                             <div class="yptll">
                                 <a href="yipigai.html">已批改</a>
                             </div>
                         </div>

                         <!--列表 -->
                         <ul class="yp">
                            <#list pageInfo.list as m>
                             <li class="ypzy" >
                                 <div class="ypl">
                                     <img src="${m.cover}" class="img_a"/>
                                 </div>
                                 <div class="ypr">
                                     <div class="ypry">
                                         <div class="ypryl">
                                             <a href="#">${m.title}</a>
                                         </div>
                                         <div class="ypryr ypryr_a">
                                             <a href="${ctx}/teacher/dianping/${m.id}">立即批改></a>
                                         </div>
                                     </div>
                                     <div class="ypre ypre_a">
                                         <span>提交人：${m.studentName}</span>
                                         <span>提交时间：${m.submitTime?string("MM月dd日")}</span>
                                     </div>

                                     <div class="ypri">
                                         <div class="yprir">
                                             <div class="ypril_a_a">${m.contentName}</div>
                                             <div class="ypril_a_a">${m.labelName}</div>
                                             <div class="ypril_a_a">${m.typesName}</div>
                                         </div>
                                     </div>
                                     <div class="yprw">
                                         <div class="yprwl">作业介绍：</div>
                                         <div class="yprwr_a">
                                            ${m.description}
                                         </div>
                                     </div>
                                 </div>
                             </li>
                            </#list>

                         </ul>

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