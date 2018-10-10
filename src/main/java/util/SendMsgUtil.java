package util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.HashMap;

/**
 * create by 1311230692@qq.com on 2018/10/10 14:01
 * 发送验证码的工具类
 **/
public class SendMsgUtil {
    public static HashMap<String,String> getMessageStatus(String phone) throws Exception {
        HashMap<String,String> hashMap = new HashMap<>();

        // 连接第三方平台
        PostMethod postMethod = new PostMethod("http://utf8.api.smschinese.cn/");
        postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8"); // 在头文件中设置转码

        // 生成随机字符的验证码
        String randNum = String.valueOf(RandomUtil.getRandNum());

        // 短信模板
        NameValuePair[] data = {
                new NameValuePair("Uid", "****"), // sms 短信通 注册的用户名
                new NameValuePair("key", "*********"), // 密钥
                new NameValuePair("smsMob", phone), // 要发送的目标手机号
                new NameValuePair("smsText", "验证码：" + randNum + ",发送")
        };

        for (NameValuePair n: data ) {
            System.out.println("短信模板为：" + n);
        }

        postMethod.setRequestBody(data);
        HttpClient client = new HttpClient();
        client.executeMethod(postMethod);

        // 获取 http 头
        Header[] headers = postMethod.getRequestHeaders();
        int statusCode = postMethod.getStatusCode();
        System.out.println("statusCode:" + statusCode);

        for (Header h: headers) {
            System.out.println(h.toString());
        }

        // 获取返回消息
        String result = new String(postMethod.getResponseBodyAsString().getBytes("utf-8"));
        System.out.println(result);
        // 将返回消息和 6 位数验证码放入 map 里面
        hashMap.put("result",result);
        hashMap.put("code",randNum);

        // 断开第三方平台连接
        postMethod.releaseConnection();
        return hashMap;
    }
}
