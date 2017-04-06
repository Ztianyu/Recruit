/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 * 
 *  提示：如何获取安全校验码和合作身份者id
 *  1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *  2.点击“商家服务”(https://b.alipay.com/order/myorder.htm)
 *  3.点击“查询合作者身份(pid)”、“查询安全校验码(key)”
 */

package cn.zty.recruit.pay;

//
// 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。
// 这里签名时，只需要使用生成的RSA私钥。
// Note: 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。
public final class Keys {

    // 合作身份者id，以2088开头的16位纯数字
    public static final String DEFAULT_PARTNER = "2088021074619772";

    // 收款支付宝账号
    public static final String DEFAULT_SELLER = "yleagle@elinkdeyuan.com";

    // 商户私钥，自助生成
    public static final String PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK6/AxT57VGGXUXjiSBck5rvUWKPM0Op2F3SjKXYHDJRI12GtjU2Kl3vw/isys/1t05EG+glFpzLbwgxxaD3BOp0BJrbbVqF+NLKOyN4Jjweaa+gF4lAg3Ett1BnORO+vEBwi4Fk6p6nMlFiYcdB292n5oCqt8LJBBlbVxRXi72XAgMBAAECgYEAjZsUcEcn3Uy5AQ2l7aeXpZIRrYDF1isovr8EYtCB6PR/aCbmOLCwHHdTo4zCvUstRCtFQhXsARuJ2mYa9Jm4TL2l+1TC6RSzAbPJP2BwfzmJwdHMT5sdgsZwkVPoe2lIfw0zLUeRHNwuZJIA59uh2EmoK5xKEXw/MIpZITbjq+ECQQDmycUposSj4IgdgDt5u4mL6NeMiGQUj9u5wa5N5/f3Es7qjPiliu0sKX7Ihn3v7024b0VT4xue+DO3/cfL5dmNAkEAwdX3+6XYbObXqqcbstWEuv1Y+aJ1XbYpyRcgUCUD3MaJnfg8iLMGlS8I7yuB8nQNsQe4kfCGgnLgIqzxsO8gswJBALRQiN8S8Rgo4bBK9C0UPU82yNJf7fGyxDXKAWmVB8PeyhOvuMV+WffbI9eAXP0Nt2eWaFA2bOozehUTDvtbxjECQA8vQeP21AOD+fUmbicOTv2PBz9XGLakpJVaOG/O1oSfEPzNC4Dx9VwOeTazQ9TIfqIVpsHYA9GISkKtJanksb0CQCBamnPpVRyq6pOQ1bMisCINXZerawaTUG5rALQZBIEMFGnXlcJKw7HJ0pgQrhHOQDXS5ad+/4wjVhtcHRiJ6lo=";
    public static final String PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuvwMU+e1Rhl1F44kgXJOa71FijzNDqdhd0oyl2BwyUSNdhrY1Nipd78P4rMrP9bdORBvoJRacy28IMcWg9wTqdASa221ahfjSyjsjeCY8HmmvoBeJQINxLbdQZzkTvrxAcIuBZOqepzJRYmHHQdvdp+aAqrfCyQQZW1cUV4u9lwIDAQAB";


}
