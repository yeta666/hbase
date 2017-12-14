//公用ajax方法
function ajax_(type_, url_, data_, method_) {
	$.ajax({
		type: type_,
		url: url_,
		data: data_,
		dataType: "json",
		success: method_,
		error: function(XHR) {
			alert(XHR.status);
		}
	});
}