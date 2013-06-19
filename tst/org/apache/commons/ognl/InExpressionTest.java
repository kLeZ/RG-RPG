/*
 * $Id: InExpressionTest.java 1104080 2011-05-17 09:22:09Z mcucchiara $
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.ognl;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

/**
 * Test for OGNL-118.
 */
public class InExpressionTest
{

    @Test
    public void test_String_In()
        throws Exception
    {
        OgnlContext context = (OgnlContext) Ognl.createDefaultContext( null );
        Object node = Ognl.parseExpression( "#name in {\"Greenland\", \"Austin\", \"Africa\", \"Rome\"}" );
        Object root = null;

        context.put( "name", "Austin" );
        assertEquals( Boolean.TRUE, Ognl.getValue( node, context, root ) );
    }
}
