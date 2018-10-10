package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MailService;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * create by 1311230692@qq.com on 2018/10/10 13:08
 * 邮件发送控制层实现
 **/
@Controller
@RequestMapping("/mail")
public class MailController {

    @Resource(name="MailService")
    private MailService mailService;

    /**
     * 发送 HTML 账户激活邮件
     * */
    @RequestMapping("/send")
    @ResponseBody
    public String send(String email) throws Exception{
        HashMap<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        mailService.sendHtmlMail(email);
        map.put("result", "success");

        return objectMapper.writeValueAsString(map);
    }
}
