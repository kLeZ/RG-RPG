/*
 * $Id: Messages.java 1103095 2011-05-14 13:18:29Z simonetripodi $
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
package org.apache.commons.ognl.test.objects;

import java.util.Map;

/**
 *
 */
public class Messages
{

    Map _source;

    public Messages( Map source )
    {
        _source = source;
    }

    public String getMessage( String key )
    {
        return (String) _source.get( key );
    }

    public String format( String key, Object[] parms )
    {
        return "foo";
    }

    public String format( String key, Object param1, Object param2, Object param3 )
    {
        return "blah";
    }

    public String format( String key, Object param1 )
    {
        return "first";
    }

    public String format( String key, Object param1, Object param2 )
    {
        return "haha";
    }
}
