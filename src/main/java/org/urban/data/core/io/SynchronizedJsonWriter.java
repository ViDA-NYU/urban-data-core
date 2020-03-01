/*
 * Copyright 2019 New York University.
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.OutputStreamWriter;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class SynchronizedJsonWriter implements AutoCloseable {
    
    private final JsonWriter _writer;
    
    public SynchronizedJsonWriter(File file) throws java.io.IOException {
        
        _writer = new JsonWriter(new OutputStreamWriter(FileSystem.openOutputFile(file), "UTF-8"));
        _writer.beginArray();
    }
    
    public synchronized void write(JsonObject doc) {
        
        new Gson().toJson(doc, _writer);
    }

    @Override
    public void close() throws java.io.IOException {

        _writer.endArray();
        _writer.close();
    }
}