all: clean build

clean:
	rm -rf bin/*

build:
	find src/ -type f -name *.java -print -exec javac -source 1.6 -target 1.6 -d bin/ {} \+

tar:

pre-dist:
	cp README.md bin/it/d4nguard/rgrpg/

dist: pre-dist

install: dist

uninstall:

