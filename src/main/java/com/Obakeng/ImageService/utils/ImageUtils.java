package com.Obakeng.ImageService.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {
    public static byte[] compressImage(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4*1024];
        while (!deflater.finished()){
            int size = deflater.deflate(temp);
            byteOutputStream.write(temp, 0, size);
        }
        try {
            byteOutputStream.close();
        }catch (Exception ignored){
        }
        return byteOutputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4*1024];
        try {
            while (!inflater.finished()){
                int count = inflater.inflate(temp);
                byteOutputStream.write(temp, 0,count);
            }
            byteOutputStream.close();
        } catch (Exception ignored) {
        }
        return byteOutputStream.toByteArray();
    }
}
