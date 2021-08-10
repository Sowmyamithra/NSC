//DIFFIE-HELLMAN KEY EXCHANGE ALGORITHM

import java.math.BigInteger;
import java.util.*;

public class DH{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter p : ");
		BigInteger p = sc.nextBigInteger();
		System.out.print("\nEnter g : ");
		BigInteger g = sc.nextBigInteger();
		BigInteger a = new BigInteger("2");
		BigInteger b = new BigInteger("3");
		BigInteger x = g.modPow(a , p);
		BigInteger y = g.modPow(b , p);
		System.out.println("\nx : " + x + "\ny : " + y);
		BigInteger ka = y.modPow(a , p);
		BigInteger kb = x.modPow(b , p);
		System.out.println("\nka : " + ka + "\nkb : " + kb);
	}
}