package org.apache.commons.ognl.internal.entry;

/*
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

/*
 * $Id: PermissionCacheEntry.java 1194957 2011-10-29 18:04:20Z mcucchiara $
 */

import java.lang.reflect.Method;

public class PermissionCacheEntry
    implements CacheEntry
{
    public Method method;

    public PermissionCacheEntry( Method method )
    {
        this.method = method;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( !( o instanceof PermissionCacheEntry ) )
        {
            return false;
        }

        PermissionCacheEntry that = (PermissionCacheEntry) o;

        return !( method != null ? !method.equals( that.method ) : that.method != null );

    }

    @Override
    public int hashCode()
    {
        return method != null ? method.hashCode() : 0;
    }
}
