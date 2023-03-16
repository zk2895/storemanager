<%@ page language="java" contentType="text/html; charset=UTF-8"  isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>示例演示</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 注意：项目正式环境请勿引用该地址 -->
  <link href="//unpkg.com/layui@2.7.6/dist/css/layui.css" rel="stylesheet">
</head>
<body>
<form class="layui-form" action="" lay-filter="inoutwareform" >
<div class="layui-form">
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">根据年查询出入库数量</label>
      <div class="layui-input-inline">
        <input name="year" type="text" class="layui-input" id="test1" placeholder="yyyy">
      </div>
    </div>
    <br>
    <div class="layui-inline">
      <label class="layui-form-label">根据月查询出入库数量</label>
      <div class="layui-input-inline">
        <input name="month" type="text" class="layui-input" id="test2" placeholder="MM">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">出库数量:</label>
      <div class="layui-input-inline">
        <input id="out" type="text" name="num" autocomplete="off" class="layui-input" readonly>
      </div>
    </div>
    <br>
    <div class="layui-inline">
      <label class="layui-form-label">根据日查询出入库数量</label>
      <div class="layui-input-inline">
        <input name="day" type="text" class="layui-input" id="test3" placeholder="dd">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">入库数量:</label>
      <div class="layui-input-inline">
        <input id="in" type="text" name="num2" autocomplete="off" class="layui-input" readonly>
      </div>
    </div>
  </div>
</div>
<div class="layui-btn-group">
					<button id="save" type="button" class="layui-btn">查询</button>
				</div>
</form>
<!-- 注意：项目正式环境请勿引用该地址 -->
<script src="//unpkg.com/layui@2.7.6/dist/layui.js"></script>

<script>
layui.use(['form', 'table','layer','laydate'], function(){
	var $ = layui.jquery,
    form = layui.form,
    table = layui.table,
    layer=layui.layer,
	laydate=layui.laydate;  //年选择器
  laydate.render({
    elem: '#test1'
    ,type: 'year'
  });
  
  //年月选择器
  laydate.render({
    elem: '#test2'
    ,type: 'month'
  });
  //日期时间选择器
  laydate.render({
    elem: '#test3'
    ,type: 'date'
  });
  $("#save").on("click",function(){
	  var data=form.val("inoutwareform");
	  data.action="selectByYear";
	  $.post("/refer",data,function(s){
		  console.log(s.data);
		  
		  $("#out").val(s.data[0].num);
		  $("#in").val(s.data[1].num);
		 
	  });
});
});
   
</script>

</body>
</html>