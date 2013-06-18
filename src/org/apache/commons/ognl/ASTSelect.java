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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * $Id: ASTSelect.java 1454294 2013-03-08 09:02:08Z grobmeier $
 */
class ASTSelect
    extends SimpleNode
{

    public ASTSelect( int id )
    {
        super( id );
    }

    public ASTSelect( OgnlParser p, int id )
    {
        super( p, id );
    }

    protected Object getValueBody( OgnlContext context, Object source )
        throws OgnlException
    {
        Node expr = children[0];
        List answer = new ArrayList();

        ElementsAccessor elementsAccessor = OgnlRuntime.getElementsAccessor( OgnlRuntime.getTargetClass( source ) );

        for ( Enumeration e = elementsAccessor.getElements( source ); e.hasMoreElements(); )
        {
            Object next = e.nextElement();

            if ( OgnlOps.booleanValue( expr.getValue( context, next ) ) )
            {
                answer.add( next );
            }
        }

        return answer;
    }

    public String toGetSourceString( OgnlContext context, Object target )
    {
        throw new UnsupportedCompilationException( "Eval expressions not supported as native java yet." );
    }

    public String toSetSourceString( OgnlContext context, Object target )
    {
        throw new UnsupportedCompilationException( "Eval expressions not supported as native java yet." );
    }
    
    public <R, P> R accept( NodeVisitor<? extends R, ? super P> visitor, P data )
        throws OgnlException
    {
        return visitor.visit( this, data );
    }
}
