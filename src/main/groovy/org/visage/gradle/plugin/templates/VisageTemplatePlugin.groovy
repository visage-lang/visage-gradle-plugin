package org.visage.gradle.plugin.templates

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Adds basic tasks for bootstrapping Java projects. Adds createJavaClass, createJavaProject,
 * exportJavaTemplates, and initJavaProject tasks.
 */
class VisageTemplatePlugin implements Plugin<Project> {
	
	
	void createBase(String path = System.getProperty('user.dir')) {
		ProjectTemplate.fromRoot(path) {
			'src' {
				'main' {
					'visage' {}
					'java' {}
					'resources' {}
				}
				'test' {
					'visage' {}
					'java' {}
					'resources' {}
				}
			}
			'License.txt' '// Your License Goes here'
			'Readme.txt' '// Your Readme Goes here'
		}
	}
	
	static String findMainJavaDir(Project project) {
		File rootDir = project.projectDir
		def mainSrcDir = project.sourceSets?.main?.visage?.srcDirs*.path
		mainSrcDir = mainSrcDir?.first()
		mainSrcDir = mainSrcDir?.minus(rootDir.path)
		return mainSrcDir
	}

	@Override
	public void apply(Project project) {
		
		def props = project.properties
		
		
		configureCreateVisageTask(project,props)
		configureInitVisageTask(project, props)
		
	}
	
	
	def configureCreateVisageTask (project,props) {
	
		project.task('createVisageProject', group: TemplatesPlugin.group, description: 'Creates a new Visage Java project in a new directory named after your project.') << {
			def projectName = props['newProjectName'] ?: TemplatesPlugin.prompt('Project Name:')
			if (projectName) {
				String projectGroup = props['projectGroup'] ?: TemplatesPlugin.prompt('Group:', projectName.toLowerCase())
				String projectVersion = props['projectVersion'] ?: TemplatesPlugin.prompt('Version:', '1.0')
				createBase(projectName)
				ProjectTemplate.fromRoot(projectName) {
					'build.gradle' template: '/templates/visage/build.gradle.tmpl', projectGroup: projectGroup
					'gradle.properties' content: "version=$projectVersion", append: true
				}
			} else {
				println 'No project name provided.'
			}
		}
		
	}
	
	def configureInitVisageTask(project, props) {
	
		project.task('initVisageProject', group: TemplatesPlugin.group, description: 'Initializes a new Visage Java project in the current directory.') << {
			createBase()
			File buildFile = new File('build.gradle')
			buildFile.exists() ?: buildFile.createNewFile()
			TemplatesPlugin.prependPlugin 'java', buildFile
		}
			
	}
	
	
	

}
