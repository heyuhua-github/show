package com.show.comm.utils.ext;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.show.comm.utils.StringUtils;

/**
 * 编码加密工具类
 * 
 * @author HeHongxin
 * @date 2014-2-28
 * 
 */
public class CodecUtils {

	private CodecUtils() {
	}

	/** 日志 */
	private static final Logger LOG = LoggerFactory.getLogger(CodecUtils.class);

	/** DES加密算法 */
	private static final String DES_ALGORITHM = "DESede"; // 可用 DES,DESede,Blowfish
	/** AES加密算法 */
	private static final String AES_ALGORITHM = "AES";
	/** DES默认KEY */
	private static final SecretKey DES_DAUKEY;
	/** AES默认KEY */
	private static final SecretKeySpec AES_DAUKEY;
	/** 安全的随机数源 */
	private static final SecureRandom RANDOM = new SecureRandom();
	/** Base64 编码 */
	public static final Base64 B64 = new Base64();

	static {
		DES_DAUKEY = desKey("dipcacesu666");
		AES_DAUKEY = aesKey("dipcaaesu888");
	}

	/**
	 * 密码不可逆加密
	 * 
	 * <pre>
	 * 1、明文密码->sha512Hex得到128位字母+数字密文;
	 * 2、128位密文，取65~128位，进行奇偶交换(abcd>>badc)，得到密文1；
	 * 3、128位密文，取1~64，反序排列(1234>>4321)，得到密文2；
	 * 4、密文1+密文2进行md5签名
	 * </pre>
	 * 
	 * @param source
	 *            密码原文
	 * @return 密码密文(32位)
	 */
	public static String pwd(String source) {
		String _hex = sha512Hex(source);
		char[] _rr = _hex.toCharArray();// len 128
		StringBuilder sb = new StringBuilder();
		for (int i = 64; i < 128; i++) {
			if (i % 2 == 0) {
				char a = _rr[i];
				char b = _rr[i + 1];
				sb.append(b).append(a);// 交换位置
			}
		}
		for (int i = 63; i >= 0; i--) {
			sb.append(_rr[i]);
		}
		return md5(sb.toString());
	}

	/**
	 * SHA-256签名
	 * 
	 * @param str
	 *            明文
	 * @return 签名密文
	 */
	public static String sha256Hex(String str) {
		return DigestUtils.sha256Hex(str);
	}

	/**
	 * SHA-512签名
	 * 
	 * @param str
	 *            明文
	 * @return 签名密文
	 */
	public static String sha512Hex(String str) {
		return DigestUtils.sha512Hex(str);
	}

	/**
	 * 生成32位MD5密文
	 * 
	 * @param str
	 *            明文
	 * @return 密文
	 */
	public static String md5(String str) {
		if (null != str) {
			return DigestUtils.md5Hex(str);
		}
		return null;
	}

	/**
	 * Base64编码
	 * 
	 * @param bs
	 *            byte数组
	 * @return 编码后的byte数组
	 */
	public static byte[] b64Encode(byte[] bs) {
		return B64.encode(bs);
	}

	/**
	 * Base64编码字符串
	 * 
	 * @param str
	 *            需要编码的字符串
	 * @return 编码后的字符串
	 */
	public static String b64Encode(String str) {
		if (null != str) {
			return new String(B64.encode(str.getBytes()));
		}
		return null;
	}

	/**
	 * Base64编码字符串(Url安全)
	 * 
	 * @param str
	 *            需要编码的字符串
	 * @return 编码后的字符串
	 */
	public static String b64Url(String str) {
		if (null != str) {
			return Base64.encodeBase64URLSafeString(str.getBytes());
		}
		return null;
	}

	/**
	 * Base64解码
	 * 
	 * @param bs
	 *            byte数组
	 * @return 解码后的byte数组
	 */
	public static byte[] b64Decode(byte[] bs) {
		return B64.decode(bs);
	}

	/**
	 * Base64解码字符串
	 * 
	 * @param str
	 *            需要解码的字符串
	 * @return 解码后的字符串
	 */
	public static String b64Decode(String str) {
		if (null != str) {
			byte[] bs = B64.decode(str.getBytes());
			if (null != bs) {
				return new String(bs);
			}
		}
		return null;
	}

	/**
	 * 默认密码进行DES加密
	 * 
	 * @param str
	 *            明文
	 * @return 密文
	 */
	public static String desEncrypt(String str) {
		if (null != str) {
			try {
				Cipher c = Cipher.getInstance(DES_ALGORITHM);
				c.init(Cipher.ENCRYPT_MODE, DES_DAUKEY, RANDOM);
				byte[] bytes = c.doFinal(str.getBytes(StringUtils.UTF8));// 加密
				return Base64.encodeBase64URLSafeString(bytes);
			} catch (Exception e) {
				LOG.error("DES默认KEY加密失败,明文:" + str + ",错误:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * 默认密码进行DES解密
	 * 
	 * @param str
	 *            密文
	 * @return 明文
	 */
	public static String desDecrypt(String str) {
		if (null != str) {
			try {
				Cipher c = Cipher.getInstance(DES_ALGORITHM);
				c.init(Cipher.DECRYPT_MODE, DES_DAUKEY, RANDOM);
				byte[] bytes = c.doFinal(B64.decode(str));
				return new String(bytes, StringUtils.UTF8);
			} catch (BadPaddingException e) {
				LOG.error("DES默认KEY解密失败,密文:" + str + ",错误:" + e.getMessage());
			} catch (Exception e) {
				LOG.error("DES默认KEY解密失败,密文:" + str + ",错误:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * DES加密
	 * 
	 * @param str
	 *            需要加密的明文
	 * @param key
	 *            密钥(长度小于24字节自动补足，大于24取前24字节)
	 * @return 加密后的密文(base64编码字符串)
	 */
	public static String desEncrypt(String str, String key) {
		return desEncrypt(str, key, false);
	}

	/**
	 * DES加密
	 * 
	 * @param str
	 *            需要加密的明文
	 * @param key
	 *            密钥(长度小于24字节自动补足，大于24取前24字节)
	 * @param urlSafety
	 *            密文是否需要Url安全
	 * @return 加密后的密文(str/key为null返回null)
	 */
	public static String desEncrypt(String str, String key, boolean urlSafety) {
		if (null != str && null != key) {
			try {
				Cipher c = Cipher.getInstance(DES_ALGORITHM);
				c.init(Cipher.ENCRYPT_MODE, desKey(key), RANDOM);
				// 加密
				byte[] bytes = c.doFinal(str.getBytes(StringUtils.UTF8));// 加密
				// 返回b64处理后的字符串
				if (urlSafety) {
					return Base64.encodeBase64URLSafeString(bytes);
				} else {
					return Base64.encodeBase64String(bytes);
				}
			} catch (Exception e) {
				LOG.error("DES加密失败,明文:" + str + ",key:" + key + ",错误:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * DES解密
	 * 
	 * @param str
	 *            需要解密的密文(base64编码字符串)
	 * @param key
	 *            密钥(长度小于24字节自动补足，大于24取前24字节)
	 * @return 解密后的明文
	 */
	public static String desDecrypt(String str, String key) {
		if (null != str && null != key) {
			try {
				Cipher c = Cipher.getInstance(DES_ALGORITHM);
				c.init(Cipher.DECRYPT_MODE, desKey(key), RANDOM);
				byte[] bytes = c.doFinal(B64.decode(str));
				return new String(bytes, StringUtils.UTF8);
			} catch (BadPaddingException e) {
				LOG.error("DES解密失败,密文:" + str + ",key:" + key + ",错误:" + e.getMessage());
			} catch (Exception e) {
				LOG.error("DES解密失败,密文:" + str + ",key:" + key + ",错误:" + e.getMessage());
			}
		}
		return null;
	}

	/** DES密钥 */
	private static SecretKey desKey(String key) {
		byte[] bs = key.getBytes();
		if (bs.length != 24) {
			bs = Arrays.copyOf(bs, 24);// 处理数组长度为24
		}
		return new SecretKeySpec(bs, DES_ALGORITHM);
	}

	/**
	 * 默认密码进行AES加密
	 * 
	 * @param str
	 *            明文
	 * @return 密文
	 */
	public static String aesEncrypt(String str) {
		if (null != str) {
			try {
				Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
				c.init(Cipher.ENCRYPT_MODE, AES_DAUKEY, RANDOM);
				byte[] bytes = c.doFinal(str.getBytes(StringUtils.UTF8));// 加密
				return Base64.encodeBase64URLSafeString(bytes);
			} catch (Exception e) {
				LOG.error("AES默认KEY加密失败,明文:" + str + ",错误:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * 默认密码进行AES解密
	 * 
	 * @param str
	 *            密文
	 * @return 明文
	 */
	public static String aesDecrypt(String str) {
		if (null != str) {
			try {
				Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
				c.init(Cipher.DECRYPT_MODE, AES_DAUKEY, RANDOM);
				byte[] bytes = c.doFinal(B64.decode(str));
				return new String(bytes, StringUtils.UTF8);// 解密
			} catch (BadPaddingException e) {
				LOG.error("AES默认KEY解密失败,密文:" + str + ",错误:" + e.getMessage());
			} catch (Exception e) {
				LOG.error("AES默认KEY解密失败,密文:" + str + ",错误:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * AES加密
	 * 
	 * @param str
	 *            需要加密的明文
	 * @param key
	 *            密钥
	 * @return 加密后的密文(str/key为null返回null)
	 */
	public static String aesEncrypt(String str, String key) {
		return aesEncrypt(str, key, false);
	}

	/**
	 * AES加密
	 * 
	 * @param str
	 *            需要加密的明文
	 * @param key
	 *            密钥
	 * @param urlSafety
	 *            密文是否需要Url安全
	 * @return 加密后的密文(str/key为null返回null)
	 */
	public static String aesEncrypt(String str, String key, boolean urlSafety) {
		if (null != str && null != key) {
			Cipher c = aesEncryptCipher(key);
			if (null != c) {
				try {
					byte[] bytes = c.doFinal(str.getBytes(StringUtils.UTF8));// 加密
					if (urlSafety) {
						return Base64.encodeBase64URLSafeString(bytes);
					} else {
						return Base64.encodeBase64String(bytes);
					}
				} catch (Exception e) {
					LOG.error("AES加密失败,明文:" + str + ",key:" + key + ",错误:" + e.getMessage());
				}
			}
		}
		return null;
	}

	/**
	 * AES解密
	 * 
	 * @param str
	 *            需要解密的密文(base64编码字符串)
	 * @param key
	 *            密钥
	 * @return 解密后的明文
	 */
	public static String aesDecrypt(String str, String key) {
		if (null != str && null != key) {
			Cipher c = aesDecryptCipher(key);
			if (null != c) {
				try {
					return new String(c.doFinal(B64.decode(str)), StringUtils.UTF8);// 解密
				} catch (BadPaddingException e) {
					LOG.error("AES解密失败,密文:" + str + ",key:" + key + ",错误:" + e.getMessage());
				} catch (Exception e) {
					LOG.error("AES解密失败,密文:" + str + ",key:" + key + ",错误:" + e.getMessage());
				}
			}
		}
		return null;
	}

	/**
	 * 构造AES加密Cipher
	 * 
	 * @param key
	 *            密钥
	 * @return Cipher
	 */
	public static Cipher aesEncryptCipher(String key) {
		try {
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, aesKey(key), RANDOM);
			return c;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			LOG.error("AES加密失败,构造Cipher失败,key:" + key + ",错误:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 构造AES解密Cipher
	 * 
	 * @param key
	 *            密钥
	 * @return Cipher
	 */
	public static Cipher aesDecryptCipher(String key) {
		try {
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, aesKey(key), RANDOM);
			return c;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			LOG.error("AES解密失败,构造Cipher失败,key:" + key + ",错误:" + e.getMessage());
			return null;
		}
	}

	/** AES密钥 */
	public static SecretKeySpec aesKey(String key) {
		byte[] bs = key.getBytes();
		if (bs.length != 16) {
			bs = Arrays.copyOf(bs, 16);// 处理数组长度为16
		}
		return new SecretKeySpec(bs, AES_ALGORITHM);
	}

	/**
	 * 格式化aes密钥
	 * 
	 * @param key
	 *            密钥
	 * @param keepEnds
	 *            超长时是否保留后部分
	 * @return 16Bytes密钥
	 */
	public static String aesKeyFmt(String key, boolean keepEnds) {
		byte[] bs = key.getBytes();
		int l = bs.length;
		if (l != 16) {
			// 处理数组长度为16
			if (keepEnds && l > 16) {
				bs = Arrays.copyOfRange(bs, l - 16, l);
			} else {
				bs = Arrays.copyOf(bs, 16);// 处理数组长度为16
			}
			return new String(bs);
		}
		return key;
	}

}
