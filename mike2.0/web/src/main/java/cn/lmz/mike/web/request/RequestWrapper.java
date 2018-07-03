package cn.lmz.mike.web.request;


import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RequestWrapper extends HttpServletRequestWrapper {
    //保存流中的数据
    private final byte[] body;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        //从流中获取数据
        body = IOUtils.toByteArray(request.getInputStream());
    }

    public ServletInputStream getInputStream(){
        //在调用getInputStream函数时，创建新的流，包含原先数据流中的信息，然后返回

        return new CustomServletInputStream(new ByteArrayInputStream(body));
    }

    class CustomServletInputStream extends ServletInputStream{
        private InputStream inputStream;

        public CustomServletInputStream(InputStream inputStream){
            this.inputStream = inputStream;
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public boolean isFinished() {
            try {
                return inputStream.available() == 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }
}
