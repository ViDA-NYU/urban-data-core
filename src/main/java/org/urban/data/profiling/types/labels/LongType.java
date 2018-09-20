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
package org.urban.data.profiling.types.labels;

/**
 * Long data type class label.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class LongType extends DataTypeLabel {
    
    public static final int IDENTIFIER = 3;

    public LongType() {
        
        super(IDENTIFIER, "Long");
    }

    @Override
    public boolean isDate() {

        return false;
   }

    @Override
    public boolean isNumeric() {

        return true;
    }

    @Override
    public boolean isText() {

        return false;
    }
}
