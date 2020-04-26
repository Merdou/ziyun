<#-- 参数说明：nav自定义宏名称，
              pageInfo返回的分页插件地象，
              callFunName回调方法名（需在js中自己定义）
              jumpToCallback 自已定义js-->
<#macro nav pageInfo  callFunName jumpToCallback>
<div class="pagediv clearfix">

    <#if pageInfo.pages==0 >
         <h4>查询不到记录</h4>
    <#else>

        <#if pageInfo.hasPreviousPage >
            <a href="javascript:${callFunName+'('+1+')'};" class="prebtn">首页</a>
            <a  href="javascript:${callFunName+'('+pageInfo.prePage+')'};" class="prebtn">上一页</a>
        </#if>

        <#list pageInfo.navigatepageNums as i>
            <#if i==pageInfo.pageNum>
                <span class="current">${i}</span>
            <#else >
                <a href="javascript:${callFunName+'('+i+')'};" class="zxfPagenum">${i}</a>
            </#if>
        </#list>

        <#if pageInfo.hasNextPage >
            <a href="javascript:${callFunName+'('+pageInfo.nextPage+')'};" class="nextbtn">下一页</a>
            <a href="javascript:${callFunName+'('+pageInfo.pages+')'};" class="prebtn">尾页</a>
        </#if>

        <span>共<b id="totalPages">${pageInfo.pages}</b>页</span>

        <#if pageInfo.pages  &gt; 1 >
            <span>，到第<input id="pageNum" name="pageNum" type="text" class="zxfinput" value="${pageInfo.pageNum}">页</span>
            <span class="zxfokbtn" onclick="${jumpToCallback+'()'}">确定</span>
        </#if>

    </#if>


</div>




</#macro>