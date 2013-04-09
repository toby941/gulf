<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <link href="/style/bootstrap-wysihtml5.css" rel="stylesheet">
  <script src="/js/wysihtml5-0.3.0.js"></script>
 <script src="/js/bootstrap-wysihtml5.js"></script>
  <div class="hero-unit" style="margin-top:20px">
  <form class="form-horizontal" action="/admin/news/add" method="post">
        <fieldset>
          <legend>新增</legend>
          <div class="control-group">
            <label class="control-label" for="input01">标题</label>
            <div class="controls">
              <input type="text" class="input-xlarge" id="input01" required="required" name="title" >
            </div>
          </div>
           <div class="control-group">
            <label class="control-label" for="input01">摘要</label>
            <div class="controls">
              <input type="text" class="input-xlarge" id="input02" placeholder="默认为正文的前140字" name="summary" >
            </div>
          </div>
            <div class="control-group">
            <label class="control-label" for="select01">类型</label>
            <div class="controls">
              <select id="select01" name="type">
              <optgroup label="请选择">
               <option value="1">日记</option>
               <option value="2">医生/医院</option>
               <option value="3">用药</option>
               <option value="4">进食</option>
               <option value="5">各种玩意</option>
              </optgroup>
               
              </select>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="fileInput">特色图片</label>
            <div class="controls">
             <input type="text" class="input-xlarge" id="input03" placeholder="输入图片URL以 http://开头" name="imgUrl" >
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="textarea">正文</label>
            <div class="controls">
              <textarea class="textarea" name="content" placeholder="Enter text ..." style="width: 810px; height: 400px"></textarea>
            </div>
          </div>
          <div class="form-actions">
            <button type="submit" class="btn btn-primary">保存更改</button>
            <button class="btn">取消</button>
          </div>
        </fieldset>
      </form>
  
  
	
	</div>
	<script>
	$('.textarea').wysihtml5();
</script>