package jp.co.systena.tigerscave.model;

import java.util.ArrayList;
import java.util.List;

public class ResultForm {
  List<String> resultList;

  public ResultForm() {
    resultList = new ArrayList<String>();
  }

  public void setResultList(List<String> resultList) {
    this.resultList = resultList;
  }

  public List<String> getResultList() {
    return resultList;
  }

  public void addResult(String result) {
    resultList.add(result);
  }
}