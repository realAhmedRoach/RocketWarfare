package dev.thetechnokid.rw.net;

public enum Rank {
	NOOB("Noob", "Nb"),
	AIRMAN("Airman", "Am"),
	AIRMAN_FIRST_CLASS("Airman First Class", "AmFC"),
	SENIOR_AIRMAN("Senior Airman", "SrAm"),
	AIR_MARSHAL("Air Marshal", "AMsh"),
	SENIOR_AIR_MARSHAL("Senior Air Marshal", "SrAMsh"),
	SERGEANT("Sergeant", "Srgt"),
	MASTER_SERGEANT("Master Sergeant", "MSrgt"),
	SENIOR_MASTER_SERGEANT("Senior Master Sergeant", "SrMSrgt"),
	CHIEF_MASTER_SERGEANT("Chief Master Sergeant", "CMSrgt"),
	COMMAND_CHIEF_MASTER_SERGEANT("Command Chief Master Sergeant", "CoCMSrgt"),
	SECOND_LIEUTENANT("Second Lieutenant", "SLt"),
	FIRST_LIEUTENANT("First Lieutenant", "FLt"),
	CAPTAIN("Captian", "Cap"),
	MAJOR("Major", "Mjr"),
	LIEUTENANT_COLONEL("Lieutenant Colonel", "LtCl"),
	COLONEL("Colonel", "Cl"),
	BRIGADIER_GENERAL("Brigadier General", "BgGnrl"),
	MAJOR_GENERAL("Major General", "MjrGnrl"),
	LIEUTENANT_GENERAL("Lieutenant General", "LtGnrl"),
	GENERAL("General", "Gnrl"),
	LIEUTENANT_DICTATOR("Lieutenant Dictator", "LtDcttr"),
	DICTATOR("Dictator", "Dcttr");

	private String name;
	private String abbv;

	private static Rank[] vals = values();

	Rank(String name, String abbv) {
		this.name = name;
		this.abbv = abbv;
	}

	public Rank next() {
		return vals[(this.ordinal() + 1) % vals.length];
	}

	public int getLevel() {
		return this.ordinal() + 1;
	}

	public String getName() {
		return name;
	}

	public String getAbbv() {
		return abbv;
	}
}
