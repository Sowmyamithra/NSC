//Rail Fence Cipher

import java.util.*;

class Encryption{
    String encrypt(String plain , int shift){
	int n = plain.length();
        char[][] cipher_arr = new char[shift][n];
	int i = 0 , j = 0;
	boolean down = true;
	for(int l = 0;l < n;l++){
		//System.out.println(i + " " + j);
		cipher_arr[i][j++] = plain.charAt(l);
		if(down){
			if(i == shift - 1){
				i--;
				down = false;
			}
			else{
				i++;
			}
		}
		else{
			if(i == 0){
				i++;
				down = true;
			}	
			else{
				i--;
			}
		}
	}
	String cipher = "";
	for(i = 0;i < shift;i++){
		for(j = 0;j < n;j++){
			if(cipher_arr[i][j] >= 65 && cipher_arr[i][j] <= 90){
				cipher += cipher_arr[i][j];
			}
		}		
	}
	return cipher;
    }
}
class Decryption{
    String decrypt(String cipher , int shift){
	int n = cipher.length();
	char[][] cipher_arr = new char[shift][n];	
	int i = 0 , j = 0;
	boolean down = true;
		
	for (int l = 0; l < n;l++){	 
        	if(i == 0){ 
           		down = true;
		} 
        	if(i == shift - 1){ 
            		down = false;
		} 
        	cipher_arr[i][j++] = '*'; 
  		i = down ? i + 1 : i - 1;  
    	} 
    	int index = 0; 
    	for (i = 0;i < shift;i++){
        	for (j = 0;j < n ;j++){ 
            		if (cipher_arr[i][j] == '*' && index < n){ 
                		cipher_arr[i][j] = cipher.charAt(index++); 
			}
		}
	}
    	String original = ""; 
  	i = 0;
	j = 0; 
    	for (int l = 0;l < n;l++){  
        	if(i == 0){ 
            		down = true;
		} 
        	if(i == shift - 1){ 
            		down = false;
		} 
        	if(cipher_arr[i][j] != '*'){
			original += cipher_arr[i][j++]; 
  		} 
        	i = down ? i + 1 : i - 1; 
   	}
	return original;	   
    }
}

public class RF{
	public static void main(String args[]){
		String plain , cipher , original;
		int shift;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter plaintext : ");
		plain = sc.nextLine();
		System.out.print("\nEnter shift value : ");
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
