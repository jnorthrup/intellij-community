package com.siyeh.igtest.abstraction.overly_strong_type_cast;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;





interface TestInter{}

public class OverlyStrongTypeCast
{
  void iterate(Object o) {
    for (Object object : (<warning descr="Cast to 'ArrayList' can be weakened to 'Iterable'">ArrayList</warning>) o) {}
    for (String s : (<warning descr="Cast to 'ArrayList<String>' can be weakened to 'Iterable<String>'">ArrayList<String></warning>) o) {}
  }

    void optional(Object foo) {
        if (foo instanceof SubClass2) {
            ((SubClass2)foo).doSmth();
        }
        ((<warning descr="Cast to 'SubClass2' can be weakened to 'SuperClass'">SubClass2</warning>)foo).doSmth();
    }

    public static void main(String[] args)
    {
        List bar = new ArrayList();
        AbstractList foo = (<warning descr="Cast to 'ArrayList' can be weakened to 'AbstractList'">ArrayList</warning>) bar;
        List foo2 = (ArrayList) bar;
        double x = (double)3.0f;
    }                  

    <T> void test(T foo){}

    void test2()
    {
        Object o = null;
        test((TestInter)o);
    }

    public static Object[] array(List<?> l, Class type){
        return l.toArray((Object[]) Array.newInstance(type, l.size()));
    }

    public static void test3()
    {
        final SuperClass testSub = new SubClass();
        ((SubClass)testSub).doSmth();
        ((SubClass2)testSub).doSmth();
    }
}
class SuperClass{
  protected void doSmth(){
  }
}
class SubClass extends SuperClass{
  public void doSmth(){
  }
}
class SubClass2 extends SubClass{
  public void doSmth(){
    super.doSmth();
  }
}

class SAM {
  {
    Object runnable = (Runnable) () -> {};
  }
}