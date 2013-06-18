package org.apache.commons.ognl.enhance;

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

import org.apache.commons.ognl.OgnlContext;

public class ContextClassLoader
    extends ClassLoader
{
    private final OgnlContext context;

    /*
     * =================================================================== Constructors
     * ===================================================================
     */
    public ContextClassLoader( ClassLoader parentClassLoader, OgnlContext context )
    {
        super( parentClassLoader );
        this.context = context;
    }

    /*
     * =================================================================== Overridden methods
     * ===================================================================
     */
    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<?> findClass( String name )
        throws ClassNotFoundException
    {
        if ( ( context != null ) && ( context.getClassResolver() != null ) )
        {
            return context.getClassResolver().classForName( name, context );
        }
        return super.findClass( name );
    }

}
