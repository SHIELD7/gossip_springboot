function addAttention(memberId) {
	var data = {};
	data.followingId = memberId;
	var token = app.getMemberInfo().token;
	var headers = {
		"Token": token
	};

	$.ajax({
		url: app.serverUrl + 'relation',
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
			console.log("45");
			$("a[name='link_relation_" + memberId + "']").each(function() {
				$(this).text('取消关注');
				$(this).attr('onclick', 'deleteAttention(' + memberId + ')');
			});
		} else {
			alert(responseResult.msg);
		}
	}

	function errorCall(error) {
		alert(error.msg);
	}
}

function deleteAttention(memberId, gossipId) {
	var data = {};
	data.followingId = memberId;
	var token = app.getMemberInfo().token;
	var headers = {
		"Token": token
	};

	$.ajax({
		url: app.serverUrl + 'relation',
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
			$("a[name='link_relation_" + memberId + "']").each(function() {
				$(this).text('关注');
				$(this).attr('onclick', 'addAttention(' + memberId + ')');
			});
		} else {

		}
	}

	function errorCall(error) {
		console.log(error.toString())
	}
}

function findFollowing(pageCurrent) {
	var data = {};
	data.pageCurrent = pageCurrent;
	data.pageSize = 10;
	var token = app.getMemberInfo().token;
	var headers = {
		"Token": token
	};

	$.ajax({
		url: app.serverUrl + 'following',
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
			$('#postGossipContent').hide();
			var payload = {};
			payload.list = responseResult.data.records;
			console.log(responseResult.data.records)
			var html = template(document.getElementById('followingListTemplate').innerHTML, payload);
			console.log(html);
			document.getElementById('gossipDiv').innerHTML = html;
			$('.pagination').each(function () {
				$(this).hide();
			});
			$('#followingPagination').show();

			window.pagObj = $('#followingPagination').twbsPagination({
				totalPages: responseResult.data.pages,
				visiblePages: 6,
				onPageClick: function(event, page) {
					console.info(page + ' (from options)');
					findFollowing(page);
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		} else {

		}
	}

	function errorCall(error) {
		console.log(error.toString())
	}
}

function findFollower(pageCurrent) {
	var data = {};
	data.pageCurrent = pageCurrent;
	data.pageSize = 10;
	var token = app.getMemberInfo().token;
	var headers = {
		"Token": token
	};

	$.ajax({
		url: app.serverUrl + 'follower',
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
			$('#postGossipContent').hide();
			var payload = {};
			payload.list = responseResult.data.records;
			console.log(responseResult.data.records)
			var html = template(document.getElementById('followerListTemplate').innerHTML, payload);
			document.getElementById('gossipDiv').innerHTML = html;
			$('.pagination').each(function () {
				$(this).hide();
			})
			$('#followerPagination').show();
			window.pagObj = $('#followerPagination').twbsPagination({
				totalPages: responseResult.data.pages,
				visiblePages: 6,
				onPageClick: function(event, page) {
					console.info(page + ' (from options)');
					findFollower(page);
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		} else {

		}
	}

	function errorCall(error) {
		console.log(error.toString())
	}
}
