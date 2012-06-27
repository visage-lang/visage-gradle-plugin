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


import groovy.io.FileType;

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.execution.TaskExecutionGraph
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.plugins.WarPlugin
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.bundling.War
import org.gradle.api.tasks.StopExecutionException
import org.gradle.api.tasks.testing.Test
import org.gradle.api.internal.project.ProjectInternal


/** 
 * <p>Primary class for Visage Gradle Plugin</p>
 * 
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 *
 *
 */
class VisagePlugin implements Plugin<Project> {
	
	static final String VISAGE_CONFIGURATION_NAME = "visage"
	
	static final String VISAGE_GROUP = "Visage"

	void apply(Project project) {

		configureSetup(project)

		project.getPlugins().apply(JavaPlugin.class);

		project.convention.plugins.visage = new VisagePluginConvention(project);

		configureSourceSets(project)
		configureCompileTask(project)
		configureRunTask(project)
	}

	/*private void configureConfigurations(Project project) {
		project.configurations {
			visage {
				transitive = false
				visible = false
				description = "Visage internal configuration. Don't use!"
			}
			development {
				transitive = false
				visible = false
				description = "Development only dependencies"
			}
		}
	}*/

	private void configureSetup(project) {

		String visageHome = System.env["VISAGE_HOME"]
		//isExisit = false
		
		if (!visageHome)
			throw new StopExecutionException("VISAGE_HOME is not set.")
				
	}

	private void configureSourceSets(Project project) {
		ProjectInternal projectInternal = (ProjectInternal)project

		project.sourceSets.each { sourceSet ->
			VisageSourceSet visageSourceSet =
					new VisageSourceSet(sourceSet.name, projectInternal.fileResolver)

			sourceSet.convention.plugins.visage = visageSourceSet
			sourceSet.visage.srcDirs = [
				String.format("src/%s/visage", sourceSet.name)
			]
			// sourceSet.resources.filter.exclude("**/*.visage")
			sourceSet.allSource.source(visageSourceSet.visage)
		}
	}

	private void configureCompileTask(Project project) {
		project.sourceSets.each { set ->
			if (set.equals(project.sourceSets.test))
				return
			String compileTaskName = set.getCompileTaskName("visage")
			
			VisageCompileTask task = project.tasks.add(name: compileTaskName,
					type: VisageCompileTask.class) {
						destinationDir = set.output.classesDir
						source set.visage
						visageRoots = set.visage
						classpath = project.files(
								set.compileClasspath,
								//project.configurations.development
								)
						description =
								String.format("Compile the %s Visage source.",
								set.name)
						group = VISAGE_GROUP
					}
			project.tasks[set.classesTaskName].dependsOn task
		}
	}
	
	private void configureRunTask(Project project) {
	
			
			
			project.sourceSets.each { set ->
				if (set.equals(project.sourceSets.test))
					return
			
				VisageRunTask task = project.tasks.add(name: 'runVisage',
			type: VisageRunTask.class) {
						//	destinationDir = set.output.classesDir
							source set.visage
						//	visageRoots = set.visage
							classpath = project.files(
									set.compileClasspath,
									//project.configurations.development
									)
							description = 'Run a Viaage main file.'
			group = VISAGE_GROUP
						}
				project.tasks[set.classesTaskName].dependsOn task
			}
			
			
			
			
		}
}