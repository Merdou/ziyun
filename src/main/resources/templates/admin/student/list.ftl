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

    <!--multiselect 多选 -->
    <link href="${ctx }/static/css/plugins/multiselect/bootstrap-multiselect.css" rel="stylesheet">

    <!-- kindeditor  -->
    <link href="${ctx }/static/js/plugins/kindeditor/themes/default/default.css" rel="stylesheet">

    <!-- bootstrap-dialog 模态框框 -->
    <link href="${ctx }/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">

    <link href="${ctx }/static/css/style.css?v=4.1.0" rel="stylesheet">

    <style>
        #editForm   .form-group{
            margin-right: -5px;
            margin-left: -5px;
        }
    </style>
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
                            <select name="majorId" id="majorId" class="form-control">
                                <option value="0">所有专业</option>

                                <#list arrMajor as m>
                                    <option value="${m.id}">${m.name}</option>
                                </#list>

                            </select>
                        </div>

                        <div class="form-group">
                            <label>班级</label>
                            <select name="classesId" id="classesId" class="form-control">
                                <option value="0">全部</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" name="name" id="name" class="form-control" placeholder="支持模糊查询">
                        </div>

                        <div class="form-group">
                            <label>学历</label>
                            <select name="education" id="education" class="form-control">
                                <option value="0">全部</option>

                                <#list arrEdu as m>
                                    <option value="${m.id}">${m.name}</option>
                                </#list>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>状态</label>
                            <select name="state" id="state" class="form-control">
                                <option value="0">全部</option>
                                <option value="1">正常</option>
                                <option value="2">休学</option>
                                <option value="3">退学</option>
                            </select>
                        </div>


                        <button type="button" class="btn btn-info" id="btnSearch">查询</button>

                        <button type="button" class="btn btn-primary" id="btnAdd">新增</button>
                        <button type="button" class="btn btn-warning" id="btnEdit">编辑</button>

                        <button type="button" class="btn btn-success" id="btnDetail">查看详情</button>

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
        <div class="modal-dialog modal-lg" role="document">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">标题</h4>
                </div>
                <div class="modal-body">
                    <form id="editForm"  class="form-horizontal" >

                        <table class="table table-bordered">
                            <tr>
                                <td width="10%" align="center">姓名</td>
                                <td width="23%">

                                    <div class="form-group">
                                        <input type="text" class="form-control" id="edit_name" name="name">
                                    </div>

                                </td>
                                <td width="10%" align="center">性别</td>
                                <td width="23%">
                                    <div class="form-group">
                                        <select name="sex" id="edit_sex" class="form-control">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </div>
                                </td>
                                <td colspan="2" rowspan="2">
                                    <img src="${ctx}/static/img/noimg.png" height="150" id="imgHead">
                                    <input type="text" id="edit_portrait" name="portrait" >
                                    <p></p>
                                    <input type="button" class="btn btn-primary" value="选择头像" id="btnImg">

                                </td>
                            </tr>
                            <tr>
                                <td align="center">专业</td>
                                <td>
                                    <div class="form-group">
                                        <select name="majorId" id="edit_majorId" class="form-control">
                                            <option value="0">所有专业</option>
                                            <#list arrMajor as m>
                                                <option value="${m.id}">${m.name}</option>
                                            </#list>
                                        </select>
                                    </div>
                                </td>
                                <td align="center">班级</td>
                                <td>
                                    <div class="form-group">
                                       <select id="edit_classesId" name="classesId" class="form-control">
                                       </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">手机</td>
                                <td>
                                    <div class="form-group">
                                        <input type="text" name="mobile" id="edit_mobile" class="form-control">
                                    </div>
                                </td>
                                <td align="center">入学日期</td>
                                <td>
                                    <div class="form-group">
                                        <input type="text" name="joinDate" id="edit_joinDate" class="form-control">
                                    </div>
                                </td>
                                <td width="10%" align="center">生日</td>
                                <td >
                                    <div class="form-group">
                                        <input type="text" name="birthday" id="edit_birthday" class="form-control">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">文化程度</td>
                                <td>
                                    <div class="form-group">
                                        <select id="edit_education" name="education" class="form-control">
                                            <#list arrEdu as m>
                                                <option value="${m.id}">${m.name}</option>
                                            </#list>
                                        </select>
                                    </div>
                                </td>
                                <td align="center">毕业院校</td>
                                <td>
                                    <div class="form-group">
                                        <input type="text" name="schoolName" id="edit_schoolName" class="form-control">
                                    </div>
                                </td>
                                <td align="center">所学专业</td>
                                <td>
                                    <div class="form-group">
                                        <input type="text" name="collegeMajor" id="edit_collegeMajor" class="form-control">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">身份证号</td>
                                <td>
                                    <div class="form-group">
                                        <input type="text" name="idCard" id="edit_idCard" class="form-control">
                                    </div>
                                </td>
                                <td align="center">QQ</td>
                                <td>
                                    <div class="form-group">
                                        <input type="text" name="qq" id="edit_qq" class="form-control">
                                    </div>
                                </td>
                                <td align="center">状态</td>
                                <td>
                                    <div class="form-group">
                                        <select name="state" id="edit_state" class="form-control">
                                            <option value="0">全部</option>
                                            <option value="1">正常</option>
                                            <option value="2">休学</option>
                                            <option value="3">退学</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">家庭地址</td>
                                <td colspan="5">
                                    <div class="form-group">
                                        <input type="text" name="homeAddress" id="edit_homeAddress" class="form-control">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">现住地址</td>
                                <td colspan="5">
                                    <div class="form-group">
                                        <input type="text" name="currentAddress" id="edit_currentAddress" class="form-control">
                                    </div>
                                </td>
                            </tr>
                        </table>


                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="btnSave">保存</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 详情详情 模态框 -->
    <div class="modal fade" id="myModalDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" >详情</h4>
                </div>
                <div class="modal-body">
                    <form   class="form-horizontal" >

                        <table class="table table-bordered">
                            <tr>
                                <td width="10%" align="center">姓名</td>
                                <td width="23%">
                                     <h4 id="detail_name"></h4>
                                </td>
                                <td width="10%" align="center">性别</td>
                                <td width="23%">
                                    <h4 id="detail_sex"></h4>
                                </td>
                                <td colspan="2" rowspan="3">
                                    <img src="${ctx}/static/img/noimg.png" height="150" id="imgHeadDetail">
                                </td>
                            </tr>
                            <tr>
                                <td align="center">专业</td>
                                <td>
                                     <h4 id="detail_majorId"></h4>
                                </td>
                                <td align="center">班级</td>
                                <td>
                                    <h4 id="detail_classesId"></h4>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">开班日期</td>
                                <td>
                                    <h4 id="detail_openingDate"></h4>
                                </td>
                                <td align="center">结课日期</td>
                                <td>
                                    <h4 id="detail_endDate"></h4>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">手机</td>
                                <td>
                                    <h4 id="detail_mobile"></h4>
                                </td>
                                <td align="center">入学日期</td>
                                <td>
                                    <h4 id="detail_joinDate"></h4>
                                </td>
                                <td width="10%" align="center">生日</td>
                                <td >
                                    <h4 id="detail_birthday"></h4>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">文化程度</td>
                                <td>
                                    <h4 id="detail_education"></h4>
                                </td>
                                <td align="center">毕业院校</td>
                                <td>
                                    <h4 id="detail_schoolName"></h4>
                                </td>
                                <td align="center">所学专业</td>
                                <td>
                                    <h4 id="detail_collegeMajor"></h4>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">身份证号</td>
                                <td>
                                    <h4 id="detail_idCard"></h4>
                                </td>
                                <td align="center">QQ</td>
                                <td>
                                    <h4 id="detail_qq"></h4>
                                </td>
                                <td align="center">状态</td>
                                <td>
                                    <h4 id="detail_state"></h4>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">家庭地址</td>
                                <td colspan="5">
                                    <h4 id="detail_homeAddress"></h4>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">现住地址</td>
                                <td colspan="5">
                                    <h4 id="detail_currentAddress"></h4>
                                </td>
                            </tr>
                        </table>


                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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

<!-- multiselect 多选-->
<script src="${ctx }/static/js/plugins/multiselect/bootstrap-multiselect.js"></script>

<!-- kindeditor-->
<script src="${ctx }/static/js/plugins/kindeditor/kindeditor-all-min.js"></script>
<script src="${ctx }/static/js/plugins/kindeditor/lang/zh-CN.js"></script>


<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>
<!-- 自定义js -->
<script src="${ctx }/static/js/content.js?v=1.0.0"></script>

<!-- 自定义 student.js -->
<script src="${ctx }/static/js/admin/student.js?v=1.0.0"></script>

</body>

</html>

