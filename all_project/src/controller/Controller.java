package controller;

import util.ScanUtil;
import util.View;

public class Controller {
	public static void main(String[] args) {
		new Controller().start();
	}
	
	
	
	public int start() {
		int view =View.HOME;
		
		while(true){
			switch(view){
			case View.HOME: view = home(); break;
			case View.LOGIN: break;
			case View.JOIN: break;
			case View.SEARCH: break;
			}
		}
	}

	private int home() {
		System.out.println("============ DDIT RPG ============");
		System.out.println("1.로그인\t2.회원가입\t3.아이디및 비밀번호 찾기");
		System.out.print("메뉴를 선택해 주세요 >>");
		int input =ScanUtil.nextInt();
		
		switch(input){
		case 1: return View.LOGIN;
		case 2: return View.JOIN;
		case 3: return View.SEARCH;
		default:
			System.out.println("다시 입력해주세요");
			break;
		}
		return View.HOME;
	}
}
