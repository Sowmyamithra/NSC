//Polyalphabetic Cipher
import java.util.*;

class Encryption{
    String encrypt(String plain , String shift){
        String[] alphabet_arr = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ" , 				 "BCDEFGHIJKLMNOPQRSTUVWXYZA" , 				 "CDEFGHIJKLMNOPQRSTUVWXYZAB", 
                              	 "DEFGHIJKLMNOPQRSTUVWXYZABC" , 				 "EFGHIJKLMNOPQRSTUVWXYZABCD" , 				 "FGHIJKLMNOPQRSTUVWXYZABCDE" , 
                              	 "GHIJKLMNOPQRSTUVWXYZABCDEF" , 				 "HIJKLMNOPQRSTUVWXYZABCDEFG" , 				 "IJKLMNOPQRSTUVWXYZABCDEFGH" , 
                              	 "JKLMNOPQRSTUVWXYZABCDEFGHI" , 				 "KLMNOPQRSTUVWXYZABCDEFGHIJ" , 				 "LMNOPQRSTUVWXYZABCDEFGHIJK" ,
                              	 "MNOPQRSTUVWXYZABCDEFGHIJKL" , 				 "NOPQRSTUVWXYZABCDEFGHIJKLM" , 				 "OPQRSTUVWXYZABCDEFGHIJKLMN" , 
                              	 "PQRSTUVWXYZABCDEFGHIJKLMNO" , 				 "QRSTUVWXYZABCDEFGHIJKLMNOP" , 				 "RSTUVWXYZABCDEFGHIJKLMNOPQ" , 
                              	 "STUVWXYZABCDEFGHIJKLMNOPQR" , 				 "TUVWXYZABCDEFGHIJKLMNOPQRS" , 				 "UVWXYZABCDEFGHIJKLMNOPQRST" , 
                              	 "VWXYZABCDEFGHIJKLMNOPQRSTU" ,		 			 "WXYZABCDEFGHIJKLMNOPQRSTUV" , 				 "XYZABCDEFGHIJKLMNOPQRSTUVW" , 
                              	 "YZABCDEFGHIJKLMNOPQRSTUVWX" , 				 "ZABCDEFGHIJKLMNOPQRSTUVWXY" };		
	String cipher = "";
	int n = plain.length();
	for(int i = 0;i < n;i++){
		int r = plain.charAt(i) - 'A';
		int c = shift.charAt(i) - 'A';
		cipher += alphabet_arr[r].charAt(c);
	}
	return cipher;
    }
}
class Decryption{
    String decrypt(String cipher , String shift){
        String[] alphabet_arr = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ" , 				 "BCDEFGHIJKLMNOPQRSTUVWXYZA" , 				 "CDEFGHIJKLMNOPQRSTUVWXYZAB", 
                              	 "DEFGHIJKLMNOPQRSTUVWXYZABC" , 				 "EFGHIJKLMNOPQRSTUVWXYZABCD" , 				 "FGHIJKLMNOPQRSTUVWXYZABCDE" , 
                              	 "GHIJKLMNOPQRSTUVWXYZABCDEF" , 				 "HIJKLMNOPQRSTUVWXYZABCDEFG" , 				 "IJKLMNOPQRSTUVWXYZABCDEFGH" , 
                              	 "JKLMNOPQRSTUVWXYZABCDEFGHI" , 				 "KLMNOPQRSTUVWXYZABCDEFGHIJ" , 				 "LMNOPQRSTUVWXYZABCDEFGHIJK" ,
                              	 "MNOPQRSTUVWXYZABCDEFGHIJKL" , 				 "NOPQRSTUVWXYZABCDEFGHIJKLM" , 				 "OPQRSTUVWXYZABCDEFGHIJKLMN" , 
                              	 "PQRSTUVWXYZABCDEFGHIJKLMNO" , 				 "QRSTUVWXYZABCDEFGHIJKLMNOP" , 				 "RSTUVWXYZABCDEFGHIJKLMNOPQ" , 
                              	 "STUVWXYZABCDEFGHIJKLMNOPQR" , 				 "TUVWXYZABCDEFGHIJKLMNOPQRS" , 				 "UVWXYZABCDEFGHIJKLMNOPQRST" , 
                              	 "VWXYZABCDEFGHIJKLMNOPQRSTU" ,		 			 "WXYZABCDEFGHIJKLMNOPQRSTUV" , 			 	 "XYZABCDEFGHIJKLMNOPQRSTUVW" , 
                              	 "YZABCDEFGHIJKLMNOPQRSTUVWX" , 				 "ZABCDEFGHIJKLMNOPQRSTUVWXY" };		
	String original = "";
	int n = cipher.length();
	for(int i = 0;i < n;i++){
		int c = shift.charAt(i) - 'A';
		String s = alphabet_arr[c];
		for(int j = 0;j < 26;j++){
			if(s.charAt(j) == cipher.charAt(i)){
				//System.out.println(shift.charAt(i) + " " + j);
				original += (char)('A' + j);
				break;
			}
		}
	}
	return original;
    }
}

public class PA{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter input text : ");
		String plaintext = sc.nextLine();
		System.out.print("\nEnter shift text : ");
		String shift = sc.nextLine();
		
		int i = 0;
		while(shift.length() < plaintext.length()){
			shift += shift.charAt(i++);
		}
		
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

