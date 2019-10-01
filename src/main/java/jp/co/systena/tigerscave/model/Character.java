package jp.co.systena.tigerscave.model;

import javax.validation.constraints.Pattern;

public class Character {

	@Pattern(regexp="^[0-9]*$")
	private String characterId;
	public String getCharacterId() {
		return characterId;
	}
	public void setCharacterId(String characterId) {
		this.characterId = characterId;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public String getAttackPoint() {
		return attackPoint;
	}
	public void setAttackPoint(String attackPoint) {
		this.attackPoint = attackPoint;
	}
	private String characterName;
	@Pattern(regexp="^[0-9]*$")
	private String attackPoint;
}