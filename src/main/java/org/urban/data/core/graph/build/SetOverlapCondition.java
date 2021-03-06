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
package org.urban.data.core.graph.build;

import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.IdentifiableObjectSet;

/**
 * Evaluate whether a pair of nodes should be merged based on their overlap.
 * 
 * Nodes that are subset of each other cannot be merged.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public class SetOverlapCondition<T extends IdentifiableIDSet> implements GraphBuilderEdgeCondition {

    private final IdentifiableObjectSet<T> _nodes;
    
    public SetOverlapCondition(IdentifiableObjectSet<T> nodes) {
        
        _nodes = nodes;
    }
    
    @Override
    public boolean hasEdge(int sourceId, int targetId) {

        T nodeI = _nodes.get(sourceId);
        T nodeJ = _nodes.get(targetId);
        
        return nodeI.overlaps(nodeJ);
    }

    @Override
    public boolean isSymmetric() {

        return true;
    }
}
