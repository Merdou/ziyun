<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>智云作业后台管理系统</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="${ctx }/favicon.ico"/>

    <link href="${ctx }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">


    <!-- bootstrap-table -->
    <link href="${ctx }/static/js/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <!-- bootstrap-treeview -->
    <link href="${ctx }/static/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
    <!-- bootstrap-对话框 -->
    <link href="${ctx }/static/js/plugins/dialog/css/bootstrap-dialog.min.css" rel="stylesheet">

    <!-- bootstrap-validator验证 -->
    <link href="${ctx }/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">


    <link href="${ctx }/static/css/style.css?v=4.1.0" rel="stylesheet">

</head>


<body data-project="${ctx }">


<div class="wrapper wrapper-content animated fadeInRight">


    <!--左边显示树形节点，右边显示表格 -->
    <div class="row">
        <div class="col-md-4">
            <h3>字典列表</h3>
            <div id="dictTree" class=""></div>
        </div>

        <div class="col-md-8">

            <div class="panel panel-success" id="panelEdit" style="display: none;">
                <div class="panel-heading">
                    <h3 class="panel-title" id="typeName"></h3>
                </div>
                <div class="panel-body">
                    <button type="button" id="btnAdd" class="btn btn-success">新增</button>
                    <button type="button" id="btnEdit" class="btn btn-primary">修改</button>
                    <button type="button" id="btnDelete" class="btn btn-danger">删除</button>
                </div>
            </div>

            <table id="tb" class="table table-bordered  table-hover"></table>

        </div>
    </div>


    <!--编辑窗体 -->
    <div id="editDialog" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="modelTitle">标题</h3>
                </div>
                <!-- 内容区-->
                <div class="modal-body">
                    <form class="form-horizontal" id="editForm">

                        <div class="form-group">
                            <label   class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_name" name="name" data-bv-trigger="blur" >
                            </div>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="btnSave" class="btn btn-primary">保存</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>


<!-- 全局js -->
<script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>

<!-- bootstrap-table -->
<script src="${ctx }/static/js/plugins/bootstrap-table/bootstrap-table.js?v=1.14.2"></script>
<script src="${ctx }/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js?v=1.14.2"></script>

<!-- bootstrap-treeview -->
<script src="${ctx }/static/js/plugins/treeview/bootstrap-treeview.js"></script>

<!-- bootstrap-对话框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>

<!-- Peity -->
<script src="${ctx }/static/js/plugins/peity/jquery.peity.min.js"></script>

<!-- bootstrap-validator验证 -->
<script src="${ctx }/static/js/plugins/validator/js/bootstrapValidator.js"></script>
<script src="${ctx }/static/js/plugins/validator/js/language/zh_CN.js"></script>


<!-- h+框架写的自定义js -->
<script src="${ctx }/static/js/content.js?v=1.0.0"></script>

<!-- 我们自己写的 role.js -->
<script src="${ctx }/static/js/admin/data.js?v=1.0.0"></script>

</body>

</html>
    
    