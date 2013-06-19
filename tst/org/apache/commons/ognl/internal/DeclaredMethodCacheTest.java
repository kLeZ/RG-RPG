/*
 * $Id: DeclaredMethodCacheTest.java 1188000 2011-10-23 23:10:24Z mcucchiara $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.commons.ognl.internal;

import org.apache.commons.ognl.internal.entry.DeclaredMethodCacheEntry;
import org.apache.commons.ognl.internal.entry.DeclaredMethodCacheEntryFactory;
import org.apache.commons.ognl.test.objects.Root;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * User: Maurizio Cucchiara
 * Date: 10/15/11
 * Time: 9:18 AM
 */
public class DeclaredMethodCacheTest
{
    Cache<DeclaredMethodCacheEntry, Map<String, List<Method>>> cache =
        new ConcurrentHashMapCache<DeclaredMethodCacheEntry, Map<String, List<Method>>>(new DeclaredMethodCacheEntryFactory( ) );

    @Test
    public void testStaticGet( )
        throws Exception
    {
        Map<String, List<Method>> methods = cache.get( new DeclaredMethodCacheEntry( Root.class, DeclaredMethodCacheEntry.MethodType.STATIC) );
        assertNotNull( methods );
        assertTrue( methods.containsKey( "getStaticInt" ) );
    }

    @Test
    public void testNonStaticGet( )
        throws Exception
    {
        Map<String, List<Method>> methods = cache.get( new DeclaredMethodCacheEntry( Root.class, DeclaredMethodCacheEntry.MethodType.NON_STATIC ) );
        assertNotNull( methods );
        assertTrue( methods.containsKey( "format" ) );
    }

}
