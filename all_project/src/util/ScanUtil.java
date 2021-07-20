package util;

import java.util.Scanner;

public class ScanUtil {
private static Scanner s = new Scanner(System.in);
	
	public static String nextLine(){
		String str = null;
		try {
			str =s.nextLine();
		} catch (Exception e) {
			System.out.println("잘못 입력하셨습니다.");
		}
		return str;
	}
	
	public static int nextInt(){
		int i = 0;
		try {
			i = Integer.parseInt(s.nextLine()); 
		} catch (Exception e) {
			System.out.println("잘못 입력하셨습니다.");
		}
		return i;
	}
}
