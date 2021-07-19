package util;

import vo.CharacterVO;

public class CharacterSessionUtil {
	private CharacterSessionUtil() {}
	private static CharacterSessionUtil instance;
	private static CharacterVO character;
	public static CharacterSessionUtil getCharacterSessionUtil() {
		if(instance == null) {
			instance = new CharacterSessionUtil();
		}
		return instance;
	}
	
	public void setCharacter(CharacterVO vo) {
		character = vo;
	}
	
	public CharacterVO getCharacter() {
		return character;
	}
}
