package jp.co.systena.tigerscave.model;

public class Witch extends Job{

	public Witch() {

	}

	public Witch(String name) {
		this.name = name;
	}

	public Witch(String name, String action) {
		this.name = name;
		this.action = action;
	}

	public String fight() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append("はまほうで攻撃した！　HP：-10");

		return builder.toString();
	}

	public String strongFight() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append("はまほうで強攻撃した！　HP：-20");

		return builder.toString();
	}

	public String recovery() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append("はまほうで回復した！");

		return builder.toString();
	}
}