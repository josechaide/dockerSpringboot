def gv
node{
    agent any
    tools {
        maven 'maven_3_6_3'
    }
    parameters{
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description:'')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }
    stages {
        stage('Load utils.groovy') {
            steps {
                script{
                    gv = load "/groovy/utils.groovy"
                }
            }
        }
        stage('Mvn clean Package ') {
            steps {
                script{
                    gv.buildApp()
                }
            }
        }
        stage('Building docker image'){
            steps{

           }
        }
        stage('Pushing image to dockerHub'){
            steps{

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
            steps {
                script{
                    gv.deployApp()
                }
            }
        }
    }
}
