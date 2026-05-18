mvnw := if os() == "windows" { "./mvnw.cmd" } else { "./mvnw" }

default:
    just --list

# Build all modules including tests, and install to local Maven repository
build:
    {{ mvnw }} clean install
