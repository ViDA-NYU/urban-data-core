/*
 * Copyright 2018 New York University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.urban.data.core.io;

import java.io.PrintWriter;

/**
 * Implements a thread safe writer that allows to output lines.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class SynchronizedWriter implements AutoCloseable {
    
    private final PrintWriter _out;
    
    public SynchronizedWriter(PrintWriter out) {
	
	_out = out;
    }
    
    @Override
    public void close() {
        
        _out.close();
    }

    public synchronized void flush() {
        
        _out.flush();
    }

    public synchronized void write(String line) {
	
	_out.println(line);
    }
}
