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
package org.urban.data.core.set;

import java.util.List;
import org.urban.data.core.object.filter.ObjectFilter;

/**
 * List of unique identifier.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public interface IDSet extends ObjectSet<Integer>, ObjectFilter<Integer> {
    
    public ImmutableIDSet difference(IDSet list);
    public int first();
    public ImmutableIDSet intersect(IDSet list);
    public boolean isTrueSubsetOf(IDSet list);
    public int maxId();
    public int minId();
    public int[] toArray();
    public String toIntString();
    public List<Integer> toSortedList();
    public ImmutableIDSet union(IDSet list);
    public ImmutableIDSet union(int id);
}
