import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public final class DynamicChangeMaker {
  private DynamicChangeMaker() { }

  // main ting
  public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int target) {
    validateDenominations(denominations);
    validateTarget(target);
    Map<Integer, Integer> coin_to_index = mapping(denominations);
    Tuple[][] table = new Tuple[denominations.length][target + 1];
    int rows = denominations.length;
    int columns = target + 1;
    int cost = 0;
    int previousCost = 0;

    Arrays.sort(denominations);

    System.out.println("denominations: ");
    for (int i = 0; i < denominations.length; i++) {
      System.out.println(denominations[i]);
    }
    initColZero(table, denominations.length);

    for (int i = 0; i < rows; i++) {
      for (int j = 1; j < columns; j++) {
        cost = j;
        if (denominations[i] > j) {
          table[i][j] = Tuple.IMPOSSIBLE;
          if (i != 0) {
            if (!table[i-1][j].isImpossible()) {
              table[i][j] = table[i-1][j];
            }
          }
        } else {
          Tuple combinations = new Tuple(denominations.length);
          combinations.setElement(i, 1);
          table[i][j] = combinations;

          if ( (j - denominations[i]) >= 0 ) {
            previousCost = j - denominations[i];
            if (!table[i][previousCost].isImpossible()) {
              table[i][j] = table[i][j].add(table[i][previousCost]);
            } else { table[i][j] = Tuple.IMPOSSIBLE; }
          }

          if (i != 0) {
            if (!table[i-1][j].isImpossible()) {
              if (table[i-1][j].total() < table[i][j].total()) {
                table[i][j] = table[i-1][j];
              }
            }
          }
        }
      }
    }
    return table[rows-1][columns-1];
  }

  // initialize col zero to zero tuple
  private static void initColZero(Tuple[][] table, int coins) {
    for (int i = 0; i < coins; i++) {
      table[i][0] = new Tuple(coins);
    }
  }

  // validate denominations
  private static void validateDenominations(int[] denominations) {
    boolean existingNegatives = checkForNegatives(denominations);
    boolean existingDuplicates = checkForDuplicates(denominations);
    if (existingDuplicates || existingNegatives) { throw new IllegalArgumentException(); }
  }

  // validate target
  private static void validateTarget(int target) {
    if (target < 0) { throw new IllegalArgumentException(); }
  }

  // checks for neg denominations
  private static boolean checkForNegatives(int[] denominations) {
    for (int i = 0; i < denominations.length; i++) {
      if (denominations[i] <= 0) { return true; }
    }

    return false;
  }

  // checks for duplicate denominations
  private static boolean checkForDuplicates(int[] denominations) {
    Set<Integer> coins = new HashSet<Integer>();

    for (int i = 0; i < denominations.length; i++) {
      if (!coins.contains(new Integer(denominations[i]))) {
        coins.add(new Integer(denominations[i]));
      } else { return true; }
    }

    return false;
  }

  private static HashMap<Integer, Integer> mapping(int[] denominations) {
    HashMap<Integer, Integer> coin_to_index = new HashMap<Integer, Integer>();

    for (int i = 0; i < denominations.length; i++) {
        if (!coin_to_index.containsKey(new Integer(denominations[i]))) {
          coin_to_index.put(new Integer(denominations[i]), new Integer(i));
        }
    }

    return coin_to_index;
  }
  public static void main(String[] args) {
    int[] denomination = { 2, 3, 7, 5, 51, 29, 11 };
    int target = 13579;

    try {
      Tuple result = DynamicChangeMaker.makeChangeWithDynamicProgramming(denomination, target);
      System.out.println(result.toString());
    } catch (IllegalArgumentException e) {
      System.out.println("BAD DATA");
    }
  }
}
