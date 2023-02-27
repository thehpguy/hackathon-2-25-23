import java.util.*;
import java.io.*;

class Main {
  public static void createFile(String name) {
    try {
      File file = new File(name + ".txt");
      if (file.createNewFile()) {
        System.out.println("File created");
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    try {
      FileWriter writer = new FileWriter("all_files.txt", true);
      writer.write(name + "\n");
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void deleteFile(String name) {
    try {
      File file = new File(name + ".txt");
        if(file.delete()) {
          System.out.println(file.getName() + " is deleted!");
        } else {
          System.out.println("An error occured");
        }
      } catch(Exception e) {
        e.printStackTrace();
      }
      
    

    // deleting the filename from all_files.txt
    ArrayList<String> master = new ArrayList<String>();
    try (BufferedReader reader = new BufferedReader(new FileReader("all_files.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        master.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < master.size(); i++) {
      if (master.get(i).equals(name)) {
        master.remove(i);
        break;
      }
    }
    
      try {
        FileWriter writer = new FileWriter("all_files.txt");
        for (int i = 0; i < master.size(); i++) {
          writer.write(master.get(i) + "\n");    
        }
        writer.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    
  }
  

  public static void testTerms(ArrayList<String> sSet) {
    Collections.shuffle(sSet);
    if (sSet.size() > 0) {
    } else {
      System.out.println("Nothing is in this set!");
    }
    Scanner sc = new Scanner(System.in);
    while(true && sSet.size() > 0) {
      int lives = 3;
      int max = sSet.size()-1;
      int num = (int)(Math.random() * (max + 1));
      if(sSet.size() == 0) {
        break;
      }
      String term = sSet.get(num).substring(0, sSet.get(num).indexOf("|"));
      max--;
      System.out.println(term);
      String desc = sc.nextLine();
      String ans = sSet.get(num).substring(sSet.get(num).indexOf("|")+1);
      sSet.remove(num);
      if(ans.equals(desc)) {
        System.out.println("==----==\nCorrect\n==----==\n");
      } else {
        while(lives > 1){
          lives--;
          System.out.println("Try again... " + lives + " tries left");
          desc = sc.nextLine();

          if(ans.equals(desc)) {
            System.out.println("==----==\nCorrect\n==----==\n");
            break;
          } else if(lives == 0) {
            System.out.println();
          }
        }
      }
    }
  }

  public static void testDefs(ArrayList<String> sSet) {
    Collections.shuffle(sSet);
    Scanner sc = new Scanner(System.in);
    if (sSet.size() > 0) {
    } else {
      System.out.println("Nothing is in this set!");
    }
    while(true && sSet.size() > 0) {
      int lives = 3;
      int max = sSet.size()-1;
      int num = (int)(Math.random() * (max + 1));
      if(sSet.size() == 0) {
        break;
      }
      String term = sSet.get(num).substring(sSet.get(num).indexOf("|")+1);
      max--;
      System.out.println(term);
      String desc = sc.nextLine();
      String ans = sSet.get(num).substring(0, sSet.get(num).indexOf("|"));
      sSet.remove(num);
      if(ans.equals(desc)) {
        System.out.println("==----==\nCorrect\n==----==\n");
      } else {
        while(lives > 1){
          lives--;
          System.out.println("Try again... " + lives + " tries left");
          desc = sc.nextLine();

          if(ans.equals(desc)) {
            System.out.println("==----==\nCorrect\n==----==\n");
            break;
          } else if(lives == 0) {
            System.out.println();
          }
        }
      }
    }
  }
  
  public static void main(String[] args) {
    System.out.println("Welcome to Wizlet! An everyday tool to help you study!\n");
    boolean theFinalSent = true;
    while (theFinalSent) {
    System.out.println(
        "Would you like to:\n(1) Create a new study set\n(2) Edit an existing one\n(3) Use an existing study set\n(4) Delete a file\n(0) Exit out\n");

    Scanner scan = new Scanner(System.in);

    ArrayList<String> master = new ArrayList<String>();
    try (BufferedReader reader = new BufferedReader(new FileReader("all_files.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        master.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    String first = scan.nextLine();
    System.out.println();
      boolean scn = true;
    if (first.equals("1")) {
        int count = 0;
        while (true && scn) {
          System.out.print("Enter name of the study set: ");

          String name = scan.nextLine();
          System.out.println();
          createFile(name); // creates the new study set
          count++;

          System.out.println("Would you like to create another one (y/n)? ");
          String ans = scan.nextLine();
          

        boolean senty = true;
        while (senty) {
        if(ans.equals("y")) {
          senty = false;
        } else if (ans.equals("n")) {
          System.out.println("NO files created");
          senty = false;
          scn = false;
        } else {
          System.out.println("Input error, Try again..");
          ans = scan.nextLine();
        }
        }
        }
          

        System.out.println(count + " study set(s) created!");
      System.out.println();
    } else if (first.equals("2")) {
      // access one in the list
        System.out.println("Which study set would you like to edit?");
        System.out.print("Enter name of the study set: ");
        String name = scan.nextLine();
        System.out.println();
        while (!master.contains(name)) {
          System.out.println("Invalid name");
          System.out.print("Enter a vaild name of the study set: ");
          name = scan.nextLine();
          System.out.println();
        }
                
        StudySet sSet = new StudySet();

        try (BufferedReader feeder = new BufferedReader(new FileReader(name + ".txt"))) {
          String line;
          while ((line = feeder.readLine()) != null) {
            sSet.addTerm(line);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        System.out.println("What would you like to do with your set?");
        System.out.println("Here are all the methods you can use to edit the study set and create terms!");
        System.out.println();

        boolean sen = true;
        while (sen) {
          System.out.println("Commands:\n(1) Add a term\n(2) Remove a term\n(3) Modify a term or definition\n(0 to finish)\n"); 
          int choice = scan.nextInt();
          String mx = scan.nextLine();
          System.out.println();
          if (choice == 1) {
              System.out.print("Enter the term name: ");
              String term = scan.nextLine();
              System.out.println();
              System.out.print("Enter the term definition: ");
              String def = scan.nextLine();
              System.out.println();
              sSet.addTerm(term, def);
              System.out.println();
              System.out.println("Term added");

          }
            else if (choice == 2) {
              boolean vvv = true;
              if(sSet.getSet().size() == 0) {
                System.out.println("There aren't any terms to remove");
              }
              while (vvv && sSet.getSet().size() > 0) {
              System.out.println("Which term would you like to remove?\nHere's a list of your terms:\n");
              sSet.printSet();
              String s1 = scan.nextLine();
              System.out.println();
              boolean ooo =  sSet.removeTerm(s1);
              System.out.println("Removed.");


              if (ooo != true) {
                
              } else {
                vvv = false;
              }
            }
            }

            else if (choice == 3) {
              if(sSet.getSet().size() == 0) {
                System.out.println("There aren't any terms to remove");
              } else {
              System.out.println("Would you like to:\n(1) Change a term\n(2) Change a definition");
              System.out.println();
                while(true) {
                  int input = scan.nextInt();
                  String mf = scan.nextLine();
                  if(input == 1) {
                    boolean vv = true;
                    while (vv) {
                    sSet.printSet();
                    System.out.println("Which term would you like to change?");
                    sSet.printSet();
                    String s2 = scan.nextLine();
                    System.out.println("What would you like to change it to?");
                    String s3 = scan.nextLine();
                    System.out.println();
                    boolean oo = sSet.changeTerm(s2,s3);
                     if (oo != true) {
                       
                     } else {
                       vv = false;
                     }
                  }
                    break;
                  } else if(input == 2) {
                    boolean v = true;
                    while (v) {
                    sSet.printSet();
                    System.out.println("Which definition would you like to change?");
                    System.out.println();
                    String s2 = scan.nextLine();
                    System.out.println();
                    System.out.println("What would you like to change it to?");
                    String s3 = scan.nextLine();
                    System.out.println();
                      boolean o = sSet.changeDef(s2,s3);
                    if (o != true) {
                    } else {
                      v = false;
                    }
                    }
                    break;
                  } else if(input == 3) {
                    
                  } else {
                    System.out.println("Invalid input.");
                  }
                }
                }
            }
          else if (choice == 0) {
            sen = false;
            if(sSet.getSet().size() > 0) {
              System.out.println();
              System.out.println("Final:");
              sSet.printSet();
            
              //writing it into the file
              try {
                FileWriter myWriter = new FileWriter(name + ".txt");
                for(int i = 0;i < sSet.getSet().size(); i++) {
                  myWriter.write(sSet.getSet().get(i) + "\n");
                }
                myWriter.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
            }
          }
        }




      } else if (first.equals("3")) {
        System.out.println("Welcome to Wizlet Testing!");
        System.out.print("Enter the name of the set: ");
        String nameG = scan.nextLine();
        while (!master.contains(nameG)) {
          System.out.println("Invalid name");
          System.out.print("Enter a valid name of the study set: ");
          nameG = scan.nextLine();
          System.out.println();
        }

        ArrayList<String> testSetz = new ArrayList<String>();
        try (BufferedReader feederrr = new BufferedReader(new FileReader(nameG + ".txt"))) {
          String linez;
          while ((linez = feederrr.readLine()) != null) {
            testSetz.add(linez);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }

      
          boolean fewSen = true;
          while (fewSen) {
            System.out.println("Would you like to test your definitions or terms? (d/t)");
            String theAns = scan.nextLine();
            System.out.println();

            if (theAns.equals("t")) {
              testDefs(testSetz);
              fewSen = false;
            } else if (theAns.equals("d")) {
              testTerms(testSetz);
              fewSen = false;
            } else {
              System.out.println("Invalid answer.");
              theAns = scan.nextLine();
            }
          }
      } else if(first.equals("4")) {
        System.out.println("Which file would you like to delete?");
        String delFil = scan.nextLine();
        while (!master.contains(delFil)) {
          System.out.println("Invalid name");
          System.out.print("Enter a vaild set name to delete: ");
          delFil = scan.nextLine();
          System.out.println();
        }

        System.out.println("Are you sure? (y/n)");
        String input = scan.nextLine();
        boolean senty = true;
        while (senty) {
        if(input.equals("y")) {
          deleteFile(delFil);
          senty = false;
        } else if (input.equals("n")) {
          System.out.println("Set NOT deleted.");
          senty = false;
        
        } else {
          System.out.println("Input error, Try again..");
          input = scan.nextLine();
        }
        }
      }
      
      else if (first.equals("0")){
        System.out.println("The program is exiting! Goodbye!");
        theFinalSent = false;
      }
      else {
          System.out.println("That is an invalid option :(");
      }
    }
    }
}