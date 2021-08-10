//Caeser Cipher

import java.util.*;

class Encryption{
    String encrypt(String plain , int shift){
        String cipher = "";
        char[] alphabet = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J' , 'K' , 'L' , 'M' , 'N' , 'O' , 'P' , 'Q' , 'R' , 'S' , 'T' , 'U' , 'V' , 'W' , 'X' , 'Y' , 'Z'};
        int len = plain.length();
        for(int i = 0;i < len;i++){
            char c = plain.charAt(i);
            int j = (c - 'A' + shift) % 26;
            cipher = cipher + alphabet[j];
        }
        return cipher;
    }
}
class Decryption{
    String decrypt(String cipher , int shift){
        String original = "";
        char[] alphabet = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J' , 'K' , 'L' , 'M' , 'N' , 'O' , 'P' , 'Q' , 'R' , 'S' , 'T' , 'U' , 'V' , 'W' , 'X' , 'Y' , 'Z'};
        int len = cipher.length();
        for(int i = 0;i < len;i++){
            char c = cipher.charAt(i);
            int j = (26 - shift + c - 'A') % 26;
            original = original + alphabet[j];
        }
        return original;
    }
}

public class CC{
	public static void main(String args[]){
		String plain , cipher , original;
		int shift;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter plaintext : ");
		plain = sc.nextLine();
		System.out.println("Enter shift value");
		shift = sc.nextInt();
		Encryption enc = new Encryption();
		cipher = enc.encrypt(plain , shift);
		Decryption dec = new Decryption();
		original = dec.decrypt(cipher , shift);
		System.out.println();
		System.out.println("Original plain text : " + plain);
		System.out.println("Encrypted text : " + cipher);
		System.out.println("Decrypted text : " + original);
	}
}