# template-rest
This sub-module provides necessary services to handle non-functional requirements:
  1. Automatically log out the execution time of all public service method that are annotated by @LogExecutionTime
  2. Formats the log of console appender, also prints out all requestId.

# architecture
Firstly, this project is a standalone application.
I apply DDD architecture for this particular module.
CLEAN code from my opinion is not only just well coding, but also need to be CLEAN architected, CLEAN test, comes together with CLEAN DEPENDENCIES.

