package dev.thetechnokid.rw.net;

public enum Rank {
	NOOB(1, "Noob", "Nb"), AIRMAN(2, "Airman", "Am"), AIRMAN_FIRST_CLASS(3, "Airman First Class",
			"AmFC"), SENIOR_AIRMAN(4, "Senior Airman", "SrAm"), SERGEANT(5, "Sergeant", "Srgt"), MASTER_SERGEANT(6,
					"Master Sergeant",
					"MSrgt"), SENIOR_MASTER_SERGEANT(7, "Senior Master Sergeant", "SrMSrgt"), CHIEF_MASTER_SERGEANT(8,
							"Chief Master Sergeant", "CMSrgt"), COMMAND_CHIEF_MASTER_SERGEANT(9,
									"Command Chief Master Sergeant",
									"CoCMSrgt"), SECOND_LIEUTENANT(10, "Second Lieutenant", "SecLt"), FIRST_LIEUTENANT(
											11, "First Lieutenant", "FLt"), CAPTAIN(12, "Captian", "Cap"), MAJOR(13,
													"Major", "Mjr"), LIEUTENANT_COLONEL(14, "Lieutenant Colonel",
															"LtCl"), COLONEL(15, "Colonel", "Cl"), BRIGADIER_GENERAL(16,
																	"Brigadier General", "BgGnrl"), MAJOR_GENERAL(17,
																			"Major General",
																			"MjrGnrl"), LIEUTENANT_GENERAL(18,
																					"Lieutenant General",
																					"LtGnrl"), GENERAL(19, "General",
																							"Gnrl"), DICTATOR(20,
																									"Dictator", "Dctr");

	private int num;
	private String name;
	private String abbv;

	Rank(int num, String name, String abbv) {
		this.num = num;
		this.name = name;
		this.abbv = abbv;
	}

	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public String getAbbv() {
		return abbv;
	}
}
