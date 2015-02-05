import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by andreita on 2/5/2015.
 */
public class DES3Encrypt {
    public KeyGenerator keygenerator;
    public SecretKey myDesKey;
    public Cipher desCipher;

    public String planeText;
    public String encryptText;
    public String decryptText;

    public String instance;
    byte[] textEncrypted;


    public void generateKey() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        keygenerator = KeyGenerator.getInstance("DESede");
        myDesKey = keygenerator.generateKey();
        instance = "DESede";
        // Create the cipher
        desCipher = Cipher.getInstance(instance);
        // Initialize the cipher for encryption
        desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
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
}
