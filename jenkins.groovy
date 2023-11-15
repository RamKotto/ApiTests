task_branch = "${TEST_BRANCH_NAME}"
def branch_cutted = task_branch.contains("origin") ? task_branch.split('/')[1] : task_branch.trim()
currentBuild.displayName = "$branch_cutted"
base_git_url = "https://github.com/RamKotto/ApiTests.git"


node {
    withEnv(["branch=${branch_cutted}", "base_url=${base_git_url}"]) {
        stage("Checkout Branch") {
            if (!"$branch_cutted".contains("develop")) {
                try {
                    getProject("$base_git_url", "$branch_cutted")
                } catch (err) {
                    echo "Failed get branch $branch_cutted"
                    throw ("${err}")
                }
            } else {
                echo "Current branch is develop"
            }
        }

        try {
            parallel getTestStages(["colortests", "usertests"])
        } finally {
            stage ("Allure") {
                generateAllure()
            }
        }
    }
}


def getTestStages(testTasks) {
    def stages = [:]
    testTasks.each { task ->
        stages["${task}"] = {
            testTasks
        }
    }
    return stages
}


def runTestTask(String task) {
    try {
        labelledShell(label: "Run ${task}", script: "gradle ${task}")
    } finally {
        echo "some failed tests"
    }
}

def getProject(String repo, String branch) {
    cleanWs()
    checkout scm: [
            $class           : 'GitSCM', branches: [[name: branch]],
            userRemoteConfigs: [[
                                        url: repo
                                ]]
    ]
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'build/allure-results']]
    ])
}
