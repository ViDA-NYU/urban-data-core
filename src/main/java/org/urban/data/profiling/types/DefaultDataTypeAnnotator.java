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
package org.urban.data.profiling.types;

import org.urban.data.profiling.types.labels.DataTypeLabel;
import org.urban.data.profiling.types.labels.TextType;

/**
 * Default data type annotator.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class DefaultDataTypeAnnotator implements DataTypeAnnotator {

    private final DataTypeChecker[] _dateCheckers;
    private final DataTypeChecker[] _numericCheckers;
    
    /**
     * Initialize the arrays of type checkers the default annotator uses.
     * 
     */
    public DefaultDataTypeAnnotator() {
        
	_dateCheckers = new DataTypeChecker[]{
	    new SimpleDateFormatChecker("EEEEE, MM/dd/yyyy"),
	    new SimpleDateFormatChecker("yyyy-MM-dd'T'HH:mm:ss"),
	    new SimpleDateFormatChecker("yyyy-MM-dd'T'HH:mm:ss.SSS"),
	    new SimpleDateFormatChecker("yyyy-MM-dd", "-", new int[]{2, 1, 1}),
	    new SimpleDateFormatChecker("MM/dd/yyyy HH:mm:ss"),
	    new SimpleDateFormatChecker("MM/dd/yyyy", "/", new int[]{1, 1, 2}),
	    new SimpleDateFormatChecker("dd/MM/yyyy", "/", new int[]{1, 1, 2}),
	    new SimpleDateFormatChecker("MM/dd/yy", "/", new int[]{1, 1, 2}),
	    new SimpleDateFormatChecker("dd/MM/yy", "/", new int[]{1, 1, 2}),
	    new SimpleDateFormatChecker("dd-MMM-yy", "-", new int[]{2, 3, 2}),
	    new SimpleDateFormatChecker("MMM-dd"),
	    new SimpleDateFormatChecker("dd-MMM")
	};
	_numericCheckers = new DataTypeChecker[]{
	    new DefaultIntegerChecker(),
	    new AdvancedIntegerChecker(),
	    new LongChecker(),
	    new DefaultDecimalChecker(),
	    new AdvancedDecimalChecker()
	};
    }
    
    private DataTypeLabel matchValue(String value, DataTypeChecker[] typeCheckers) {
	
	for (DataTypeChecker tChecker : typeCheckers) {
	    if (tChecker.isMatch(value)) {
		return tChecker.getRawType();
	    }
	}
        return null;
    }
    
    @Override
    public DataTypeLabel getType(String value) {

        String val = value;
        if (val.startsWith("$")) {
            val = val.substring(1);
        } else if (val.endsWith("%")) {
            val = val.substring(0, val.length() - 1);
        }
        DataTypeLabel type = this.matchValue(val, _numericCheckers);
        if (type == null) {
            type = this.matchValue(value, _dateCheckers);
        }
        if (type != null) {
            return type;
        } else {
            return new TextType();
        }
    }
}
