package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.cli.Option;
import org.junit.runner.RunWith;

public class OptionTest {

    /* This method tests the method hasArgName() of the production class.
       The method returns true if the argument is empty (such as "") and this is not correct.
     */
  @Test
  public void test01()  throws Throwable  {
      Option option0 = new Option("", "");
      option0.setArgName("");
      boolean boolean0 = option0.hasArgName();
      assertFalse(boolean0); //OK
  }

  /* This method tests the method getId() of the production class.
     The method returns the second character instead of the first one.
   */
  @Test
  public void test02()  throws Throwable  {
      Option option0 = new Option("arg", "arg");
      int int0 = option0.getId();
      assertEquals("arg", option0.getArgName());
      assertFalse(option0.hasLongOpt());
      assertFalse(option0.hasArgs());
      assertEquals('a', int0); //OK
  }

  /* This method tests the method getKey() of the production class.
   */
  @Test
  public void test03()  throws Throwable  {
      Option option0 = new Option((String) null, (String) null);
      option0.getKey();
      assertEquals("arg", option0.getArgName());
      assertFalse(option0.hasArgs());
  }

    /* This method tests the method hasArgs() of the production class.
     */
  @Test
  public void test04()  throws Throwable  {
      Option option0 = new Option("NXb", true, "NXb");
      boolean boolean0 = option0.hasArgs();
      assertEquals("arg", option0.getArgName());
      assertFalse(option0.hasLongOpt());
      assertTrue(boolean0);
      assertEquals(1, option0.getArgs());
  }

  /* This method tests the method equals(Object) of the production class.
     The problem of the method is that it returns true when the longOpt are null and "".
   */
  @Test
  public void test05()  throws Throwable  {
      Option option0 = new Option("", "");
      Option option1 = new Option("", "", false, "");
      boolean boolean0 = option0.equals(option1);
      assertEquals("arg", option1.getArgName());
      assertFalse(option0.hasLongOpt());
      assertFalse(option1.hasArgs());
      assertFalse(boolean0);  //OK
  }

  /* This method tests the method equals(Object) of the production class.
     The problem is the same as test05.
  */
  @Test
  public void test06()  throws Throwable  {
      Option option0 = new Option((String) null, "", false, "");
      Option option1 = new Option((String) null, "");
      boolean boolean0 = option0.equals(option1);
      assertTrue(option0.hasLongOpt());
      assertFalse(boolean0);  //OK
      assertEquals("arg", option1.getArgName());
      assertFalse(option1.hasLongOpt());
      assertFalse(option1.hasArgs());
  }

  /* This method tests the method equals(Object) of the production class.
   */
  @Test
  public void test07()  throws Throwable  {
      Option option0 = new Option("", "Pz");
      Option option1 = new Option("Pz", "");
      boolean boolean0 = option0.equals(option1);
      assertFalse(boolean0);
      assertEquals((-1), option1.getArgs());
      assertFalse(option1.hasLongOpt());
      assertEquals("", option1.getDescription());
      assertEquals("Pz", option1.getOpt());
      assertEquals("arg", option1.getArgName());
  }

  /* This method tests the method hasArg() of the production class.
   */
  @Test
  public void test08()  throws Throwable  {
      Option option0 = new Option((String) null, (String) null);
      boolean boolean0 = option0.hasArg();
      assertFalse(boolean0);
      assertEquals((-1), option0.getArgs());
      assertEquals("arg", option0.getArgName());
  }

  /* This method tests the method clone() of the production class.
   */
  @Test
  public void test09()  throws Throwable  {
      Option option0 = new Option("", "", true, "");
      Option option1 = (Option)option0.clone();
      boolean boolean0 = option0.equals(option1);
      assertEquals("arg", option1.getArgName());
      assertTrue(boolean0);
      assertTrue(option1.hasArg());
      assertNotSame(option1, option0);
  }

  /* This method tests the method getOpt() of the production class.
     An equality check assertion was added.
   */
  @Test
  public void test10()  throws Throwable  {
      Option option0 = new Option("NO_ARGS_ALLOWED", "NO_ARGS_ALLOWED");
      String opt = option0.getOpt();
      assertEquals("arg", option0.getArgName());
      assertFalse(option0.hasLongOpt());
      assertFalse(option0.hasArgs());
      assertEquals("NO_ARGS_ALLOWED", opt);
  }
}
