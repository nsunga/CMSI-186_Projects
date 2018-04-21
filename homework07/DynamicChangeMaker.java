import java.util.Arrays;

public final class DynamicChangeMaker {
  private DynamicChangeMaker() { }

  public static Tuple makeChangeWithDynamicProgramming(int[] denomination, int target) {
    Arrays.sort(denomination);
    return new Tuple(0);
  }
}
