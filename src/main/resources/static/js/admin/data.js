var $tb;
var projectName;  //项目名
var typeId;      //选中的类型
var primaryKey=0;  //当前选中的主键是谁，0代表新增

$(function () {
    //加载表格
    $tb = $("#tb");
    projectName = $("body").attr("data-project");

    loadTreeView();

    //按钮事件
    initButtonHandler();

    //数据验证规则
    initValidator();


});

//数据验证规则
function initValidator(){

    $('#editForm').bootstrapValidator({
        // 默认的提示消息
        message: '这个值无效',
        // 表单框里右侧的icon
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',             //验证通过了
            invalid: 'glyphicon glyphicon-remove',       //验证不通过
            validating: 'glyphicon glyphicon-refresh'    // 正在验证中
        },
        excluded: [':disabled'],
        //验证规则
        fields: {
            name:{
                message: '数据验证失败',
                validators:{
                    notEmpty: {
                        message: '值不能为空'
                    }, remote: { // ajax校验，获得一个json数据（{'valid': true or false}）
                        url: projectName+'/admin/data/exist',       //验证地址
                        message: '值已经存在，请重新输入!',   //提示信息
                        type: 'POST',          //请求方式
                        data: function(validator){  //自定义提交数据，默认为当前input name值
                            return {
                                id: primaryKey,
                                typeId:typeId,
                                name: $("#edit_name").val()
                            };
                        }
                    }
                }  //validators
            } // 验证结束
        }
    });

}


/**
 * 加载左边菜单
 */
function loadTreeView() {
    //一会从后台读过来
    $.ajax({
        type: "GET",
        url: projectName + "/admin/data/tree",
        dataType: "json",  //服务返回的结果，通知jquery强制转换为json对象
        success: function (resp) {
            if (resp.code === 0) {
                //加载数据
                $('#dictTree').treeview({
                    color: "#428bca",
                    data: resp.data
                });
            }
        }
    });

    //点击事件
    $('#dictTree').on('nodeSelected', function (event, data) {
        typeId = data.typeId;

        $("#typeName").html(data.text + "列表信息");
        //加载右边的树形节点

        //显示面板
        $("#panelEdit").show();
        //先销毁表格
        $tb.bootstrapTable("destroy");
        loadTable(typeId);
    });

}

/**
 * 根据类型加载相应数据
 * @param typeId
 */
function loadTable(typeId) {

    $tb.bootstrapTable({
        striped: true,   //隐藏变色
        method: "POST",  //请求后台的数据的方式
        url: projectName + "/admin/data/data",         //后台的服务器地址
        sidePagination: "server",              //从后台服务器读取
        contentType: "application/x-www-form-urlencoded",        //提交数据查询参数的格式
        queryParamsType: "limit", //分页查询类型limit(limit[取几笔]，offset[排除前几笔]， sort[排序列]，order[排序的方式 asc/desc]),
                                  //分页查询类型"page(pageSize[每页要显示几笔]，pageNumber[查询第几页]，  sortName[排序列]，sortOrder[排序的方式 asc/desc])
        queryParams: function (params) {   //自定义查询参数
            params["types"] = typeId;
            return params;
        },

        pagination: true,         //要显示分页条
        pageNumber: 1,            //当前第1页
        pageSize: 5,              //每页显示几笔
        pageList: [2, 5, 10, 25, 50, 100],   //每页显示的笔数列表
        clickToSelect: true,               //点击选中一行
        singleSelect: false,               //只能true单选,false多选
        columns: [
            {radio: true},    //checkbox显示多选框,radio显示单选框
            {field: "id", title: "编号"},
            {field: "name", title: "名称"}
        ]
    });


}


//按钮事件
function initButtonHandler(){

    //新增
    $("#btnAdd").click(addForm);

    $("#btnEdit").click(editForm);

    $("#btnDelete").click(deleteForm);

    //保存
    $("#btnSave").click(saveForm);
}


//新增处理事件
function addForm(){
    $("#modelTitle").text("新增");

    primaryKey=0;   //主键一定为0

    //重置表单   content.js中的方法
    resetFormValue("#editForm input");

    //显示模态框
    $('#editDialog').modal('show');
}

//修改处理事件
function editForm(){
    $("#modelTitle").text("修改");
    //如果在table一笔都没有选中，提示一下
    var arr=$tb.bootstrapTable('getSelections');
    if(arr.length===0){

        BootstrapDialog.alert({
            title: '警告',
            message: '请选择要修改的记录!',
            type: BootstrapDialog.TYPE_WARNING,
            draggable: true, // 可拖拽
        });

        return;
    }

    //重置表单   content.js中的方法
    resetFormValue("#editForm input");

    //还原值
    var obj=arr[0];
    primaryKey=obj.id; //主键
    console.log(obj);

    $("#edit_name").val(obj.name);


    $('#editDialog').modal('show');
}

//删除处理事件
function deleteForm(){
    var arr=$tb.bootstrapTable('getSelections');
    if(arr.length===0){
        BootstrapDialog.alert({
            title: '警告',
            message: '请选择要删除的记录!',
            type: BootstrapDialog.TYPE_WARNING,
            draggable: true, // 可拖拽
        });
        return;
    }

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
             ajaxSaveData();
        }
    },200);
}

function ajaxSaveData(){
    var formData=$("#editForm").serialize();
    //这个还要拼一个s
    formData+="&types="+typeId;

    //修改
    if(primaryKey!=0){
        formData+="&id="+primaryKey;
    }

    $.ajax({
        type:"post",
        url:projectName+"/admin/data/save",
        data:formData,
        dataType:"json",
        success:function(resp){
            //隐藏模态框
            $('#editDialog').modal('hide');

            if(resp.code===0){
                //刷新表格
                $tb.bootstrapTable("refresh");
            }else{

                BootstrapDialog.alert({
                    title: '操作失败',
                    message: resp.message,
                    type: BootstrapDialog.TYPE_WARNING
                });

            }
        }
    });
}


