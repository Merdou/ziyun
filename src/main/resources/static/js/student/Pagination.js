/**
 * 点击页码转跳
 * @param i
 */
function gotoPage(i){
    //显示第几页
    $("#pageNum").val(i);
    $("#searchForm").submit();
}

/**
 * 点击确定到指定页数
 */
function jumpTo(){
    //总页数
    let pages=$("#totalPages").text();
    //用户输入的页码
    let pageNum=$("#pageNum").val();
    //判断是否是整数 正则表达式
     var reg=/^[1-9]\d*$/;
     if(!reg.test(pageNum)){
         artAlert("警告","输入页码必须是整数!");
         return;
     }
     //把字符串转换成数字
     let intPageNum=parseInt(pageNum);
     let intPages=parseInt(pages);
     //用户输入的页码 > 总页数 ，把页码还原到最大页数
     if(intPageNum>intPages){
         $("#pageNum").val(intPages);
     }
    //模拟提交
     $("#searchForm").submit();
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