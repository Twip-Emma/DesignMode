package file.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class FileMD5Check {
    public static void main(String[] args) {
        File file1 = new File("data/测试-真图片.png");
        File file2 = new File("data/测试-真图片.png");
        File file3 = new File("data/测试-文本文件转图片.png");

        String s1 = md5HashCode("data/测试-真图片.png");
        String s2 = md5HashCode("data/测试-真图片.png");
        String s3 = md5HashCode("data/测试-文本文件转图片.png");

        if (s1.equals(s2)) {
            System.out.println("1和2一致");
        } else {
            System.out.println("1和2不一致");
        }

        if (s1.equals(s3)) {
            System.out.println("1和3一致");
        } else {
            System.out.println("1和3不一致");
        }
    }


    /**
     * 获取文件md5值
     */
    public static String md5HashCode(String filePath) {
        try {
            InputStream fis = new FileInputStream(filePath);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();
            //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes = md.digest();
            BigInteger bigInt = new BigInteger(1, md5Bytes);
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
