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
   <div class="layui-inline">
      <label class="layui-form-label">根据日期查询单据</label>
      <div class="layui-input-inline">         
	<input name="year" type="text" class="layui-input" id="input1" placeholder="yyyy-MM-dd"/>
	 </div>
	 </div>
	<div class="layui-input-inline">
	<input type="hidden" class="layui-input" id="input2"/>
	</div>	
	    <input id="searchBtn" type="button" value="搜索" class="layui-btn" />
		<input id="addBtn" type="button" value="新增商品单据" class="layui-btn" />
	  </div>
	</div>
	<!-- 表格属性 -->
	<table id="memberTable" lay-filter="table1"></table>
	<script src="../lib/layui-v2.6.3/layui.js"></script>
	<!-- 会员弹出窗口中的模板 -->
	<script id="memberTemplate" type="text/html">
           <form class="layui-form"  lay-filter="memberForm">
            <div class="layui-form-item">
                <label class="layui-form-label">商品id</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id"/>
                    <input type="hidden" name="createby"/>
                    <input type="hidden" name="createtime"/>
                    <input type="hidden" name="action" value="save"/>
                    <input type="text"  name="productid"  placeholder="请输入商品id" class="layui-input">
                </div>
            </div>
              <div class="layui-form-item">
                <label class="layui-form-label">商品出/入数量</label>
                <div class="layui-input-inline">
                    <input type="text" name="num" autocomplete="on" placeholder="请输入商品数量" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">单据类型:出/入库</label>
                <div class="layui-input-inline">
                   出库<input type="radio" name="type" value="1"/>
	        	   入库<input type="radio" name="type" value="0"/>   
                </div>
            </div>
    <div class="layui-form-item">
                <label class="layui-form-label">出/入库的日期</label>
                <div class="layui-input-inline">
                    <input type="date" name="storedate"  placeholder="" class="layui-input">
                </div>
            </div>
    <div class="layui-form-item">
                <label class="layui-form-label">出/入库的公司名</label>
                <div class="layui-input-inline">
                    <input type="text" name="company"  placeholder="输入出/入库的公司名" class="layui-input">
                </div>
            </div>
    <div class="layui-form-item">
                <label class="layui-form-label">联系人</label>
                <div class="layui-input-inline">
                    <input type="text" name="contacts"  placeholder="输入联系人" class="layui-input">
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
					url : "/docket?action=list",
					where:{keyword:$("#input1").val()},
					cols : [ [ {		
						title : "ID",
						field : "id",
						width : 10
					}, {
						title : "商品名称",
						field : "name",
						width : 90
					}, {
						title : "生产厂商",
						field : "factory",
						width : 90
					},{
						title : "型号",
						field : "model",
						width : 90
					},
					{
						title : "规格",
						field : "spec",
						width : 90
					},				
					{
						title : "出/入库数量",
						field : "num",
						width : 120
					}, {
						title : "出库/入库日期",
						field : "storedate"
					},{
						title : "创建日期",
						field : "createtime"
					},
					{
						title : "出库/入库公司名称",
						field : "company"
					}, {
						title : "联系人",
						field : "contacts",
						width : 90
					},
					{
						title :"操作",
						toolbar : "#operateTemplate"
					}

					] ],
					page : true,
					limits:[5,10,15,20,25,30]
				});
				
			}).click();
			//进入页面用代码点一下搜索
			//$("#searchBtn").click();		
			//查询按钮绑定事件
			$("#select").on("click",function(){
				//渲染表格	
				table.render({
					elem : "#memberTable",
					url : "/docket?action=selectRangetime",
				//	where:{keyword:$("#input1").val()},
					cols : [ [ {		
						title : "ID",
						field : "id",
						width : 10
					}, {
						title : "商品名称",
						field : "name",
						width : 90
					}, {
						title : "生产厂商",
						field : "factory",
						width : 90
					},{
						title : "型号",
						field : "model",
						width : 90
					},
					{
						title : "规格",
						field : "spec",
						width : 90
					},				
					{
						title : "出/入库数量",
						field : "num",
						width : 120
					}, {
						title : "出库/入库日期",
						field : "storedate"
					},{
						title : "创建日期",
						field : "createtime"
					},
					{
						title : "出库/入库公司名称",
						field : "company"
					}, {
						title : "联系人",
						field : "contacts",
						width : 90
					},
					{
						title :"操作",
						toolbar : "#operateTemplate"
					}

					] ],
					page : true,
					limits:[5,10,15,20,25,30]
				});
				
			});
			//编辑,删除表格
			table.on("tool(table1)", function(obj) {
				console.log(obj);
				if (obj.event == "edit") {//编辑
					//通过id查所有数据
					$.get("/docket?id="+obj.data.id+"&action=select", function(m) {
						layer.open({
							type : 1,
							area : [ "600px", "400px" ],
							title : "新增商品单据",
							content : $("#memberTemplate").html(),
							
						});						
						//会员数据回显
						console.log(m);
						form.val("memberForm", m);
						form.render();
					});					
					
				}
				if (obj.event == "del") {//删除
					layer.confirm("您确定删除该记录吗?", function() {
						$.post("/docket", {id : obj.data.id,action:"delete"
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
				
				$.post("/docket", data, function() {
					layer.closeAll();
					$("#searchBtn").click();
				});
			});

			//给新增按钮绑定事件
			$("#addBtn").on("click", function() {

				layer.open({
					type : 1,
					area : [ "600px", "400px" ],
					title : "新增单据",
					content : $("#memberTemplate").html(),
				});

				form.render();

			});

		});
	</script>
	<script>
layui.use(['form', 'table','layer','laydate'], function(){
	var $ = layui.jquery,
    form = layui.form,
    table = layui.table,
    layer=layui.layer,
	laydate=layui.laydate;  //年选择器
  laydate.render({
    elem: '#input1'
    ,type: 'date'
  });
});
	</script>
</body>
</html>