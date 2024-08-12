package file.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

public class FileTypeTest {
    public static void main(String[] args) throws IOException {
        String base64String = null;
        String filePath = "D:\\work\\Java\\data\\input.txt";
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        if (!lines.isEmpty()) {
            base64String = lines.get(0);
        }
        System.out.println(base64String);
        if (base64String != null) {
            detectFileType(base64String);
        }
    }

    public static void detectFileType(String base64String) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);

        if (decodedBytes.length >= 4) {
            byte[] magicNumber = new byte[4];
            System.arraycopy(decodedBytes, 0, magicNumber, 0, 4);

            String magicNumberHex = bytesToHex(magicNumber);
            System.out.println(magicNumberHex);
            String fileType = determineFileType(magicNumberHex);

            System.out.println("File type: " + fileType);
        } else {
            System.out.println("Base64 string is too short to determine file type.");
        }
    }

    private static String determineFileType(String magicNumberHex) {
        switch (magicNumberHex) {
            case "504B0304": // PK\x03\x04
                return "ZIP archive";
            case "89504E47": // PNG
                return "PNG image";
            case "47494638": // GIF8
                return "GIF image";
            case "49492A00": // II*0
                return "TIFF image (little-endian)";
            case "4D4D002A": // MM*0
                return "TIFF image (big-endian)";
            case "424D": // BM
                return "BMP image";
            case "4A464946": // JFIF
                return "JPEG image";
            case "464C4946": // FLIF
                return "FLIF image";
            case "52494646": // RIFF
                return "Wave audio";
            case "41544643": // ATFC
                return "TrueType font";
            case "5044462D": // PDF-
                return "PDF document";
            case "7B5C727466": // {\\rtf
                return "RTF document";
            case "3C3F786D6C20": // <?xml
                return "XML document";
            case "D0CF11E0": // D0CF
                return "Microsoft Office document";
            case "50415254": // PART
                return "Apple ProRes video";
            case "46464646": // FFFF
                return "AV1 video";
            default:
                return "Unknown file type";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase();
    }
}
