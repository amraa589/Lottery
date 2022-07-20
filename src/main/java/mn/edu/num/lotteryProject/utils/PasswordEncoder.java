package mn.edu.num.lotteryProject.utils;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

public class PasswordEncoder {
    private static final String TOKEN = "passwd";
    private String salt;
    private int pwdIterations = 65536;
    private int keySize = 256;
    private byte[] ivBytes;
    private String keyAlgorithm = "AES";
    private String encryptAlgorithm = "AES/CBC/PKCS5Padding";
    private String secretKeyFactoryAlgorithm = "PBKDF2WithHmacSHA1";

    public PasswordEncoder(){
        this.salt = getSalt();
    }

    private String getSalt(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String text = new String(bytes);
        return text;
    }

    /**
     *
     * @param plainText
     * @return encrypted text
     * @throws Exception
     */
    public List<String> encrypt(String plainText) throws Exception{
        //generate key
        byte[] saltBytes = salt.getBytes("UTF-8");

        SecretKeyFactory skf = SecretKeyFactory.getInstance(this.secretKeyFactoryAlgorithm);
        PBEKeySpec spec = new PBEKeySpec(TOKEN.toCharArray(), saltBytes, this.pwdIterations, this.keySize);
        SecretKey secretKey = skf.generateSecret(spec);
        SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), keyAlgorithm);

        //AES initialization
        Cipher cipher = Cipher.getInstance(encryptAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        //generate IV
        this.ivBytes = cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encryptedText = cipher.doFinal(plainText.getBytes("UTF-8"));
        List<String> list = new ArrayList<>();
        list.add(new Base64().encodeAsString(encryptedText));
        list.add(salt);
        return list;
    }

    /**
     *
     * @param encryptText
     * @return decrypted text
     * @throws Exception
     */
    public String decrypt(String encryptText, String _salt) throws Exception {
        byte[] saltBytes = _salt.getBytes("UTF-8");
        byte[] encryptTextBytes = new Base64().decode(encryptText.getBytes());

        SecretKeyFactory skf = SecretKeyFactory.getInstance(this.secretKeyFactoryAlgorithm);
        PBEKeySpec spec = new PBEKeySpec(TOKEN.toCharArray(), saltBytes, this.pwdIterations, this.keySize);
        SecretKey secretKey = skf.generateSecret(spec);
        SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), keyAlgorithm);

        //decrypt the message
        Cipher cipher = Cipher.getInstance(encryptAlgorithm);
        this.ivBytes = cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivBytes));

        byte[] decyrptTextBytes = null;
        try {
            decyrptTextBytes = cipher.doFinal(encryptTextBytes);
        } catch (IllegalBlockSizeException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        String text = new String(decyrptTextBytes);
        return text;
    }
}