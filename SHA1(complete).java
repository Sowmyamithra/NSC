import java.util.*;

class SHA_1{
    
    int h1 = 0x67452301;
    int h2 = 0xEFCDAB89;
    int h3 = 0x98BADCFE;
    int h4 = 0x10325476;
    int h5 = 0xC3D2E1F0;
    int k1 = 0x5A827999;
    int k2 = 0x6ED9EBA1;
    int k3 = 0x8F1BBCDC;
    int k4 = 0xCA62C1D6;

    String toBinary(String s){
        byte[] b = s.getBytes();
        int n = b.length;
        String ans = "";
        for(int i = 0;i < n;i++){
            String t = Integer.toBinaryString(b[i]);
            while(t.length() > 8){
                t = "0" + t;
            }
            ans = ans + t;
        }
        return ans;
    }
   
    int leftrotate(int x, int shift){
        return ((x << shift) | (x >>> (32 - shift)));
    }
    
    int toDecimal(String s){
        int n = s.length();
        int x = 0;
        for(int i = n-1;i >= 0;i--){
            x = x + (s.charAt(i) - '0') * 2;
        }
        return x;
    }
    
    String digest(String input){
        
        //convert to binary
        input = toBinary(input);
        int x = 1;
        int n = input.length();
        while(true){
            if(n+64 < x*512){
                break;
            }
            x++;
        }
        
        //padding additional bits
        input = input + "1";
        int add = x * 512 - 64 - n;
        add--;
        while(add > 0){
            input = input + "0";
            add--;
        }
        
        //appending length
        String endLength = Integer.toBinaryString(n);
        while(endLength.length() < 64){
            endLength = "0" + endLength;
        }
        input = input + endLength;
        
        System.out.println("\n" + input);
        
        //divide into blocks
        n = input.length();
        x = n / 512;
        String[] blocks = new String[x];
        int p = 0;
        for(int i = 0;i < n;i = i+512){
            blocks[p++] = input.substring(i , i+512);
        }
        
        //processing blocks
        for(int i = 0;i < p;i++){
            int[] words = new int[80];
            String b = blocks[i];
            int k = 0;
            for(int j = 0;j < 512;j = j+32){
                String temp = b.substring(j , j+32);
                words[k++] = toDecimal(temp);
            }
            for(int j = 16;j < 80;j++){
                words[j] = leftrotate(words[j-3] ^ words[j-8] ^ words[j-14] ^ words[j-16] , 1);
            }
            int A = h1;
            int B = h2;
            int C = h3;
            int D = h4;
            int E = h5;
            int t = 0;
            
            for(x = 0;x <= 19;x++){
                t = leftrotate(A , 5) + ((B & C) | ((~B) & D)) + E + words[x] + k1;
                E = D; D = C; C = leftrotate(B , 30); B = A; A = t;
            }
            for(x = 20;x <= 39;x++){
                t = leftrotate(A , 5) + (B ^ C ^ D) + E + words[x] + k2;
                E = D; D = C; C = leftrotate(B , 30); B = A; A = t;
            }
            for(x = 40;x <= 59;x++){
                t = leftrotate(A , 5) + ((B & C) | (B & D) | (C & D)) + E + words[x]+k3;
                E = D; D = C; C = leftrotate(B , 30); B = A; A = t;
            }
            for(x = 60;x <= 79;x++){
                t = leftrotate(A , 5) + (B ^ C ^ D) + E + words[x] + k4;
                E = D; D = C; C = leftrotate(B,30); B = A; A = t;
            }
            h1+=A; h2+=B; h3+=C; h4+=D; h5+=E;
        }

        String h1Length = Integer.toHexString(h1);
        String h2Length = Integer.toHexString(h2);
        String h3Length = Integer.toHexString(h3);
        String h4Length = Integer.toHexString(h4);
        String h5Length = Integer.toHexString(h5);
       
        if(h1Length.length() < 8){
            StringBuilder h1L = new StringBuilder(h1Length);
            h1L.insert(0,0);
            h1Length = h1L.toString();
        } 
        else if(h2Length.length() < 8){
            StringBuilder h2L = new StringBuilder(h2Length);
            h2L.insert(0,0);
            h2Length = h2L.toString();
        } 
        else if(h3Length.length() < 8){
            StringBuilder h3L = new StringBuilder(h3Length);
            h3L.insert(0,0);
            h3Length = h3L.toString();
        } 
        else if(h4Length.length() < 8){
            StringBuilder h4L = new StringBuilder(h4Length);
            h4L.insert(0,0);
            h4Length = h4L.toString();
        } 
        else if(h5Length.length() < 8){
            StringBuilder h5L = new StringBuilder(h5Length);
            h5L.insert(0,0);
            h5Length = h5L.toString();
        }
        String output = h1Length + h2Length + h3Length + h4Length + h5Length;
        return output;
    }
}


public class SHA1{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter input : ");
        String input = sc.nextLine();
        
        SHA_1 hash = new SHA_1();
        String md = hash.digest(input);
        
        System.out.println("\nRequired Message Digest is : " + md);
    }
}