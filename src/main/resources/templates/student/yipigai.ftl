<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>已批改</title>

    <link href="${ctx}/static/css/animate.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/student/style.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/js/plugins/artDialog/css/dialog.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/student/daipigai.css" />
</head>
<body data-project="${ctx}">
<#include  "common/header.ftl">
<#include "common/info.ftl" >

<!-- 中间内容-->


<#include "common/footer.ftl" >

<!-- 全局js -->
<script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/static/js/plugins/artDialog/dialog-plus.js"></script>
</body>
</html>