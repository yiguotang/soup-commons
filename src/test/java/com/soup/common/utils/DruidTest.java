package com.soup.common.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

/**
 * druid加密
 *
 * @author zhaoyi
 */
public class DruidTest {

    /**
     * 测试解密
     * 加密方法：
     *  java -cp druid-1.1.10.jar com.alibaba.druid.filter.config.ConfigTools 123456
     * @throws Exception
     */
    @Test
    public void db_decrypt_test() throws Exception {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAITW2233FYXjOUp3myBKqfQnTxwkMemXH16Rk8s3ePgRXYOiYMzoiFv33KyyTUVE0r++Zh2eJ9Wm2wV0EFvyzSUCAwEAAQ==";
        String password = "OB2KTyGuZN5H5smgTnzVVwkVyazCyvEA8libP6J7GX2FDiaAwo9n1qvk90jEuZ7nCmB9W3IKKhfwJZHshDxmPw==";
        System.out.println(ConfigTools.decrypt(publicKey, password));
    }
}
