function postComment() {
	var content = $('#text_comment').val();
	var commentBo = {};
	commentBo.commentContent = content;
	var gossipId = $('#modal_header').find('.row').first().attr('id');
	gossipId = gossipId.substring(9);
	commentBo.gossipId = gossipId;

	var token = app.getMemberInfo().token;
	var headers = {
		Token: token
	};
	console.log(content);

	$.ajax({
		url: app.serverUrl + 'comment',
		dataType: 'json',
		data: commentBo,
		method: 'POST',
		headers: headers,
		success: successCall,
		error: errorCall,
		complete:completeCall
	});

	function successCall(responseResult) {
		if (responseResult.status == 200) {
			$('#text_comment').val('');
			queryComment(gossipId);
		} else {
			alert(responseResult.msg);
		}
	}

	function errorCall(error) {
		console.log(error);
	}
}

function queryComment(gossipId) {
	document.getElementById('modal_comment').innerHTML = "";
	console.log(gossipId);
	var data = {};
	data.gossipId = gossipId;
	data.pageSize = 10;
	data.pageCurrent = 0;
	var token = app.getMemberInfo().token;
	var headers = {
		Token: token
	};
	var queryCommentxhr = $.ajax({
		url: app.serverUrl+'comment',
		dataType: 'json',
		data: data,
		method: 'GET',
		headers: headers,
		success: successCall,
		error: errorCall,
		complete:completeCall
	});

	function successCall(responseResult) {
		if (responseResult.status == 200) {
			var payload = {}
			var result = responseResult.data.records;
			for (var i = 0; i < result.length; i++) {
				result[i].avatar = app.imgServerUrl + result[i].avatar;
			}
			payload.list = result;
			var html = template(document.getElementById('commentTemplate').innerHTML, payload);
			document.getElementById('modal_comment').innerHTML = html;
		} else {
			alert(responseResult.msg);
		}

	}

	function errorCall(error) {
		console.log(error);
	}
}
