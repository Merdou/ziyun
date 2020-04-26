<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>作业点评</title>


		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/student/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/static/js/plugins/artDialog/css/dialog.css"/>
		
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/teacher/dianping.css"/>
		
	</head>
	<body  data-project="${ctx}">

	     <#include  "common/header.ftl">
		 
		 
		 <!-- 中间上传作业 -->
		 <div class="uploadSection">
			 <div class="wrap dpz">
				 <div class="title"  >作业点评</div>
				 
				 <div class="dp">
					 <!--左边 -->
					<div class="zy">
						  <h4 >${jobtable.studentName}个人作业</h4>
						  <h2>——${jobtable.title}</h2>
						  
						  <div class="page-header">
								<ul class="tp">
									<#list jobtable.detailsList as c>
										<li>
											<img src="${c.attachment}"/>
										</li>
									</#list>

								</ul>				 
						  </div>
					</div>
					
					
					 
					
					<!-- 右边 --> 
					 <div class="pf">
						 <form method="post" action="" id="editForm">

							 <!-- 学生作业的id-->
							 <input type="hidden" id="jobTableId" name="jobTableId"  value="${jobtable.id}">
                             <input type="hidden" id="classesId" name="classesId"  value="${jobtable.classes.id}">

							 <!--作品信息 -->
							 <div class="zpxx">
								<h5>作业评分</h5>
								
								<p>云值：<input type="text" id="score" name="score" class="yz">分</p>
								 
								 <!-- 星级评价-->
								 <div class="block">									 
									<div class="info">星级评价：</div>
									  <div id="starttwo">
										 <div class="star_score"></div>
											<p style="float:left;margin-left: 10px;">
												<input class="fenshu"  type="hidden" id="rating" name="rating" >
											</p>
											<div class="attitude"></div>
									</div>
								</div>
									
									<div class="order-evaluation-checkbox">
										<span class="bqp">标签评价：</span>
										<ul class="clearfix ck1"> 
											<#list commentArr as c>
										    	<li class="order-evaluation-check ">
													<b>${c.name}</b>
													<i class="iconfont icon-checked"></i>
												</li>
											</#list>
								            <input type="hidden" id="tags" name="tags" >
										</ul>
									</div>
									 			 
									   
							 </div>
								 
							 
							 
							 <!--评语点评 -->
							  <div class="zpxx">
									<h5>评语点评</h5>
									
									<div class="order-evaluation-textarea">
									     <textarea name="comments" id="comments"  maxlength="140" onKeyUp="words_deal();"   placeholder="请输入评语"></textarea>
									     <span>还可以输入<em id="textCount">140</em>个字</span>
									</div>		 			 
							  									   
							  </div>
						 
							 
							  <div class="qued">
									 <input type="button" value="提交" id="btnSave">
									 <a href="#" id="goReturn" class="fh_1">返回</a>
							  </div>
							 
						 </form>
						 
					 </div>
					 
					 
				 </div>
				 
			 </div> 
		 </div>


		 <#include "common/footer.ftl" >

		<!-- 全局js -->
		<script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
        <script src="${ctx}/static/js/plugins/artDialog/dialog-plus.js"></script>
		 <script src="${ctx}/static/js/star.js"></script>
		
		<script src="${ctx}/static/js/teacher/dianping.js"> </script>
		
		
	</body>
</html>
