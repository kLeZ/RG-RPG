/*
 * $Id: PropertyDescriptorCacheTest.java 1188000 2011-10-23 23:10:24Z mcucchiara $
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

import org.apache.commons.ognl.internal.entry.PropertyDescriptorCacheEntryFactory;
import org.apache.commons.ognl.test.objects.Bean2;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * User: Maurizio Cucchiara
 * Date: 10/15/11
 * Time: 8:00 AM
 */
public class PropertyDescriptorCacheTest
{
    ConcurrentHashMapCache<Class<?>, Map<String,PropertyDescriptor>> cache =
        new ConcurrentHashMapCache<Class<?>, Map<String,PropertyDescriptor>>( new PropertyDescriptorCacheEntryFactory());

    @Test
    public void testGet( )
        throws Exception
    {
        Map<String, PropertyDescriptor> d = getPropertyDescriptor( Bean2.class  );
        assertTrue( d.containsKey( "id" ) );
        assertTrue( d.containsKey( "bean3" ) );
        assertTrue( d.containsKey( "carrier" ) );
    }

    private Map<String, PropertyDescriptor> getPropertyDescriptor( Class<?> entry )
        throws CacheException
    {
        Map<String, PropertyDescriptor> descriptor = cache.get( entry );
        assertNotNull( descriptor );
        return descriptor;
    }
}
