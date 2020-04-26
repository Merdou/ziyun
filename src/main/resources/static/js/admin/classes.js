var $tb;
//项目名称
var projectName="";
var primaryKey=0;

//1、初始化
$(function () {
    projectName=$("body").attr("data-project");
    $tb=$("#tb");

    //远程加载数据ajax
    loadData();

    //查询操作
    $("#btnSearch").click(function(){
        //刷新
        $tb.bootstrapTable('refresh',{query:{offset:0}});
    });

    // 验证
    formValidator();

    //新增按钮
    $("#btnAdd").click(addForm);

    //修改按钮
    $("#btnEdit").click(editForm);

    //日期初始化
    $('#edit_openingDate').datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose : true,  //选中日期自动关闭
        todayHighlight : true,  //今天的日期高亮显示
        minView:2,
        maxView:2,
        language:"zh-CN"
    }).on('changeDate', function(ev){
       //指定更新 开班日期 验证
        $('#editForm').data('bootstrapValidator')
            .updateStatus('openingDate', 'NOT_VALIDATED',null)
            .validateField('openingDate');
    });

    $('#edit_endDate').datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose : true,
        todayHighlight : true,
        minView:2,
        maxView:2,
        language:"zh-CN"
    });

    //保存
    $("#btnSave").click(saveForm);

    //初始化多选项插件
    $("#edit_teacher").multiselect();

});



function loadData(){
    $tb.bootstrapTable({
        url: projectName+'/admin/classes/data',
        striped:true,    // 隔行变色
        method:'post',   //请求方式 get, post
        contentType:'application/x-www-form-urlencoded',  //post方式发送到服务器的方式
        queryParams:function(params) {
            //自定义查询参数
            params['marjorId'] = $("#marjorId").val();
            params['state']=$("#state").val();

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
            title: '班级编号'
        }, {
            field: 'name',
            title: '班级名称'
        }, {
            field: 'majorName',
            title: '专业名称'
        }, {
            field: 'teacherNames',
            title: '带班老师',
            formatter:teacherFormatter
        }, {
            field: 'openingDate',
            title: '开班时间'
        }, {
            field: 'endDate',
            title: '结课时间'
        }]
    });
}

function teacherFormatter(value,row,index){
    var str=[];
    if(row.teacherArr){
        for(let t of row.teacherArr){
            str.push(t.name);
        }
    }
    return str.join(",");
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
            },openingDate: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    },date: {
                        format: 'YYYY-MM-DD',
                        message: '日期值格式无效YYYY-MM-DD'
                    }
                }
            },
        }
    });
}


//新增
function addForm(){
   //1、主键设定为0
    primaryKey=0;
    //2、清空数据
    resetFormValue("#editForm input");

    //3、重置验证样式
    $('#editForm').data('bootstrapValidator').resetForm(true);

    //还原多选项目
    $('#edit_teacher option:selected').each(function() {
        $(this).prop('selected', false);
    });

    $('#edit_teacher').multiselect('refresh');


    //4、显示模态框
    $("#myModalLabel").text("新增");
    $("#myModal").modal('show');
}

//修改
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
    $("#edit_marjorId").val(data.marjorId);
    $("#edit_openingDate").val(data.openingDate);
    //数据库这一列有可能为空
    $("#edit_endDate").val(data.endDate || '' );

    //还原多选项目
    $('#edit_teacher option:selected').each(function() {
        $(this).prop('selected', false);
    });
    //还原原来的数据
    if(data.teacherArr){
       var str=[];
        for(let t of data.teacherArr){
            str.push(t.id);
        }
        //还原选中的值
        $('#edit_teacher').multiselect('select', str);
    }

    $('#edit_teacher').multiselect('refresh');


    //3、显示 模态框
    $("#myModalLabel").text("编辑");
    $("#myModal").modal('show');

}

//保存
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
            let teacherId=$("#edit_teacher").val();
           if(teacherId){
               ajaxSaveData();
           }else{
               BootstrapDialog.show({
                   type: BootstrapDialog.TYPE_DANGER,
                   title: '错误',
                   message:"请选择带班老师"
               });
           }

        }
    },200);
}

function ajaxSaveData(){
    //1、收集数据
    var dataForm=$("#editForm").serialize();
    dataForm+="&id="+primaryKey;

    //2、ajax提交
    $.ajax({
        type:"POST",
        url: projectName+"/admin/classes/save",
        data: dataForm,
        dataType:"json",
        success:function(resp) {
            //隐藏模态框
            $("#myModal").modal('hide');

            if (resp.code === 0) {
                //成功
                BootstrapDialog.show({
                    title: '提示',
                    message: '操作成功'
                });
                //重新加载数据
                $tb.bootstrapTable('refresh', {query: {offset: 0}});

            } else {
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