pipeline {
    agent any
    tools {
        maven 'MAVEN_3_8_5'
        jdk 'JDK_1_11'
    }

    stages {
        stage ('Compile Stage 2022-01') {

            steps {
                withMaven(maven : 'MAVEN_3_8_5') {
                    bat 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage 2022-01') {

            steps {
                withMaven(maven : 'MAVEN_3_8_5') {
                    bat 'mvn test'
                }
            }
        }

        stage ('package Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_8_5') {
                    bat 'mvn package'
                }
            }
        }
    }
}