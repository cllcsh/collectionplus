$(document).ready( function() {
		var menus = $(".STYLE4");
		var oldMenu;
		var link = $("#menulink").val();

		if (link == "") {
			oldMenu = menus.eq(0);
		} else {
			oldMenu = $(document.getElementById(link));
		}

		oldMenu.addClass("STYLE6");
		
		menus.bind("click", function() {
			if ($(this).hasClass("STYLE6")) {
				return;
			} else {
				$(this).addClass("STYLE6");
				oldMenu.removeClass("STYLE6");
				oldMenu = $(this);
			}
		});
	});



	function mouseover_me(obj)
	{
		obj.style.backgroundImage='url(main/tab_bg.gif)';
		obj.style.borderStyle='solid';
		obj.style.borderWidth='1';
		borderColor='#adb9c2'; 
	}

	function mouseout_me(obj)
	{
		obj.style.backgroundImage='url()';
		obj.style.borderStyle='none';
	}
	
	
	function onclick_urlchange(obj)
	{
		//parent.document.getElementById('mainIFrame').src='..'+obj;
		parent.document.getElementById('mainIFrame').src=obj;
	}
	