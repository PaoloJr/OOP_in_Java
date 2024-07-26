package myChallenges;
public class MyClass2
{
  private int a;
  public double b;
  
  public MyClass2(int first, double second)
  {
    this.a = first;
    this.b = second;
  }

  // new method
  public static void incrementBoth(MyClass2 c1) {
    c1.a = c1.a + 1;
    c1.b = c1.b + 1.0;
  }

  public static void main(String[] args)
  {
    MyClass2 c1 = new MyClass2(10, 20.5);
    MyClass2 c2 = new MyClass2(10, 31.5);
    // different code below
    incrementBoth(c2);
    System.out.println(c1.a + ", "+ c2.a);
  }
}