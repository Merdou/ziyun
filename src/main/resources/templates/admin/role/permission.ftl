<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>商品管理系统</title>

    <meta name="keywords" content="">
    <meta name="description" content="">


    <link href="${ctx }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">

    <link href="${ctx }/static/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">

    <!-- bootstrap-dialog 模态框框 -->
    <link href="${ctx }/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">

    <link href="${ctx }/static/css/style.css?v=4.1.0" rel="stylesheet">

</head>


<body   data-project="${ctx }" >



<div class="wrapper wrapper-content animated fadeInRight">

    <!-- 查询条件 -->
    <div class="row">
        <div class="col-md-12">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">为[${role.rolename} ]角色分配菜单权限</h3>
                </div>

                <div class="panel-body">
                    <form class="form-inline">
                        <input type="hidden" id="id" name="id" value="${role.id}">

                        <button type="button" class="btn btn-primary" id="btnSave">保存</button>

                        <a  href="${ctx}/admin/role/search" class="btn btn-primary" >返回</a>
                    </form>
                </div>
            </div>

        </div>
    </div>

    <!-- 显示表格 ztree 需要写的html -->
    <div class="row">
        <div class="col-md-12">

            <div class="panel panel-success">
                <div class="panel-heading">
                       <input type="checkbox" id="checkAll"  value="1"> 全选
                </div>
                <div class="panel-body">
                    <ul id="menu" class="ztree"></ul>
                </div>
            </div>



        </div>
    </div>




</div>


<!-- 全局js -->
<script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>

<!-- Peity -->
<script src="${ctx }/static/js/plugins/peity/jquery.peity.min.js"></script>

<!-- ztree-->
<script src="${ctx }/static/js/plugins/ztree/js/jquery.ztree.all.js"></script>


<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>
<!-- 自定义js -->
<script src="${ctx }/static/js/content.js?v=1.0.0"></script>

<!-- 自定义 permission.js -->
<script src="${ctx }/static/js/admin/permission.js?v=1.0.0"></script>

</body>

</html>

