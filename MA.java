//Monoalphabetic Cipher
import java.util.*;

class Encryption{
    String encrypt(String plain , String shift){
        HashMap<Character , Character> mp = new HashMap<>();
	int n = plain.length();
	char c = 'A';
	for(int i = 0;i < 26;i++){
		mp.put(c , shift.charAt(i));
		c++;
	}
	String cipher = "";
	for(int i = 0;i < n;i++){
		cipher += mp.getOrDefault(plain.charAt(i) , '`');
	}
	return cipher;
    }
}
class Decryption{
    String decrypt(String cipher , String shift){
        HashMap<Character , Character> mp = new HashMap<>();
	int n = cipher.length();
	char c = 'A';
	for(int i = 0;i < 26;i++){
		mp.put(shift.charAt(i) , c);
		c++;
	}
	String original = "";
	for(int i = 0;i < n;i++){
		original += mp.getOrDefault(cipher.charAt(i) , '`');
	}
	return original;
    }
}

public class MA{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter input text : ");
		String plaintext = sc.nextLine();
		System.out.print("\nEnter shift text : ");
		String shift = sc.nextLine();

		Encryption enc = new Encryption();
		String cipher = enc.encrypt(plaintext , shift);
		
		Decryption dec = new Decryption();
		String original = dec.decrypt(cipher , shift);

		System.out.println();
		System.out.println("plain text : " + plaintext);
		System.out.println("Cipher text : " + cipher);
		System.out.println("Original text : " + original);
	}
}