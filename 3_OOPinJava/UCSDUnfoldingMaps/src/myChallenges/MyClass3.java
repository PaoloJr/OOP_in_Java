package myChallenges;
public class MyClass3
{
  private int a;
  public double b;
  
  public MyClass3(int first, double second)
  {
    this.a = first;
    this.b = second;
  }
  
  public static void incrementBoth(MyClass3 c1) {
    c1.a = c1.a + 1;
    c1.b = c1.b + 1.0;
  }
  
  // new method
  public static void incrementA(int first)
  {
    first = first + 1;
  }
  
  // new method
  public static void incrementB(double second)
  {
    second = second + 1.0;
  }
  
  public static void main(String[] args)
  {
    MyClass3 c1 = new MyClass3(10, 20.5);
    MyClass3 c2 = new MyClass3(10, 31.5);
    // different code below
    incrementA(c2.a);
    incrementB(c2.b);
    System.out.println(c2.a + ", "+ c2.b);
  }
}