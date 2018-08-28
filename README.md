# Keystore Cleaner

This project is used in removing expired keys from a keystore.

## Getting Started

To run this tool, first clone the repository and build it with maven.

~~~
git clone https://bitbucket.org/payara/keystore-cleaner.git
cd keystore-cleaner/
mvn clean install
~~~

The runnable JAR will then be available in target/ with the `jar-with-dependencies` classifier.

Run the project with no options to see the usage.

~~~
java -jar target/keystore-cleaner-0.1-jar-with-dependencies.jar
~~~