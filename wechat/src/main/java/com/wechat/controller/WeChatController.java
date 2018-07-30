package com.wechat.controller;

import com.wechat.util.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by 12168 on 2018/7/26.
 */
@Controller
@RequestMapping("wechat")
public class WeChatController {

    @RequestMapping("check")
    public void checkSignature(HttpServletRequest request, HttpServletResponse response){
        System.out.println("=======开始请求校验======");

        // 微信加密签名.
        String signature = request.getParameter("signature");
        System.out.println("signature====" + signature);
        // 时间戳.
        String timestamp = request.getParameter("timestamp");
        System.out.println("timestamp====" + timestamp);
        // 随机数.
        String nonce = request.getParameter("nonce");
        System.out.println("nonce====" + nonce);
        // 随机字符串.
        String echostr = request.getParameter("echostr");
        System.out.println("echostr====" + echostr);

        PrintWriter out = null;
        try{
            out = response.getWriter();

            // 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败.
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                System.out.println("=======请求校验成功======" + echostr);
                out.print(echostr);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(null != out){
                out.close();
                out = null;
            }
        }

    }
}
