package com.soup.common.support.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * Description: 重新包装HttpServletRequest，重写getInputStream和getReader，使其可以多次调用
 * </p>
 *
 * @author zhaoyi
 * @date 2017-08-11 23:31
 */
@Slf4j
public class RequestWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody = null;

    /**
     * HttpServletRequest 构造方法
     *
     * @param request HttpServletRequest
     */
    public RequestWrapper(HttpServletRequest request) {
        super(request);

        // 缓存请求body
        try {
            requestBody = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            log.error("get requestBody error!", e);
        }
    }

    /**
     * 重写 getInputStream()
     */
    @Override
    public ServletInputStream getInputStream() {
        if (requestBody == null) {
            requestBody = new byte[0];
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public int read() {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return bais.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener arg0) {
                // reduce sonar
            }
        };
    }

    /**
     * 重写 getReader()
     */
    @Override
    public BufferedReader getReader() {
        InputStreamReader inputStreamReader = new InputStreamReader(getInputStream(), StandardCharsets.UTF_8);
        return new BufferedReader(inputStreamReader);
    }
}
