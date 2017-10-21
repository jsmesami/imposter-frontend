# Imposter Frontend

The frontend part of automated poster generation system for 
Municipal Library of Prague.

## Overview

A standalone repository for single-page JS application consuming 
[backend](FIXME:url) API. The app is written in 
[ClojureScript](https://clojurescript.org/) for better maintainability.

## Development

### Prequisities

* Node.js, Yarn, Gulp 
* Java 8, [Leiningen](https://leiningen.org/) 2.7+

### Environment

To get an interactive development environment for ClojureScript run:

    lein dev

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. 

To start working on Sass files run:

    gulp dev

This will watch Sass sources for changes and feed compiled stylesheets, 
including sourcemaps, into the browser.

## Build

To create a production build run:

    make

To clean all installed and compiled files:

    make clean

## Test

    lein test

## License

Copyright © 2017 Ondřej Nejedlý

Distributed under the Eclipse Public License either version 1.0 or 
(at your option) any later version.
