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

/**
 * Bin in a histogram. Contains the start and end bounds of the bin and the
 * distribution probability.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class Bin {

    private final long _count;
    private final double _end;
    private final double _probability;
    private final double _start;
    
    public Bin(double start, double end, double probability, long count) {
        
        _start = start;
        _end = end;
        _probability = probability;
        _count = count;
    }
    
    public long count() {
        
        return _count;
    }
    
    public double end() {
        
        return _end;
    }
    
    public double probability() {
        
        return _probability;
    }
    
    public double start() {
        
        return _start;
    }
}
