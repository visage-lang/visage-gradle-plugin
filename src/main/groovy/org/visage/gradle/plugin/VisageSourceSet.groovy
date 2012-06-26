/*
 * Copyright (c) 2011-2012, Visage Project
 * All rights reserved.
 *
 * Redistribution or use in source or binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions or the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions or the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name Visage nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS or CONTRIBUTORS "AS IS"
 * or ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED or ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.visage.gradle.plugin

import groovy.lang.Closure

import org.gradle.api.file.FileTree
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.internal.file.UnionFileTree
import org.gradle.api.internal.file.DefaultSourceDirectorySet
import org.gradle.api.internal.file.FileResolver
import org.gradle.api.tasks.util.PatternFilterable
import org.gradle.api.tasks.util.PatternSet
import org.gradle.util.ConfigureUtil

class VisageSourceSet {
    private final SourceDirectorySet visage
    private final UnionFileTree allVisage
    private final PatternFilterable visagePatterns = new PatternSet()

    public VisageSourceSet(String displayName, FileResolver fileResolver) {
        visage = new DefaultSourceDirectorySet(String.format("%s Visage source", displayName), fileResolver)
        visage.filter.include("**/*.visage")
        visagePatterns.include("**/*.visage")
        allVisage = new UnionFileTree(String.format("%s Visage source", displayName), visage.matching(visagePatterns))
    }

    public SourceDirectorySet getVisage() {
        return visage
    }

    public VisageSourceSet visage(Closure configureClosure) {
        ConfigureUtil.configure(configureClosure, this.visage)
        return this
    }

    public PatternFilterable getVisageSourcePatterns() {
        return visagePatterns
    }

    public FileTree getAllVisage() {
        return allVisage
    }

    public void visageIncludeNamespace(String pattern) {
        visage.include(
            pattern.replaceAll("-", "_").replaceAll("\\.", "/") + ".visage"
        )
    }

    public void visageExcludeNamespace(String pattern) {
        visage.exclude(
            pattern.replaceAll("-", "_").replaceAll("\\.", "/") + ".visage"
        )
    }
}