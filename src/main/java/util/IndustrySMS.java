package util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * create by 1311230692@qq.com on 2018/10/10 13:58
 * 秒嘀科技短信验证码
 * 验证码通知短信接口
 **/
public class IndustrySMS {
    private static String operation = "/industrySMS/sendSMS";

    private static String accountSid = Config.ACCOUNT_SID;
    private static String to = "";
    private static String param = "123456";
    private static String smsContent = "【易科技】尊敬的用户，您好，登录验证码：";


    /**
     * 验证码通知短信
     */
    public static void execute() throws IOException {

        HashMap<String, String> map = new HashMap<>();

        // 生成验证码
        param = String.valueOf(RandomUtil.getRandNum());

        String tmpSmsContent = null;
        try {
            tmpSmsContent = URLEncoder.encode(smsContent + param + "，如非本人操作，请忽略此短信。", "UTF-8"); // 注意模板中的逗号是中文的
            System.out.println(smsContent + param + "， 如非本人操作，请忽略此短信。");
        } catch (Exception e){

        }

        System.out.println("生成验证码为：" + param);


        String url = Config.BASE_URL + operation;
        String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
                + HttpUtil.createCommonParam();

        // 提交请求
        String result = HttpUtil.post(url, body);
        System.out.println("result:" + System.lineSeparator() + result);

        String status = result.substring(12,18);
        System.out.println("status:" + status);
    }

    public static HashMap<String, String> executeHash(String phone) throws Exception {
        HashMap<String, String> map = new HashMap<>();

        // 生成验证码
        param = String.valueOf(RandomUtil.getRandNum());

        String tmpSmsContent = null;
        try {
            tmpSmsContent = URLEncoder.encode(smsContent + param + "，如非本人操作，请忽略此短信。", "UTF-8"); // 注意模板中的逗号是中文的
            System.out.println(smsContent + param + "， 如非本人操作，请忽略此短信。");
        } catch (Exception e){

        }

        System.out.println("生成验证码为：" + param);


        String url = Config.BASE_URL + operation;
        String body = "accountSid=" + accountSid + "&to=" + phone + "&smsContent=" + tmpSmsContent
                + HttpUtil.createCommonParam();

        // 提交请求
        String result = HttpUtil.post(url, body);
        System.out.println("result:" + System.lineSeparator() + result);

        String status = result.substring(13,18);
//        System.out.println("status:" + status);

        map.put("respCode", status);
        map.put("checkCode",param);

        System.out.println("map:" + map);

        return map;
    }

}
