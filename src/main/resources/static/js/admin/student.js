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

    //查询操作
    $("#btnSearch").click(function(){
        //刷新
        $tb.bootstrapTable('refresh',{query:{offset:0}});
    });

    //专业变化监听
    $("#majorId").change(loadClasses);
    $("#edit_majorId").change(loadNotCloseClasses);

    formValidator();

    //日期初始化
    $('#edit_joinDate').datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose : true,  //选中日期自动关闭
        todayHighlight : true,  //今天的日期高亮显示
        minView:2,
        maxView:2,
        language:"zh-CN"
    }).on('changeDate', function(ev){
        //指定更新  日期 验证
        $('#editForm').data('bootstrapValidator')
            .updateStatus('joinDate', 'NOT_VALIDATED',null)
            .validateField('joinDate');
    });

    //日期初始化
    $('#edit_birthday').datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose : true,  //选中日期自动关闭
        todayHighlight : true,  //今天的日期高亮显示
        minView:2,
        maxView:2,
        language:"zh-CN"
    }).on('changeDate', function(ev){
        //指定更新 开班日期 验证
        $('#editForm').data('bootstrapValidator')
            .updateStatus('birthday', 'NOT_VALIDATED',null)
            .validateField('birthday');
    });


    //添加按钮
    $("#btnAdd").click(addForm);

    //修改按钮
    $("#btnEdit").click(editForm);

    //详情按钮
    $("#btnDetail").click(detailForm);

    //初始化文件上传按钮
    initImageUpload();

    //保存按钮
    $("#btnSave").click(saveForm);

});


function loadData(){
    $tb.bootstrapTable({
        url: projectName+'/admin/student/data',
        striped:true,    // 隔行变色
        method:'post',   //请求方式 get, post
        contentType:'application/x-www-form-urlencoded',  //post方式发送到服务器的方式
        queryParams:function(params) {
            //自定义查询参数
            params["majorId"]=$("#majorId").val();
            params["classesId"]=$("#classesId").val();
            params["name"]=$("#name").val();
            params["education"]=$("#education").val();
            params["state"]=$("#state").val();

            return params;
        },
        pagination:true,  //要在底部显示分页条
        sidePagination:'server',
        clickToSelect:true,
        singleSelect:false, //设置True 将禁止多选
        columns: [{
            radio:true
        },{
            field: 'majorName',
            title: '专业'
        },{
            field: 'classesName',
            title: '班级'
        },{
            field: 'openingDate',
            title: '开班日期'
        },{
            field: 'endDate',
            title: '结果日期'
        },{
            field: 'name',
            title: '姓名'
        }, {
            field: 'sex',
            title: '性别'
        }, {
            field: 'joinDate',
            title: '入学时间'
        }, {
            field: 'birthday',
            title: '生日'
        }, {
            field: 'eduName',
            title: '学历'
        }, {
            field: 'mobile',
            title: '联系电话'
        }, {
            field: 'state',
            title: '状态',
            formatter:stateFormatter
        }]
    });
}

//格式化单元格内容， 1正常   2：休学    3：退学
//function(value, row, index),
// value：该cell本来的值，
// row：该行数据，
// index：该行序号（从0开始）`
function stateFormatter(value, row, index){
    var str='';
    if(value==1){
        str="<span class=\"label label-primary\">正常</span>";
    }else if(value==2){
        str="<span class=\"label label-success\">休学</span>";
    }else{
        str= "<span class=\"label label-danger\">退学</span>";
    }
   return str;
}

//根据专业加载班级信息
function loadClasses(){
   //得到当前选中的专业
    var mid=$(this).val();
    //清空之前的班级选项(保留全部)
    $("#classesId option:gt(0)").remove();

    if(mid=="0"){
        return;
    }

    //ajax查询信息
    $.ajax({
        type:"get",
        url: projectName+"/admin/classes/major/"+mid,
        dataType:"json",
        success:function(resp){

            if(resp.code===0){
                var arr=resp.data;
                //得到班级id
                var clsObj=$("#classesId");

                for(let i=0;i<arr.length; i++){
                    let m=arr[i];
                    let temp=`<option value="${m.id}">${m.name}</option>`;
                    clsObj.append(temp);
                }
            }
        }
    });

}

//根据专业加载未结课班级信息
function loadNotCloseClasses(){
    //得到当前选中的专业
    var mid=$(this).val();
    //清空之前的班级选项
    $("#edit_classesId option").remove();
    if(mid=="0"){
        return;
    }
    //ajax查询信息
    $.ajax({
        type:"get",
        url: projectName+"/admin/classes/notclose/major/"+mid,
        dataType:"json",
        success:function(resp){

            if(resp.code===0){
                var arr=resp.data;
                //得到班级id
                var clsObj=$("#edit_classesId");

                for(let i=0;i<arr.length; i++){
                    let m=arr[i];
                    let temp=`<option value="${m.id}">${m.name}</option>`;
                    clsObj.append(temp);
                }
            }
        }
    });

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
            },mobile: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    },phone: {
                        country:'CN',
                        message:'请输入正确的手机号'
                    }
                }
            },joinDate: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    }
                }
            },birthday: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    }
                }
            },idCard: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    },
                    stringLength:{
                        min:18,
                        max:18,
                        message: '身份证号码必须是18位'
                    }
                }
            },homeAddress: {
                message: '值无效',
                validators: {
                    notEmpty: {
                        message: '必须输入值'
                    }
                }
            },currentAddress: {
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

    //如果已结课班级，不允许编辑
    if(rows[0].endDate){
        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_WARNING,
            title: '警告',
            message: '已毕业学员不允许修改!'
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
    $("#edit_sex").val(data.sex);

    //专业改变 不会去触发change事件
    $("#edit_majorId").val(data.majorId);
    //动调触发 change事件
    $("#edit_majorId").trigger("change");

    //延迟时1秒再去填充班级  edit_classesId
    setTimeout(function(){
        $("#edit_classesId").val(data.classesId);
    },500);

    $("#edit_mobile").val(data.mobile);
    $("#edit_joinDate").val(data.joinDate);
    $("#edit_birthday").val(data.birthday);
    $("#edit_education").val(data.education);
    $("#edit_schoolName").val(data.schoolName);
    $("#edit_collegeMajor").val(data.collegeMajor);

    //还原头像
    if(data.portrait){
        $("#edit_portrait").val(data.portrait);
        $("#imgHead").attr("src", projectName+data.portrait);
    }else{
        $("#edit_portrait").val('');
        $("#imgHead").attr("src", projectName+'/static/img/noimg.png');
    }

    $("#edit_idCard").val(data.idCard);
    $("#edit_qq").val(data.qq);
    $("#edit_state").val(data.state);
    $("#edit_homeAddress").val(data.homeAddress);
    $("#edit_currentAddress").val(data.currentAddress);

    //3、显示 模态框
    $("#myModalLabel").text("编辑");
    $("#myModal").modal('show');
}

//显示详情
function detailForm(){
    //判断是否选中了值
    var rows=$tb.bootstrapTable('getSelections');
    if(rows.length!=1){
        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_WARNING,
            title: '警告',
            message: '请选择要查看详情的数据!'
        });
        return;
    }
    //2、重新还原数据
    var data=rows[0];
    $("#detail_name").html(data.name);
    $("#detail_sex").html(data.sex);
    $("#detail_majorId").html(data.majorName);
    $("#detail_classesId").html(data.classesName);
    $("#detail_openingDate").html(data.openingDate);
    $("#detail_endDate").html(data.endDate || '');

    $("#detail_mobile").html(data.mobile);
    $("#detail_joinDate").html(data.joinDate);
    $("#detail_birthday").html(data.birthday);
    $("#detail_education").html(data.eduName);
    $("#detail_schoolName").html(data.schoolName || '');
    $("#detail_collegeMajor").html(data.collegeMajor  || '');

    $("#detail_idCard").html(data.idCard);
    $("#detail_qq").html(data.qq  || '');

    //头像imgHeadDetail
    if(data.portrait){
        $("#imgHeadDetail").attr("src", projectName+data.portrait);
    }else{
        $("#imgHeadDetail").attr("src", projectName+'/static/img/noimg.png');
    }

    if(data.state==1){
        $("#detail_state").html("正常");
    }else if(data.state==2){
        $("#detail_state").html("休学");
    }else{
        $("#detail_state").html("退学");
    }

    $("#detail_homeAddress").html(data.homeAddress);
    $("#detail_currentAddress").html(data.currentAddress);

  //显示
    $("#myModalDetail").modal({
        backdrop:'static',
        show:true
    });

}

function initImageUpload(){

    KindEditor.ready(function(K) {
        //配置
        var editor = K.editor({
            allowFileManager : false,
            uploadJson : projectName+'/common/upload',  // 文件上传路径
        });

        //注册按钮
        K('#btnImg').click(function() {
            editor.loadPlugin('image', function() {
                editor.plugin.imageDialog({
                    showRemote : false,
                    imageUrl : K('#edit_portrait').val(),
                    clickFn : function(url, title, width, height, border, align) {
                        K('#edit_portrait').val(url);
                        //显示图像
                        $("#imgHead").attr("src",url);
                        editor.hideDialog();
                    }
                });
            });
        });


    });

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
        url: projectName+"/admin/student/save",
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