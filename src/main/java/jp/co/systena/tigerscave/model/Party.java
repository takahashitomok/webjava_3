package jp.co.systena.tigerscave.model;

import java.util.ArrayList;
import java.util.List;

public class Party {
  public static final String PARTY_SESSION_KEY = "PARTY_SESSION_KEY";
  public static final int MAX_MEMBER = 4;

  List<Object> partyMember;
  int numberOfPeople;
  int dispNumber;

  public Party() {
    partyMember = new ArrayList<Object>();
  }

  public int getNumberOfPeople() {
    return numberOfPeople;
  }

  public void setNumberOfPeople(int numberOfPeople) {
    this.numberOfPeople = numberOfPeople;
  }

  public int getDispNumber() {
    return dispNumber;
  }

  public void setDispNumber(int dispNumber) {
    this.dispNumber = dispNumber;
  }

  public void addDispNumber() {
    dispNumber++;
  }

  public List<Object> getPartyMember() {
    return partyMember;
  }

  public void setPartyMember(List<Object> partyMember) {
    this.partyMember = partyMember;
  }

  public ListForm setDispData(ListForm dispData) {
    Object member = partyMember.get(dispNumber);

    if (member instanceof Senshi) {
      dispData.setName(((Senshi) member).getName());
      dispData.setJob(Job.SENSHI);
    } else if (member instanceof Witch) {
      dispData.setName(((Witch) member).getName());
      dispData.setJob(Job.WITCH);
    } else if (member instanceof MartialArtist) {
      dispData.setName(((MartialArtist) member).getName());
      dispData.setJob(Job.MARTIALARTIST);
    }

    return dispData;
  }

  @SuppressWarnings("incomplete-switch")
public void addMember(String name, String job) {
    numberOfPeople++;
    // 職業に応じてキャラクターを保存
    switch (job) {
      case Job.SENSHI:
        Senshi Senshi = new Senshi(name);
        partyMember.add(Senshi);
        break;
      case Job.WITCH:
        Witch witch = new Witch(name);
        partyMember.add(witch);
        break;
      case Job.MARTIALARTIST:
        MartialArtist martialArtist = new MartialArtist(name);
        partyMember.add(martialArtist);
        break;
    }
  }

  public void checkDisplayContinued(ListForm dispData) {
    if (numberOfPeople + 1 >= MAX_MEMBER) {
      dispData.isDisplayContinued = false;
    }
  }

  public void resetDispNumber() {
    dispNumber = 0;
  }
}