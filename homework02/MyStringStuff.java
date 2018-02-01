public class MyStringStuff {
  public static void main (String[] args) {
    System.out.println(containsVowel("Blo00op"));
    System.out.println(isPalindrome("aba"));
    System.out.println(evensOnly("REHEARSALS"));
    System.out.println(oddsOnly("xylophones"));
    System.out.println(reverse("hello0ooo"));
  }

  public static boolean containsVowel(String s) {
    String vowels = new String("AEIOUaeiou");
    char[] s_array = s.toCharArray();

    for (int i = 0; i < s_array.length; i++) {
      if (vowels.indexOf(s_array[i]) != -1) {
        return true;
      }
    }

    return false;
  }

  public static boolean isPalindrome(String s) {
    char[] s_array = s.toCharArray();
    boolean s_palindrome_value = false;

    if (s_array.length == 0 || s_array.length == 1) {
      return s_palindrome_value = true;
    } else {
      for (int i = 0; i < s_array.length; i++) {
        if (i > s_array.length - (i + 1)) {
          s_palindrome_value = true;
          break;
        }

        if (s_array[i] != s_array[s_array.length - (i + 1)]) {
          return s_palindrome_value = false;
        }
      }
    }

    return s_palindrome_value;
  }


  public static String evensOnly(String s) {
    char[] s_array = s.toCharArray();
    String s_even_characters = new String();

    for (int i = 0; i < s_array.length; i++) {
      if ((int)s_array[i] > 64 && (int)s_array[i] < 91) {
        if (((int)s_array[i] - 64) % 2 == 0) {
          s_even_characters += s_array[i];
        }
      } else if ((int)s_array[i] > 96 && (int)s_array[i] < 123) {
        if (((int)s_array[i] - 96) % 2 == 0) {
          s_even_characters += s_array[i];
        }
      }
    }

    return s_even_characters;
  }

  public static String oddsOnly(String s) {
    char[] s_array = s.toCharArray();
    String s_odd_characters = new String();

    for (int i = 0; i < s_array.length; i++) {
      if ((int)s_array[i] > 64 && (int)s_array[i] < 91) {
        if (((int)s_array[i] - 64) % 2 != 0) {
          s_odd_characters += s_array[i];
        }
      } else if ((int)s_array[i] > 96 && (int)s_array[i] < 123) {
        if (((int)s_array[i] - 96) % 2 != 0) {
          s_odd_characters += s_array[i];
        }
      }
    }

    return s_odd_characters;
  }

  public static String reverse(String s) {
    char[] s_array = s.toCharArray();
    String s_array_reversed = new String();

    for (int i = 0; i < s_array.length; i++) {
      s_array_reversed = s_array[i] + s_array_reversed;
    }

    return s_array_reversed;
  }
}
