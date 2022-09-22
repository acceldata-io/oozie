/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.oozie.util.db;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOozieDmlStatementPredicate {

    private final FailingConnectionWrapper.OozieDmlStatementPredicate statementPredicate =
            new FailingConnectionWrapper.OozieDmlStatementPredicate();

    @Test
    public void testDmlNotOozieTableDoesNotApply() {
        assertFalse("DML statement but not of an Oozie table",
                statementPredicate.test("SELECT * FROM wellhello"));
    }

    @Test
    public void testNotDmlOozieTableDoesNotApply() {
        assertFalse("not a DML statement but of an Oozie table",
                statementPredicate.test("CREATE TABLE WF_JOBS"));
    }

    @Test
    public void testNotDmlNotOozieTableDoesNotApply() {
        assertFalse("not a DML statement and not of an Oozie table",
                statementPredicate.test("CREATE TABLE wellhello"));
    }

    @Test
    public void testDmlAndOozieTableAppliesIgnoreCase() {
        assertTrue("a DML statement and of an Oozie table",
                statementPredicate.test("SELECT * FROM wf_jobs"));

        assertTrue("a DML statement and of an Oozie table",
                statementPredicate.test("insert into WF_JOBS"));

        assertTrue("a DML statement and of an Oozie table",
                statementPredicate.test("update wf_jobs"));

        assertTrue("a DML statement and of an Oozie table",
                statementPredicate.test("DELETE FROM WF_JOBS"));
    }
}