package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.IndustrySMS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * create by 1311230692@qq.com on 2018/10/10 13:54
 * 第三方平台使用秒嘀科技
 * 控制层实现
 **/
@Controller
@RequestMapping("/sms1")
public class SMS1Controller {

    /**
     * 发送验证码
     * */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public String send(String phone, HttpServletRequest request) throws Exception{
        HashMap<String, String> map = IndustrySMS.executeHash(phone);
        ObjectMapper objectMapper = new ObjectMapper();

        String status = map.get("respCode");
        String checkCode = map.get("checkCode");
        // 将验证码缓存到 session 中
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60 * 1); // 设置 session 一分钟内有效,单位是秒s
        if (status.trim().equals("00000")) {
            map.put("result", "00000");
            session.setAttribute("checkCode",checkCode);
            session.setAttribute("phone", phone);
        } else if(status.trim().equals("00141")) {
            map.put("result", "00141");
        } else {
            map.put("result","fail");
        }
        return objectMapper.writeValueAsString(map);
    }

    /**
     * 比对校验码
     * */
    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    @ResponseBody
    public String checkCode(String code, String phone, HttpServletRequest request) throws Exception{
        HashMap<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        HttpSession session = request.getSession(); // 设置 session
        String sessionCode = (String)session.getAttribute("checkCode");
        String phoneNum = (String)session.getAttribute("phone");
        if ((phoneNum).equals(phone) && (code).equals(sessionCode)) { // 和缓存比对验证码是否相同
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return objectMapper.writeValueAsString(map);
    }
}
