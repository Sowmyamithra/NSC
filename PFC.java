//PlayFair Cipher
import java.util.*;

class Pair{
    int i , j;
    Pair(int x , int y){
        i = x;
        j = y;
    }
}

class Encryption{
    String encrypt(String plain , String key){
        char[][] keyMatrix = new char[5][5];
        int l = key.length();
        boolean[] alphabet = new boolean[26];
        int r = 0 , c = 0;
        for(int i = 0;i < l;i++){
            char k = key.charAt(i);
            if(!alphabet[k - 'A']){
                if(k == 'I' || k == 'J'){
                    if(k == 'I'){
                        alphabet[k - 'A'] = true;
                        alphabet['J' - 'A'] = true;
                        keyMatrix[r][c++] = k;
                    }
                    else{
                        alphabet[k - 'A'] = true;
                        alphabet['I' - 'A'] = true;
                        keyMatrix[r][c++] = k;
                    }
                }
                else{
                    alphabet[k - 'A'] = true;
                    keyMatrix[r][c++] = k;
                }
                if(c == 5){
                    c = 0;
                    r++;
                }
            }
        }
        char ch = 'A';
        while(r < 5 && ch <= 'Z'){
            char k = ch;
            if(!alphabet[ch - 'A']){
                if(k == 'I' || k == 'J'){
                    if(k == 'I'){
                        alphabet[k - 'A'] = true;
                        alphabet['J' - 'A'] = true;
                        keyMatrix[r][c++] = k;
                    }
                    else{
                        alphabet[k - 'A'] = true;
                        alphabet['I' - 'A'] = true;
                        keyMatrix[r][c++] = k;
                    }
                }
                else{
                    alphabet[ch - 'A'] = true;
                    keyMatrix[r][c++] = ch;
                }
                if(c == 5){
                    c = 0;
                    r++;
                }
            }
            ch++;
        }
        /*for(int i = 0;i < 5;i++){
            for(int j = 0;j < 5;j++){
                System.out.print(keyMatrix[i][j] + " ");
            }
            System.out.println();
        }*/
        Pair[] pos = new Pair[26];
        for(int i = 0;i < 5;i++){
            for(int j = 0;j < 5;j++){
                int k = keyMatrix[i][j] - 'A';
                if(keyMatrix[i][j] == 'I'){
                    pos['J' - 'A'] = new Pair(i , j);
                }
                if(keyMatrix[i][j] == 'J'){
                    pos['I' - 'A'] = new Pair(i , j);
                }
                pos[k] = new Pair(i , j);
            }
        }
        /*for(int i = 0;i < 26;i++){
            if(pos[i] != null){
                System.out.println((char)(i + 'A') + " " + pos[i].i + " " + pos[i].j);   
            }
        }*/
        l = plain.length();
        if(l%2 == 1){
            plain = plain + "Z";
        }
        String cipher = "";
        for(int i = 0;i < l;i=i+2){
            char a = plain.charAt(i);
            char b = plain.charAt(i+1);
            Pair p = pos[a - 'A'];
            Pair q = pos[b - 'A'];
            if(p != null && q != null){
                if(p.i == q.i){
                    cipher = cipher + keyMatrix[p.i][(p.j+1)%5] + keyMatrix[q.i][(q.j+1)%5];
                }
                else if(p.j == q.j){
                    cipher = cipher + keyMatrix[(p.i+1)%5][p.j] + keyMatrix[(q.i+1)%5][q.j];
                }
                else{
                    cipher = cipher + keyMatrix[p.i][q.j] + keyMatrix[q.i][p.j];
                }
            }
        }
        return cipher;
    }
}
class Decryption{
    String decrypt(String cipher , String key){
        char[][] keyMatrix = new char[5][5];
        int l = key.length();
        boolean[] alphabet = new boolean[26];
        int r = 0 , c = 0;
        for(int i = 0;i < l;i++){
            char k = key.charAt(i);
            if(!alphabet[k - 'A']){
                if(k == 'I' || k == 'J'){
                    if(k == 'I'){
                        alphabet[k - 'A'] = true;
                        alphabet['J' - 'A'] = true;
                        keyMatrix[r][c++] = k;
                    }
                    else{
                        alphabet[k - 'A'] = true;
                        alphabet['I' - 'A'] = true;
                        keyMatrix[r][c++] = k;
                    }
                }
                else{
                    alphabet[k - 'A'] = true;
                    keyMatrix[r][c++] = k;
                }
                if(c == 5){
                    c = 0;
                    r++;
                }
            }
        }
        char ch = 'A';
        while(r < 5 && ch <= 'Z'){
            char k = ch;
            if(!alphabet[ch - 'A']){
                if(k == 'I' || k == 'J'){
                    if(k == 'I'){
                        alphabet[k - 'A'] = true;
                        alphabet['J' - 'A'] = true;
                        keyMatrix[r][c++] = k;
                    }
                    else{
                        alphabet[k - 'A'] = true;
                        alphabet['I' - 'A'] = true;
                        keyMatrix[r][c++] = k;
                    }
                }
                else{
                    alphabet[ch - 'A'] = true;
                    keyMatrix[r][c++] = ch;
                }
                if(c == 5){
                    c = 0;
                    r++;
                }
            }
            ch++;
        }
        /*for(int i = 0;i < 5;i++){
            for(int j = 0;j < 5;j++){
                System.out.print(keyMatrix[i][j] + " ");
            }
            System.out.println();
        }*/
        Pair[] pos = new Pair[26];
        for(int i = 0;i < 5;i++){
            for(int j = 0;j < 5;j++){
                int k = keyMatrix[i][j] - 'A';
                if(keyMatrix[i][j] == 'I'){
                    pos['J' - 'A'] = new Pair(i , j);
                }
                if(keyMatrix[i][j] == 'J'){
                    pos['I' - 'A'] = new Pair(i , j);
                }
                pos[k] = new Pair(i , j);
            }
        }
        /*for(int i = 0;i < 26;i++){
            if(pos[i] != null){
                System.out.println((char)(i + 'A') + " " + pos[i].i + " " + pos[i].j);   
            }
        }*/
        l = cipher.length();
        String original = "";
        System.out.println();
        for(int i = 0;i < l;i=i+2){
            char a = cipher.charAt(i);
            char b = cipher.charAt(i+1);
            Pair p = pos[a - 'A'];
            Pair q = pos[b - 'A'];
            System.out.println("(" + p.i + " , " + p.j + ")" + "(" + q.i + " , " + q.j + ")" + a + " " + b);
            int x_a , x_b , y_a , y_b;
            x_a = p.i;
            x_b = p.j;
            y_a = q.i;
            y_b = q.j;
            if(p != null && q != null){
                if(p.i == q.i){
                    x_b = (x_b == 0) ? (4) : (x_b - 1);
                    y_b = (y_b == 0) ? (4) : (y_b - 1);
                }
                else if(p.j == q.j){
                    x_a = (x_a == 0) ? (4) : (x_a - 1);
                    y_a = (y_a == 0) ? (4) : (y_a - 1);
                }
                else{
                    int k = x_b;
                    x_b = y_b;
                    y_b = k;
                }
                original = original + keyMatrix[x_a][x_b] + keyMatrix[y_a][y_b] ;
                System.out.println("(" + x_a + " , " + x_b + ")" + "(" + y_a + " , " + y_b + ")" + keyMatrix[x_a][x_b] + " " + keyMatrix[y_a][y_b]);
            }
        }
        return original;
    }
}

public class PFC{
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