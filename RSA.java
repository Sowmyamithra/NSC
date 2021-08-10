//RSA Algorithm

import java.math.BigInteger;
import java.util.*;
 
public class RSA {
	public static void main (String[] args){
		BigInteger p , q , N , phi , e , d;
		int bitlength = 1024;
 		int blocksize = 256;
 		Random r = new Random();
		p = BigInteger.probablePrime(bitlength, r);
		q = BigInteger.probablePrime(bitlength, r); 
		N = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = BigInteger.probablePrime(bitlength/2, r);
		while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) {
			e.add(BigInteger.ONE);
		}
		d = e.modInverse(phi);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the plain text:");
		BigInteger in = sc.nextBigInteger();
		BigInteger enc = in.modPow(e , N);
		BigInteger dec = enc.modPow(d , N);
		//System.out.println("\np : " + p + "\nq : " + q + "\nN : " + N + "\nphi : " + phi + "\ne : " + e + "\nd : " + d);
		System.out.println("Encrypted message : " + enc);
		System.out.println("Decrypted message : " + dec);
	}
}