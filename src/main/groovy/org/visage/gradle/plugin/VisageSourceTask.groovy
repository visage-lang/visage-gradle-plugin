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

import org.gradle.api.tasks.SourceTask

/** 
 * <p>Visage base task</p>
 * 
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 *
 *
 */
public class VisageSourceTask extends SourceTask {
   
    public VisageSourceTask includeNamespace(String pattern) {
        include(pattern.replaceAll("-", "_").replaceAll("\\.", "/") + ".visage")
        return this
    }

  /*  public VisageSourceTask excludeNamespace(String pattern) {
        exclude(pattern.replaceAll("-", "_").replaceAll("\\.", "/") + ".visage")
        return this
    }*/
}