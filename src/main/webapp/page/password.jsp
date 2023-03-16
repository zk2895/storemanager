<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/public.css" media="all">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
		<form id="saveForm" action="">
         <div class="layui-form layuimini-form">
            <div class="layui-form-item"> 
               <label class="layui-form-label required">旧的密码</label>
               <div class="layui-input-block">
                   <label for="oldPwd"></label><input id="oldPwd" type="password" name="oldPwd" lay-verify="required" lay-reqtext="旧的密码不能为空" placeholder="请输入旧的密码" value="" class="layui-input">
                   <tip>填写自己账号的旧的密码。</tip>
               </div>
         	</div>

            <div class="layui-form-item">
               <label class="layui-form-label required">新的密码</label>
               <div class="layui-input-block">
                   <label for="newPwd"></label><input id="newPwd" type="password" name="newPwd" lay-verify="required" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码" value="" class="layui-input">
               </div>
           </div>
           <div class="layui-form-item">
               <label class="layui-form-label required">新的密码</label>
               <div class="layui-input-block">
                   <label for="againPwd"></label><input id="againPwd" type="password" name="againPwd" lay-verify="required" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码" value="" class="layui-input">
               </div>
           </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" value="确认保存" class="layui-btn layui-btn-normal" id="saveBtn">
                </div> 
               </div> 
            </div> 
         </form>
    </div>
</div>
<script src="../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['form','miniTab', 'jquery'], function () {
        let form = layui.form,
            layer = layui.layer,
            $ = layui.jquery,
            miniTab = layui.miniTab;
        
        $("#saveBtn").on("click", function(){
        	
        	let data = form.val("saveForm");
        	console.log(data);

            let oldPwd = $("#oldPwd").val();
			let newPwd = $("#newPwd").val();
            let againPwd = $("#againPwd").val();

        	$.get("/newMember?action=checkOldPwd", {oldPwd: oldPwd}, function(r){
        		console.log(oldPwd);
        		if(r.code === 0){
                    if (newPwd === oldPwd){
                        layer.msg("新密码不能与旧密码一致",{
                            icon: 5,
                            time: 2000
                        });
                        return false;
                    } else{
                        if(newPwd === againPwd){
                            $.post("/newMember", {newPwd: newPwd,action: 'changePwd'}, function(r){
                                if(r.code === 0){
                                    layer.msg(r.msg, {
                                        icon: 1,
                                        time: 1000
                                    }, function(){
                                        top.location = '/login';
                                    }); //end layer.msg
                                } //end if
                            }); //end post
                        } else {
                            layer.msg("两次输入的新密码不一致，请重新输入",{
                                icon: 5,
                                time: 1000
                            });
                            return false;
                        }
                    }

        		} 
        		if(oldPwd == ""){
        			layer.msg("输入的旧密码不能为空",{
                        icon: 5,
                        time: 1000      			
        		});
        			return false;
        		}
        		if(newPwd == ""){
        			layer.msg("输入的密码不能为空",{
                        icon: 5,
                        time: 1000      			
        		});
        			return false;
        		}
        		else {
        			layer.msg(r.msg,{
        				icon: 5,
        				time: 2000
        			}
        			);
        			return false;
        		}
        	});
        });        

    });
</script>
</body>
</html>