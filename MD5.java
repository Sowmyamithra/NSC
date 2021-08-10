//MD5 Algorithm
import java.util.*;

class MD_5{
    
    int shift[] = {7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22 ,
                   5,  9, 14, 20,  5,  9, 14, 20,  5,  9, 14, 20,  5,  9, 14, 20 ,
                   4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23 ,
                   6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21 };
    int K[] = { 0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee ,
                0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501 ,
                0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be ,
                0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821 ,
                0xf61e2562, 0xc040b340, 0x265e5a51, 0xe9b6c7aa ,
                0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8 ,
                0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed ,
                0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a ,
                0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c ,
                0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70 ,
                0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05 ,
                0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665 ,
                0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039 ,
                0x655b59c3, 0x8f0ccc92, 0xffeff47d, 0x85845dd1 ,
                0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1 ,
                0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391 };
    int a0 = 0x67452301;
    int b0 = 0xefcdab89;  
    int c0 = 0x98badcfe;  
    int d0 = 0x10325476; 
    
    String toBinary(String s){
        byte[] bytes = s.getBytes();
        String ans = "";
        for(int i = 0;i < bytes.length;i++){
            String b = Integer.toBinaryString(bytes[i]);
            while(b.length() < 8){
                b = "0" + b;
            }
            ans = ans + b;
        }
        return ans;
    }
    
    int toDecimal(String s){
        int n = s.length();
        int x = 0;
        for(int i = n-1;i >= 0;i--){
            x = x + (s.charAt(i) - '0') * 2;
        }
        return x;
    }
    
    int leftrotate(int x, int shift){
        return ((x << shift) | (x >>> (32 - shift)));
    }
    
    String getMD(String input){
        
        //convert input to binary
        input = toBinary(input);
        
        //padding
        input = input + "1";
        int l = input.length();
        int x = 1;
        while(true){
            if(l+64 <= x*512){
                break;
            }
            x++;
        }
        int add = x * 512 - 64 - l;
        while(add > 0){
            input = input + "0";
            add--;
        }
        
        //append length of input
        l = l - 1;
        String bLength = Integer.toBinaryString(l);
        while(bLength.length() < 64){
            bLength = "0" + bLength;
        }
        input = input + bLength;
        
        //convert input to blocks of 512
        int nb = input.length() / 512;
        String[] blocks = new String[nb];
        int k = 0;
        for(int i = 0;i < input.length();i=i+512){
            blocks[k++] = input.substring(i , i+512);
        } 
        
        String output = "";
        for(int i = 0;i < nb;i++){
            
            //divide blocks to 16 - 32 bit words
            int[] words = new int[16];
            k = 0;
            String b = blocks[i];
            for(int j = 0;j < 512;j=j+32){
                String temp = b.substring(j , j+32);
                words[k++] = toDecimal(temp);
            }
            
            //process blocks
            int A = a0;
            int B = b0;
            int C = c0;
            int D = d0;
            
            for(int j = 0;j < 64;j++){
                int F = 0, g = 0;
                if(j >= 0 && j <= 15){
                    F = (B & C) | ((~B) & D);
                    g = j;
                }
                else if(j >= 16 && j <= 31){
                    F = (D & B) | ((~D) & C);
                    g = (5 * j + 1) % 16;
                }
                else if(j >= 32 && j <= 47){
                    F = B ^ C ^ D;
                    g = (3 * j + 5) % 16;
                }
                else if(i >= 48 && j <= 63){
                    F = C ^ (B | (~D));
                    g = (7 * j) % 16;
                }
                F = F + A + K[j] + words[g];
                A = D;
                D = C;
                C = B;
                B = B + leftrotate(F, shift[j]);
            }
            a0 = a0 + A;
            b0 = b0 + B;
            c0 = c0 + C;
            d0 = d0 + D;
        } 
        output = Integer.toHexString(a0) + Integer.toHexString(b0) + Integer.toHexString(c0) + Integer.toHexString(d0);
        return output;
    }
}

public class MD5{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter input : ");
        String input = sc.nextLine();
        
        MD_5 hash = new MD_5();
        String md = hash.getMD(input);
        
        System.out.println("\nRequired message digest : " + md);
    }
}