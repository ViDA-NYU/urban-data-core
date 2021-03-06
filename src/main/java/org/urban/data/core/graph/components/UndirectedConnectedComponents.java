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
package org.urban.data.core.graph.components;

import java.util.HashMap;
import java.util.HashSet;
import org.urban.data.core.set.HashIDSet;
import org.urban.data.core.set.HashObjectSet;
import org.urban.data.core.set.IDSet;
import org.urban.data.core.set.IdentifiableIDSet;
import org.urban.data.core.set.IdentifiableObjectSet;
import org.urban.data.core.set.ImmutableIDSet;
import org.urban.data.core.set.ImmutableIdentifiableIDSet;

/**
 * Default connected component generator. Use  for nodes that are unstructured
 * integers.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class UndirectedConnectedComponents implements ConnectedComponentGenerator {

    private final HashMap<Integer, HashSet<Integer>> _components;
    private final Integer[] _componentMap;
    private final IDSet _nodes;
    
    public UndirectedConnectedComponents(IDSet nodes) {
	
        _nodes = nodes;
        
        _components = new HashMap<>();
        
        int maxId = nodes.maxId();
        _componentMap = new Integer[maxId + 1];
        for (int nodeId : nodes) {
            _componentMap[nodeId] = nodeId;
        }
    }
    
    public int componentCount() {
        
        return _components.size();
    }

    public boolean contains(int nodeId) {
    
        //return _nodes.contains(nodeId);
        if (nodeId < _componentMap.length) {
            return (_componentMap[nodeId] != null);
        } else {
            return false;
        }
    }
    
    /**
     * Get the connected component that contains the node with the given
     * identifier.
     * 
     * @param nodeId
     * @return 
     */
    private int getComponentForNode(int nodeId) {
	
        //if (!_nodes.contains(nodeId)) {
        //    throw new RuntimeException("Unknown node identifier: " + nodeId);
        //}
        
        // If the nodeId is not contained in the component map then the node
        // is in the component that has the same identifier as the nodeId
        //if (_componentMap.containsKey(nodeId)) {
        //    return _componentMap.get(nodeId);
        //} else {
        //    return nodeId;
        //}
        return _componentMap[nodeId];
    }
    
    @Override
    public void edge(int sourceId, int targetId) {	
        
        int sourceCompId = this.getComponentForNode(sourceId);
        int targetCompId = this.getComponentForNode(targetId);

        if (sourceCompId != targetCompId) {
            // The respective components may not have been instantiated yet.
            boolean sourceExists = _components.containsKey(sourceCompId);
            boolean targetExists = _components.containsKey(targetCompId);
            if ((sourceExists) && (targetExists)) {
                // Merge the two components. We add the values from the smaller
                // component to the larger one
                HashSet<Integer> source = _components.get(sourceCompId);
                HashSet<Integer> target = _components.get(targetCompId);
                if (source.size() > target.size()) {
                    this.merge(source, sourceCompId, target, targetCompId);
                } else {
                    this.merge(target, targetCompId, source, sourceCompId);
                }
            } else if ((sourceExists) && (!targetExists)) {
                // Add targetId to source component
                _components.get(sourceCompId).add(targetId);
                _componentMap[targetId] = sourceCompId;
            } else if ((!sourceExists) && (targetExists)) {
                // Add sourceId to target component
                _components.get(targetCompId).add(sourceId);
                _componentMap[sourceId] = targetCompId;
            } else {
                // Create component for source and add target
                HashSet<Integer> comp = new HashSet();
                comp.add(sourceId);
                comp.add(targetId);
                _components.put(sourceCompId, comp);
                _componentMap[targetId] = sourceCompId;
            }
        }
    }
    
    @Override
    public synchronized IdentifiableObjectSet<IdentifiableIDSet> getComponents() {

        HashObjectSet result = new HashObjectSet();
	
        HashIDSet clusteredNodes = new HashIDSet();
        
        for (int compId : _components.keySet()) {
            HashSet<Integer> comp = _components.get(compId);
            ImmutableIDSet cluster = new ImmutableIDSet(comp);
            result.add(new ImmutableIdentifiableIDSet(compId, cluster));
            clusteredNodes.add(cluster);
	}
        
        // Add single node components for all unclustered nodes
        for (int nodeId : _nodes) {
            if (!clusteredNodes.contains(nodeId)) {
                result.add(
                        new ImmutableIdentifiableIDSet(
                                nodeId,
                                new ImmutableIDSet(nodeId)
                        )
                );
            }
        }
	
        return result;
    }

    @Override
    public boolean isDirected() {

        return false;
    }
    
    private void merge(
            HashSet<Integer> target,
            int targetCompId,
            HashSet<Integer> source,
            int sourceCompId
    ) {
        
        for (int nodeId : source) {
            target.add(nodeId);
            _componentMap[nodeId] = targetCompId;
        }
        
        _components.remove(sourceCompId);
    }

    public synchronized boolean nodesAreInSameComponent(int node1, int node2) {
        
        int comp1 = this.getComponentForNode(node1);
        int comp2 = this.getComponentForNode(node2);
        return (comp1 == comp2);
    }
}
