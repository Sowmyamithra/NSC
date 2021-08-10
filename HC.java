//Hill Cipher
import java.util.*;

class Encryption{
    String encrypt(String plain , String key){
        int[][] keyMatrix = new int[3][3];
        int l = key.length();
        int k1 = 0;
        for(int i = 0;i < 3;i++){
            for(int j = 0;j < 3;j++){
                keyMatrix[i][j] = key.charAt(k1++) - 'A';
            }
        }
        int x = 0;
        while(plain.length()%3 != 0){
            plain = plain + "Z";
        }
        String cipher = "";
        while(x+3 <= plain.length()){
            int[][] plainMatrix = new int[3][1];
            for(int i = 0;i < 3;i++){
                for(int j = 0;j < 1;j++){
                    plainMatrix[i][j] = plain.charAt(x++) - 'A';
                }
            }
            int[][] cipherMatrix = new int[3][1];
            for(int i = 0;i < 3;i++){
                for(int j = 0;j < 1;j++){
                    for(int k = 0;k < 3;k++){
                        cipherMatrix[i][j] += keyMatrix[i][k] * plainMatrix[k][j];
                    }
                }
            }
            for(int i = 0;i < 3;i++){
                for(int j = 0;j < 1;j++){
                    cipherMatrix[i][j] %= 26;
                    cipher = cipher + (char)(cipherMatrix[i][j] + 'A');
                }
            }
        }
        return cipher;
    }
}
class Decryption{
    String decrypt(String cipher , String key){
        int determinant = 0;
        int[][] keyMatrix = new int[3][3];
        int l = key.length();
        int k1 = 0;
        for(int i = 0;i < 3;i++){
            for(int j = 0;j < 3;j++){
                keyMatrix[i][j] = key.charAt(k1++) - 'A';
            }
        }
        for(int i = 0;i < 3;i++){
            int val = keyMatrix[0][i] * (keyMatrix[1][(i+1)%3]*keyMatrix[2][(i+2)%3] - keyMatrix[1][(i+2)%3]*keyMatrix[2][(i+1)%3]);
            if(i%2 == 1){
                determinant = determinant - val;
            }
            else{
                determinant = determinant + val;
            }
        }
        double[][] inverse = new double[3][3];
        for(int i = 0;i < 3;i++){
            for(int j = 0;j < 3;j++){
                inverse[i][j] = (((keyMatrix[(j+1)%3][(i+1)%3] * keyMatrix[(j+2)%3][(i+2)%3]) - (keyMatrix[(j+1)%3][(i+2)%3] * keyMatrix[(j+2)%3][(i+1)%3])) * 1.0)/ determinant;
            }
        }
        int x = 0;
        String original = "";
        while(x+3 <= cipher.length()){
            int[][] cipherMatrix = new int[3][1];
            for(int i = 0;i < 3;i++){
                for(int j = 0;j < 1;j++){
                    cipherMatrix[i][j] = cipher.charAt(x++) - 'A';
                }
            }
            int[][] originalMatrix = new int[3][1];
            for(int i = 0;i < 3;i++){
                for(int j = 0;j < 1;j++){
                    for(int k = 0;k < 3;k++){
                        originalMatrix[i][j] += (int)(inverse[i][k] * cipherMatrix[k][j]);
                    }
                }
            }
            for(int i = 0;i < 3;i++){
                for(int j = 0;j < 1;j++){
                    originalMatrix[i][j] %= 26;
                    original = original + (char)(originalMatrix[i][j] + 'A');
                }
            }
        }
        return original;
    }
}

public class HC{
	public static void main(String args[]){
		String plain , cipher , original , key;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter plaintext : ");
		plain = sc.nextLine();
		System.out.println("Enter key : ");
		key = sc.nextLine();
		Encryption enc = new Encryption();
		cipher = enc.encrypt(plain , key);
		Decryption dec = new Decryption();
		original = dec.decrypt(cipher , key);
		System.out.println();
		System.out.println("Original plain text : " + plain);
		System.out.println("Encrypted text : " + cipher);
		System.out.println("Decrypted text : " + original); 
	}
}