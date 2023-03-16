<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>member</title>
<link href="../lib/layui-v2.6.3/css/layui.css" rel="stylesheet">
</head>

<body>
	<div style="margin: 15px 0 0 15px">	
	<lable class="layui-form-label">商品名称</lable>	
	<div class="layui-input-inline">
	<input type="text" class="layui-input" id="input1"/>
	</div>
	</div>
	<div style="margin: 15px 0 0 15px">	
	<lable class="layui-form-label">生产厂商</lable>
	<div class="layui-input-inline">
	<input type="text" class="layui-input" id="input2"/>
	</div>		
	    <input id="searchBtn" type="button" value="搜索" class="layui-btn" />
		<input id="addBtn" type="button" value="新增商品" class="layui-btn" />
	  </div>
	
	<!-- 表格属性 -->
	<table id="memberTable" lay-filter="table1"></table>
	<script src="../lib/layui-v2.6.3/layui.js"></script>
	<!-- 会员弹出窗口中的模板 -->
	<script id="memberTemplate" type="text/html">
           <form class="layui-form"  lay-filter="memberForm">
            <div class="layui-form-item">
                <label class="layui-form-label">商品名称</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id"/>
                    <input type="hidden" name="status" value="0"/>
                    <input type="hidden" name="action" value="save"/>
                    <input type="text"  name="name"  placeholder="请输入商品名称" class="layui-input">
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">生产厂商</label>
                <div class="layui-input-inline">
                    <input type="text" name="factory" autocomplete="on" placeholder="请输入生产厂商" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">型号</label>
                <div class="layui-input-inline">
                    <input type="text" name="model" autocomplete="on" placeholder="请输入商品型号" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">规格</label>
                <div class="layui-input-inline">
                    <input type="text" name="spec" autocomplete="on" placeholder="请输入商品规格" class="layui-input">
                </div>
            </div>           
        <div class="layui-form-item">
                <label class="layui-form-label">商品库存量</label>
                <div class="layui-input-inline">
                    <input type="text" name="storenum" autocomplete="on" placeholder="请输入商品库存量" class="layui-input">
                </div>
            </div>
              <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button"   id="saveBtn"  value="立即提交" class="layui-btn" >立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
            </form>
</script>

	<script type="text/html" id="operateTemplate">
       <input type="button" class="layui-btn layui-btn-xs" value="编辑" lay-event="edit"/>
       <input type="button" class="layui-btn layui-btn-xs layui-btn-danger" value="删除" lay-event="del"/>
</script>
	<script type="text/javascript">
		layui.use([ "table", "layer", "form" ], function() {
			var table = layui.table;
			var $ = layui.jquery;
			var layer =layui.layer;
			var form = layui.form;
			
			//搜索按钮绑定事件
			$("#searchBtn").on("click",function(){
				//渲染表格	
				table.render({
					elem : "#memberTable",
					url : "/product?action=list",
					where:{keyword1:$("#input1").val(),keyword2:$("#input2").val()},
					cols : [ [ {
						title : "ID",
						field : "id"
					}, {
						title : "商品名称",
						field : "name"
					}, {
						title : "生产厂商",
						field : "factory"
					}, {
						title : "商品型号",
						field : "model"
					}, {
						title : "商品规格",
						field : "spec"
					}, {
						title : "商品库存量",
						field : "storenum"				
					},
					{
						title : "操作",
						toolbar : "#operateTemplate"
					}

					] ],
					page : true,
					limits:[5,10,15,20,25,30]
				});
				
			}).click();
			//进入页面用代码点一下搜索
			//$("#searchBtn").click();		
			
			//编辑,删除表格
			table.on("tool(table1)", function(obj) {
				console.log(obj);
				if (obj.event == "edit") {//编辑
					//通过id查所有数据
					$.get("/product?id="+obj.data.id+"&action=select", function(m) {
						layer.open({
							type : 1,
							area : [ "600px", "400px" ],
							title : "新增商品",
							content : $("#memberTemplate").html(),
							
						});						
						//会员数据回显
						console.log(m);
						form.val("memberForm", m);
						form.render();
					});					
					
				}
				if (obj.event == "del") {//删除
					layer.confirm("您确定删除该商品吗?", function() {
						$.post("/product", {id : obj.data.id,action:"delete"
						}, function() {
							layer.closeAll();
							$("#searchBtn").click();
						});
					});

				}

			});
			//绑定提交保存按钮  	  
			$("body").on("click", "#saveBtn", function() {
				var data = form.val('memberForm');
				console.log(data);
				
				$.post("/product", data, function() {
					layer.closeAll();
					$("#searchBtn").click();
				});
			});

			//给新增按钮绑定事件
			$("#addBtn").on("click", function() {

				layer.open({
					type : 1,
					area : [ "600px", "400px" ],
					title : "新增商品",
					content : $("#memberTemplate").html(),
				});

				form.render();

			});

		});
	</script>
</body>
</html>