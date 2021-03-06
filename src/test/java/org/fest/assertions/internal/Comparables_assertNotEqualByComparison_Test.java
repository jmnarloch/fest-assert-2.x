/*
 * Created on Sep 14, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldNotBeEqual.shouldNotBeEqual;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.test.Person;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Comparables#assertNotEqual(Description, Comparable, Comparable)}.
 *
 * @author Alex Ruiz
 */
public class Comparables_assertNotEqualByComparison_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private Failures failures;
  private Comparables comparables;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    comparables = new Comparables();
    comparables.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    comparables.assertNotEqual(mock(Description.class), null, 8);
  }

  @Test
  public void should_pass_if_objects_are_not_equal() {
    Person a = spy(new Person("Han"));
    Person o = new Person("Yoda");
    comparables.assertNotEqual(mock(Description.class), a, o);
    verify(a).compareTo(o);
  }

  @Test
  public void should_fail_if_objects_are_equal() {
    Description description = new TestDescription("Testing");
    try {
      comparables.assertNotEqual(description, "Yoda", "Yoda");
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotBeEqual("Yoda", "Yoda"));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
