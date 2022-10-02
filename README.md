Gatling plugin for Gradle - Java examples
=========================================

# Run simulations

To run simulationst, use following commands:

## Run all Gatling simulations at once

```$ ./gradlew gatlingRun```

## Run single simulation by its FQN (fully qualified class name)

```$ ./gradlew gatlingRun-com.net128.testing.load.computerdatabase.ComputerDatabaseSimulation```

```$ ./gradlew gatlingRun-com.net128.testing.load.reqres.BasicSimulation```

```$ ./gradlew gatlingRun-com.net128.testing.load.reqres.ReqPerSecSimulation```

# How to build an executable jar

```shell
./gradlew fatJar
```

## How to run simulations

```shell
export LIMIT=<Number of requests needs to invoke>
export DURATION_IN_SECONDS=<How long performance tests should run>
java -jar build/libs/gatling-example-1.0-SNAPSHOT-all.jar # to run all simulations sequentially
java -jar build/libs/gatling-example-1.0-SNAPSHOT-all.jar <simulation name(s)> # to run specific simulation(s)
```

### example 1

```shell
export LIMIT=5
export DURATION_IN_SECONDS=5

java -jar build/libs/gatling-java-examples-1.0-SNAPSHOT-all.jar
```

### example 2

```shell
export LIMIT=5
export DURATION_IN_SECONDS=5

java -jar build/libs/gatling-java-examples-1.0-SNAPSHOT-all.jar com.net128.testing.load.reqres.BasicSimulation

### example 2

```shell
export LIMIT=5
export DURATION_IN_SECONDS=5

java -jar build/libs/gatling-java-examples-1.0-SNAPSHOT-all.jar com.net128.testing.load.reqres.ReqPerSecSimulation
```

### example 4

```shell
export LIMIT=5
export DURATION_IN_SECONDS=5

java -jar build/libs/gatling-java-examples-1.0-SNAPSHOT-all.jar com.net128.testing.load.computerdatabase.ComputerDatabaseSimulation com.net128.testing.load.reqres.BasicSimulation
```

# Links
- https://www.softwaretestinghelp.com/gatling-reports/
- https://docs.getxray.app/display/XRAYCLOUD/Performance+and+load+testing+with+Gatling
