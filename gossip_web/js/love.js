function loveGossip(gossipId) {
	var data = {};
	data.gossipId = gossipId;
	var token = app.getMemberInfo().token;
	var headers = {
		"Token": token
	};

	$.ajax({
		url: app.serverUrl + 'love',
		dataType: 'json',
		data: data,
		method: 'POST',
		headers: headers,
		success: successCall,
		error: errorCall,
		complete:completeCall
	});

	function successCall(responseResult) {
		if (responseResult.status == 200) {
			$('#love_'+gossipId).attr('style','color: red;');
			$('#love_'+gossipId).attr('class','fa fa-heart');
			$('#love_'+gossipId).attr('onclick','unLoveGossip('+gossipId+')');
			$('#loveCount_'+gossipId).text(parseInt($('#loveCount_'+gossipId).text())+1)
		} else {

		}
	}

	function errorCall(error) {
		console.log(error.toString())
	}
}

function unLoveGossip(gossipId) {
	var data = {};
	data.gossipId = gossipId;
	var token = app.getMemberInfo().token;
	var headers = {
		"Token": token
	};

	$.ajax({
		url: app.serverUrl + 'love',
		dataType: 'json',
		data: data,
		method: 'DELETE',
		headers: headers,
		success: successCall,
		error: errorCall,
		complete:completeCall
	});

	function successCall(responseResult) {
		if (responseResult.status == 200) {
			$('#love_'+gossipId).attr('style','');
			$('#love_'+gossipId).attr('class','fa fa-heart-o');
			$('#love_'+gossipId).attr('onclick','loveGossip('+gossipId+')');
			$('#loveCount_'+gossipId).text(parseInt($('#loveCount_'+gossipId).text())-1)
		} else {

		}
	}

	function errorCall(error) {
		console.log(error.toString())
	}
}