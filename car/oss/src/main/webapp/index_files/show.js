STK.register("kit.extra.language",function(a){window.$LANG||(window.$LANG={});return function(b,c){var d=a.core.util.language(b,$LANG);d=d.replace(/\\}/ig,"}");c&&(d=a.templet(d,c));return d}});
typeof scope=="undefined"&&(scope={});scope.loginKit=function(){var a=document.cookie+";",b=["SUP","=([^;]*)?;"].join(""),c=["(\\?|&)","uid","=([^&]*)(&|$)"].join(""),d=a.match(new RegExp(b,"i"));d=d?d[1]||"":"";d=unescape(d);var e=d.match(new RegExp(c));e=e?e[2]||"":"";var f=scope.$oid;return{uid:e,isLogin:!!e,isAdmin:e&&f&&e==f}};scope.$isLogin=function(){return scope.loginKit().isLogin};scope.$isAdmin=function(){return scope.loginKit().isAdmin};
STK.register("core.obj.parseParam",function(a){return function(a,b,c){var d,e={};b=b||{};for(d in a){e[d]=a[d];b[d]!=null&&(c?a.hasOwnProperty[d]&&(e[d]=b[d]):e[d]=b[d])}return e}});
STK.register("common.widget.log",function(a){var b={app_sharebutton:1,app_followbutton:2,app_livestream:4,app_listweibo:5,app_weiboshow:6,app_commentbox:7};return function(c){var d=a.core.obj.parseParam({vsrc:"app_weiboshow",refer:"",step:1},c),e=scope.refer||scope.$refer||d.refer,f=scope.loginKit().uid||"",g=scope.appkey||$CONFIG.$appkey||$CONFIG.appkey||0,h=b[d.vsrc]||"",i="http://rs.sinajs.cn/r.gif?uid="+f+"&appid="+g+"&refer="+e+"&cat="+h+"&step="+d.step+"&rnd="+ +(new Date),j=new Image;j.src=i;j=null}});
STK.register("common.widget.login",function(a){var b={vsrc:"app_weiboshow",appsrc:"",showlogo:0,callback:function(){}};typeof App=="undefined"&&(App={});return function(c){c=a.parseParam(b,c);c.appsrc||(c.appsrc=scope?scope.appsrc?scope.appsrc:scope.$appsrc?scope.$appsrc:"":"");var d={};App.loginBackUrlCallBack=function(b){a.custEvent.fire(d,"login",b);c.step=2;a.common.widget.log(c)};var e=function(){f();g();h();i()},f=function(){},g=function(){},h=function(){a.custEvent.define(d,"login")},i=function(){a.custEvent.add(d,"login",function(a){c.callback(a)})};d.showLogin=function(){var b="&"+a.jsonToQuery(c),d="http://service.weibo.com/reg/loginindex.php?regbackurl=http%3A%2F%2Fweibo.com&backurl=http%3A%2F%2F"+location.host+"%2Fstaticjs%2FloginProxy.html"+b+"&rnd="+ +(new Date).valueOf();/weibo.com/.test(location.host)&&(d=d.replace(/\/widget/,""));var e=window.open(d,"miniblog_login",["toolbar=1,status=0,resizable=1,width=620,height=540,left=",(screen.width-620)/2,",top=",(screen.height-450)/2].join(""));e.focus();a.common.widget.log(c)};e();return d}});
STK.register("module.layer",function(a){var b=function(a){var b={};if(a.style.display=="none"){a.style.visibility="hidden";a.style.display="";b.w=a.offsetWidth;b.h=a.offsetHeight;a.style.display="none";a.style.visibility="visible"}else{b.w=a.offsetWidth;b.h=a.offsetHeight}return b},c=function(c,d){d=d||"topleft";var e=null;if(c.style.display=="none"){c.style.visibility="hidden";c.style.display="";e=a.core.dom.position(c);c.style.display="none";c.style.visibility="visible"}else e=a.core.dom.position(c);if(d!=="topleft"){var f=b(c);if(d==="topright")e.l=e.l+f.w;else if(d==="bottomleft")e.t=e.t+f.h;else if(d==="bottomright"){e.l=e.l+f.w;e.t=e.t+f.h}}return e};return function(d){var e=a.core.dom.builder(d),f=e.list.outer[0],g=e.list.inner[0],h=a.core.dom.uniqueID(f),i={},j=a.core.evt.custEvent.define(i,"show");a.core.evt.custEvent.define(j,"hide");var k=null;i.show=function(){f.style.display="";a.core.evt.custEvent.fire(j,"show");return i};i.hide=function(){f.style.display="none";a.custEvent.fire(j,"hide");return i};i.getPosition=function(a){return c(f,a)};i.getSize=function(a){if(a||!k)k=b.apply(i,[f]);return k};i.html=function(a){a!==undefined&&(g.innerHTML=a);return g.innerHTML};i.text=function(b){text!==undefined&&(g.innerHTML=a.core.str.encodeHTML(b));return a.core.str.decodeHTML(g.innerHTML)};i.appendChild=function(a){g.appendChild(a);return i};i.getUniqueID=function(){return h};i.getOuter=function(){return f};i.getInner=function(){return g};i.getParentNode=function(){return f.parentNode};i.getDomList=function(){return e.list};i.getDomListByKey=function(a){return e.list[a]};i.getDom=function(a,b){return e.list[a]?e.list[a][b||0]:!1};i.getCascadeDom=function(b,c){return e.list[b]?a.core.dom.cascadeNode(e.list[b][c||0]):!1};return i}});
STK.register("ui.tipPrototype",function(a){var b=10003;return function(c){var d,e,f,g,h,i='<div node-type="outer" class="WB_widgets W_layer" style="position: absolute; display:none;" ><div node-type="inner" class="bg"></div></div>';d=a.parseParam({direct:"up",showCallback:a.core.func.empty,hideCallback:a.core.func.empty},c);e=a.module.layer(i,d);f=e.getOuter();g=e.getInner();e.setTipWH=function(){h=this.getSize(!0);this.tipWidth=h.w;this.tipHeight=h.h;return this};e.setTipWH();e.setContent=function(a){typeof a=="string"?g.innerHTML=a:g.appendChild(a);this.setTipWH();return this};e.setLayerXY=function(c){if(!c)throw"ui.tipPrototype need pNode as first parameter to set tip position";var e=STK.core.dom.position(c),g=e.l,h=c.offsetWidth,i=c.offsetHeight,j=Math.min(Math.max(g+(h-this.tipWidth)/2,a.scrollPos().left),a.scrollPos().left+STK.winSize().width-this.tipWidth),k=e.t;d.direct==="down"&&(k+=i);var l=[";"];l.push("z-index:",b++,";");l.push("width:",this.tipWidth,"px;");l.push("height:",this.tipHeight,"px;");l.push("top:",k,"px;");l.push("left:",j,"px;");f.style.cssText+=l.join("")};e.aniShow=function(){var b=this.getOuter();b.style.height="0px";b.style.display="";var c=a.core.ani.tween(b,{end:d.showCallback,duration:250,animationType:"easeoutcubic"});if(d.direct==="down")c.play({height:this.tipHeight},{staticStyle:"overflow:hidden;position:absolute;"});else{var e=parseInt(b.style.top,10)-this.tipHeight;c.play({height:this.tipHeight,top:Math.max(e,a.scrollPos().top)},{staticStyle:"overflow:hidden;position:absolute;"})}};e.anihide=function(){var b=this.getOuter(),c=this,e=a.core.ani.tween(b,{end:function(){b.style.display="none";b.style.height=c.tipHeight+"px";d.hideCallback()},duration:300,animationType:"easeoutcubic"});if(d.direct==="down")e.play({height:0},{staticStyle:"overflow:hidden;position:absolute;"});else{var f=parseInt(b.style.top,10)+this.tipHeight;e.play({height:0,top:f},{staticStyle:"overflow:hidden;position:absolute;"})}};document.body.appendChild(f);e.destroy=function(){document.body.removeChild(f);e=null};return e}});
STK.register("ui.tipAlert",function(a){var b=a.core.util.easyTemplate;return function(c){c=a.parseParam({direct:"up",className:"WB_widgets W_layer",showCallback:a.core.func.empty,hideCallback:a.core.func.empty,template:'<#et temp data><table cellspacing="0" cellpadding="0" border="0"><tbody><tr><td><div node-type="msgDiv" class="content layer_mini_info"><p class="clearfix alt_text"><span class="tip_icon WB_tipS_${data.type}"></span>${data.msg}&nbsp; &nbsp; &nbsp; </p></div></td></tr></tbody></table></#et>',type:"ok",msg:""},c);var d=a.ui.tipPrototype(c),e=d.getInner(),f=d.getOuter();f.className=c.className;e.className="bg";var g=c.template,h=a.builder(b(g,{type:c.type,msg:c.msg}).toString());d.setContent(h.box);var i=d.destroy;d.destroy=function(){i();d=null};return d}});
STK.register("common.widget.reg",function(a){var b=a.kit.extra.language,c=a.ui.tipAlert({className:"WB_tips_top",showCallback:function(){var a=c.getOuter();a.style.height="";a=null},template:'<#et temp data><div class="tips_inner"><span class="WB_tipS_warn"></span><span class="WB_icon_txt">'+b("#L{您的帐号尚未开通微博，}")+'<a href="http://weibo.com/signup/full_info.php?appsrc=6cm7D0&backurl='+encodeURIComponent(document.URL)+'&showlogo=0&vsrc=weiboshow&from=zw" target="_blank">'+b("#L{立即开通}")+"</a></span>"+"</#et>"});return function(b){if(!a.isNode(b))throw"[common.widget.reg] need node as first parameter";c.setLayerXY(b);c.aniShow();return c}});
STK.register("common.widget.addFollow",function(a){var b=a.kit.extra.language,c=function(){},d={uid:scope.loginKit().uid,url:"",fuid:"",appsrc:"",vsrc:"app_weiboshow",success:c,fail:c},e,f=function(c){if(c.fuid===c.uid){e.parentNode.innerHTML=b('<span class="WB_btnC"><span><em class="WB_btnicn_ok"></em><em>#L{你自己}</em></span></span>');return"yourself"}if(!c.url){c.url="/widget/weiboshow/aj_attention.php";/weibo.com/.test(location.host)&&(c.url="/weiboshow/aj_attention.php")}a.ajax({method:"post",url:c.url,args:{wsrc:$CONFIG.wsrc||"app_weibo_show",uid:c.uid,fuid:c.fuid},onComplete:function(d){switch(d.code){case"A00006":break;case"A10007":break;case-1:a.common.widget.reg(e);break;case-2:var g=a.common.widget.login();a.custEvent.add(g,"login",function(){c.uid=scope.loginKit().uid;f(c)});g.showLogin();break;case-3:}(d.code=="A00006"||d.code=="A10007")&&e&&(e.parentNode.innerHTML=b('<span class="WB_btnC"><span><em class="WB_btnicn_ok"></em><em>#L{已关注}</em></span></span>'));typeof c.success=="function"&&c.success(d)},onFail:function(a){typeof c.fail=="function"&&c.fail(a)}})};return function(b,c){if(!b)throw"[common.widget.addFollow] need node as parameter";e=b;var g=function(){if(parent==self||parent==parent.parent){if(!scope.$isLogin()){var b=a.common.widget.login();a.custEvent.add(b,"login",function(){c.uid=scope.loginKit().uid;f(c)});b.showLogin();return}f(c)}},h={},i=function(){c=a.parseParam(d,c);c.fuid||(c.fuid=b.getAttribute("uid"));j()},j=function(){a.addEvent(b,"click",g)},k=function(){a.removeEvent(b,"click",g)};h.destroy=k;i();return h}});
STK.register("comp.widget.show.error",function(a){var b=a.addEvent,c=a.E("showTXA"),d=a.E("showTX"),e=a.E("showBtn"),f=a.kit.extra.language,g;return function(){var g={};if(!c)return g;var h=function(){e.style.display="none";d.style.display="block"},i=function(){var b=a.E("txContent").value,c="/widget/weiboshow/aj_addmblog.php";/weibo.com/.test(location.host)&&(c="/weiboshow/aj_addmblog.php");a.ajax({url:c,args:{appkey:$CONFIG.$appsrc,content:encodeURIComponent(b)},method:"post",onComplete:function(b){var c=b.code;switch(c){case"A00006":a.E("showTX").innerHTML=f("#L{提醒成功，辛苦了}");break;case"M01155":alert(f("#L{你刚才好像提醒一次了}"));break;case"M00005":var d=a.common.widget.login();d.showLogin();break;case"M00004":alert(f("#L{参数错误}"));break;case"M00006":alert(f("#L{你未开通新浪微博。}"));break;case"M18003":alert(f("#L{提醒失败}"));break;default:alert(f("#L{提醒失败}"))}}})};b(c,"click",h);b(a.E("txBtn"),"click",i);var j=function(){a.removeEvent(c,"click",h);a.removeEvent(a.E("txBtn"),"click",i)};g.destroy=j;return g}});
STK.register("comp.widget.show.scroll",function(a){var b,c,d=location.search,e=a.E("weibo_list_con"),f=a.E("weibo_list"),g=a.E("fans_list_con"),h=a.core,i=h.evt.addEvent,j=h.evt.getEvent,k=h.evt.fireEvent,l=h.evt.stopEvent,m=h.arr.foreach,n=function(){},o=null,p=0;c={autoScroll:function(a,b){var b=b||5,d=f.offsetHeight-e.clientHeight;clearInterval(this._timer);var g=setInterval(function(){if(!!c.isOutRange){if(c.lock)return;var b=e.scrollTop;b=a=="down"?Math.min(b+2,d):Math.max(b-2,0);e.scrollTop=b;(b==0||b==d)&&c.stop(g)}},b)},start:function(a,b){c.lock=!0;var b=b||5,d=f.offsetHeight-e.clientHeight;clearInterval(this._timer);this._timer=setInterval(function(){var b=e.scrollTop;b=a==="down"?Math.min(b+2,d):Math.max(b-2,0);e.scrollTop=b;(b==0||b==d)&&c.stop()},b)},lock:!1,isOutRange:!0,scroll:function(a){clearTimeout(this.ctimer);if(!c.isOutRange){var b=e.scrollTop,d=f.offsetHeight-e.clientHeight;b=a.wheelDelta<=0||a.detail>0?Math.min(b+20,d):Math.max(b-20,0);e.scrollTop=b;if(b==0||b==d){n();return}l(a);this.ctimer=setTimeout(function(){n()},500)}},stop:function(a){clearInterval(a||this._timer);c.lock=!1;n()}};b=function(){if(!!a.E("weibo_con")&&a.E("weibo_con").style.display!="none"){e.style.position="relative";e.scrollTop=0;p=f.offsetTop;o=a.sizzle(".weiboShow_mainFeed_list",e);(function(){function d(){var a=e.scrollTop,d=parseInt(e.style.height);b.style.display=a==0?"none":"";c.style.display=a+d==f.offsetHeight?"none":""}var b=a.E("weibo_upbtn");if(!!b){b=b.getElementsByTagName("em")[0];var c=a.E("weibo_downbtn").getElementsByTagName("em")[0],g=null;n=function(){clearTimeout(g);setTimeout(d,200)}}})();n();if(o.length==0)return;(function(){var b=null;a.addEvent(e,"mouseover",function(){clearTimeout(b);c.isOutRange=!1});a.addEvent(e,"mouseout",function(a){clearTimeout(b);b=setTimeout(function(){c.isOutRange=!0},50)})})();try{window.addEventListener("DOMMouseScroll",function(a){c.scroll(a||event)},!1)}catch(b){document.onmousewheel=function(a){c.scroll(a||event)}}m(["up","down"],function(b,d){i(a.E("weibo_"+b+"btn"),"mouseover",function(){c.start(b)});i(a.E("weibo_"+b+"btn"),"mouseout",function(){c.stop()})});m(o,function(a,b){i(a,"click",function(){l();window.open(a.getAttribute("gosrc"))})});(function(){var a=/speed=(\d+)/.test(d)?RegExp.$1:0;if(a!=0){var b=f.offsetHeight-e.clientHeight;e.scrollTop=b;c.autoScroll("up",+a);setTimeout(function(){n()},+a)}})()}};return function(){return{init_scroll:b,autoScroll:c}}});
STK.register("comp.widget.show.style",function(a){var b=a.core,c=b.evt.addEvent,d=b.evt.getEvent,e=b.str.trim,f=b.evt.reomveEvent,g=b.dom.sizzle,h=b.arr.foreach,i=b.evt.fireEvent,j=b.evt.stopEvent,k=a.E("weibo_list_con"),l=a.E("weibo_list"),m=a.E("fans_list_con"),n=location.search,o;(function(){var b=!1;o=function(b){function e(){document.getElementsByTagName("head")[0].appendChild(c)}var c=document.createElement("style");c.type="text/css";if(a.IE)c.styleSheet.cssText=b;else{var d=document.createDocumentFragment();d.appendChild(document.createTextNode(b));c.appendChild(d)}e()}})();var p=function(){var b=/colors=([A-Fa-f\d,]+)/.test(n)?RegExp.$1:"";if(b){b=b.split(",");var c="";b[0]&&(c+=".weiboShow .weiboShow_topborder, .weiboShow .weiboShow_title {background:#"+b[0]+";}\n");b[1]&&(c+=".weiboShow .weiboShow_wrap { background:#"+b[1]+" }\n");b[2]&&(c+=".weiboShow { color:#"+b[2]+";}\n .weiboShow .weiboShow_developerDetail_namedir {color:#"+b[2]+" }\n");b[3]&&(c+=".weiboShow a,\n .weiboShow .WB_linkA a,\n.weiboShow .WB_linkA, \n.weiboShow .WB_linkB a,\n.weiboShow .WB_linkB {color:#"+b[3]+" }");b[4]&&(c+=".weiboShow .weiboShow_mainFeed_list:hover, .weiboShow .weiboShow_mainFeed_list_focus{background:#"+b[4]+" ;}");o(c)}var d=/noborder=(\d+)/.test(n)?RegExp.$1:1;d==0&&(a.E("pl_weibo_show").className="WB_widgets weiboShow weiboShow_noborder");try{var e={};e.fansRow=/fansRow=(\d+)/.test(n)?RegExp.$1:2;e.isTitle=a.E("weibo_title")?1:0;e.isFans=m?1:0;e.isWeibo=a.E("weibo_con")?1:0;e.height=parseInt(a.E("pl_weibo_show").style.height,10);e.width=a.E("pl_weibo_show").offsetWidth}catch(f){}var h=e.height-30;e.isTitle==1&&(h-=30);a.E("weibo_head")&&(h-=86);if(!(h<0)){if(e.isFans){var i=g("li",m),j=0;if(i.length==0)j=m.offsetHeight;else{var p=m.getElementsByTagName("ul")[0],q=e.width,r=Math.floor((q-11)/66);r==0&&(r=1);var s="0 "+(q-16-r*66)/2+"px";try{p.style.padding=s}catch(f){var t=a.core.dom.cssText(p.style.cssText);t.push("padding",s);p.style.cssText=t.getCss()}var u=Math.ceil(i.length/r);e.fansRow=Math.min(e.fansRow,u,(h-30)/84>>0||1);j=e.fansRow*84;j>0&&(j-=12);j+=30;j>=h&&(j=h);m.style.height=parseInt(j-3)+"px"}h-=j}if(e.isWeibo){if(h<32){a.E("weibo_con").style.display="none";return}try{var v=null,w=function(){var b=l.offsetHeight;v&&clearInterval(v);if(b>0){var c;if(a.sizzle(".weiboShow_main_errorBox").length){c=(h-20>b?h-20:b)+"px";l.style.height=c}else c=(h-20>b?b:h-6)+"px";k.style.height=c}else v=setInterval(w,500)};w()}catch(f){}}}};return p});
typeof App=="undefined"&&(App={});typeof scope=="undefined"&&(scope=$CONFIG);STK.register("comp.widget.show.init",function(a){var b=a.core,c=b.evt.addEvent,d=b.evt.getEvent,e=b.str.trim,f=b.evt.reomveEvent,g=b.dom.sizzle,h=b.io.ajax,i=b.arr.foreach,j=b.evt.fireEvent,k=b.evt.stopEvent,l=a.kit.extra.language,m=a.comp.widget.show.scroll(),n=m.init_scroll,o=m.autoScroll,p=location.search,q=a.E("weibo_list_con"),r=a.E("weibo_list"),s=a.E("fans_list_con");return function(b,c){var d={},e=function(){a.comp.widget.show.style();n();f()},f=function(){var b=a.E("followBtn");b&&a.common.widget.addFollow(b,{uid:scope.$uid});a.comp.widget.show.error()},g=function(){i(["up","down"],function(b,c){removeEvent(a.E("weibo_"+b+"btn"),"mouseover",function(){o.start(b)});removeEvent(a.E("weibo_"+b+"btn"),"mouseout",function(){o.stop()})})};e();d.destroy=g;return d}});
STK.pageletM.register("pl.widget.show",function(a){try{var b={},c=a.E("pl_weibo_show"),d=a.comp.widget.show.init(c,b);return d}catch(e){}});
STK.pageletM.start();