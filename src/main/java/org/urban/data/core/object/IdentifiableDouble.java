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
package org.urban.data.core.object;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.urban.data.core.util.FormatedBigDecimal;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class IdentifiableDouble extends IdentifiableObjectImpl implements Comparable<IdentifiableDouble> {
    
    private double _value;
    
    public IdentifiableDouble(int id, double value) {
        
        super(id);
        
        _value = value;
    }
    
    public IdentifiableDouble(int id, BigDecimal value) {
        
        this(id, value.doubleValue());
    }
    
    public void add(double val) {
        
        _value += val;
    }
    
    @Override
    public int compareTo(IdentifiableDouble obj) {

        return Integer.compare(this.id(), obj.id());
    }
    
    public BigDecimal toBigDecimal() {
    
        return new BigDecimal(_value);
    }
    
    public FormatedBigDecimal toFormatedDecimal() {
    
        return new FormatedBigDecimal(_value);
    }
    
    public String toPlainString() {
    
        return new BigDecimal(_value).setScale(8, RoundingMode.HALF_DOWN).toPlainString();
    }
    
    public double value() {
        
        return _value;
    }
}
