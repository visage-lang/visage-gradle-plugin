/*
 * Copyright (c) 2013, Visage Project
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

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.StopExecutionException


class VisageJavaFXPlugin  implements Plugin<Project>  {

	public void apply(Project project) {

		final javafxHome = System.env["JAVAFX_HOME"]
		def jfxJar =''

		project.getPlugins().apply(VisagePlugin.class);

		if (javafxHome) {
			jfxJar = "${javafxHome}/rt/lib/jfxrt.jar"
		}
		else{
			final javaHome = System.env["JAVA_HOME"]
			jfxJar = "${javaHome}/jre/lib/jfxrt.jar"
		}

		if(!(new File(jfxJar)).exists())
			throw new StopExecutionException("JAVAFX_HOME is not set or your JDK is not having JAVAFX jar.")


		project.dependencies  {
			compile project.files(jfxJar)
			compile 'org.visage-lang:visage-javafx:0.1-SNAPSHOT'
		}

	}
}
