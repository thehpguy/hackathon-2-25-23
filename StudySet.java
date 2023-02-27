import java.util.*;

class StudySet{

  private ArrayList<String> sett = new ArrayList<String>();
  
  public StudySet() {
  }

  public ArrayList<String> getSet() {
    return sett;
  }

  public void addTerm(String term, String definition) {
    sett.add(term + "|" + definition);
  }
  public void addTerm(String term) {
    sett.add(term);
  }

  public boolean removeTerm(String term) {
    int index = 0;
    boolean  valid = false;
    for (int i = 0; i < sett.size(); i++) {
       if (term.equals(sett.get(i).substring(0, sett.get(i).indexOf("|")))) {
        index = i;
         valid = true;
        break;
      }
    }
    if (valid) {
    sett.remove(index);
    return true;
    } 
    return false;    
  }

  
  public boolean changeTerm(String term, String term2) {
    int index = 0;
    String def = "";
    boolean valid = false;
    for (int i = 0; i < sett.size(); i++) {

      if (term.equals(sett.get(i).substring(0, sett.get(i).indexOf("|")))) {
        index = i;
        def = sett.get(i).substring((sett.get(i).indexOf("|")+1));
        System.out.println(def);
        valid = true;
        break;
      }
    }
    if (valid) {
    sett.set(index, (term2+"|"+def));
      return true;
    } 
    return false;
  }

  public boolean changeDef(String term, String term2) {
    int index = 0;
    String def = "";
    boolean valid = false;
    for (int i = 0; i < sett.size(); i++) {

      if (term.equals(sett.get(i).substring((sett.get(i).indexOf("|")+1)))) {
        index = i;
        def = sett.get(i).substring(0, (sett.get(i).indexOf("|")));
        valid = true;
        break;
      }
    } if (valid) {
    sett.set(index, (def+"|"+term2));
      return true;
    } return false;
  }

  public void printSet() {
    int count = 0;
    
    for (String i : sett) {
      String definition = i.substring(i.indexOf("|") + 1);
      String term = i.substring(0, i.indexOf("|"));
      System.out.println(term + " - " + definition + "\n");
      count++;
    }
  }

}