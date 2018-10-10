package util;

/**
 * create by 1311230692@qq.com on 2018/10/10 14:00
 * 随机生成验证码工具类
 **/
public class RandomUtil {
    // 得到随机六位数
    public static int getRandNum() {
        return (int)((Math.random()*9+1)*100000);
    }
}
