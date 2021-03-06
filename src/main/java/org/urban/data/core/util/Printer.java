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
package org.urban.data.core.util;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import org.urban.data.core.io.FileSystem;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class Printer extends PrintWriter {
    
    public Printer(File file) throws java.io.IOException {

        super(new OutputStreamWriter(FileSystem.openOutputFile(file)));
    }

    @Override
    public void flush() {

        super.flush();
        System.out.flush();
    }

    @Override
    public void println(String line) {

        super.println(line);
        System.out.println(line);
    }

    @Override
    public void println() {

        super.println();
        System.out.println();
    }
}
