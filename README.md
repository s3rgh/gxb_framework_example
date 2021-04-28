#Task #1

###For start tests:

`gradlew clean test`

###Suites:

`gradlew clean testMail`

`gradlew clean testDisk`

###Options:

`-Pbrowser=firefox/chrome`

`-Pheadless=on/off`

for example: 

`gradle clean testMail -Pbrowser=firefox -Pheadless=off`
