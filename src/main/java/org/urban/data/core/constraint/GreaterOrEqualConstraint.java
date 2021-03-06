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
package org.urban.data.core.constraint;

import java.math.BigDecimal;
import org.urban.data.core.util.FormatedBigDecimal;

/**
 * The constraint is satisfied if the given value is greater or equal than a
 * defined threshold.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class GreaterOrEqualConstraint extends Threshold {

    private final BigDecimal _threshold;
    
    public GreaterOrEqualConstraint(BigDecimal threshold) {
        
        _threshold = threshold;
    }

    @Override
    public Threshold decreaseBy(BigDecimal value) {

        return new GreaterOrEqualConstraint(_threshold.subtract(value));
    }
    
    @Override
    public boolean isSatisfied(BigDecimal value) {

        return (value.compareTo(_threshold) >= 0);
    }
    
    @Override
    public String toPlainString() {
        
        return Threshold.GEQ + new FormatedBigDecimal(_threshold, 2);
    }
}
