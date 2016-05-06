package dev.thetechnokid.rw.net;

import java.security.*;
import java.security.spec.*;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * A class that can encrypt and authenticate Strings using the PBKDF2 with SHA1
 * algorithm. Useful for storing passwords.
 *
 * @author theTechnoKid
 */
public final class StringEncryptor {

	/**
	 * @param password
	 *            the password to encrypt
	 * @param salt
	 *            the salt to encrypt the password
	 * @return the encrypted password in bytes
	 */
	public static byte[] encryptPassword(String password, byte[] salt) {
		try {
			// PBKDF2 with SHA-1 as the hashing algorithm.
			String algorithm = "PBKDF2WithHmacSHA1";
			// SHA-1 generates 160 bit hashes, so that's what makes sense here
			int derivedKeyLength = 160;
			int iterations = 20000;

			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);

			SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

			return f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Authenticates the <code>attemptedPassword</code> by encrypting it with
	 * the same salt and seeing if they both are equal.
	 * 
	 * @param attemptedPassword
	 *            the attempted password to be checked
	 * @param encryptedPassword
	 *            the encrypted password to check against
	 * @param salt
	 *            the <code>encryptedPassword</code>s salt
	 * @return whether the <code>attemptedPassword</code> is correct
	 */
	public static boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt) {
		// Encrypt the clear-text password using the same salt that was used to
		// encrypt the original password
		byte[] encryptedAttemptedPassword = encryptPassword(attemptedPassword, salt);

		// Authentication succeeds if encrypted password that the user entered
		// is equal to the stored hash
		return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}

	/**
	 * Generates a random salt to be used for encrypting passwords
	 * 
	 * @return a random salt
	 */
	public static byte[] generateSalt() {
		try {
			SecureRandom r = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[10];
			r.nextBytes(salt);
			return salt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
