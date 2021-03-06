package controller;

import java.util.List;
import java.util.Random;

import dao.CharacterDAO;
import dao.MonstersDAO;
import dao.SkillsDAO;
import vo.CharacterVO;
import vo.MonstersVO;
import vo.SkillsVO;

public class HunttingController {
	private HunttingController() {}
	private static HunttingController instance;
	public static HunttingController getHunttingController() {
		if(instance == null) {
			instance = new HunttingController();
		}
		return instance;
	}
	
	private Random rnd = new Random();
	private MonstersDAO monDao = MonstersDAO.getMonsterDAO();
	private CharacterDAO charDao = CharacterDAO.getCharacterDAO();
	
	public MonstersVO getMonster(CharacterVO vo) throws Exception {
		List<MonstersVO> list = monDao.showMonsterList();
		MonstersVO monster = null;
		
		while(true) {
			int index = rnd.nextInt(11);
			if(list.get(index).getMomLev() <= vo.getCharLevel()) {
				monster = list.get(index);
				break;
			}
		}
		
		
		return monster;
	}
	
	public boolean run() {
		return charDao.run();
	}
	
	public MonstersVO characterBasicAtt(List<Object> fighters) throws Exception {
		CharacterVO user = (CharacterVO)fighters.get(0);
		MonstersVO monster = (MonstersVO)fighters.get(1);
		
		double userAtt = charDao.basicAtt(user);
		monster = monDao.defMonster(monster, userAtt);
		
		
		double monAtt = monDao.attMonster(monster);
		charDao.beingAtt(user, monAtt);
		
		
		return monster;
	}
	
	public MonstersVO characterSkillAtt(List<Object> fighters, SkillsVO skillVo) throws Exception {
		CharacterVO user = (CharacterVO)fighters.get(0);
		MonstersVO monster = (MonstersVO)fighters.get(1);
		
		SkillsVO userSkill = charDao.skillAtt(user, skillVo);
		charDao.beforSkillUse(user, userSkill);
		monster = monDao.defMonster(monster, userSkill.getSkillAtt());
		
		return monster;
	}
	
	public List<SkillsVO> showSkillList(CharacterVO vo) {
		List<SkillsVO> skillList = SkillsDAO.getSkillsDAO().showSkillList(vo);
		return skillList;
	}
	
	public void userDie(CharacterVO vo) {
		charDao.die(vo);
	}
	
	public boolean getExe(CharacterVO charVo, MonstersVO monVo) throws Exception {
		return charDao.getExe(charVo, (monVo.getMomLev() * 30));
	}
}













