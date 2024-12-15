pipeline {
    agent any

    environment {
        GITHUB_ACTOR = 'laurensdevos2001'      // Vervang door je GitHub username
        GITHUB_TOKEN = credentials('PAT_TOKEN')      // GitHub Personal Access Token opgeslagen in Jenkins credentials
    }

    stages {
        stage('Checkout Repository') {
            steps {
                echo "Checking out repository..."
                checkout scm
            }
        }

        stage('Set up JDK 17') {
            steps {
                echo "Setting up JDK 17..."
                // Installeer JDK 17 via Jenkins Tools (configureer dit in Jenkins Global Tool Configuration)
                script {
                    tool name: 'jdk17', type: 'jdk'
                }
            }
        }

        stage('Configure Maven for GitHub Packages') {
            steps {
                echo "Configuring Maven for GitHub Packages..."
                sh """
                mkdir -p ~/.m2
                echo "<settings xmlns='http://maven.apache.org/SETTINGS/1.0.0'>
                    <servers>
                        <server>
                            <id>github</id>
                            <username>${env.GITHUB_ACTOR}</username>
                            <password>${env.GITHUB_TOKEN}</password>
                        </server>
                    </servers>
                </settings>" > ~/.m2/settings.xml
                """
            }
        }

        stage('Build and Package') {
            steps {
                echo "Building and packaging the project..."
                sh "mvn clean package"
            }
        }

        stage('Publish to GitHub Packages') {
            steps {
                echo "Publishing to GitHub Packages..."
                sh """
                mvn deploy \
                    -Dgithub.actor=${env.GITHUB_ACTOR} \
                    -Dgithub.token=${env.GITHUB_TOKEN}
                """
            }
        }
    }

    post {
        success {
            echo "Pipeline executed successfully for Repo1!"
        }
        failure {
            echo "Pipeline failed. Please check the logs for errors."
        }
    }
}
