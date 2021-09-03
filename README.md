# Project analyzer

# Module 1: Parser
### * Notice: Algorithm will be modified for working on huge projects with big data.

"Virtual Project" - recursively go through the folder. Create a virtual in-memory structure of project.  called "virtual project".
"Project history" - if the project uses git engine for version control, use git commands and their outputs to get data about the project history, commiters, statistics, heatmaps and etc.

# Module 2: Core

Analyze parsed data. make asumptions, create statistics, heatmaps, history of the project and etc.

# Module 3: Processor

Takes "Virtual Project" as an input and using bunch of processings and calculations outputs useful data


# Building and using project
In the root of the project use: ./gradlew clean shadowJar
This will create build directory in the root of the project and then use:
java -jar build/libs/XXX.jar to run the application
