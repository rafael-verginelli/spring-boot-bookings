function setPicker() {
	$("#datepicker").datepicker({
		dateFormat: 'yy-mm-dd',
		onSelect: function(d, i) {
			if (d !== i.lastVal) {
				reloadPageForDateSelection();
			}
		},
	});
};

function getRequestParam(p) {
	return (window.location.search.match(new RegExp('[?&]' + p + '=([^&]+)'))
		|| [, null])[1];
}

function setInitialDate() {
	var formatedDate = formatDate(null);
	$('#datepicker').datepicker('setDate', formatedDate);
}

function reloadPageForDateSelection() {
	var selectedDate = $('#datepicker').val();

	var redirectLink = window.location.protocol + "//" + window.location.host + window.location.pathname + "?date=" + selectedDate;
	console.log('Redirect to ' + redirectLink);
	window.location.href = redirectLink;
};

function formatDate(input) {
	var dateString = "";
	if (input == null) {
		var today = new Date();
		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2);
		dateString = year + '-' + month + '-' + day;
	} else {
		dateString = input;
	}
	return new Date(dateString);
}

$(document).ready(function() {
	setPicker();
	setInitialDate();
});