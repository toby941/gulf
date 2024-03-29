<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf"%>
<link rel="stylesheet/less" href="/style/markdown.less">
<script src="/less/less-1.2.2.min.js"></script>

<script src="/bootstrap/js/bootstrap-transition.js"></script>
<script src="/bootstrap/js/bootstrap-modal.js"></script>
<script src="/bootstrap/js/bootstrap-tooltip.js"></script>
<script src="/js/markdown/Markdown.Converter.js"></script>
<script src="/js/markdown/Markdown.Editor.js"></script>
<script src="/js/markdown/Markdown.Sanitizer.js"></script>

<div>
	<form class="form-horizontal" action="/admin/news/edit" method="post">
		<fieldset>
			<legend>新增</legend>
			<div class="control-group">
				<label class="control-label" for="input01">标题</label>
				<div class="controls">
				<input type="hidden" name="id" value="${obj.news.id}" ">
					<input type="text" class="input-xlarge" id="input01" value="${obj.news.title}"
						required="required" name="title">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="input01">摘要</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="input02" value="${obj.news.summary}"
						placeholder="默认为正文的前140字" name="summary" required="required" >
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="select01">类型 </label>
				<div class="controls">
					<select id="select01" name="type" >
						<optgroup label="请选择">
						<c:forEach var="item" items="${obj.types}" varStatus="s">
						<option value="${s.index}"  <c:if test="${obj.news.type eq s.index}">selected="selected"</c:if>>${item}</option>
						</c:forEach>
						</optgroup>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="fileInput">特色图片</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="input03"
						placeholder="输入图片URL以 http://开头" name="imgUrl" value="${obj.news.imgUrl}"  >
				</div>
			</div>

			<div class="container">
				<div class="wmd-panel">
					<div id="wmd-button-bar"></div>
					<textarea class="wmd-input"  id="wmd-input" name="content">${obj.news.content}</textarea>
					
				</div>
				<div id="wmd-preview" class="wmd-panel wmd-preview" id="previewtext"></div>
                <input class="previewtexthidden" type="hidden" name="contentHtml">
				<%--     <div class="controls">
              <textarea class="textarea" name="content" placeholder="Enter text ..." style="width: 810px; height: 400px"></textarea>
            </div>
             --%>
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">保存更改</button>
					<button class="btn">取消</button>
				</div>
		</fieldset>
	</form>



</div>
<script>
	(function() {
		var converter1 = Markdown.getSanitizingConverter();
		var editor1 = new Markdown.Editor(converter1);
		editor1.run();
	})();
	//$('.textarea').wysihtml5();
</script>