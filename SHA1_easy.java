//SHA1 ALGORITHM

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.util.*;

public class SHA1{ 
	public static void main(String args[]) throws NoSuchAlgorithmException 
	{ 
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter input : ");
		String in = sc.nextLine(); 
		String hashtext = "";
		try { 
			MessageDigest md = MessageDigest.getInstance("SHA-1"); 
			byte[] messageDigest = md.digest(in.getBytes()); 
			BigInteger no = new BigInteger(1, messageDigest); 
			hashtext = no.toString(16);
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
		} 
		catch (NoSuchAlgorithmException e) { 
			System.out.println(e);
		} 
		System.out.println("\nMessage Digest : " + hashtext); 
	} 
} 
