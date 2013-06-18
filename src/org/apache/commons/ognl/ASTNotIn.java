package org.apache.commons.ognl;

/*
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

import org.apache.commons.ognl.enhance.UnsupportedCompilationException;

/**
 * $Id: ASTNotIn.java 1454294 2013-03-08 09:02:08Z grobmeier $
 */
class ASTNotIn
    extends SimpleNode
    implements NodeType
{
    public ASTNotIn( int id )
    {
        super( id );
    }

    public ASTNotIn( OgnlParser p, int id )
    {
        super( p, id );
    }

    protected Object getValueBody( OgnlContext context, Object source )
        throws OgnlException
    {
        Object v1 = children[0].getValue( context, source );
        Object v2 = children[1].getValue( context, source );
        return OgnlOps.in( v1, v2 ) ? Boolean.FALSE : Boolean.TRUE;
    }

    public Class getGetterClass()
    {
        return Boolean.TYPE;
    }

    public Class getSetterClass()
    {
        return null;
    }

    public String toGetSourceString( OgnlContext context, Object target )
    {
        try
        {
            String result = "(! org.apache.commons.ognl.OgnlOps.in( ($w) ";

            result +=
                OgnlRuntime.getChildSource( context, target, children[0] ) + ", ($w) "
                    + OgnlRuntime.getChildSource( context, target, children[1] );

            result += ") )";

            context.setCurrentType( Boolean.TYPE );

            return result;
        }
        catch ( NullPointerException e )
        {

            // expected to happen in some instances
            e.printStackTrace();

            throw new UnsupportedCompilationException( "evaluation resulted in null expression." );
        }
        catch ( Throwable t )
        {
            throw OgnlOps.castToRuntime( t );
        }
    }
    
    public <R, P> R accept( NodeVisitor<? extends R, ? super P> visitor, P data )
        throws OgnlException
    {
        return visitor.visit( this, data );
    }
}
