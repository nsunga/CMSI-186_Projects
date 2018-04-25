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
    Map<Integer, Integer> coinToIndex = mapping(denominations);
    Tuple[][] table = new Tuple[denominations.length][target + 1];
    int rows = denominations.length;
    int columns = target + 1;
    int previousCost = 0;

    Arrays.sort(denominations);
    Map<Integer, Integer> indexToCoinSorted = mappingIndex(denominations);

    initColZero(table, denominations.length);

    for (int i = 0; i < rows; i++) {
      for (int j = 1; j < columns; j++) {
        if (denominations[i] > j) {
          table[i][j] = Tuple.IMPOSSIBLE;
          if (i != 0) {
            if (!table[i-1][j].isImpossible()) {
              table[i][j] = table[i-1][j];
            }
          }
        } else {
          table[i][j] = new Tuple(denominations.length);
          table[i][j].setElement(i, 1);

          previousCost = j - denominations[i];
          if (table[i][previousCost].isImpossible()) {
            table[i][j] = Tuple.IMPOSSIBLE;
          } else {
            table[i][j] = table[i][j].add(table[i][previousCost]);
          }

          if (i != 0) {
            if (!table[i-1][j].isImpossible()) {
              if (table[i-1][j].total() < table[i][j].total()) {
                table[i][j] = table[i-1][j];
              }
            } //else { table[i][j] = Tuple.IMPOSSIBLE; }
          }
        }
      }
    }

    Tuple result = table[rows-1][columns-1];
    if (result.isImpossible()) { return result; }
    result = tupleSorter(coinToIndex, indexToCoinSorted, result);

    return result;
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
    HashMap<Integer, Integer> coinToIndex = new HashMap<Integer, Integer>();

    for (int i = 0; i < denominations.length; i++) {
        if (!coinToIndex.containsKey(new Integer(denominations[i]))) {
          coinToIndex.put(new Integer(denominations[i]), new Integer(i));
        }
    }

    return coinToIndex;
  }

  private static HashMap<Integer, Integer> mappingIndex(int[] denominations) {
    HashMap<Integer, Integer> indexToCoin = new HashMap<Integer, Integer>();

    for (int i = 0; i < denominations.length; i++) {
      if (!indexToCoin.containsKey(new Integer(i))) {
        indexToCoin.put(new Integer(i), new Integer(denominations[i]));
      }
    }

    return indexToCoin;
  }

  private static Tuple tupleSorter(Map<Integer, Integer> coinToIndex, Map<Integer, Integer> indexToCoinSorted, Tuple result) {
    Tuple sortedResult = new Tuple(result.length());
    for (int i = 0; i < result.length(); i++) {
      if (result.getElement(i) != 0) {
        Integer correctIndex = coinToIndex.get(indexToCoinSorted.get(i));
        sortedResult.setElement(correctIndex, result.getElement(i));
      }
    }
    return sortedResult;
  }
  public static void main(String[] args) {
    int[] denomination = { 17, 23, 121, 47, 3 };
    int target = 13579;
    // answer <3,0,111,2,1>
    try {
      Tuple result = DynamicChangeMaker.makeChangeWithDynamicProgramming(denomination, target);
      System.out.println(result.toString());
    } catch (IllegalArgumentException e) {
      System.out.println("BAD DATA");
    }
  }
}
