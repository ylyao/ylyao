/*浮动窗口*/
(function() {
	
	window.onscroll = function() {
		obj.style.top = (document.body.scrollTop || document.documentElement.scrollTop)
				+ n + 'px';
		x = (document.body.scrollTop || document.documentElement.scrollTop) + n;
		if (x == 0) {
			fe.fadeOut().hide();
		} else {
			fe.fadeIn().show();
		}
	};
	window.onresize = function() {
		obj.style.top = (document.body.scrollTop || document.documentElement.scrollTop)
				+ n + 'px';
	};
})();

/*懒人图库 www.lanrentuku.com */

