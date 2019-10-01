package jp.co.systena.tigerscave.model;

public class Enemy {
	public static final String ENEMY_KEY = "ENEMY_KEY";
	int hp = 100;

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void damage() {
		if (hp > 0) {
			hp = hp - 10;
		}
	}

	public void strongDamage() {
		if (hp > 0) {
			hp = hp - 20;
		}
	}
}