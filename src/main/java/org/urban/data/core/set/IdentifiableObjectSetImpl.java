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

import org.urban.data.core.object.IdentifiableObject;

/**
 *
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public abstract class IdentifiableObjectSetImpl <T extends IdentifiableObject> implements IdentifiableObjectSet<T> {
    
    @Override
    public int getMaxId() {
        
        if (this.isEmpty()) {
            throw new RuntimeException("Cannot get maximum identifier of empty set");
        }
        
        int maxId = Integer.MIN_VALUE;
        for (T obj : this) {
            if(obj.id() > maxId) {
                maxId = obj.id();
            }
        }
        return maxId;
    }

    @Override
    public int getSize() {

        return this.length();
    }
    
    @Override
    public int overlap(IdentifiableObjectSet<T> list) {

        IdentifiableObjectSet<T> setInner,setOuter;
        if (this.length() < list.length()) {
            setOuter = this;
            setInner = list;
        } else {
            setOuter = list;
            setInner = this;
        }
        
        int count = 0;
        for (IdentifiableObject object : setOuter) {
            if (setInner.contains(object.id())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int overlap(IDSet list) {

        int count = 0;
        for (IdentifiableObject object : this) {
            if (list.contains(object.id())) {
                count++;
            }
        }
        return count;
    }
}
