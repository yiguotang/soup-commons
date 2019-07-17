package com.soup.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * <p>
 * Description: 文件工具类
 * </p>
 *
 * @author zhaoyi
 * @create 2018-05-31 20:23
 */
public final class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    /**
     * 上传文件，返回上传文件的名称
     *
     * @param is 待上传的文件
     * @param originName 文件原名称
     * @param path 存储路径
     * @return 新文件名
     */
    public static String saveFile(InputStream is, String originName, String path) {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            boolean result = file.mkdirs();
            LOGGER.info("mkdirs result:{}", result);
        }

        try {
            String fileExtName = originName.substring(originName.lastIndexOf("."));
            // 构建新文件名
            String newFileName = DateUtil.getTime(DateUtil.DATE_FORMATE_LONG) + fileExtName;
            // 上传
            long size = Files.copy(is, Paths.get(path, newFileName), StandardCopyOption.REPLACE_EXISTING);
            if (size > 0) {
                return newFileName;
            }
        } catch (IOException e) {
            LOGGER.error("save file occur error! file name: {}, save path: {}", originName, path);
        }

        return null;
    }
}
