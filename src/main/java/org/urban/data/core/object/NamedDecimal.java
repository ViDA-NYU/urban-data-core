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
package org.urban.data.core.object;

import java.math.BigDecimal;

/**
 * Decimal value with a unique name.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class NamedDecimal implements Comparable<NamedDecimal>, NamedObject {

    private final String _name;
    private final BigDecimal _value;
    
    public NamedDecimal(String name, BigDecimal value) {
        
        _name = name;
        _value = value;
    }
    
    @Override
    public String name() {

        return _name;
    }

    @Override
    public int compareTo(NamedDecimal o) {

        return _value.compareTo(o.value());
    }
    
    public BigDecimal value() {
        
        return _value;
    }
}