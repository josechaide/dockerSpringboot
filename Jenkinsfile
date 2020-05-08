def gv

pipeline{
    agent any
    tools {
        maven 'maven_3_6_3'
    }
    parameters{
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description:'Choose release version')
        booleanParam(name: 'do_package_jar', defaultValue: true, description: 'Build .JAR package in local repository')
        booleanParam(name: 'do_docker_stuff', defaultValue: true, description: 'Build docker img and push it to repository')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Test fully application')
        booleanParam(name: 'deploy_on_qa', defaultValue: false, description: 'Deploy application on QA environment')
    }
    stages {
        stage('Load utils.groovy') {
            steps {
                script{
                    gv = load "/groovy/utils.groovy"
                }
                sh 'echo groovy utils loaded!'
            }
        }
        stage('Mvn clean Package ') {
            when {
                expression {
                    params.do_package_jar == true //Expression must evaluate booleanParams
                }
            }
            steps {
                script{
                    gv.buildApp()
                }
            }
        }
        stage('Building docker image'){
            when {
                expression {
                    params.do_docker_stuff == true //Expression must evaluate booleanParams
                }
            }
            steps{
                script{
                    gv.buildDockerImg()
                }
           }
        }
        stage('Pushing image to dockerHub'){
            when {
                expression {
                    params.do_docker_stuff == true //Expression must evaluate booleanParams
                }
            }
            steps{
               script{
                    gv.pushImgToDocker()
                }
            }
        }
        stage('Testing application') {
            when {
                expression {
                    params.executeTests == true //Expression must evaluate booleanParams
                }
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
                    params.deploy_on_qa == true //Expression must evaluate booleanParams
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
