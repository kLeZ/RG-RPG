all: clean build jar

clean:
	find . -regextype posix-extended -regex '(.*~|\..*~|.*\.swp|\..*\.swp)' -print -exec rm {} \;
	find . -name rgrpg-dist.tar.gz -delete
	mvn clean

build:
	mvn compile

jar: build
	mvn package

run: all
	java -jar bin/rgrpg-all.jar

install: all
	mvn install

dist: clean
	find . -exec tar -czf rgrpg-dist.tar.gz {} \+
