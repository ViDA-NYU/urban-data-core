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
package org.urban.data.core.profiling.datatype;

import org.urban.data.core.profiling.datatype.label.DataType;

/**
 * Abstract class for type checkers that try to determine the data type of
 * column values. Each checker iterates over the aggregated set of values in a
 * column and records the number of values that pass a type-dependent value
 * test.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public interface DataTypeChecker {

    /**
     * Type-dependent implementation of the type test. Returns true if the given
     * value is of the data type represented by this checker.
     * 
     * @param value
     * @return 
     */
    public boolean isMatch(String value);
    
    /**
     * Get label for the checked data type.
     * 
     * @return 
     */
    public DataType label();
}
