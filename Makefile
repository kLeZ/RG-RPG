all: clean build test jar

clean:
	find . -regextype posix-extended -regex '(.*~|\..*~|.*\.swp|\..*\.swp)' -print -exec rm {} \;
	find . -name rgrpg-dist.tar.gz -delete
	mvn clean

build: clean
	mvn compile

test:
	mvn test

jar: build
	mvn package

run:
	java -jar bin/rgrpg-all.jar

try: all run

install: all
	mvn install

docs: all
	mvn javadoc

site: docs
	mvn site

dist: clean
	find . -exec tar -czf rgrpg-dist.tar.gz {} \+
