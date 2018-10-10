package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by 1311230692@qq.com on 2018/10/10 13:34
 * 页面跳转控制层实现
 **/
@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * 跳转 mail 邮件发送页面
     * */
    @RequestMapping("/mail")
    public String toMailPage() {
        return "page/mail";
    }

    /**
     * 跳转 sms1 秒嘀短信平台页面
     * */
    @RequestMapping("/sms1")
    public String toSms1Page() {
        return "page/sms1";
    }

    /**
     * 跳转 sms2 网建短信平台页面
     * */
    @RequestMapping("/sms2")
    public String toSms2Page() {
        return "page/sms2";
    }
}
