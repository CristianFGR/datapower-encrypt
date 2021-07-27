package cl.kdu.authmethod.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by cgonzalezr on 25-03-21.
 */
@Service
public class DataPowerUtil {

    private static final String ENCRYPTION_TYPE = "AES/CBC/PKCS5Padding";
    private static final String ENCRYPTION_METHOD = "AES";
    private static Logger LOGGER = LoggerFactory.getLogger(DataPowerUtil.class);
    private byte[] iv;

    /**
     * Metodo creado para encriptar peticiones que se dirijan a datapower
     *
     * @param content contenido de la petición a encriptar
     * @param secret  sharedKey proporcionada por datapower para encriptar
     * @return String con el contenido encriptado
     */
    public String encrypt(String content, String secret) {
        LOGGER.info("EncryptDataPower valores de entrada {} ", content, secret);
        StringBuilder stringEncrypt = new StringBuilder();
        try {
            generadorIV();
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            String contentB64 = new String(Base64.encodeBase64(content.getBytes()));
            byte[] key = Base64.decodeBase64(secret);
            SecretKeySpec secretKey = new SecretKeySpec(key, ENCRYPTION_METHOD);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            String encContent = Base64.encodeBase64String(cipher.doFinal(contentB64.getBytes()));
            byte[] encContentBytes = Base64.decodeBase64(encContent);
            ByteBuffer bb = ByteBuffer.allocate(iv.length + encContentBytes.length);
            bb.put(iv);
            bb.put(encContentBytes);
            byte[] result = bb.array();
            stringEncrypt.append(Base64.encodeBase64String(result));
        } catch (Exception ex) {
            LOGGER.error("error encriptando data ", ex);
        }
        LOGGER.info("EncryptDataPower valores de salida {} ", stringEncrypt.toString());
        return stringEncrypt.toString();
    }

    /**
     * Metodo creado para desencriptar una peticion que viene desde datapower
     *
     * @param content contenido de la petición a desencriptar
     * @param secret  sharedKey proporcionada por datapower para desencriptar
     * @return String con el contenido desencriptado
     */
    public String decrypt(String content, String secret) {
        LOGGER.info("DecryptDataPower valores de entrada {} ", content, secret);
        StringBuilder stringDecrypt = new StringBuilder();
        try {
            generadorIV();
            byte[] justContent = separadorIV(content);
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            byte[] key = Base64.decodeBase64(secret);
            SecretKeySpec secretKey = new SecretKeySpec(key, ENCRYPTION_METHOD);
            String contenido = Base64.encodeBase64String(justContent);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            String resultadoB64 = new String(cipher.doFinal(Base64.decodeBase64(contenido)));
            stringDecrypt.append(new String(Base64.decodeBase64(resultadoB64)));
        } catch (Exception ex) {
            LOGGER.error("error desencriptando data ", ex);
        }
        LOGGER.info("DecryptDataPower valores de salida {} ", stringDecrypt.toString());
        return stringDecrypt.toString();
    }

    /**
     * método para generar aleatoriamente ivs
     *
     * @throws NoSuchAlgorithmException
     */
    private void generadorIV() throws NoSuchAlgorithmException {
        byte[] arr = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(arr);
        iv = arr;
    }

    private byte[] separadorIV(String content) throws NoSuchAlgorithmException {
        LOGGER.info("Se comienza a separar el IV de la data de entrada.");
        byte[] fullContent = Base64.decodeBase64(content);
        byte[] justContent = new byte[fullContent.length - iv.length];
        for (int i = 0; i < iv.length; i++) {
            iv[i] = fullContent[i];
        }
        for (int i = 0; i < justContent.length; i++) {
            justContent[i] = fullContent[i + 16];
        }
        return justContent;
    }
}
