pipeline{
    
    agent any
    
    stages{
        
        stage("Build"){
            steps{
                echo("Build project")
            }
        }
        stage("Run UTS"){
            steps{
                echo("run unit test cases")
            }
        }
         stage("Deploy to DEV"){
            steps{
                echo("dev deployment")
            }
        }
         stage("Deploy to qa"){
            steps{
                echo("qa deployment")
            }
        }
        stage("Run Automation Test"){
            steps{
                echo("run automation test cases")
            }
        }
        stage("Run Automation regression Test"){
            steps{
                echo("run automation regression test cases")
            }
        }
        stage("Deploy to stage"){
            steps{
                echo("stage depoyment")
            }
        }
         stage("Run Automation  sanity Test"){
            steps{
                echo("run automation sanity test cases")
            }
        }
         stage("Deploy to prod"){
            steps{
                echo("prod depyoment")
            }
        }
        
    }
    
}