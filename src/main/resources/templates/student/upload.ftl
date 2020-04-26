<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>上传作业</title>

		<link href="${ctx}/static/css/animate.css" rel="stylesheet">

		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/student/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/static/js/plugins/webuploader/webuploader.css"/>

        <link rel="stylesheet" type="text/css" href="${ctx}/static/js/plugins/artDialog/css/dialog.css"/>

        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/student/uploadhw.css"/>
		
	</head>

	<body data-project="${ctx}">

		 <#include  "common/header.ftl">
		
		 <#include "common/info.ftl" >

		 
		 <!-- 中间上传作业 -->
		 <div class="uploadSection">
			 <div class="wrap dpz">
				 <div class="title"  >上传作业</div>
				 
				 <div class="dp">
                     <form method="post" action="${ctx}/student/upload" id="editForm">
					 <!--左边 -->
					<div class="zy">
						  <h4 >上传封面</h4>
						  
						  <div class="page-header">
							 <ul class="upload-ul clearfix sigle"  >
							 	<li class="upload-pick"> 
								   <div class="webuploader-container clearfix" id="goodsUpload"></div>
							 	</li>
							 </ul>							 
						  </div>
					</div>
					
					<!-- 右边 --> 
					 <div class="pf">

							 <!--作品信息 -->
							 <div class="zpxx">
								<h5>作品信息</h5>
								 <div class="mc form-group">
									 <input type="text" id="title" maxlength="50" name="title" placeholder="请输入作品名称"/>
								     <span>限50字以下</span>
								 </div>
                                 <div class="mc form-group">
                                     <select   id="teacherId"  name="teacherId" >
										 <#list arrTeacher as t>
										     <option value="${t.id}">${t.name}</option>
										 </#list>
									 </select>
                                     <span>请选择作业批改的老师</span>
                                 </div>
								 <div class="sm form-group">
									<textarea class="description" id="description" name="description" maxlength="200"  placeholder="请输入作品说明"></textarea>
									 <span>限200字以下</span>
								 </div>							 
							 </div>
							 
							 <!--作品类别 -->
							 <div class="zpxx zplb">
								<h5>作品类别</h5>  
								 
								 <div class="bq1">
									<span>标签：</span>
									 <#list arrLabels as m>
									     <#if m_index==0>
                                             <input type="radio" value="${m.id}"  name="label" class="zpin" checked="checked"/>
										 <#else >
                                             <input type="radio" value="${m.id}"  name="label" class="zpin" />
									     </#if>

									     <label >${m.name}</label>

									 </#list>
								 </div>

								 <div class="nr">
									<span>内容：</span>
									 <#list arrContents as m>
									   <#if m_index==0>
                                           <input type="radio" value="${m.id}" name="content" checked />
									   <#else>
                                           <input type="radio" value="${m.id}"  name="content" />
									   </#if>
									     <label  >${m.name}</label>
									 </#list>
								 </div>

								 <div class="lx1">
									<span> 类型：</span>
									  <#list arrTypes as m>
										 <#if m_index==0>
											 <input type="radio" value="${m.id}" name="types" checked />
										 <#else>
											 <input type="radio" value="${m.id}"  name="types" />
										 </#if>
										 <label  >${m.name}</label>
									 </#list>

								 </div>							 
							 </div>
							 
							 <!-- 多个图片上传 -->
							 <div class="zpxx pic">
								 <h5>作品上传</h5>
								  <h6>注：图片不超过100张，支持批量上传。支持jpg/gif/png格式 rgb模式，不超过10m。</h6>
							   
								<div class="page-header">
									 <ul class="upload-ul clearfix mutil"  >
                                         <li class="upload-pick">
                                             <div class="webuploader-container clearfix" id="mutilUpload"></div>
                                         </li>
									  </ul>

							     </div>
							 
							 </div>
							 
							  <div class="qued">
									 <input type="button" value="提交" id="btnSave">
									 <a href="#" class="fh_1">返回</a>
							  </div>
							 

						 
					 </div>
					 
					 </form>
				 </div>
				 
			 </div> 
		 </div>
		 

        <#include "common/footer.ftl" >

		<!-- 全局js -->
         <script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
         <script src="${ctx}/static/js/plugins/webuploader/webuploader.min.js"></script>
         <script src="${ctx}/static/js/plugins/webuploader/diyUpload.js"></script>

         <script src="${ctx}/static/js/plugins/artDialog/dialog-plus.js"></script>

         <!-- 验证 -->
         <script src="${ctx}/static/js/plugins/validation/jquery.validate.js"></script>
         <script src="${ctx}/static/js/plugins/validation/localization/messages_zh.js"></script>


         <!-- 自定义文件-->
		<script src="${ctx}/static/js/student/upload.js"> </script>
		
		
	</body>
</html>
