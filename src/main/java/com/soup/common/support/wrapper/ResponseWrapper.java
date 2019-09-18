package com.soup.common.support.wrapper;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * Description: 重新包装HttpResponse
 * </p>
 *
 * @author zhaoyi
 * @date 2017-08-11 23:31
 */
@Slf4j
public class ResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    private HttpServletResponse response;
    private PrintWriter pwrite;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        // 将数据写到 byte 中
        return new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {

            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener listener) {

            }
        };
    }

    /**
     * 重写父类的 getWriter() 方法，将响应数据缓存在 PrintWriter 中
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        pwrite = new PrintWriter(new OutputStreamWriter(bytes, StandardCharsets.UTF_8));
        return pwrite;
    }

    /**
     * 获取缓存在 PrintWriter 中的响应数据
     * @return
     */
    public byte[] getBytes() {
        if(null != pwrite) {
            pwrite.close();
            return bytes.toByteArray();
        }

        if(null != bytes) {
            try {
                bytes.flush();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return bytes.toByteArray();
    }
}
