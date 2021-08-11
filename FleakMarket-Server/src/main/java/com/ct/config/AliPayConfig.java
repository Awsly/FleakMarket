package com.ct.config;

import java.io.FileWriter;
import java.io.IOException;

public class AliPayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号66666666666666666
    public static String APP_ID = "2016102400751528";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCzor9P77qvpkd/D7rtVytuKDJANculZx4kJLTSnJpvY75b0wTPyUEo//TUm61o/JkXOnz9PxxrIr3E5Jyky8AkH2p6JS90pwKhFcXUTs0bKEKGBoseHI3YKlCro7U7LjDiBjJOZpfYCUJSxUYQLY216FC7UImuBPRtgrjtJCn5g6OPtWbB4qfuc7ulPBz5yiLXR9KPPRvVS0OBi+p9ba/23+leusHQjJAwA2XjaB7I4SGkmCFuhgKVeknFgVF5aasMVvvHaAL+K/tyqUZp5Erj4O00xdFsQEkkFcwSEKinZl19ekEVzROV0Uv9NUoztrw2O5Reveg77BIRibmygnIdAgMBAAECggEAVWUx01tggS3n4SRmNXvx/n0HXnT0aZ36uy3j9BdUZ02gaeCH8gt3eXksN4IGTtl+IYfBcVZ2OQwb8KTJPqJJpiJ1KLnStH7Mhi1mjUYkxRs/fJ1Rty/V0oRTMzUw64BK5rgRZCnrV10XdWeopfox2t8kuddQjDR4yjKRnNJLTfyTn8ffjFTfpMjNLDSLj79MpqdStqMZTh9J+zXoXL8Zumhotz1Z7j6/kuASGLA65tJfbT+gR00RP22g5uB5ZIR7gVX8dfbI3uwrBNlqfLMLBC8V5dHCdB8ZaBqbNiUSQrXS93pwaNc6BUVIGQk2ojgqQikIGVFnMiPDIvMsbjNGVQKBgQD1Xn6aQNMu61rkZ2fErWPhQzGJ57jDxdmV2FNK4ZD76BCKKlCEmkXSFNG+zvQz7yqP4fLG7WnJcZEqTYW2KLigaOkNmqNN4mqnVUjvEPmFzMhpu46Vn82cRRC2kLXjvsLBa6FTtivkw+sbC+ThMVRASfyc6BMlnMa0O0hdYTCNuwKBgQC7aywr7R5URtvazsExa+eMDKFenmGYSEz45lkkPfAwrCGXCKabnKu23sLQZZfBR8LLUK8m7UzlZZ9yX7TwPvf0MwZ68tYvsy+Csli6wWImBA5hoOgV3J9lXYdDVVvI5VGZP7HdWR/wbJ9XYyQwalj7N7VUgn7PxchU5jOxUbmWBwKBgFM7fainqrt0vL1T0mBmDMaB+5DzGLrYJ2nsGr0A7SR2YP3tQqjoiByuUUPvgekIDSEHnUobiNH7gymdaGkVQSOw0rV5qkGmN1WR2Y627F2VzLoJFGHu9lk3GGdFiV26/OeNsgjqd9M/HkZrb3VwPUsdjeJP2wbcOo8yuOZsLIM3AoGBAJMkYEiYYHBwNVoDa0Ae5Gpa3DKKDDtfwDbFuoSusC22ZqTKYOvZRCNNnwu4Rz2y1meDkA/yzKVpdJZ6N3dLKKarjNHoZ2qJtqpY1TrCawlHs/bIOo43aVlO5cxibq46BCdaD42l1GKRFUSpjJcKV5PcN9wCnAcJ0+970GfNrvPvAoGAUhvLdEYm5bFydC57as1Ee+yKkvILvAEcqF8gAR8kctLjkb9PSloQD8s4jZharDEioeFRMpfLm0foSnaM2rmKenvASHx5QcPrpro4krG3qhSNbGSrKFAI5ZCrez4isMHh07WPkSK3ZxA1yOC7RnCYN6K6GXvL4pmBQdpw7SEVZ9Q=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1ILYnYHLLoC7YGtnGJVE1OlK7fETxxkfpkQnKIkKim3yZ9LGuj8IjY4BFMNIR1VD01Qsw/IUMcr9KE2faAcDBaxFTfu5guHOerTSWKN+ED3AgChqBHwkalgze5fVYRyCkCRYNwgB66F0sMmvfScUgziMKTTzT1zilapCpMn2H8LWSS5ZLux1LwFFAnrvN+8472/iT4RRL8QSWD9AkVh+mS87ZJup2uj7AQgf1QpQkGJAJOS3WsFwPkGe5ENbZLWPT5vJNMKd/Nry4IBzLp8eN5U+VrR2oVrPESpfavPOukbPraCuOisvD3LPdt6Cp4uiOo7H+YH0+02gDPwWFf/grQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:7777/#/mine";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:7777/#/mine";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "UTF-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";




    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
