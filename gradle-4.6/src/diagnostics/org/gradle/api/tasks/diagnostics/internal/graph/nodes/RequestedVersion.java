/*
 * Copyright 2012 the original author or authors.
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

package org.gradle.api.tasks.diagnostics.internal.graph.nodes;

import org.gradle.api.artifacts.component.ComponentIdentifier;
import org.gradle.api.artifacts.component.ComponentSelector;
import org.gradle.api.artifacts.result.ResolvedVariantResult;

import java.util.LinkedHashSet;
import java.util.Set;

public class RequestedVersion extends AbstractRenderableDependencyResult {
    private final ComponentSelector requested;
    private final ComponentIdentifier actual;
    private final boolean resolvable;
    private final String description;
    private final Set<RenderableDependency> children = new LinkedHashSet<RenderableDependency>();
    private final ResolvedVariantResult variantResult;

    public RequestedVersion(ComponentSelector requested, ComponentIdentifier actual, boolean resolvable, String description, ResolvedVariantResult resolvedVariant) {
        this.requested = requested;
        this.actual = actual;
        this.resolvable = resolvable;
        this.description = description;
        this.variantResult = resolvedVariant;
    }

    public void addChild(DependencyEdge child) {
        children.addAll(child.getChildren());
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ResolvedVariantResult getResolvedVariant() {
        return variantResult;
    }

    @Override
    protected ComponentIdentifier getActual() {
        return actual;
    }

    @Override
    public ComponentSelector getRequested() {
        return requested;
    }

    @Override
    public ResolutionState getResolutionState() {
        return resolvable ? ResolutionState.RESOLVED : ResolutionState.FAILED;
    }

    @Override
    public Set<RenderableDependency> getChildren() {
        return children;
    }
}
