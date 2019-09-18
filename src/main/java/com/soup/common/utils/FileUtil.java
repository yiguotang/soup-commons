package com.soup.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * <p>
 * Description: 文件工具类
 * </p>
 *
 * @author zhaoyi
 * @date 2018-05-31 20:23
 */
@Slf4j
public final class FileUtil {

    private FileUtil() {
    }

    /**
     * 上传文件，返回上传文件的名称
     *
     * @param multipartFile 待上传的文件
     * @param path 存储路径
     * @return 新文件名
     */
    public static String saveFile(MultipartFile multipartFile, String path) {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            boolean mkdirsResult = file.mkdirs();
            log.debug("create file dir result: {}", mkdirsResult);
        }

        String originName = multipartFile.getOriginalFilename();
        try {
            String fileExtName = originName.substring(originName.lastIndexOf("."));
            // 构建新文件名
            String newFileName = DateUtil.getTime() + fileExtName;
            // 上传
            long size = Files.copy(multipartFile.getInputStream(), Paths.get(path, newFileName),
                            StandardCopyOption.REPLACE_EXISTING);
            if (size > 0) {
                return newFileName;
            }
        } catch (IOException e) {
            log.error("save file occur error! file name: {}, save path: {}", originName, path);
        }

        return null;
    }

    /**
     *  删除文件
     *
     * @param filePath 文件的绝对路径
     * @return <tt>true</tt>删除成功
     */
    public static boolean delFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                return file.delete();
            }
            return true;
        } catch (Exception e) {
            log.error("File cannot be deleted, path: {}", filePath);
        }
        return false;
    }
}
