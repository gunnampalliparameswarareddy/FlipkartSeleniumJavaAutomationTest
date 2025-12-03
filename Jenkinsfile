pipeline{
	agent any
	parameters {
		choice(name: 'browser', choices: ['chrome','firefox','edge'], description: 'Browser to run tests')
		choice(name: 'version', choices: ['117.0','116.0'], description: 'Browser Version to run tests')
	}
	stages{
		stage('checkout from GitHub')
		{
			steps {
				checkout scm
			}
		}
		stage('Run TestNG Tests') {
			steps {
				// Pass Jenkins parameters into Maven
				sh "mvn clean test -Dbrowser=${params.browser} -Dversion=${params.version}"
			}
		}
	}
}