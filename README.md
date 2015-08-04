# aot

A Leiningen plugin used to AOT compile dependencies found in namespace declarations

## Install

This plugin is only available for local install. To install locally:

1. Clone this repo and `cd` to the cloned directory
2. `lein install` will install the plugin to your local `~/.m2` repository

That's it!

## Usage

Use this for project-level plugins:

Put `[aot "0.1.0"]` into the `:plugins` vector of your project.clj.

Execute this in your project to compile:

    $ lein aot

## TODO

* Exclude project's source files from compilation
