package com.ikang.idata.common.utils;

import org.springframework.core.io.ByteArrayResource;

public class ByteArrayResource1 extends ByteArrayResource {
    public ByteArrayResource1(byte[] byteArray) {
        super(byteArray);
    }

    /**
     *发送二维码的图片到邮箱，为了让生成的二维码不落地，直接采用流的方式输出
     *ByteArrayResource 这个类 getFilename 返回值 null 邮件无法判断发送文件类型，这里就是类型的判断
     *
     * */
    @Override
    public String getFilename() {
        return "ikang.png";
    }
}
