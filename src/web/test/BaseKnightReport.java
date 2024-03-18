package web.test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class BaseKnightReport {
    public static void main(String[] args) {
        String apiUrl = "https://api.game.bilibili.com/game/player/tools/kan_gong/接口";
        String appKey = "a5e793dd8b8e425c9bff92ed79e4458f";
        String appSecret = "xoNO7qa9761mNPyLtTn8zxPeX80iLnDonYCOzqS7bG8=";

        Map<String, String> params = new HashMap<>();
        // Replace with actual request parameters
        params.put("param1", "value1");
        params.put("param2", "value2");
        // Add other request parameters

        long ts = System.currentTimeMillis() / 1000;
        String nonce = generateRandomNonce();
        params.put("ts", String.valueOf(ts));
        params.put("nonce", nonce);
        params.put("appkey", appKey);

        List<Map.Entry<String, String>> sortedParams = new ArrayList<>(params.entrySet());
        sortedParams.sort(Comparator.comparing(Map.Entry::getKey));

        StringBuilder sortedParamsStr = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams) {
            sortedParamsStr.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sortedParamsStr.deleteCharAt(sortedParamsStr.length() - 1);

        String sign = generateMD5(sortedParamsStr.toString() + "&secret=" + appSecret);

        params.put("sign", sign);

        params.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });

        // Construct the request URL
//        String apiUrlWithParams = apiUrl + "?" + sortedParamsStr.toString() + "&sign=" + sign;

        // Perform the HTTP request (you can use a library like Apache HttpClient or HttpURLConnection)
        // and process the response
//        System.out.println("API URL with Parameters: " + apiUrlWithParams);
    }

    private static String generateRandomNonce() {
        Random random = new Random();
        byte[] nonceBytes = new byte[9];
        random.nextBytes(nonceBytes);
        return bin2hex(nonceBytes[0]) + "-" + bin2hex(nonceBytes[3]) + "-" + bin2hex(nonceBytes[6]);
    }

    private static String bin2hex(byte b) {
        return String.format("%02x", b);
    }

    private static String generateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
