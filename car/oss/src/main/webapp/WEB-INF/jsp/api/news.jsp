<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,user-scalable=no">
<title>苏菲雅</title>
<style type="text/css">
#main{width:320px;position:relative;overflow:hidden;margin:auto}
body{width:100%; padding:0; margin:0; color:#7F7F7F;font-size:10px; line-height:15px}
#wrapper{ margin:0 30px ;color:#7F7F7F }
img{border:0; margin:0}
p{text-align:left }
.c{text-align:center}
.cText{padding:8px 0}
.cImg{ padding:8px 0; text-align:center; width:100%}
.green{color:#82D7CD;}
h1{font-size:14px; line-height:25px; font-weight:normal; color:#82D7CD;margin:16px 0 0 0}
h2{font-size:10px; line-height:15px; font-weight:normal; margin:0}
.line{background:url(../../images/line.png) no-repeat center; width:100%; height:2px ; margin:13px 0 0 0}
.topBar{width:100%; height:10px; background-color:#82D7CD; }
.call{background:url(../../images/phone.png) no-repeat center; width:82px; height:29px; display:block; float:right; margin-top:6px}
.call:hover{background:url(../../images/phone_on.png)}
</style>
</head>
<body>
<div id="main">
<div id="wrapper">
  <h1 class="c">${title}</h1>
  <h2 class="c">${summaryContent}</h2>
  <div class="line"></div>
  <c:if test="${!empty pics}">
  <c:forEach items="${pics}" var="pic">
  <div class="cImg" ><img src="${pic}" width=260 height=200 ></div>
  </c:forEach>
  </c:if>
  <div class="cText">${content}</div>
</div>
</div>
</body>
</html>