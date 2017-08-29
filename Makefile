default: build

build: clean install buildjs buildcss

install:
	yarn

buildjs:
	lein build

buildcss:
	gulp build

clean:
	rm -rf resources/public/css/*
	rm -rf resources/public/components
	rm -rf resources/public/js/*
	rm -rf node_modules
	rm -rf target
	rm -rf figwheel_server.log
