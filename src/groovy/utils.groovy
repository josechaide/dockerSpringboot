def buildApp () {
    echo 'Building the application...'
    sh "mvn clean package"
}

def buildDockerImg () {
    echo 'Building a docker image...'
    sh "docker build -t springbootapp:${params.VERSION} ."
}

def pushImgToDocker (){
    withCredentials([string(credentialsId: 'dockerpwd', variable: 'dockerHubPwd')]){
        sh "docker login -u josebaubay -p ${dockerHubPwd}"
        echo 'Login dockerHub success...'
    }
    sh """
        docker tag springbootapp:${params.VERSION} josebaubay/springbootapp:${params.VERSION}
        docker push josebaubay/springbootapp:${params.VERSION}
        docker rmi springbootapp:${params.VERSION}
    """
}

def testApp () {
    echo 'Testing the application...'
}

def deployApp () {
    echo " Deploying springbootapp:${params.VERSION}"
    sh "docker run --name maven-springboot -p 8081:8081 josebaubay/springbootapp:${params.VERSION}"
}

return this
