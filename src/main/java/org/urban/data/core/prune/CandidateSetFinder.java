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
package org.urban.data.core.prune;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.urban.data.core.constraint.ThresholdConstraint;
import org.urban.data.core.object.IdentifiableDouble;
import org.urban.data.core.set.IDSet;
import org.urban.data.core.set.ImmutableIDSet;
import org.urban.data.core.util.StringHelper;

/**
 * For a given list of identifiable double, find the pruning index for an
 * implementation-specific pruning condition.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 * @param <T>
 */
public abstract class CandidateSetFinder <T extends IdentifiableDouble> {
   
    // Drop finder names
    public static final String MAX_DIFF = "MAX-DIFF";
    public static final String THRESHOLD = "THRESHOLD";
    
    // Drop finder specification syntax
    public static final String MAXDIFFFINDER =
            MAX_DIFF +
                ":<empty-threshold>" +
                ":<full-set-constraint>[true | false]" +
                ":<ignore-last-drop>[true | false]";
    public final static String THRESHOLDFINDER =
            THRESHOLD + ":<threshold-constraint>";
    
    /**
     * Print command line statement for drop finder arguments.
     * 
     * @param indent
     * @return 
     */
    public static String getCommand(String indent) {
     
        return indent + "<drop-finder> [\n" +
                indent + "  " + MAXDIFFFINDER + " |\n" +
                indent + "  " + THRESHOLDFINDER + "\n" +
                indent + "]";
    }

    /**
     * Get candidate set finder instance from specification string.
     * 
     * @param spec
     * @return 
     */
    public static CandidateSetFinder getFunction(String spec) {
	
        String[] tokens = spec.split(":");
        
        try {
            String name = tokens[0];
            if (name.equalsIgnoreCase(MAX_DIFF)) {
                if (tokens.length == 4) {
                    return new MaxDropFinder(
                            Double.parseDouble(tokens[1]),
                            Boolean.parseBoolean(tokens[2]),
                            Boolean.parseBoolean(tokens[3])
                    );
                }
            } else if (name.equalsIgnoreCase(THRESHOLD)) {
                if ((tokens.length == 3) || (tokens.length == 4)) {
                    return new ThresholdFinder(
                            ThresholdConstraint.getConstraint(
                                    StringHelper.joinStrings(tokens, 2, ":")
                            )
                    );
                }
            } else {
                throw new java.lang.IllegalArgumentException("Unknown candidate set finder: " + name);
            }
        } catch (java.lang.NumberFormatException ex) {
        }
        throw new java.lang.IllegalArgumentException("Invalid candidate set finder specification: " + spec);
    }

    /**
     * Return the pruning index.
     * 
     * @param elements
     * @return 
     */
    public int getPruneIndex(List<T> elements) {
        
        return this.getPruneIndex(elements, 0);
    }
    
    /**
     * Return pruning index after the given start position.
     * 
     * @param elements
     * @param start
     * @return 
     */
    public abstract int getPruneIndex(List<T> elements, int start);
    
    /**
     * Return identifier of elements that occur before the pruning index.
     * 
     * @param elements
     * @return 
     */
    public IDSet pruneElements(List<T> elements) {
        
        int pruneIndex = this.getPruneIndex(elements);
        if (pruneIndex > 0) {
            List<Integer> result = new ArrayList<>();
            for (int iIndex = 0; iIndex < pruneIndex; iIndex++) {
                result.add(elements.get(iIndex).id());
            }
            return new ImmutableIDSet(result);
        } else {
            return new ImmutableIDSet();
        }
    }
    
    /**
     * Validate a given drop finder specification.
     * 
     * Will exit program if invalid specification is given. Intended for use
     * at the start of of main routines to validate command line arguments.
     * 
     * @param spec
     * @param commandLine
     * @return 
     */
    public static String validateCommand(String spec, String commandLine) {
        
        String[] tokens = spec.split(":");
        
        try {
            String name = tokens[0];
            if (name.equalsIgnoreCase(MAX_DIFF)) {
                if (tokens.length == 4) {
                    try {
                        new BigDecimal(tokens[1]);
                    } catch (java.lang.NumberFormatException ex) {
                        System.out.println("Invalid candidate set finder specification: " + spec);
                        System.out.println(commandLine);
                        System.exit(-1);
                    }
                    return spec;
                }
            } else if (name.equalsIgnoreCase(THRESHOLD)) {
                if ((tokens.length == 3) || (tokens.length == 4)) {
                    ThresholdConstraint.validateCommand(
                            StringHelper.joinStrings(tokens, 2, ":"),
                            commandLine
                    );
                    return spec;
                }
            } else {
                System.out.println("Unknown drop finder name: " + name);
                System.out.println(commandLine);
                System.exit(-1);
            }
        } catch (java.lang.NumberFormatException ex) {
        }
        System.out.println("Invalid drop finder specification: " + spec);
        System.out.println(commandLine);
        System.exit(-1);
        return null;
    }
}
