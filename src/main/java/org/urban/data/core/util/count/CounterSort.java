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
package org.urban.data.core.util.count;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class CounterSort<T> {
    
    public List<CounterPair<T>> sort(HashMap<T, Counter> mapping, boolean reverse) {
    
        ArrayList<CounterPair<T>> elements = new ArrayList<>();
        
        for (T key : mapping.keySet()) {
            elements.add(new CounterPair<>(key, mapping.get(key)));
        }
        
        Collections.sort(elements);
        if (reverse) {
            Collections.reverse(elements);
        }
        
        return elements;
    }
    
    public List<CounterPair<T>> sort(HashMap<T, Counter> mapping) {
        
        return this.sort(mapping, false);
    }
}
