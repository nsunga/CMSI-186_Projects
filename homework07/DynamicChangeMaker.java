import java.util.Arrays;

public final class DynamicChangeMaker {
  private DynamicChangeMaker() { }

  public static Tuple makeChangeWithDynamicProgramming(int[] denomination, int target) {
    Tuple[][] table = new Tuple[denomination.length][target + 1];

    initColZero(table, denomination.length);

    Tuple combinations = new Tuple(denomination.length);
    Arrays.sort(denomination);
    return new Tuple(0);
  }

  private static void initColZero(Tuple[][] table, int coins) {
    for (int i = 0; i < coins; i++) {
      table[i][0] = new Tuple(coins);
    }
  }
}
