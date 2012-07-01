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

import org.gradle.api.file.FileCollection
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.StopExecutionException
import org.gradle.api.tasks.TaskAction

import java.io.File
import java.io.InputStream

import groovy.lang.Closure

/** 
 * <p>Visage Run Task</p>
 * 
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 *
 *
 */
public class VisageRunTask extends VisageSourceTask {
	def String visageMainClass
	def FileCollection classpath
	def SourceDirectorySet visageRoots
	def File destinationDir
	
	@OutputDirectory
	public File getDestinationDir() {
		return this.destinationDir
	}


	 String getVisageMainClass() {
		return this.visageMainClass
	}

	 @InputFiles
	 public FileCollection getClasspath() {
		 return this.classpath
	 }

	
	
	
	@TaskAction
	public void run() {
		

		// TODO I don't think this works for paths with spaces in them. Should fix this.
		def cp = project.files(
				// this.inputRoots,
				classpath
				//project.configurations.development
				//  this.compileClasspath
				).getAsPath();
		logger.debug("Visage running Classpath = $cp");
		
		logger.debug("Visage Running Files: $visageMainClass");
		String lCommand = "visage -cp $cp  $visageMainClass"
		logger.info("Visage Run Command: " + lCommand)
		def sout = new StringBuffer()
		def serr = new StringBuffer()
		def process = lCommand.execute()
		process.consumeProcessOutput(sout, serr)
		process.waitFor()
		// TODO this doesn't appear to stop compilation if there are missing entries on the class path.
		if (process.exitValue()) {
			logger.error(serr.toString())
			logger.info(sout.toString())
			throw new StopExecutionException("Failed to run Visage")
		} else {
			logger.info(sout.toString())
			logger.debug(serr.toString())
		}

	}








}