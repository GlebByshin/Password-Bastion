package todo.passwordbastion;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

public class Cryptography {
    // Ключ должен быть 16 символов (128 бит для AES)
    private static final String SECRET_KEY;

    static {
        try {
            Properties props = new Properties();
            props.load(Files.newInputStream(Paths.get("src/main/resources/application.properties")));
            SECRET_KEY = props.getProperty("secret.key");
            if (SECRET_KEY == null || SECRET_KEY.length() != 16) {
                throw new IllegalArgumentException("Secret key must be 16 characters long for AES-128");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load secret key", e);
        }
    }



    public static String encryptAndAddPassword(String input) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // простой режим ECB
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(input.getBytes());
            String base64 = Base64.getEncoder().encodeToString(encrypted);
            Files.writeString(Paths.get("src/main/resources/todo/passwordbastion/data/encrypted.txt"),
                   base64);
            System.out.println(encrypted.toString());
            return base64; // возвращаем строку Base64
        } catch (Exception e) {
            throw new RuntimeException("Encryption error", e);
        }
    }
    public static String decrypt(String input) {
        try {
            byte[] text = Base64.getDecoder().decode(input);
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // простой режим ECB
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] decrypted = cipher.doFinal(text);
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String encrypt(String input) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // простой режим ECB
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(input.getBytes());
            String base64 = Base64.getEncoder().encodeToString(encrypted);
            System.out.println(encrypted.toString());
            return base64; // возвращаем строку Base64
        } catch (Exception e) {
            throw new RuntimeException("Encryption error", e);
        }
    }
}
