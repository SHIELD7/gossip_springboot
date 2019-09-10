function postGossip() {
	var content = $('#message').val();
	var pics = $('div.uploader-list input');
	var arr = pics.map(function() {
		return this;
	}).get();
	var data = {};
	data.content = content;
	for (var i = 0; i < arr.length; i++) {
		var d = i + 1;
		data['pic' + d] = arr[i].value;
		console.log(arr[i].value);
	}
	var token = app.getMemberInfo().token;
	var headers = {
		"Token": token
	};

	var postgossipxhr = $.ajax({
		url: app.serverUrl + 'gossip/post',
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
			$('#message').val('');
			var liList = $('div.uploader-list li');
			var liArr = liList.map(function() {
				return this;
			}).get();
			for (var i = 0; i < liArr.length; i++) {
				liArr[i].remove();
			}
			timeLine(0);
		} else {
			alert(responseResult.msg);
		}
	}

	function errorCall(error) {
		alert(error);
		
	}

}

function timeLine(page) {
	var data = {};
	data.pageCurrent = page;
	data.pageSize = 10;
	var token = app.getMemberInfo().token;
	var headers = {
		Token: token
	};

	var queryWeiboxhr = $.ajax({
		url: app.serverUrl + 'gossip/timeline',
		dataType: 'json',
		data: data,
		method: 'GET',
		headers: headers,
		success: successCall,
		error: errorCall,
		complete:completeCall
	});

	function successCall(responseResult) {
		console.log('成功, 收到的数据: ' + responseResult);
		if (responseResult.status == 200) {
			console.log(responseResult.data)
			var result = responseResult.data.records;
			var payload = {};
			for (var i = 0; i < result.length; i++) {
				result[i].avatar = app.imgServerUrl + result[i].avatar;
				for (var q = 0; q < 9; q++) {
					if (result[i]['pic' + q] !== null) {
						var z = q + 1;
						result[i]['pic' + z] = app.imgServerUrl + result[i]['pic' + z];
					}
				}
			}
			payload.list = result;
			var html = template(document.getElementById('gossipTemplate').innerHTML, payload);
			document.getElementById('gossipDiv').innerHTML = html;
			$('.pagination').each(function () {
				$(this).hide();
			});
			$('#friendCirclePagination').show();

			window.pagObj = $('#friendCirclePagination').twbsPagination({
				totalPages: responseResult.data.pages,
				visiblePages: 6,
				onPageClick: function(event, page) {
					console.info(page + ' (from options)');
					timeLine(page);
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		}
	}

	function errorCall(error) {
		console.log('请求出错' + JSON.stringify(error));
	}
}

function square(page) {
	var data = {};
	data.pageCurrent = page;
	data.pageSize = 10;
	var token = app.getMemberInfo().token;
	var headers = {
		Token: token
	};

	var queryWeiboxhr = $.ajax({
		url: app.serverUrl + 'gossip/square',
		dataType: 'json',
		data: data,
		method: 'GET',
		headers: headers,
		success: successCall,
		error: errorCall,
		complete:completeCall
	});

	function successCall(responseResult) {
		console.log('成功, 收到的数据: ' + responseResult);
		if (responseResult.status == 200) {
			console.log(responseResult.data)
			var result = responseResult.data.records;
			var payload = {};
			for (var i = 0; i < result.length; i++) {
				result[i].avatar = app.imgServerUrl + result[i].avatar;
				for (var q = 0; q < 9; q++) {
					if (result[i]['pic' + q] !== null) {
						var z = q + 1;
						result[i]['pic' + z] = app.imgServerUrl + result[i]['pic' + z];
					}
				}
			}
			payload.list = result;
			var html = template(document.getElementById('gossipTemplate').innerHTML, payload);
			document.getElementById('gossipDiv').innerHTML = html;
			$('.pagination').each(function () {
				$(this).hide();
			});
			$('#squarePagination').show();
			window.pagObj = $('#squarePagination').twbsPagination({
				totalPages: responseResult.data.pages,
				visiblePages: 6,
				onPageClick: function(event, page) {
					console.info(page + ' (from options)');
					square(page);
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		}
	}

	function errorCall(error) {
		console.log('请求出错' + JSON.stringify(error));
	}
}


function queryByMemberId(page,memberId) {
	var data = {};
	data.pageCurrent = page;
	data.pageSize = 10;
	data.memberId= memberId;
	console.log(memberId);
	if(0==memberId){
		data.memberId = app.getMemberInfo().memberId;
	}
	console.log(data);
	var token = app.getMemberInfo().token;
	var headers = {
		Token: token
	};
	
	$.ajax({
		url: app.serverUrl + 'gossip/member_id',
		dataType: 'json',
		data: data,
		method: 'GET',
		headers: headers,
		success: successCall,
		error: errorCall,
		complete:completeCall
	});

	function successCall(responseResult) {
		console.log('成功, 收到的数据dd: ' + responseResult);
		if (responseResult.status == 200) {
			$('#postGossipContent').hide();
			console.log(responseResult.data)
			var result = responseResult.data.records;
			var payload = {};
			for (var i = 0; i < result.length; i++) {
				result[i].avatar = app.imgServerUrl + result[i].avatar;
				for (var q = 0; q < 9; q++) {
					if (result[i]['pic' + q] !== null) {
						var z = q + 1;
						result[i]['pic' + z] = app.imgServerUrl + result[i]['pic' + z];
					}
				}
			}
			payload.list = result;
			var html = template(document.getElementById('gossipTemplate').innerHTML, payload);
			document.getElementById('gossipDiv').innerHTML = html;
			$('.pagination').each(function () {
				$(this).hide();
			});
			$('#someonePagination').show();
			window.pagObj = $('#someonePagination').twbsPagination({
				totalPages: responseResult.data.pages,
				visiblePages: 6,
				onPageClick: function(event, page) {
					console.info(page + ' (from options)');
					queryByMemberId(page,memberId);
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		}
	}

	function errorCall(error) {
		console.log('请求出错' + JSON.stringify(error));
	}
}
