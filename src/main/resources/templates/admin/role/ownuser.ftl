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

    <!-- bootstrap-table -->
    <link href="${ctx }/static/js/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">

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
                    <h3 class="panel-title">为[${role.rolename} ]角色分配用户</h3>
                </div>

                <div class="panel-body">
                    <form class="form-inline">
                        <input type="hidden" id="id" name="id" value="${role.id}">

                        <a  href="${ctx}/admin/role/search" class="btn btn-primary" >返回</a>
                    </form>
                </div>
            </div>

        </div>
    </div>

    <!-- 显示表格 ztree 需要写的html -->
    <div class="row">
           <div class="col-md-6">
                <div class="panel panel-success">
                    <div class="panel-heading">
                           拥有角色用户列表
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <input type="button" id="btnRemove" class="btn btn-danger" value="移出用户">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <table id="ownUserTb"></table>
                            </div>
                        </div>

                    </div>
                </div>
           </div>

            <div class="col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        可选用户列表
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <input type="button" id="btnAdd" class="btn btn-primary" value="增加权限">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <table id="noOwnUserTb"></table>
                            </div>
                        </div>

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

<!-- bootstrap-table -->
<script src="${ctx }/static/js/plugins/bootstrap-table/bootstrap-table.js?v=1.14.2"></script>
<script src="${ctx }/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js?v=1.14.2"></script>


<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>
<!-- 自定义js -->
<script src="${ctx }/static/js/content.js?v=1.0.0"></script>

<!-- 自定义 ownuser.js -->
<script src="${ctx }/static/js/admin/ownuser.js?v=1.0.0"></script>

</body>

</html>

