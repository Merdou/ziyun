/**
 * 登录相关的
 */

var ctx;

$(function(){
	
	ctx= $.trim($("body").attr("data-project"));
	
	validatorForm();
	
	
	$("#btnLogin").click(loginForm);
	
	
	//选中用户名表单
	$("#mobile").focus();
	
	//用户名按回车，选中密码框
	$("#mobile").keypress(function(e){
		if(e.keyCode==13){
			$("#password").focus();
			return;
		}		
	});
	
	//密码按回车，选中调用登录方法loginForm()
	$("#password").keypress(function(e){
		if(e.keyCode==13){
			loginForm();
			return;
		}		
	});
	
	
});

// 验证非空的
function validatorForm(){
	
	$('#editForm').bootstrapValidator({
		   excluded: [':disabled'],  // 排除的样式
	 	   message: '输入的值无效',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok', // 验证通过的图标
	            invalid: 'glyphicon glyphicon-remove',  // 验证失败的图标
	            validating: 'glyphicon glyphicon-refresh'  // 正在验证中的图标
	        },
	        fields: {   
	            // 添加验证的表单的name
                mobile:{
	        		 message: '输入的值无效',
	        		 validators:{
	        			 notEmpty: {
	                         message: '用户名不能为空'
	                     },
	                     stringLength: {
	                         min: 11,
	                         max: 11,
	                         trim: 'trim',
	                         message: '用户名长度必须是11个字符'
	                     }
	        		 } // validators验证结束
	        
	        	}, // roleName结束
	        	password:{
	        		 message: '输入的值无效',
	        		 validators:{
	        			 notEmpty: {
	                         message: '密码不能为空'
	                     },
	                     stringLength: {
	                         min: 4,
	                         max: 20,
	                         trim: 'trim',
	                         message: '密码长度必须在4到20个字符之间'
	                     } 
	        		 } // validators验证结束
	        
	        	} // authority结束
	        
	        }
	    });
}


// 登录操作
function loginForm(){
	// 触发验证操作
	// 手动调用，验证表单是否通过了
	var bsv = $('#editForm').data('bootstrapValidator');
	// 手动调用
	 bsv.validate();
	 setTimeout(() => {
		 var result=bsv.isValid(); 
			 // 验证不成功
			 if(!result){
				 return;
			 }  
			 //ajax发送数据库，登录判断
			 //1、收集数据
			 var dataStr=$("#editForm").serialize();
			 
			 //2、写ajax
			 $.ajax({
				 type:"POST",
				 url:ctx+"/admin/login",
				 data:dataStr,
				 dataType:"json", 
				 success:function(resp){
					 console.log(resp);
					 
					 if(resp.code===0){
						 //重定向到首页
						 window.location.href=ctx+"/admin/index";
					 }else{
						 //清空密码
						 $("#password").val("");
						 
						 BootstrapDialog.show({
					            title: '提示',
					            message: resp.message,
					            draggable: true,
					            type:"type-warning"
					        }); 
						 
					 }
					 
				 }
			 });
			 
	 },100);
	
	
}