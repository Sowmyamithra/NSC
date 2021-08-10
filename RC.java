//Route Cipher (Transposition)
import java.util.*;

class Encryption{
    String encrypt(String plain , String key){
        int r = (plain.length() % key.length()  == 0) ? plain.length() / key.length() : plain.length() / key.length() + 1;
        int c = key.length();
        char[][] arr = new char[r][c];
        int l = 0;
        l:for(int i = 0;i < r;i++){
            for(int j = 0;j < c;j++){
                arr[i][j] = plain.charAt(l++);
                    if(l == plain.length()){
                        break l;
                    }
            }
        }
        char tempArray[] = key.toCharArray(); 
        Arrays.sort(tempArray); 
        String key1 = new String(tempArray);
        
        HashMap<Character , Integer> mp = new HashMap<>();
        for(int i = 0;i < key.length();i++){
            mp.put(key.charAt(i) , i);
        }
        
        /*for(int i = 0;i < r;i++){
            for(int j = 0;j < c;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }*/
            
        String cipher = "";
        for(int i = 0;i < key.length();i++){
            int index = mp.getOrDefault(key1.charAt(i) , 0);
            for(int j = 0;j < r;j++){
                cipher += arr[j][index];
            }
        }
        return cipher;
    }
}

class Decryption{
    String decrypt(String cipher , String key){
	int r = (cipher.length() % key.length()  == 0) ? cipher.length() / key.length() : cipher.length() / key.length() + 1;
        int c = key.length();
        char[][] arr = new char[r][c];
	
	int l = 0;
	for(int j = 0;j < c;j++){
		for(int i = 0;i < r;i++){
			arr[i][j] = cipher.charAt(l++);
		}
	}	

	char tempArray[] = key.toCharArray(); 
        Arrays.sort(tempArray); 
        String key1 = new String(tempArray);
	
	HashMap<Character , Integer> mp = new HashMap<>();
	for(int i = 0;i < key1.length();i++){
		mp.put(key1.charAt(i) , i);
	}
		
	char[][] temp = new char[r][c];
	for(int i = 0;i < key.length();i++){
		int j = mp.getOrDefault(key.charAt(i) , 0);	
		for(l = 0;l < r;l++){
			temp[l][i] = arr[l][j];
		}
	}
	String original = "";
	for(int i = 0;i < r;i++){
		for(int j = 0;j < c;j++){
			original += temp[i][j];
		}	
	}
        return original;
    }
}

public class RC{
    public static void main(String[] args){
        String plaintext , key;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext : ");
        plaintext = sc.nextLine();
        System.out.print("\nEnter key : ");
        key = sc.nextLine();
        
        Encryption enc = new Encryption();
        String Ciphertext = enc.encrypt(plaintext , key);
        
        Decryption dec = new Decryption();
        String Original = dec.decrypt(Ciphertext , key);
        
        System.out.println("Plaintext : " + plaintext);
        System.out.println("Encrypted text : " + Ciphertext);
        System.out.println("Decrypted text : " + Original);
    }
}
