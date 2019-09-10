window.app = {
	/**
	 * 后端服务发布的url地址
	 */
	serverUrl: 'http://127.0.0.1:8080/',
	
	/**
	 * 图片服务器的url地址
	 */
	imgServerUrl: 'https://chatwe.oss-cn-beijing.aliyuncs.com/',
	
	saveMemberInfo:function(memberInfo){
		document.cookie = "memberInfo = "+ JSON.stringify(memberInfo);
	},
	
	getMemberInfo:function(){
		var memberInfo = this.getCookie('memberInfo');
		console.log(memberInfo);
		if(memberInfo==""){
			app.toLogin();
			return;
		}
		console.log(memberInfo);
		return memberInfo = JSON.parse(memberInfo);
	},
	getCookie:function(cname) {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i].trim();
			if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
		}
		return "";
	},
	
	refresh:function () {
		var token = app.getMemberInfo().token;
		var headers = {
			"Token": token
		};
		
		$.ajax({
			url: app.serverUrl + 'refresh',
			method: 'GET',
			headers: headers,
			success: successCall,
			error: errorCall,
		});
		
		function successCall (responseResult) {
			if(responseResult.status == 200){
				app.saveMemberInfo(responseResult.data);
				initPage();
			}
		}
		
		function errorCall(error){
			console.log(JSON.stringify(error));
		}
		
	},
	
	toLogin:function () {
		location.href = "http://127.0.0.1:8848/gossip_web/login.html#";
	}
	
}

function completeCall(responseResult){
	if(responseResult.status == 401){
		console.log(responseResult);
		app.toLogin();
		return;
	}
}
