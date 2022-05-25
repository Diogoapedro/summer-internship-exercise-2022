package com.premiumminds.internship.screenlocking;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.premiumminds.internship.screenlocking.ScreenLockinPattern;

/**
 * Created by aamado on 05-05-2022.
 */
@RunWith(JUnit4.class)
public class ScreenLockinPatternTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public ScreenLockinPatternTest() {
  };


  @Test
  public void ScreenLockinPatternTestFirst3Length2Test()  throws InterruptedException, ExecutionException, TimeoutException {
    Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(3, 2);
    Integer result = count.get(10, TimeUnit.SECONDS);
    assertEquals(result.intValue(), 5);
  }
  
  @Test
  public void ScreenLockinPatternTestFirst3Length3Test()  throws InterruptedException, ExecutionException, TimeoutException {
	    Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(3, 3);
	    Integer result = count.get(10, TimeUnit.SECONDS);
	    assertEquals(result.intValue(), 31);
  }
  
  @Test
  public void ScreenLockinPatternTestLengthOutOfBoundUp() throws InterruptedException, ExecutionException, TimeoutException {
      Future<Integer> count = new ScreenLockinPattern().countPatternsFrom(5, 10);
      Integer result = count.get(10, TimeUnit.SECONDS);
      assertEquals(0, result.intValue());
  }
  
  @Test
  public void ScreenLockinPatternTestLengthOutOfBoundDown() throws InterruptedException, ExecutionException, TimeoutException {
	    Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(1, 0);
	    Integer result = count.get(1, TimeUnit.SECONDS);
	    assertEquals(0, result.intValue());
  }
  
  @Test
  public void ScreenLockinPatternTestPositionOutOfBoundUp() throws InterruptedException, ExecutionException, TimeoutException {
      Future<Integer> count = new ScreenLockinPattern().countPatternsFrom(10, 5);
      Integer result = count.get(10, TimeUnit.SECONDS);
      assertEquals(0, result.intValue());
  }
  
  @Test
  public void ScreenLockinPatternTestPositionOutOfBoundDown() throws InterruptedException, ExecutionException, TimeoutException {
    Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(0, 2);
    Integer result = count.get(1, TimeUnit.SECONDS);
    assertEquals(0, result.intValue());
  }
  
}