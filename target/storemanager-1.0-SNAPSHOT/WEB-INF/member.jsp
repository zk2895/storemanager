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
	<lable class="layui-form-label">用户名</lable>		
		<div class="layui-input-inline">
	<input type="text" class="layui-input" id="input1"/>
    </div>
	</div>
	<div style="margin: 15px 0 0 15px">
	<lable class="layui-form-label">姓名</lable>
	<div class="layui-input-inline">
	<input type="text" class="layui-input" id="input2"/>
	</div>	
	    <input id="searchBtn" type="button" value="搜索" class="layui-btn" />
		<input id="addBtn" type="button" value="新增管理员" class="layui-btn" />
	 </div>
	
	<!-- 表格属性 -->
	<table id="memberTable" lay-filter="table1"></table>
	<script src="../lib/layui-v2.6.3/layui.js"></script>
	<!-- 会员弹出窗口中的模板 -->
	<script id="memberTemplate" type="text/html">
           <form class="layui-form"  lay-filter="memberForm">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id"/>
                    <input type="hidden" name="action" value="save"/>
                    <input type="text"  name="username"  placeholder="请输入用户名" class="layui-input">
                </div>
            </div>
              <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" autocomplete="on" placeholder="请输入密码" class="layui-input">
                </div>
            </div>
           <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name"  placeholder="请输入姓名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">生日</label>
                <div class="layui-input-inline">
                    <input id="birthday1" type="date" name="birthday"  placeholder="请输入生日yyyy年MM月dd日" class="layui-input">
                </div>
            </div>
          <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="tel"  placeholder="请输入电话号码" class="layui-input">
                </div>
            </div>
          <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
	        	男<input type="radio" name="sex" value="男"/>
	        	女<input type="radio" name="sex" value="女"/>                 
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
<!-- 编辑会员弹出窗口中的模板 -->
	<script id="editmember" type="text/html">
           <form class="layui-form"  lay-filter="memberForm">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id"/>
                    <input type="hidden" name="action" value="save"/>
                    <input type="text"  name="username"  placeholder="请输入用户名" class="layui-input">
                </div>
            </div>
             
           <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name"  placeholder="请输入姓名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">生日</label>
                <div class="layui-input-inline">
                    <input id="birthday1" type="date" name="birthday"  placeholder="请输入生日yyyy年MM月dd日" class="layui-input">
                </div>
            </div>
          <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="tel"  placeholder="请输入电话号码" class="layui-input">
                </div>
            </div>
          <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
	        	男<input type="radio" name="sex" value="男"/>
	        	女<input type="radio" name="sex" value="女"/>                 
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
		layui.use([ "table", "layer", "form", "laydate" ], function() {
			var table = layui.table;
			var $ = layui.jquery;
			var layer =layui.layer;
			var form = layui.form;
			var laydate = layui.laydate;
			
			//搜索按钮绑定事件
			$("#searchBtn").on("click",function(){
				//渲染表格	
				table.render({
					elem : "#memberTable",
					url : "/newMember?action=list",
					where:{keyword1:$("#input1").val(),keyword2:$("#input2").val()},
					cols : [ [ {
						title : "ID",
						field : "id"
					}, {
						title : "用户名",
						field : "username"
					}, {
						title : "姓名",
						field : "name"
					},{
						title : "电话",
						field : "tel"
					},
					{
						title : "性别",
						field : "sex"
					}, {
						title : "生日",
						field : "birthday1"
					}, {
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
					$.get("/newMember?id="+obj.data.id+"&action=select", function(m) {
						if(m.code ==0){
							//弹出窗口
						layer.open({
							type : 1,
							area : [ "600px", "400px" ],
							title : "新增管理员",
							content : $("#editmember").html(),
							
						});
						laydate.render({
							elem: '#date'
						});
						//会员数据回显
						console.log(m);
						form.val("memberForm", m.data);
						form.render();
						laydate.render({
							elem: '#date'
						});
					}else{
						layer.msg(m.msg);
					}
				});					
					
			}
				if (obj.event == "del") {//删除
					layer.confirm("您确定删除该管理员吗?", function() {
						$.post("/newMember", {id : obj.data.id,action:"delete"
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
				if(data.username == null || data.username == ""){
					layer.msg("用户名不能为空!");
					return false;			
				}
				//if(data.password == null || data.password == ""){
				//	layer.msg("密码不能为空!");
			//		return false;				
			//	}
				
				console.log(data);
				
				$.post("/newMember", data, function(result) {
					if(result.code == 0){
						layer.closeAll();
					   $("#searchBtn").click();
					}else{
						layer.msg(result.msg);
					}
					
				});
			});

			//给新增按钮绑定事件
			$("#addBtn").on("click", function() {

				layer.open({
					type : 1,
					area : [ "600px", "400px" ],
					title : "新增管理员",
					content : $("#memberTemplate").html(),
				});

				form.render();

			});

		});
	</script>
</body>
</html>