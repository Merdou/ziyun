var projectName;

$(function(){

    projectName=$("body").attr("data-project");


    //点击返回按钮 (历史 记录 后退一步)
    $("#goReturn").click(function(e){
        //阻止a标签默认跳转事件
        e.preventDefault();
        //历史记录后退一步
        history.go(-1);
    });

    //点击作业提交按钮
    $("#btnSave").click(saveForm);


    $(".tp li img").click(function(){

        var url=$(this).attr("src");
        var msg=`<img src='${url}'>`;
        artAlert("查看图片",msg).showModal();
    });

    //星级打分
    scoreFun($("#starttwo"),{
        fen_d:22,//每一个a的宽度
        ScoreGrade:5//a的个数5
    });


    //印象
    $(".order-evaluation-check").click(function(){
        //总印像不要超过3个
        if($(this).hasClass('checked')){
            //当前为选中状态，需要取消
            $(this).removeClass('checked');
        }else{
            //当前未选中，需要增加选中，判断选中的个数不能超过3个
             var count=$(".ck1 .checked").length;
             if(count>=3){
                 artAlert("警告","<h4>标签评价最多只能选3个</h4>" ).showModal();
                 return;
             }
            $(this).addClass('checked');
        }

        //保存你经选中的标签
        var ckTags=$(".ck1 .checked");
        var tagArr=[];
        for(let i=0;i<ckTags.length;i++){
            let tag=$(ckTags[i]).find("b");
            tagArr.push( tag.text());
        }
        $("#tags").val(tagArr.join(",") );
    });

});

//评价字数限制
function words_deal()
{
    var curLength=$("#comments").val().length;
    if(curLength>140)
    {
        var num=$("#comments").val().substr(0,140);
        $("#comments").val(num);
        artAlert("超过字数限制，多出的字将被截断！" ).showModal();
    }
    else
    {
        $("#textCount").text(140-$("#comments").val().length);
    }
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


//保存
function saveForm(){
    if(checkInfo()){
        //ajax提交数据
        var dataForm=$("#editForm").serialize();
        $.ajax({
            type:"post",
            url:projectName+"/teacher/dianping",
            data: dataForm,
            dataType:"json",
            success:function(resp){
                if(resp.code===0){
                    //跳转到这个班的未批改作业
                    let classesId=$("#classesId").val();
                    window.location.href=projectName+"/teacher/work/notchecked?classesId="+classesId;
                }else{
                    artAlert("错误","点评作业失败!").showModal();
                }
            }
        });
    }
}

//检查数据是否填完
function checkInfo(){
    let regInt=/^([1-9][0-9]{0,1}|100|0)$/
    let score=$("#score").val();
    if(!regInt.test(score)){
        artAlert("警告","云值的分数必须是0-100的整数!").showModal();
        return false;
    }

    let rating=$("#rating").val();
    if(!rating){
        artAlert("警告","必须要选择一个星级评价!").showModal();
        return false;
    }

    let tags=$("#tags").val();
    if(!rating){
        artAlert("警告","至少要选择一个标签评价!").showModal();
        return false;
    }

    let comments=$("#comments").val();
    if(!comments){
        artAlert("警告","必须要填写评语点评!").showModal();
        return false;
    }
    return true;
}