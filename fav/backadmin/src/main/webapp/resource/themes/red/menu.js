$(function(){
	var span = '<span></span>';
	$('.menu li a').wrapInner(span);
	
	$('.menu li').click(function(){
		$(this).addClass('selected')
			   .siblings().removeClass('selected');
	});
});

document.changestatus = function(obj){
	$('#li'+obj).addClass('selected').siblings().removeClass('selected');
}