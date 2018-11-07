$(function(){
	//图标隐藏菜单
	$(".entrance").hover(function(){
		$(this).children(".user-menu").show();
	},function(){
		$(this).children(".user-menu").hide();
	});

	$('.action .detailed').each(function(){
		$(this).click(function() {
        	detailed_content();
        	return false;
        });
  	});

	$('.action .thinkbox-image').each(function(){
		$(this).click(function() {
        	thinkbox_image();
        	return false;
        });
  	});

	(function(){
		var $nav = $("#nav"), $current = $nav.children("[data-key=" + $nav.data("key") + "]");
		if($nav.length){
			$current.addClass("current");
		} else {
			$("#nav").children().first().addClass("current");
		}
	})();
});

//js无缝滚动代码
function marquee(i, direction){
	var obj = document.getElementById("marquee" + i);
	var obj1 = document.getElementById("marquee" + i + "_1");
	var obj2 = document.getElementById("marquee" + i + "_2");
	if (direction == "up"){
		if (obj2.offsetTop - obj.scrollTop <= 0){
			obj.scrollTop -= (obj1.offsetHeight + 20);
		}else{
			var tmp = obj.scrollTop;
			obj.scrollTop++;
			if (obj.scrollTop == tmp){
				obj.scrollTop = 1;
			}
		}
	}else{
		if (obj2.offsetWidth - obj.scrollLeft <= 0){
			obj.scrollLeft -= obj1.offsetWidth;
		}else{
			obj.scrollLeft++;
		}
	}
}

function marqueeStart(i, direction){
	var obj = document.getElementById("marquee" + i);
	var obj1 = document.getElementById("marquee" + i + "_1");
	var obj2 = document.getElementById("marquee" + i + "_2");

	obj2.innerHTML = obj1.innerHTML;
	var marqueeVar = window.setInterval("marquee("+ i +", '"+ direction +"')", 60);
	obj.onmouseover = function(){
		window.clearInterval(marqueeVar);
	}
	obj.onmouseout = function(){
		marqueeVar = window.setInterval("marquee("+ i +", '"+ direction +"')", 60);
	}
}


/* 收藏网页 Start */
function addBookmark(title,url) 
{
	if($.browser.webkit){ 
		alert("请用CTRL+D收藏本网页！");
		return true;
	}
    if (window.sidebar){ 
        window.sidebar.addPanel(title, url,""); 
    } 
    else if( document.all ){
        window.external.AddFavorite( url, title);
    } 
    else if( window.opera && window.print ){
        return true;
    }
}
/* 收藏网页 End */

/* 设为首页 Start */
function SetHome(obj,url){ 
	if($.browser.webkit){ 
		alert("请手动设置，webkit浏览器暂不支持！");
		return true;
	}
	try{ 
		obj.style.behavior='url(#default#homepage)';
		obj.setHomePage(url); 
	} 
	catch(e){ 
		if(window.netscape) { 
			try { 
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
			} 
			catch (e) { 
				//alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入"about:config"并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。"); 
			} 
			var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch); 
			prefs.setCharPref('browser.startup.homepage',url); 
		 } 
	} 
} 
/* 设为首页 End */

/* 文章显示_字体大小 */
function ContentSize(size){
	var obj=document.getElementById("ainfo");
	obj.style.fontSize=size>0 ? size+"px" : "";
}

/* 如果图片不存在,则显示错误图片 */
function imgError(url,obj){
	var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var thisURL=  url;/*curWwwPath.substring(0,pos);*/
	obj.src=thisURL+'/Public/Static/images/ImgError.gif';
	obj.onerror = null;//控制onerror事件只触发一次
}


/* 如果图片不存在,则显示错误图片 */
/* type 0正方形1长方形2竖长方形 */
function imgErrorType(url,obj,type){
	var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var thisURL=  url;/*curWwwPath.substring(0,pos);*/
	obj.src=thisURL+'public/Static/images/ImgError'+type+'.gif';
	obj.onerror = null;//控制onerror事件只触发一次
}

// 验证邮箱
function CheckMail(mail) {
	var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (filter.test(mail)){
		return true;
	}else{
		return false;
	}
}
$(function(){
	//全选的实现
	$(".checkAll").click(function(){
		$(".ids").prop("checked", this.checked);
	});
	$(".ids").click(function(){
		var option = $(".ids");
		option.each(function(i){
			if(!this.checked){
				$(".check-all").prop("checked", false);
				return false;
			}else{
				$(".check-all").prop("checked", true);
			}
		});
	});
});
