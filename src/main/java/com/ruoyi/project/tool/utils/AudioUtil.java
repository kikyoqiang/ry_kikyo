package com.ruoyi.project.tool.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioUtil {
    private static AudioUtil Instance = new AudioUtil();

    public static AudioUtil getInstance() {
        return Instance;
    }

    /**
     * pcm数据转换wav
     */
    public void pcmByteToWavFile(byte[] pcmData, String fileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            //填入参数，比特率等等。这里用的是16位 双声道 48000 hz
            WavHeader header = new WavHeader();
            //长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
            header.fileLength = pcmData.length + (44 - 8);
            header.FmtHdrLeth = 16;
            header.BitsPerSample = 16;//比特率
            header.Channels = 2;//双通道
            header.FormatTag = 0x0001;
            header.SamplesPerSec = 48000;//采样率
            header.BlockAlign = (short) (header.Channels * header.BitsPerSample / 8);
            header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
            header.DataHdrLeth = pcmData.length;
            byte[] headerBytes = header.getHeader();
            assert headerBytes.length == 44; //WAV标准，头部应该是44字节
            byte[] byteResult = new byte[headerBytes.length + pcmData.length];
            System.arraycopy(headerBytes, 0, byteResult, 0, headerBytes.length);
            System.arraycopy(pcmData, 0, byteResult, headerBytes.length, pcmData.length);
            fos.write(byteResult, 0, byteResult.length);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public class WavHeader {
        public final char fileID[] = {'R', 'I', 'F', 'F'};
        public int fileLength;
        public char wavTag[] = {'W', 'A', 'V', 'E'};
        ;
        public char FmtHdrID[] = {'f', 'm', 't', ' '};
        public int FmtHdrLeth;
        public short FormatTag;
        public short Channels;
        public int SamplesPerSec;
        public int AvgBytesPerSec;
        public short BlockAlign;
        public short BitsPerSample;
        public char DataHdrID[] = {'d', 'a', 't', 'a'};
        public int DataHdrLeth;

        public byte[] getHeader() throws IOException {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            WriteChar(bos, fileID);
            WriteInt(bos, fileLength);
            WriteChar(bos, wavTag);
            WriteChar(bos, FmtHdrID);
            WriteInt(bos, FmtHdrLeth);
            WriteShort(bos, FormatTag);
            WriteShort(bos, Channels);
            WriteInt(bos, SamplesPerSec);
            WriteInt(bos, AvgBytesPerSec);
            WriteShort(bos, BlockAlign);
            WriteShort(bos, BitsPerSample);
            WriteChar(bos, DataHdrID);
            WriteInt(bos, DataHdrLeth);
            bos.flush();
            byte[] r = bos.toByteArray();
            bos.close();
            return r;
        }

        private void WriteShort(ByteArrayOutputStream bos, int s) throws IOException {
            byte[] mybyte = new byte[2];
            mybyte[1] = (byte) ((s << 16) >> 24);
            mybyte[0] = (byte) ((s << 24) >> 24);
            bos.write(mybyte);
        }


        private void WriteInt(ByteArrayOutputStream bos, int n) throws IOException {
            byte[] buf = new byte[4];
            buf[3] = (byte) (n >> 24);
            buf[2] = (byte) ((n << 8) >> 24);
            buf[1] = (byte) ((n << 16) >> 24);
            buf[0] = (byte) ((n << 24) >> 24);
            bos.write(buf);
        }

        private void WriteChar(ByteArrayOutputStream bos, char[] id) {
            for (int i = 0; i < id.length; i++) {
                char c = id[i];
                bos.write(c);
            }
        }
    }
}
