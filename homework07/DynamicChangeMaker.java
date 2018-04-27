import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public final class DynamicChangeMaker {
  private DynamicChangeMaker() { }

  // main ting
  public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int target) {
    if (!validDenominations(denominations)) { return Tuple.IMPOSSIBLE; }
    if (!validTarget(target)) { return Tuple.IMPOSSIBLE; }

    int rows = denominations.length;
    int columns = target + 1;
    Tuple[][] table = new Tuple[rows][columns];
    int previousCost = 0;

    initColZero(table, denominations);

    for (int i = 0; i < rows; i++) {
      for (int j = 1; j < columns; j++) {

        if (denominations[i] > j) {
          table[i][j] = Tuple.IMPOSSIBLE;
        } else {
          table[i][j] = new Tuple(denominations.length);
          table[i][j].setElement(i, 1);
          previousCost = j - denominations[i];
          table[i][j] = table[i][previousCost].isImpossible() ?
            Tuple.IMPOSSIBLE :
            table[i][j].add(table[i][previousCost]);
        }

        if (i != 0) { table[i][j] = optimize(table[i][j], table[i-1][j]); }
      }
    }

    Tuple result = table[rows-1][columns-1];
    return result;
  }

  private static Tuple optimize(Tuple current, Tuple above) {
    if (!above.isImpossible()) {
      if (current.isImpossible()) { return above; }
      if (above.total() < current.total()) { return above; }
    }

    return current;
  }

  // initialize col zero to zero tuple
  private static void initColZero(Tuple[][] table, int[] coins) {
    for (int i = 0; i < coins.length; i++) {
      table[i][0] = new Tuple(coins.length);
    }
  }

  // validate denominations
  private static boolean validDenominations(int[] denominations) {
    boolean existingNegatives = checkForNegatives(denominations);
    boolean existingDuplicates = checkForDuplicates(denominations);
    return !(existingDuplicates || existingNegatives);
  }

  // validate target
  private static boolean validTarget(int target) {
    return target > 0;
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

  public static void main(String[] args) {
    int[] denomination = { 17, 23, 121, 47, 3 };
    int target = 13579;
    try {
      Tuple result = DynamicChangeMaker.makeChangeWithDynamicProgramming(denomination, target);
      System.out.println(result.toString());
    } catch (IllegalArgumentException e) {
      System.out.println("BAD DATA");
    }
  }
}
