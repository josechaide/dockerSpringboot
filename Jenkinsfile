def gv

pipeline{
    agent any

    tools {
        maven 'maven_3_6_3'
    }
    parameters{
        choice(name: 'VERSION', choices: ['latest', '1.0.0', '2.0.0', '3.0.0'], description:'Choose release version')
        booleanParam(name: 'doJarPackage', defaultValue: true, description: 'Build .JAR package in local repository')
        booleanParam(name: 'doDockerStuff', defaultValue: true, description: 'Build docker img and push it to repository')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Test fully application')
        booleanParam(name: 'deployOnQa', defaultValue: true, description: 'Deploy application on QA environment')
    }
    stages {
        stage('Load groovy utils') {
            steps {
                script{
                    gv = load "src/groovy/utils.groovy"
                }
                sh 'echo groovy utils loaded!'
            }
        }
        stage('Mvn clean package') {
            when {
                expression { params.doJarPackage == true }
            }
            steps {
                script{
                    gv.buildApp()
                }
            }
        }
        stage('Build docker image'){
            when {
               allOf {
                   expression { params.doJarPackage == true }
                   expression { params.doDockerStuff == true }
               }
            }
            steps{
                script{
                    gv.buildDockerImg()
                }
           }
        }
        stage('Push image to dockerHub'){
            when {
                allOf {
                    expression { params.doJarPackage == true }
                    expression { params.doDockerStuff == true }
                }
            }
            steps{
               script{
                    gv.pushImgToDocker()
                }
            }
        }
        stage('Test application') {
            when {
                expression { params.executeTests == true }
            }
            steps {
                script{
                    gv.testApp()
                }
            }
        }
        stage('Deploy on QA') {
            when {
                expression {
                    params.deployOnQa == true //Expression must evaluate booleanParams
                }
            }
            steps {
                script{
                    gv.deployApp()
                }
            }
        }
    }
}
