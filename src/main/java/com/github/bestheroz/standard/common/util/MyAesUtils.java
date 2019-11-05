package com.github.bestheroz.standard.common.util;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.exception.CommonExceptionCode;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MyAesUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAesUtils.class);

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String DEFAULT_SECRET_KEY = "MY";
    private static final String DEFAULT_IV = "MY";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    protected MyAesUtils() {
        throw new UnsupportedOperationException();
    }

    public static String encrypt128(final String text) throws CommonException {
        return encrypt128(text, DEFAULT_SECRET_KEY, DEFAULT_IV);
    }

    public static String encrypt128(final String text, final String secretKey) throws CommonException {
        return encrypt128(text, secretKey, DEFAULT_IV);
    }

    public static String encrypt128(final String text, final String secretKey, final String iv) throws CommonException {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Arrays.copyOf(secretKey.getBytes(DEFAULT_CHARSET), 16), ALGORITHM),
                    new IvParameterSpec(Arrays.copyOf(iv.getBytes(DEFAULT_CHARSET), 16)));
            return Base64.encodeBase64String(cipher.doFinal(text.getBytes(DEFAULT_CHARSET)));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(CommonExceptionCode.FAIL_ILLEGAL_BLOCK_SIZE_AES);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
    }

    public static String decrypt128(final String text) throws CommonException {
        return decrypt128(text, DEFAULT_SECRET_KEY, DEFAULT_IV);
    }

    public static String decrypt128(final String text, final String secretKey) throws CommonException {
        return decrypt128(text, secretKey, DEFAULT_IV);
    }

    public static String decrypt128(final String text, final String secretKey, final String iv) throws CommonException {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Arrays.copyOf(secretKey.getBytes(DEFAULT_CHARSET), 16), ALGORITHM),
                    new IvParameterSpec(Arrays.copyOf(iv.getBytes(DEFAULT_CHARSET), 16)));
            return new String(cipher.doFinal(Base64.decodeBase64(text)), DEFAULT_CHARSET);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            // throw new CommonResponseException(CommonResultCode.FAIL_ILLEGAL_BLOCK_SIZE_AES);
            return "";
        } // throw new CommonResponseException(e);

    }

    public static String encrypt256(final String text) throws CommonException {
        return encrypt256(text, DEFAULT_SECRET_KEY, DEFAULT_IV);
    }

    public static String encrypt256(final String text, final String secretKey) throws CommonException {
        return encrypt256(text, secretKey, DEFAULT_IV);
    }

    public static String encrypt256(final String text, final String secretKey, final String iv) throws CommonException {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Arrays.copyOf(secretKey.getBytes(DEFAULT_CHARSET), 32), ALGORITHM),
                    new IvParameterSpec(Arrays.copyOf(iv.getBytes(DEFAULT_CHARSET), 16)));
            return Base64.encodeBase64String(cipher.doFinal(text.getBytes(DEFAULT_CHARSET)));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(CommonExceptionCode.FAIL_ILLEGAL_BLOCK_SIZE_AES);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
    }

    public static String decrypt256(final String text) throws CommonException {
        return decrypt256(text, DEFAULT_SECRET_KEY, DEFAULT_IV);
    }

    public static String decrypt256(final String text, final String secretKey) throws CommonException {
        return decrypt256(text, secretKey, DEFAULT_IV);
    }

    public static String decrypt256(final String text, final String secretKey, final String iv) throws CommonException {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Arrays.copyOf(secretKey.getBytes(DEFAULT_CHARSET), 32), ALGORITHM),
                    new IvParameterSpec(Arrays.copyOf(iv.getBytes(DEFAULT_CHARSET), 16)));
            return new String(cipher.doFinal(Base64.decodeBase64(text)), DEFAULT_CHARSET);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            // throw new CommonResponseException(CommonResultCode.FAIL_ILLEGAL_BLOCK_SIZE_AES);
            return "";
        } // throw new CommonResponseException(e);

    }

}
