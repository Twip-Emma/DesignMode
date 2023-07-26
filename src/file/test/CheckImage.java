package file.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CheckImage {
    public static void main(String[] args) {
        File realImage = new File("data/测试-真图片.png");
        File wrongImage = new File("data/测试-文本文件转图片.png");
        File realImage2 = new File("data/测试-真图片转文本.txt");

        // 方法1：通过是否可以被ImageIO读取器所匹配来判断是否是图片文件
        try {
            ImageIO.read(realImage);
            System.out.println("方法1-image1是真图片");
        } catch (IOException ex) {
            System.out.println("方法1-image1是假图片，异常信息为：" + ex.getMessage());
        }
        try {
            ImageIO.read(wrongImage);
            System.out.println("方法1-image2是真图片");
        } catch (IOException ex) {
            System.out.println("方法1-image2是假图片，异常信息为：" + ex.getMessage());
        }


        // 方法2：通过校验文件头判断文件类型
        try {
            System.out.println("方法2-image2类型：" + getFileType(new FileInputStream(realImage)));
            System.out.println("方法2-image2类型：" + getFileType(new FileInputStream(wrongImage)));
        } catch (IOException ex) {
            System.out.println("方法2-异常：" + ex.getMessage());
        }


        // 方法3：通过判断图片的长宽是否异常
        try {
            Image img = ImageIO.read(realImage);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                System.out.println("方法3-image1是假图片");
            } else {
                System.out.println("方法3-image1是真图片");
            }
            Image img2 = ImageIO.read(wrongImage);
            if (img2 == null || img2.getWidth(null) <= 0 || img2.getHeight(null) <= 0) {
                System.out.println("方法3-image2是假图片");
            } else {
                System.out.println("方法3-image2是真图片");
            }
        } catch (Exception ex) {
            System.out.println("方法3-异常：" + ex.getMessage());
        }
    }

    public static FileType getFileType(InputStream is) throws IOException {
        byte[] src = new byte[28];
        is.read(src, 0, 28);
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            if (stringBuilder.toString().startsWith(fileType.getValue())) {
                return fileType;
            }
        }
        return null;
    }
}
