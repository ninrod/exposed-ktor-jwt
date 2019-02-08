.PHONY: build pristine test full

build:
	gradle build

pristine:
	git clean -fdx

up:
	java -jar build/libs/chess-0.0.1.jar

test:
	curl -s localhost:8080/database | jq '.[2]'

full: pristine build test
