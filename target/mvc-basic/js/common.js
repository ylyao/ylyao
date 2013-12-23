function getFormatDate(date){
		var year=date.getFullYear();
		var month=date.getMonth()+1;
		if(month<10){
			month="0"+month;
	}
	var day=date.getDate();
	if(day<10){
		day="0"+day;
	}
	return year+"-"+month+"-"+day;
}


function createMask(){
	var divHtml = '<div id="mask" class="mask" style="display: none;"><img style="position: absolute; top: 48%; left: 48%;" src="img/loading.gif" /></div>';
	$("body").append(divHtml);
	$("#mask").css({
		"height": "100%",
		"width": "100%",
		"position": "absolute",
		"left":"0",
		"top": "0",
		"z-index": "10000",
		"background-color": "rgb(208, 208, 208)",
		"opacity":"0.5",
		"filter": "alpha(Opacity =   50)",
		"-moz-opacity": "0.5"
	});
}

function openMask(){
	var top = document.body.scrollTop;
	$("#mask").css({
		"top":top+"px"
	});
	$("body").css({
		"overflow":"scroll",
		"overflow-y":"hidden",
		"overflow-x":"hidden"
	});
	$("#mask").show();
}

function closeMask(){
	$("body").css({
		"overflow":"",
		"overflow-y":"",
		"overflow-x":""
	});
	$("#mask").hide();
}