def buildApp () {
    echo 'Building the application...'
    sh "mvn clean package"
}
def buildDockerImg () {
    echo 'Building a docker image...'
    sh 'docker build -t springbootapp:latest .'
}

def pushImgToDocker (){
    withCredentials([string(credentialsId: 'dockerpwd', variable: 'dockerHubPwd')]){
        echo 'Login dockerHub...'
        sh "docker login -u josebaubay -p ${dockerHubPwd}"
        echo 'Login dockerHub success...'
    }
    sh '''
        echo 'Pushing fresh image to dockerHub'
        docker tag springbootapp:latest josebaubay/springbootapp:latest
        docker push josebaubay/springbootapp:latest
        docker rmi springbootapp:latest
    '''
}

def testApp () {

    echo 'Testing the application...'
}

def deployApp () {
    echo " Deploying version ${params.VERSION}"
}

return this
