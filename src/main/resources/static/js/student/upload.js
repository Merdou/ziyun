var projectName='';

$(function(){

    projectName=$("body").attr("data-project");

    //上传图片
    var $tgaUpload = $('#goodsUpload').diyUpload({
        url: projectName+ '/common/fileupload',
        success:function( file,data ) {

            if(data.error==0){
                var $fileBox = $('#fileBox_'+file.id);
                var inputText=$fileBox.find("input");
                //自定义后台上传的名称
                //inputText.attr("name","cover");
                inputText.val(data.url);
            } else{
                alert(data.message);
            }
        },
        error:function( err ) {
            console.log(err);
        },
        buttonText : '选择图片',
        fieldName:"cover",  //数据库列名
        accept: {
            title: "Images",
            extensions: 'gif,jpg,jpeg,bmp,png'
        },
        //只能上传1个
        fileNumLimit:1,
        thumb:{
            width:120,
            height:90,
            quality:100,
            allowMagnify:true,
            crop:true,
            type:"image/jpeg"
        },
        auto:true
    });

    //多文件上传
    var $mutilUpload = $('#mutilUpload').diyUpload({
        url:projectName+ '/common/fileupload',
        success:function( file,data ) {
            if(data.error==0){
                var $fileBox = $('#fileBox_'+file.id);
                var inputText=$fileBox.find("input");
                inputText.val(data.url);
                //artAlert("成功",data.url).showModal();
            } else{
                artAlert("警告",data.message).showModal();
            }
        },
        error:function( err ) {
            console.log(err);
        },
        finished:function(){
            console.log("---------全部上传完成------");
            console.log($mutilUpload.getStats());

            //3、验证表单是否填了
            var result=$("#editForm").valid();
            if(!result){
                return;
            }

            //验证数据是否正确 1、封面图是否有了
            var num1=$tgaUpload.getStats().successNum-$tgaUpload.getStats().cancelNum;
            if(num1==0){
                //提示必须要选择封面
                artAlert("警告","必须上传一张封面图").showModal();
                return ;
            }

            //2验证多图必须要有1张
            var num2=$mutilUpload.getStats().successNum-$mutilUpload.getStats().cancelNum;
            if(num2==0){
                //提示必须要上传一张图
                artAlert("警告","作品图片至少要上传一张!").showModal();
                return ;
            }

            //提交表单
            $("#editForm").submit();

        },
        buttonText : '',
        accept: {
            title: "Images",
            extensions: 'gif,jpg,jpeg,bmp,png'
        },
        //只能上传1个
        fileNumLimit:100,
        // 总文件大小(单位字节10MB)
        fileSizeLimit:1024*1024*10,
        //单个文件大小(单位字节 1MB)
        fileSingleSizeLimit:1024*1024*1,
        thumb:{
            width:120,
            height:90,
            quality:100,
            allowMagnify:true,
            crop:true,
            type:"image/jpeg"
        },
        auto:false,   //是否自动上传
        fieldName:"attachment",  //数据库列名
    });




    $("#btnSave").click(function(){
        //多文件上传
        $mutilUpload.upload();

    });

    //验证表单
    validatorForm();

});

//表单验证初始化
function validatorForm(){
    $("#editForm").validate({
        rules: {
            title: {
                required: true
            }, description: {
                required: true
            }
        }
    });
}




function artAlert(title,message,okCallBack,cacneCallBack){
    if(!okCallBack){
        okCallBack=function(){};
    }

    var d = dialog({
        title: title,
        content:message,
        okValue: '确定',
        ok: okCallBack,
        cancelValue: '取消',
        cancel: cacneCallBack
    });
    return d;
}





