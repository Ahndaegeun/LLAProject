package util;

import java.util.ArrayList;
import java.util.List;
import controller.CharacterController;
import controller.DunjeonConteroller;
import controller.GameController;
import controller.HunttingController;
import controller.MarketController;
import controller.UserController;
import vo.CharacterVO;
import vo.DunjeonVO;
import vo.InventoryVO;
import vo.MarketVO;
import vo.MemberVO;
import vo.MonstersVO;
import vo.SkillsVO;

public class View {
	public static void main(String[] args) throws Exception {
		int input = 0;
		UserController userController = UserController.getLoginController();
		CharacterController characterController = CharacterController.getCharacterController();
		GameController gameController = GameController.getController();
		MarketController marketController = MarketController.getController();
		HunttingController hunttingController = HunttingController.getHunttingController();
		DunjeonConteroller dunjeonConteroller = DunjeonConteroller.geDunjeonConteroller();
		
		// Main Login
		main:while (true) {
			System.out.println("======== DDIT RPG ========");
			System.out.println("1. 로그인\t2. 회원가입\t3. 아이디 찾기 및 비밀번호 초기화\t4. 프로그램 종료");
			System.out.print("입력 >> ");
			input = ScanUtil.nextInt();
			
			switch(input) {
				case 1:
					System.out.println("========== 로그인 ==========");
					
					System.out.print("아이디 >> ");
					String id = ScanUtil.nextLine();
					System.out.print("비밀번호 >> ");
					String pw = ScanUtil.nextLine();
					
					MemberVO user = new MemberVO(id, pw);
					
					userController.login(user);
					
					if(LoginSessionUtil.getInstance().getUser() != null) {
						System.out.println("로그인 완료");
						break main;
					} else {
						System.out.println("로그인 실패 임마!");
					}
					break;
					
				case 2:
					
					System.out.println("========== 회원가입 ==========");
					System.out.print("이름 >> ");
					String name = ScanUtil.nextLine();
					System.out.print("주민번호 앞 6자리 >> ");
					String regNo = ScanUtil.nextLine();
					System.out.print("아이디 >> ");
					id = ScanUtil.nextLine();
					System.out.print("비밀번호 >> ");
					pw = ScanUtil.nextLine();
					System.out.print("비밀번호 확인 >> ");
					String pwCk = ScanUtil.nextLine();
					
					user = new MemberVO(id, pw, name, regNo);
					
					if(userController.SignUp(user, pwCk)) {
						System.out.println("가입 성공");
					} else {
						System.out.println("가입 실패");
					}
					break;
					
				case 3:
					System.out.println("========== 아이디 찾기 및 비밀번호 초기화 ==========");
					System.out.println("1.아이디 찾기\t2.비밀번호 초기화");
					System.out.print("아이디 찾기 >> ");
					input = ScanUtil.nextInt();
					
					switch (input) {
						case 1:
							System.out.println("========== 아이디 찾기 ==========");
							System.out.print("이름 >> ");
							name = ScanUtil.nextLine();
							System.out.print("주민번호 앞 6자리 >> ");
							regNo = ScanUtil.nextLine();
							
							user = new MemberVO(null, null, name, regNo);
							String findId = userController.searchId(user);
							if(findId != null) {
								System.out.println("아이디 >> " + findId);
								break;
							}
							System.out.println("아이디 없음");
							break;
	
						case 2:
							System.out.println("========== 비밀번호 초기화 ==========");
							System.out.print("이름 >> ");
							name = ScanUtil.nextLine();
							System.out.print("주민번호 앞 6자리 >> ");
							regNo = ScanUtil.nextLine();
							System.out.print("아이디 >> ");
							id = ScanUtil.nextLine();
							
							user = new MemberVO(id, null, name, regNo);
							if(userController.resetPw(user)) {
								System.out.println("비밀번호가 주민번호 앞 6자리로 초기화 되었습니다.");
								break;
							}
							System.out.println("잘못 입력하셨습니다. 다시 진행해 주세요");
							break;
					}
					break;
					
				case 4:
					System.out.println("프로그램 종료....돌아와....");
					break main;
					
				default:
					System.out.println("잘못 입력했습니다.");
			}
		}
		
		//Choose Character
		character:while (LoginSessionUtil.getInstance().getUser() != null) {
			List<CharacterVO> list = new ArrayList<>();
			System.out.println("========== 캐릭터 메뉴 ==========");
			System.out.println("1.캐릭터 생성\t2.캐릭터 선택\t3.캐릭터 삭제");
			System.out.print("입력 >> ");
			input = ScanUtil.nextInt();
			switch (input) {
				case 1:
					System.out.println("========== 캐릭터 생성 ==========");
					System.out.print("캐릭터 이름 >> ");
					String charName = ScanUtil.nextLine();
					
					boolean create = characterController.createCharacter(new CharacterVO(charName, LoginSessionUtil.getInstance().getUser().getId()));
					if(create) {
						System.out.println("캐릭터 생성 완료");
					} else {
						System.out.println("생성 실패...");
					}
					break;
				case 2:
					System.out.println("========== 캐릭터 선택 ==========");
					list = characterController.showAllCharacters(new MemberVO(LoginSessionUtil.getInstance().getUser().getId()));
					if(list == null) {
						System.out.println("생성한 캐릭터가 없습니다. 생성해 주세요");
						break;
					}
					System.out.println("캐릭터 번호\t이름\t레벨\t직업");
					for(int i = 0; i < list.size(); i++)  {
						System.out.printf("%d. %s / %d / %s\n", i+1, list.get(i).getCharName(), list.get(i).getCharLevel(), list.get(i).getJob());
					}
					System.out.println("==============================");
					System.out.print("플레이할 캐릭터 번호를 선택해 주세요 (돌아가기 : 0) >> ");
					input = ScanUtil.nextInt();
					if(input == 0) {
						System.out.println("돌아가기!");
						break;
					}
					
					CharacterSessionUtil.getCharacterSessionUtil().setCharacter(list.get(input - 1));
					break character;
				case 3:
					System.out.println("========== 캐릭터 삭제 ==========");
					list = characterController.showAllCharacters(new MemberVO(LoginSessionUtil.getInstance().getUser().getId()));
					if(list == null) {
						System.out.println("생성된 캐릭터가 없습니다. 캐릭터를 생성해 주세요");
						break;
					}
					System.out.println("캐릭터 번호\t이름\t레벨\t직업");
					
					for(int i = 0; i < list.size(); i++)  {
						System.out.printf("%d. %s / %d / %s\n", i+1, list.get(i).getCharName(), list.get(i).getCharLevel(), list.get(i).getJob());
					}
					System.out.println("==============================");
					System.out.print("삭제할 캐릭터 번호를 입력해 주세요 (돌아가기 : 0) >> ");
					input = ScanUtil.nextInt();
					
					if(input == 0) {
						System.out.println("돌아가기!");
						break;
					}
					boolean result = characterController.deleteCharacter(list.get(input - 1));
					if(result == true) {
						System.out.println("캐릭터 삭제 완료!");
						break;
					}
					System.out.println("잘못 입력 했습니다.");
					break;
				default:
					System.out.println("메뉴로 돌아갑니다.");
					break;
			}
		}
		
		//Play game
		playGame:while(CharacterSessionUtil.getCharacterSessionUtil().getCharacter() != null) {
			CharacterVO currentChar = CharacterSessionUtil.getCharacterSessionUtil().getCharacter();
			CharacterVO user = gameController.getCharacterInfo(currentChar);
			System.out.println("============== DDIT RPG 시작 ==============");
			System.out.println("1.내 정보\t2.아이템 가방\t3.자유 시장\t4.필드 사냥\t5.심연의 탑\t6.게임 종료 ");
			System.out.print("메뉴 선택 >> ");
			input = ScanUtil.nextInt();
			switch (input) {
				case 1:
					System.out.println("============ 내 정보 ============");
					CharacterVO info = gameController.getCharacterInfo(currentChar);
					System.out.println("이름 : " + info.getCharName());
					System.out.println("체력 : " + info.getCharHp() + " / " + info.getCharMaxHp());
					System.out.println("마나 : " + info.getCharMp() + " / " + info.getCharMaxMp());
					System.out.println("공격력 : " + info.getCharAtt());
					System.out.println("방어력 : " + info.getCharDef());
					System.out.println("레벨 : " + info.getCharLevel());
					System.out.println("경험치 : " + info.getCharExe() + " / " + info.getCharMaxExe());
					System.out.println("무기 : " + info.getCharWeapon()); 
					System.out.println("방어구 : " + info.getCharArmor());
					System.out.println("골드 : " + info.getCharGold());
					System.out.println("직업 : " + info.getJob());
					System.out.println("심연의 탑 최고 층 : " + info.getFloor());
					System.out.println("=================================");
					
				equip:while(true) {
					System.out.println("1.장착 무기 해제\t2.장착 방어구 해제\t3.돌아가기");
					System.out.print("메뉴 선택 >> ");
					input = ScanUtil.nextInt();
					switch (input) {
						case 1:
							System.out.println("========== 무기 해제 ==========");
							boolean result = gameController.unEquippingWeapon(currentChar);
							if(result) {
								System.out.println("완료");
								break equip;
							}
							System.out.println("실패");
							break equip;
						case 2:
							System.out.println("========== 방어구 해제 ==========");
							result = gameController.unEquippingArmor(currentChar);
							if(result) {
								System.out.println("완료");
								break equip;
							}
							System.out.println("실패");
							break equip;
						case 3:
							System.out.println("메뉴로 돌아가기");
							break equip;
							
						default:
							System.out.println("다시 입력");
							break;
					}
				}
					break;
				case 2:
					System.out.println("=============== 아이템 가방 ===============");
					List<InventoryVO> list = gameController.showInventory(currentChar);
					if(list.size() == 0) {
						System.out.println("님 아이템 없음....");
						break;
					}
					System.out.println("아이템 번호\t아이템 이름\t아이템 개수");
					int i = 0;
					for(InventoryVO entry : list) {
						System.out.println(++i + "\t" + entry.getItemName() + "\t" + entry.getItemCo() + "\t" + entry.getDitin());
					}
					System.out.println("======================================");
					System.out.println("1.아이템 사용\t2.메뉴로 돌아가기");
					input = ScanUtil.nextInt();
					switch (input) {
						case 1:
							System.out.print("아이템 번호를 입력해 주세요 >> ");
							input = ScanUtil.nextInt();
							gameController.useItem(new InventoryVO(list.get(input - 1).getItemName(), currentChar.getCharIdx(), list.get(input - 1).getItemCo(), list.get(input - 1).getDitin()));
							
							break;
						default:
							System.out.println("메뉴로 돌아갑니다.");
					}
					break;
				case 3:
					market:while(true) {
						System.out.println("=============== 자유 시장 ===============");
						System.out.println("1.거래 목록\t2.글 작성\t3.나가기");
						input = ScanUtil.nextInt();
						switch (input) {
							case 1:
								System.out.println("========== 거래 목록 ==========");
								List<MarketVO> marketList = marketController.showBoardList();
								System.out.println("거래 번호\t제목\t내용\t아이템 이름\t가격");
								int marketIndex = 1;
								for(MarketVO entry : marketList) {
									System.out.println(marketIndex++ + "\t" + entry.getMarketTitle() + "\t" + entry.getMarketContents()+ "\t" + entry.getItemNm() + "\t" + entry.getMarketPrice());
								}
								System.out.println("========================");
								System.out.println("1.거래 번호 선택\t2.돌아가기");
								input = ScanUtil.nextInt();
								switch (input) {
									case 1:
										boolean result = marketController.choiceBoard(marketList.get(input - 1), currentChar);
										if(result) {
											System.out.println("거래 완료");
											break;
										}
										System.out.println("실패");
										break;
									default:
										System.out.println("자유시장으로 돌아가기");
										break;
								}
							break;
						case 2:
							System.out.println("========== 글 작성 ==========");
							System.out.print("제목 >> ");
							String title = ScanUtil.nextLine();
							System.out.print("내용 >> ");
							String contents = ScanUtil.nextLine();
							System.out.print("아이템 이름 >> ");
							String itemName = ScanUtil.nextLine();
							System.out.print("구매 가격 >> ");
							int price = ScanUtil.nextInt();
							System.out.print("아이템 갯수");
							int count = ScanUtil.nextInt();
							boolean isSuccess = marketController.insertBaord(new MarketVO(0, title, contents, price, "1", currentChar.getCharIdx(), itemName, count), currentChar);
							
							if(isSuccess) {
								System.out.println("작성 완료");
								break;
							} else {
								System.out.println("돈을 확인해 보면 좋을듯....");
							}
							break;
							
						default:
							System.out.println("돌아가....");
							break market;
						}
					}
					break;
				case 4:
					System.out.println("=============== 필드 사냥 ===============");
					System.out.println();
					MonstersVO monster = hunttingController.getMonster(user);
					List<Object> fighters = new ArrayList<>();	
					fighters.add(0, user);
					System.out.println("========== 몬스터 정보 ==========");
					System.out.println("몬스터 이름 >> " + monster.getMonNm());
					System.out.println("체력 >> " + monster.getMomHp());
					System.out.println("=============================");
					System.out.println();
					huntting:while(true) {
						user = gameController.getCharacterInfo(currentChar);
						fighters.add(0, user);
						fighters.add(1, monster);
						System.out.println("1.기본 공격\t2.기술 사용\t3.도망가기(못갈수도...)");
						input = ScanUtil.nextInt();
						switch (input) {
							case 1:
								currentChar = gameController.getCharacterInfo(user);
								monster = hunttingController.characterBasicAtt(fighters);
								user = gameController.getCharacterInfo(currentChar);
								fighters.add(0, user);
								fighters.add(1, monster);
								break;
							case 2:
								List<SkillsVO> skillList = hunttingController.showSkillList(user);
								if(skillList.size() <= 0) {
									System.out.println("님 스킬 없음");
									System.out.println();
									break;
								}
								System.out.println("========== 스킬 목록 ==========");
								for(SkillsVO entry : skillList) {
									System.out.println("스킬 이름 >> " + entry.getSkillNm());
								}
								System.out.println("===========================");
								System.out.print("사용할 스킬 이름 >> ");
								String skillName = ScanUtil.nextLine();
								monster = hunttingController.characterSkillAtt(fighters, new SkillsVO(skillName, 0, 0, 0, user.getJob()));
								user = gameController.getCharacterInfo(currentChar);
								fighters.add(0, user);
								fighters.add(1, monster);
								break;
							case 3:
								if(hunttingController.run()) {
									System.out.println("도망 성공 ㅠ.ㅠ");
									break huntting;
								}
								System.out.println("도망 실패 >_<");
								break;
	
							default:
								System.out.println("잘못 입력 했습니다.");
								break;
						}
						
						if(monster.getMomHp() <= 0) {
							System.out.println("몬스터를 죽였다!!");
							System.out.println();
							int beforLev = user.getCharLevel();
							hunttingController.getExe(user, monster);
							if(characterController.levelCheck(gameController.getCharacterInfo(user))) {
								System.out.println("전직이 가능합니다. 3가지 직업중 선택해 주세요");
								System.out.println("1.전사\t2.마법사\t3.도적");
								String job = ScanUtil.nextLine();
								characterController.changeClass(currentChar, job);
							}
							int afterLev = gameController.getCharacterInfo(user).getCharLevel();
							if(beforLev < afterLev) {
								System.out.println("레벨 업");
							}
							if(gameController.chooseItemOrGold() == 1) {
								gameController.userGetItem(user, monster);
								System.out.println("아이템 획득");
							} else {
								gameController.userGetGold(user, monster);
								System.out.println("골드 획득");
							}
							break huntting;
						} else if(user.getCharHp() <= 0) {
							
							System.out.println("죽으셨어요...50%체력으로 부활 합니다.");
							System.out.println();
							hunttingController.userDie(user);
							break huntting;
						} else {
							user = gameController.getCharacterInfo(currentChar);
							System.out.println("========== 몬스터 ==========");
							System.out.println("몬스터 이름 >> " + monster.getMonNm());
							System.out.println("남은 체력 >> " + monster.getMomHp());
							System.out.println("========== "+user.getCharName()+" ==========");
							System.out.println("내 체력 >> " + user.getCharHp() + " / " + user.getCharMaxHp());
							System.out.println("내 마나 >> " + user.getCharMp() + " / " + user.getCharMaxMp());
							System.out.println("========================");
							System.out.println();
						}
						
					}
					break;
				case 5:
					System.out.println("=============== 심연의 탑 ===============");
					System.out.println("1.층 선택\t2.메뉴로 돌아가기");
					System.out.print("입력 >> ");
					input = ScanUtil.nextInt();
					
					switch (input) {
						case 1:
							DunjeonVO dunjeon = dunjeonConteroller.showUserDunjeon(user);
							System.out.println("당신의 최고 층 >> " + dunjeon.getFloor());
							System.out.print("층을 선택해 주세요 >> ");
							int floor = ScanUtil.nextInt();
							if(floor > dunjeon.getFloor()) {
								System.out.println("아직 갈 수 없는 층 입니다.");
								break;
							}
							System.out.println("========== 현재 층 정보 ==========");
							System.out.println("층 >> " + dunjeon.getFloor());
							System.out.println("입장료 >> " + dunjeon.getAdmfee());
							System.out.println("몬스터 이름 >> " + dunjeon.getMonNm());
							System.out.println("=================================");
							System.out.print("입장 하시겠습니까? (1. 네 / 2. 아니오) !숫자 입력");
							int enter = ScanUtil.nextInt();
							if(enter == 1 && user.getCharGold() < dunjeon.getAdmfee()) {
								System.out.println("입장료 부족!!!!");
								break;
							}
							switch (enter) {
								case 1:
									monster = dunjeonConteroller.getDunjeonMonster(dunjeon);
									fighters = new ArrayList<>();	
									fighters.add(0, user);
									fighters.add(1, monster);
									//
									dunjeon:while(true) {
										user = gameController.getCharacterInfo(currentChar);
										fighters.add(0, user);
										fighters.add(1, monster);
										
										System.out.println("1.기본 공격\t2.기술 사용");
										input = ScanUtil.nextInt();
										switch (input) {
											case 1:
												monster = hunttingController.characterBasicAtt(fighters);
												user = gameController.getCharacterInfo(user);
												break;
											case 2:
												List<SkillsVO> skillList = hunttingController.showSkillList(user);
												if(skillList.size() <= 0) {
													System.out.println("스킬이 없네요...");
													System.out.println("5레벨에 전직하면 스킬 사용이 가능합니다.");
													System.out.println();
													break;
												}
												System.out.println("========== 스킬 목록 ==========");
												for(SkillsVO entry : skillList) {
													System.out.println("스킬 이름 >> " + entry.getSkillNm());
												}
												System.out.println("===========================");
												System.out.print("사용할 스킬 이름 입력 >> ");
												String skillName = ScanUtil.nextLine();
												monster = hunttingController.characterSkillAtt(fighters, new SkillsVO(skillName, 0, 0, 0, user.getJob()));
												user = gameController.getCharacterInfo(user);
												break;
											default:
												System.out.println("다시 입력해 주세요");
												break;
										}
										if(monster.getMomHp() <= 0) {
											System.out.println("몬스터 처치!");
											System.out.println();
											int beforLev = currentChar.getCharLevel();
											hunttingController.getExe(user, monster);
											if(characterController.levelCheck(gameController.getCharacterInfo(user))) {
												System.out.println("전직이 가능합니다. 3가지 직업중 선택해 주세요");
												System.out.println("1.전사\t2.마법사\t3.도적");
												String job = ScanUtil.nextLine();
												characterController.changeClass(user, job);
											}
											int afterLev = gameController.getCharacterInfo(user).getCharLevel();
											if(beforLev < afterLev) {
												System.out.println("레벨 업");
											}
											if(gameController.chooseItemOrGold() == 1) {
												gameController.userGetItem(user, monster);
												System.out.println("아이템 획득");
											} else {
												gameController.userGetGold(user, monster);
												System.out.println("골드 획득");
											}
											//층 올라가기
											dunjeonConteroller.goUpOneFloor(user);
											break dunjeon;
										} else if(user.getCharHp() <= 0) {
											
											System.out.println("죽으셨네요...50% 체력으로 부활 합니다.");
											System.out.println();
											hunttingController.userDie(user);
											break dunjeon;
										} else {
											user = gameController.getCharacterInfo(currentChar);
											System.out.println("========== 몬스터 ==========");
											System.out.println("몬스터 이름 >> " + monster.getMonNm());
											System.out.println("남은 체력 >> " + monster.getMomHp());
											System.out.println("========== "+user.getCharName()+" ==========");
											System.out.println("내 체력 >> " + user.getCharHp() + " / " + user.getCharMaxHp());
											System.out.println("내 마나 >> " + user.getCharMp() + " / " + user.getCharMaxMp());
											System.out.println("========================");
											System.out.println();
										}
									}
									break;
	
								default:
									System.out.println("뒤로가기.");
									break;
							}
							break;
						case 2:
							System.out.println("돌아갑니다.");
							break;
	
						default:
							System.out.println("다시 입력하세요");
							break;
					}
					
					break;
				case 6:
					System.out.println("게임 종료");
					System.out.println("다시 올꺼지...?");
					break playGame;
				default:
					System.out.println("잘못 입력하셨습니다.");
					break;
			}
		}
		
		
	}
}
