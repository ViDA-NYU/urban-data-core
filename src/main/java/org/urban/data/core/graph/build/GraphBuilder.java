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

/**
 * The graph builder add edges to a graph.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public interface GraphBuilder {
    
    /**
     * Add an (directed) edge from the source node to the target node.
     * 
     * @param sourceId
     * @param targetId 
     */
    public void edge(int sourceId, int targetId);
    
    /**
     * Return true if the component generator uses directed edges.
     * 
     * @return 
     */
    public boolean isDirected();
}
