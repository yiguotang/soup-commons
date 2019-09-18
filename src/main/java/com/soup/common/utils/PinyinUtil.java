package com.soup.common.utils;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

/**
 * 拼音工具类
 *
 * @author zhaoyi
 */
@Slf4j
public final class PinyinUtil {

    private static HanyuPinyinOutputFormat defaultFormat;

    static {
        defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    private PinyinUtil() {
    }

    /**
     * 转换字符中的汉字为拼音
     */
    public static String toPinyin(String str) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(str)) {
            char[] strChars = str.toCharArray();
            for (char strChar : strChars) {
                if (strChar > 128) {
                    try {
                        String pinyin = PinyinHelper.toHanyuPinyinStringArray(strChar, defaultFormat)[0];
                        sb.append(pinyin);
                    } catch (BadHanyuPinyinOutputFormatCombination e) {
                        log.error("[{}]-[{}] convert to pinyin occur error!", str, strChar, e);
                    }
                } else if (strChar != 32) {
                    sb.append(strChar);
                }
            }
        }

        return sb.toString();
    }
}
