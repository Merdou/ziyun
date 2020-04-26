var $tb;
//项目名称
var projectName="";
//主键(0代表新增， 非空代表修改)
var primaryKey=0;

//1、初始化
$(function () {
    projectName=$("body").attr("data-project");
    $tb=$("#tb");

    //远程加载数据ajax
    loadData();

    //表单验证方法
    formValidator();

    //添加按钮
    $("#btnAdd").click(addForm);

    //修改按钮
    $("#btnEdit").click(editForm);

    //保存按钮
    $("#btnSave").click(saveForm);

});


function loadData(){
    $tb.bootstrapTable({
        url: projectName+'/admin/major/data',
        striped:true,    // 隔行变色
        method:'get',   //请求方式 get, post
        contentType:'application/x-www-form-urlencoded',  //post方式发送到服务器的方式
        queryParams:function(params) {
            //自定义查询参数
            return params;
        },
        pagination:true,  //要在底部显示分页条
        sidePagination:'server',
        clickToSelect:true,
        singleSelect:false, //设置True 将禁止多选
        columns: [{
            radio:true
        },{
            field: 'id',
            title: '编号'
        }, {
            field: 'name',
            title: '专业名称'
        }, {
            field: 'state',
            title: '状态',
            formatter:stateFormatter
        }]
    });
}

//格式化单元格内容，
//function(value, row, index),
// value：该cell本来的值，
// row：该行数据，
// index：该行序号（从0开始）`
function stateFormatter(value, row, index){
   return value == 1 ? "<span class=\"label label-primary\">启用</span>" :  "<span class=\"label label-danger\">禁用</span>";
}

//表单验证
function formValidator(){

    $('#editForm').bootstrapValidator({
        message: 'This value is not valid',
        excluded: [':disabled'],  //在bootstrapValidator.js 第1779行有配置说明
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    }
                }
            }
        }
    });
}


function addForm(){

    primaryKey=0;

   //1、清空表单中的数据
    resetFormValue("#editForm input");

    //2、重置验证框架
    $('#editForm').data('bootstrapValidator').resetForm(true);

    //3、显示 模态框
    $("#myModalLabel").text("新增");
    $("#myModal").modal('show');
}


function editForm(){
    //判断是否选中了值
    var rows=$tb.bootstrapTable('getSelections');

    console.log(rows);

    if(rows.length!=1){
        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_WARNING,
            title: '警告',
            message: '请选择要修改的值!'
        });
        return;
    }

    //1、清空表单中的数据
    resetFormValue("#editForm input");
    //2、重置验证框架
    $('#editForm').data('bootstrapValidator').resetForm(true);

    //3、重新还原数据
    var data=rows[0];
    //主键
    primaryKey=data.id;
    $("#edit_name").val(data.name);
    $("#edit_state").val(data.state);

    //3、显示 模态框
    $("#myModalLabel").text("编辑");
    $("#myModal").modal('show');
}

//保存验证
function saveForm(){
    //验证表单是否通过 bootstrapValidator, 手动触发验证事件
    var bsv = $('#editForm').data('bootstrapValidator');
    //手动调用
    bsv.validate();

    //有时候有ajax验证，就是异步，会有延时，延迟200毫秒调用是否通过setTimeout(函数, 延迟时间);
   setTimeout(function(){
       //验证数据合性
       var result=bsv.isValid();
       if(result){
           ajaxSaveData();
       }
   },200);
}

//保存数据-ajax
function ajaxSaveData(){
    //1、收集数据
    var formData=$("#editForm").serialize();
    formData+="&id="+primaryKey;
    console.log(formData);

    //2、jquery中的ajax语法往后台传数据
    $.ajax({
        type:"POST",
        url: projectName+"/admin/major/save",
        data:formData,
        dataType:"json",
        success:function(resp){
            //隐藏模态框
            $("#myModal").modal('hide');

            if(resp.code===0){
                //成功
                BootstrapDialog.show({
                    title: '提示',
                    message: '操作成功'
                });
                //重新加载数据
                $tb.bootstrapTable('refresh',{query:{offset:0}});

            }else{
                //失败
                BootstrapDialog.show({
                    type: BootstrapDialog.TYPE_DANGER,
                    title: '错误',
                    message: resp.message
                });
            }
        }
    });


}