package service;

/**
 * create by 1311230692@qq.com on 2018/10/10 11:57
 * 发送邮件业务接口
 **/
public interface MailService {
    /**
     * 简单文本邮件发送
     * @param email 邮件接收者
     * */
    void sendSimpleMail(String email);

    /**
     * 发送富文本邮件 支持文本、附件、HTML、图片等
     * @param email 邮件接收者
     * */
    void sendHtmlMail(String email);

    /**
     * 发送带附件的邮件
     * @param email 邮件接收者
     * @param filePath 附件地址
     * */
    void sendAttatchmentMail(String email, String filePath);
}
