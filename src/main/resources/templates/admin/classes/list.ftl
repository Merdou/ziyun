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


    <link href="${ctx }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">


    <!-- bootstrap-table -->
    <link href="${ctx }/static/js/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">

    <!-- bootstrap-validator验证 -->
    <link href="${ctx }/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">

    <!--datepicker 日期 -->
    <link href="${ctx }/static/css/plugins/datapicker/bootstrap-datetimepicker.css" rel="stylesheet">

    <link href="${ctx }/static/css/plugins/multiselect/bootstrap-multiselect.css" rel="stylesheet">

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
                    <h3 class="panel-title">操作</h3>
                </div>

                <div class="panel-body">
                    <form class="form-inline">

                        <div class="form-group">
                            <label>专业</label>
                            <select class="form-control" id="marjorId" name="marjorId">
                                <option value="0">全部</option>

                                <#list allMajor as m>
                                    <option value="${m.id}">${m.name}</option>
                                </#list>

                            </select>
                        </div>

                        <div class="form-group">
                            <label>状态</label>
                            <select class="form-control" id="state" name="state">
                                <option value="1">学习中</option>
                                <option value="2">已结课</option>
                                <option value="3">全部</option>
                            </select>
                        </div>

                        <button type="button" class="btn btn-info" id="btnSearch">查询</button>

                        <button type="button" class="btn btn-primary" id="btnAdd">新增</button>
                        <button type="button" class="btn btn-primary" id="btnEdit">编辑</button>

                    </form>
                </div>
            </div>

        </div>
    </div>

    <!-- 显示表格 bootstrap-table需要写的html -->
    <div class="row">
        <div class="col-md-12">

            <table id="tb">
            </table>

        </div>
    </div>


    <!-- 模态框 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">标题</h4>
                </div>
                <div class="modal-body">
                    <form id="editForm"  class="form-horizontal" >

                        <div class="form-group">
                            <label  class="col-sm-2 control-label">班级名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_name" name="name"  data-bv-trigger="blur"   placeholder="班级名称">
                            </div>
                        </div>

                        <div class="form-group">
                            <label  class="col-sm-2 control-label">所属专业</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="edit_marjorId" name="marjorId">
                                    <#list allMajor as m>
                                        <option value="${m.id}">${m.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label  class="col-sm-2 control-label">开班日期</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_openingDate" name="openingDate"     placeholder="开班日期YYYY-MM-DD">

                            </div>
                        </div>

                        <div class="form-group">
                            <label  class="col-sm-2 control-label">结课日期</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_endDate" name="endDate"     placeholder="结课日期">
                            </div>
                        </div>

                        <div class="form-group">
                            <label  class="col-sm-2 control-label">带课老师</label>
                            <div class="col-sm-10">
                                <select multiple="multiple" id="edit_teacher" name="teacherIds" class="form-control">
                                    <#list allTeacher as t>
                                        <#if t.majorNames=="">
                                            <option value="${t.id}">${t.getName()}</option>
                                        <#else>
                                            <option value="${t.id}">${t.getName()}-${t.majorNames}</option>
                                        </#if>
                                    </#list>

                                </select>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="btnSave">保存</button>
                </div>
            </div>
        </div>
    </div>



</div>


<!-- 全局js -->
<script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>

<!-- bootstrap-table -->
<script src="${ctx }/static/js/plugins/bootstrap-table/bootstrap-table.js?v=1.14.2"></script>
<script src="${ctx }/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js?v=1.14.2"></script>


<!-- Peity -->
<script src="${ctx }/static/js/plugins/peity/jquery.peity.min.js"></script>

<!-- bootstrap-validator验证 -->
<script src="${ctx }/static/js/plugins/validator/js/bootstrapValidator.js"></script>
<script src="${ctx }/static/js/plugins/validator/js/language/zh_CN.js"></script>

<!-- datepicker 日期-->
<script src="${ctx }/static/js/plugins/datapicker/bootstrap-datetimepicker.js"></script>
<script src="${ctx }/static/js/plugins/datapicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<!--multiselect -->
<script src="${ctx }/static/js/plugins/multiselect/bootstrap-multiselect.js"></script>


<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>
<!-- 自定义js -->
<script src="${ctx }/static/js/content.js?v=1.0.0"></script>

<!-- 自定义 classes.js -->
<script src="${ctx }/static/js/admin/classes.js?v=1.0.0"></script>

</body>

</html>

