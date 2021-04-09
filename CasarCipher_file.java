
import java.io.File;
import java.util.Scanner;

/**
 *
 * @Hawzin Hassan Mohamed
 	SID:22007895
 */

public class CasarCipher_file {

    static String[] en_alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    static String[] fr_alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "é", "è", "à", "ù", "ç", "â", "ê", "î", "ô", "û", "ë", "ï"};

    static String[] sp_alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    static String[] tr_apphabetic = {"a", "b", "c", "ç", "d", "e", "f", "g", "g", "h", "i", "i", "j", "k", "l", "m", "n", "o", "ö", "p", "r", "s", "s", "t", "u", "ü", "v", "y", "z", "q", "w", "x"};



    /////////////// encrypt Method ///////////////////
    public static String encrypt(String text, String[] lang, int shift) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result += " ";
                continue;
            }
            for (int j = 0; j < lang.length; j++) {
                if (lang[j].equals(text.charAt(i) + "")) {

                    result += lang[(j + shift) % lang.length];
                }
            }
        }
        return result;
    }

    /////////////// decrypt Method ///////////////////
    public static String decrypt(String text, String[] lang, int shift) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result += " ";
                continue;
            }
            for (int j = 0; j < lang.length; j++) {
                if (lang[j].equals(text.charAt(i) + "")) {

                    int index = (j - shift) % lang.length;
                    if (index < 0) {
                        index = index + lang.length;
                    }
                    result += lang[index];
                }
            }
        }
        return result;
    }



    public static void main(String[] args) {

         try {
      File myObj = new File("input.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String text_line = myReader.nextLine();

         System.out.println("text : " + text_line);

        String[] txt = text_line.split(":");

            if (txt.length != 4) {
                System.out.println("You have an Error in Syntax");
            } else {
                int shift = Integer.parseInt(txt[0]);
                String type = txt[1];
                String la = txt[2];
                String palin_txt = txt[3].toLowerCase();

                String[] lang = null;

                if (la.equals("0")) {
                    lang = en_alphabet;
                } else if (la.equals("1")) {
                    lang = fr_alphabet;
                } else if (la.equals("2")) {
                    lang = sp_alphabet;
                } else if (la.equals("3")) {
                    lang = tr_apphabetic;
                }

                if (type.equals("0")) {
                    String cipher = encrypt(palin_txt, lang, shift);
                    System.out.println("Encrypted text : " + cipher);

                } else if (type.equals("1")) {
                    String plain = decrypt(palin_txt, lang, shift);
                    System.out.println("Decrypted text : " + plain);

                }
            }

	  System.out.println("\n*************************************\n");

      }
      myReader.close();
    } catch (Exception e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    }

}
