class Term {
  private String term = "";
  private String definition = "";
  
  public Term(String userTerm, String userDefinition) {
    term = userTerm;
    definition = userDefinition;
  }

  public String getTerm() {
    return term;
  }

  public String getDefinition() {
    return definition;
  }
  
}