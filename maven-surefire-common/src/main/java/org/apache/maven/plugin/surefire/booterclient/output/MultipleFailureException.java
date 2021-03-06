package org.apache.maven.plugin.surefire.booterclient.output;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

final class MultipleFailureException
        extends IOException
{
    private final Queue<Throwable> exceptions = new ConcurrentLinkedQueue<Throwable>();

    void addException( Throwable exception )
    {
        exceptions.add( exception );
    }

    boolean hasNestedExceptions()
    {
        return !exceptions.isEmpty();
    }

    @Override
    public String getLocalizedMessage()
    {
        StringBuilder messages = new StringBuilder();
        for ( Throwable exception = exceptions.peek(); exception != null; exception = exceptions.peek() )
        {
            if ( messages.length() != 0 )
            {
                messages.append( '\n' );
            }
            String message = exception.getLocalizedMessage();
            messages.append( message == null ? exception.toString() : message );
        }
        return messages.toString();
    }

    @Override
    public String getMessage()
    {
        StringBuilder messages = new StringBuilder();
        for ( Throwable exception = exceptions.peek(); exception != null; exception = exceptions.peek() )
        {
            if ( messages.length() != 0 )
            {
                messages.append( '\n' );
            }
            String message = exception.getMessage();
            messages.append( message == null ? exception.toString() : message );
        }
        return messages.toString();
    }
}
