/*
 * Copyright (c) 2007, tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao;

import org.junit.Test;
import cloud.tamacat.dao.sql.SQLParser;
import junit.framework.TestCase;

public class ConditionTest extends TestCase {

    @Test
    public void testGetReplaceHolder() {
        assertEquals(SQLParser.VALUE1, Condition.EQUAL.getReplaceHolder());
        assertEquals("#{value1}", Condition.EQUAL.getReplaceHolder());
    }

    @Test
    public void testGetCondition() {
        assertEquals(" like ", Condition.LIKE_HEAD.getCondition());
        assertEquals("<>", Condition.NOT_EQUAL.getCondition());
        assertEquals("=", Condition.EQUAL.getCondition());
    }
}
