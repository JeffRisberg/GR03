// Selenium-RC configuration

selenium {
	browser = "*firefox"
	url = "http://localhost:8080/GR03"
	screenshot {
		dir = "./target/test-reports/screenshots"	// directory where screenshots are placed relative to project root
		onFail = true								// true to capture screenshots on test failures
	}
}