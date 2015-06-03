all: clean build test jar

clean:
	echo "Executing cleaning tasks..."
	find . -regextype posix-extended -regex '(.*~|\..*~|.*\.swp|\..*\.swp)' -print -exec rm {} \;
	find . -name rgrpg-dist.tar.gz -delete
	mvn clean

build: clean
	echo "Building sources..."
	mvn compile

test:
	echo "Running JUnit tests..."
	mvn test

jar: build
	echo "Creating the jar..."
	mvn package

run:
	java -jar bin/rgrpg-all.jar

try: all run

install: all
	echo "Installing..."
	mvn install

docs: all
	echo "Building javadocs..."
	mvn javadoc

site: docs
	echo "Building site from maven template..."
	mvn site

dist: clean
	echo "Creating a distributable tar.gz..."
	find . -exec tar -czf rgrpg-dist.tar.gz {} \+
