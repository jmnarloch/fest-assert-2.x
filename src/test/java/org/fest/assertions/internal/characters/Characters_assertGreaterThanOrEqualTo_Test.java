/*
 * Created on Oct 24, 2010
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
package org.fest.assertions.internal.characters;

import static org.fest.assertions.error.ShouldBeGreaterOrEqual.shouldBeGreaterOrEqual;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;

import static org.mockito.Mockito.verify;

import org.junit.Test;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.internal.Characters;
import org.fest.assertions.internal.CharactersBaseTest;

/**
 * Tests for <code>{@link Characters#assertNotLessThan(AssertionInfo, Character, char)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class Characters_assertGreaterThanOrEqualTo_Test extends CharactersBaseTest {

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    characters.assertNotLessThan(someInfo(), null, 'a');
  }

  @Test
  public void should_pass_if_actual_is_greater_than_other() {
    characters.assertNotLessThan(someInfo(), 'b', 'a');
  }

  @Test
  public void should_pass_if_actual_is_equal_to_other() {
    characters.assertNotLessThan(someInfo(), 'b', 'b');
  }

  @Test
  public void should_fail_if_actual_is_less_than_other() {
    AssertionInfo info = someInfo();
    try {
      characters.assertNotLessThan(info, 'a', 'b');
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeGreaterOrEqual('a', 'b'));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_null_according_to_custom_comparison_strategy() {
    thrown.expectAssertionError(actualIsNull());
    charactersWithCaseInsensitiveComparisonStrategy.assertNotLessThan(someInfo(), null, 'a');
  }

  @Test
  public void should_pass_if_actual_is_greater_than_other_according_to_custom_comparison_strategy() {
    charactersWithCaseInsensitiveComparisonStrategy.assertNotLessThan(someInfo(), 'B', 'a');
  }

  @Test
  public void should_pass_if_actual_is_equal_to_other_according_to_custom_comparison_strategy() {
    charactersWithCaseInsensitiveComparisonStrategy.assertNotLessThan(someInfo(), 'B', 'b');
  }

  @Test
  public void should_fail_if_actual_is_less_than_other_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    try {
      charactersWithCaseInsensitiveComparisonStrategy.assertNotLessThan(info, 'a', 'B');
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeGreaterOrEqual('a', 'B', caseInsensitiveComparisonStrategy));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
