package com.ruoyi.project.tool.socket.server;

import org.smartboot.socket.Protocol;
import org.smartboot.socket.extension.decoder.DelimiterFrameDecoder;
import org.smartboot.socket.transport.AioSession;

import java.nio.ByteBuffer;
import java.util.List;

//客户端解码类
public class CurrencyServerProtocol implements Protocol<byte[]> {

    private byte[] protocolBytes = null;

    private List<byte[]> protocolBytesList = null;

    private CurrencyServerProtocol() {
    }

    public CurrencyServerProtocol(byte[] protocolBytes) {
        this.protocolBytes = protocolBytes;
    }

    public CurrencyServerProtocol(List<byte[]> protocolBytesList) {
        this.protocolBytesList = protocolBytesList;
    }

    public byte[] decode(ByteBuffer byteBuffer, AioSession aioSession) {
        DelimiterFrameDecoder delimiterFrameDecoder = null;
        byte[] bytes;
        boolean isdecode = false;

        if (protocolBytesList != null) {
            for (byte[] protocolBytes : protocolBytesList) {
                delimiterFrameDecoder = new DelimiterFrameDecoder(protocolBytes, 64);
                aioSession.setAttachment(delimiterFrameDecoder);
                if (delimiterFrameDecoder.decode(byteBuffer)) {
                    isdecode = true;
                    break;
                } else {
                    continue;
                }
            }
        } else {
            delimiterFrameDecoder = new DelimiterFrameDecoder(protocolBytes, 64);
            aioSession.setAttachment(delimiterFrameDecoder);
            if (delimiterFrameDecoder.decode(byteBuffer)) {
                isdecode = true;
            }
        }

        if (!isdecode) {
            return null;
        }

        ByteBuffer decodeByteBuffer = delimiterFrameDecoder.getBuffer();
        int length = decodeByteBuffer.remaining();
        bytes = new byte[length];
        decodeByteBuffer.get(bytes);
        aioSession.setAttachment(null);

        return bytes;
    }
}
