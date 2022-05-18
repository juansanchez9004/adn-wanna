@Library('ceiba-jenkins-library@master') _
pipeline {

    //Donde se va a ejecutar el Pipeline
    agent {
        label 'Slave_Induccion'
    }

    //Opciones específicas de Pipeline dentro del Pipeline
    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        disableConcurrentBuilds()
    }

    //Una sección que define las herramientas “preinstaladas” en Jenkins
    tools {
        jdk 'JDK17_Centos' //Verisión preinstalada en la Configuración del Master
    }

    //Aquí comienzan los “items” del Pipeline
    stages {
        stage('Checkout') {
            steps {
                echo "------------>Checkout<------------"
            }
        }

        stage('Compile & Unit Tests') {
            steps {
                echo "------------>Clean Tests<------------"
                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle clean'

                echo "------------>Compile & Unit Tests<------------"
                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle test'
            }
        }

        stage('Static Code Analysis') {
            steps {
                echo "------------>Análisis de código estático<------------"
                sonarqubeMasQualityGatesP(
                        sonarKey:'co.com.ceiba.adn:wanna.pablo.tabares',
                        sonarName:'''"CeibaADN-wanna(pablo.tabares)"''',
                        sonarPathProperties:'./sonar-project.properties')
            }
        }

        stage('Build') {
            steps {
                echo "------------>Build<------------"
                sh './microservicio/gradlew --b ./microservicio/build.gradle build -x test'
            }
        }
    }

    post {
        success {
            echo 'This will run only if successful'
            junit 'build/test-results/test/*.xml'
        }

        failure {
            echo 'This will run only if failed'
            mail (
                    to: 'pablo.tabares@ceiba.com.co',
                    subject: "Failed Pipeline:${currentBuild.fullDisplayName}",
                    body: "Something is wrong with ${env.BUILD_URL}"
            )
        }
    }
}