// @FunctionalInterface
// => allow to verify that interface SAM (Single abstract method)
@FunctionalInterface
public interface NumericTest {
    boolean computeTest(int a) throws RuntimeException;
}
