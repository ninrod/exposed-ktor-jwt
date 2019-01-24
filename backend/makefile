.PHONY: build pristine test full

build:
	gradle build

pristine:
	git clean -fdx

test:
	DATABASE_ADDRESS=db java -jar build/libs/chess-0.0.1.jar

full: pristine build test
