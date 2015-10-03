import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Andrei on 2/4/2015.
 */
public class Encryption {

    public KeyGenerator keygenerator;
    public SecretKey myDesKey;
    public Cipher desCipher;

    public String planeText;
    public String encryptText;
    public String decryptText;

    public String instance;
    byte[] textEncrypted;


    public void setKeyGenerator(String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        keygenerator = KeyGenerator.getInstance(key);
        myDesKey = keygenerator.generateKey();

        if(key.equals("DES")) {
            instance = "DES/ECB/PKCS5Padding";
            // Create the cipher
            desCipher = Cipher.getInstance(instance);
            // Initialize the cipher for encryption
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
        }else if(key.equals("AES")) {
            instance = "AES";
            // Create the cipher
            desCipher = Cipher.getInstance(instance);
            // Initialize the cipher for encryption
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
        }else if(key.equals("DESede")) {
            instance = "DESede";
            // Create the cipher
            desCipher = Cipher.getInstance(instance);
            // Initialize the cipher for encryption
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
        }
    }

    public void encryptDES() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //sensitive information
        byte[] text = planeText.getBytes();
        System.out.println("Text : " + planeText);

        // Encrypt the text
        textEncrypted = desCipher.doFinal(text);
        System.out.println("Text Encryted : " + new String(textEncrypted));
        encryptText = new String(textEncrypted);
    }

    public void dencryptDES() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Initialize the same cipher for decryption
        desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

        // Decrypt the text
        byte[] textDecrypted = desCipher.doFinal(textEncrypted);
        decryptText = new String(textDecrypted);

        System.out.println("Text Decryted : " + new String(textDecrypted));
    }

    public static void decryptDES(){

    }

}
