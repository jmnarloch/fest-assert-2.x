/*
 * Created on Oct 20, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal.integers;

import static org.fest.assertions.error.ShouldBeGreaterOrEqual.shouldBeGreaterOrEqual;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;

import static org.mockito.Mockito.verify;

import org.junit.Test;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.internal.Integers;
import org.fest.assertions.internal.IntegersBaseTest;

/**
 * Tests for <code>{@link Integers#assertNotLessThan(AssertionInfo, Integer, int)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class Integers_assertGreaterThanOrEqualTo_Test extends IntegersBaseTest {

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    integers.assertNotLessThan(someInfo(), null, 8);
  }

  @Test
  public void should_pass_if_actual_is_greater_than_other() {
    integers.assertNotLessThan(someInfo(), 8, 6);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_other() {
    integers.assertNotLessThan(someInfo(), 6, 6);
  }

  @Test
  public void should_fail_if_actual_is_less_than_other() {
    AssertionInfo info = someInfo();
    try {
      integers.assertNotLessThan(info, 6, 8);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeGreaterOrEqual(6, 8));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    thrown.expectAssertionError(actualIsNull());
    integersWithAbsValueComparisonStrategy.assertNotLessThan(someInfo(), null, 8);
  }

  @Test
  public void should_pass_if_actual_is_greater_than_other_according_to_custom_comparison_strategy() {
    integersWithAbsValueComparisonStrategy.assertNotLessThan(someInfo(), -8, 6);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_other_according_to_custom_comparison_strategy() {
    integersWithAbsValueComparisonStrategy.assertNotLessThan(someInfo(), -6, 6);
  }

  @Test
  public void should_fail_if_actual_is_less_than_other_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    try {
      integersWithAbsValueComparisonStrategy.assertNotLessThan(info, 6, -8);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeGreaterOrEqual(6, -8, absValueComparisonStrategy));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
