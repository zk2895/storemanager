package com.situ.emp.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import com.situ.emp.util.ImageUtil;
import org.springframework.stereotype.Controller;

@Controller
@WebServlet("/image")
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 返回值类型为image图片
        response.setContentType("image/jpeg");
        // 禁止图片缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ServletOutputStream sos = response.getOutputStream();

        String code = ImageUtil.createCode(); // 随机生成验证码
        request.getSession().setAttribute("code", code); // 验证码写入session
        ImageIO.write(ImageUtil.image(code), "JPEG", sos); // 流写出图像

        // 释放资源
        sos.flush();
        sos.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
