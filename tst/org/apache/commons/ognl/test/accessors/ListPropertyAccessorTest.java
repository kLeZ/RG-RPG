/*
 * $Id: ListPropertyAccessorTest.java 1104080 2011-05-17 09:22:09Z mcucchiara $
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
package org.apache.commons.ognl.test.accessors;

import org.apache.commons.ognl.ListPropertyAccessor;
import org.apache.commons.ognl.Ognl;
import org.apache.commons.ognl.OgnlContext;
import org.apache.commons.ognl.enhance.ExpressionCompiler;
import org.apache.commons.ognl.test.objects.ListSource;
import org.apache.commons.ognl.test.objects.ListSourceImpl;
import org.apache.commons.ognl.test.objects.Root;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Tests functionality of various built in object accessors.
 */
public class ListPropertyAccessorTest
{

    @Test
    public void test_Get_Source_String_Number_Index()
    {
        ListPropertyAccessor pa = new ListPropertyAccessor();

        Root root = new Root();

        OgnlContext context = (OgnlContext) Ognl.createDefaultContext( null );
        context.setRoot( root );
        context.setCurrentObject( root );
        context.setCurrentType( Integer.TYPE );

        assertEquals( ".get(0)", pa.getSourceAccessor( context, root.getList(), "0" ) );

        assertEquals( List.class, context.getCurrentAccessor() );
        assertEquals( Object.class, context.getCurrentType() );
        assertEquals( Integer.TYPE, context.getPreviousType() );
        assertEquals( null, context.getPreviousAccessor() );
    }

    @Test
    public void test_Get_Source_Object_Number_Index()
    {
        ListPropertyAccessor pa = new ListPropertyAccessor();

        Root root = new Root();

        OgnlContext context = (OgnlContext) Ognl.createDefaultContext( null );
        context.setRoot( root );
        context.setCurrentObject( root );
        context.setCurrentType( Integer.class );

        assertEquals( ".get(indexValue.intValue())", pa.getSourceAccessor( context, root.getList(), "indexValue" ) );

        assertEquals( List.class, context.getCurrentAccessor() );
        assertEquals( Object.class, context.getCurrentType() );
        assertEquals( Integer.class, context.getPreviousType() );
        assertEquals( null, context.getPreviousAccessor() );
    }

    @Test
    public void test_List_To_Object_Property_Accessor_Read()
        throws Exception
    {
        ListPropertyAccessor pa = new ListPropertyAccessor();

        ListSource list = new ListSourceImpl();

        OgnlContext context = (OgnlContext) Ognl.createDefaultContext( null );
        context.setRoot( list );
        context.setCurrentObject( list );

        assertEquals( ".getTotal()", pa.getSourceAccessor( context, list, "total" ) );

        assertNull( context.get( ExpressionCompiler.PRE_CAST ) );
        assertEquals( int.class, context.getCurrentType() );
        assertEquals( ListSource.class, context.getCurrentAccessor() );
    }
}
