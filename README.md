Create a program for processing payment documents and providing financial statements.
Access to the program must be carried out by login and password.
The program should get the path to the folder with financial documents, read information from the documents and compile statistics.
There are three types of documents: invoices, orders, and checks.
All documents are in tt format.
Each type of document has its own structure and its own title template.
Sample documents will be provided.
It is necessary to process files only for the current year.
To make technical documentation of the program according to the C4 model.
Implement various checks.
Implement saving logs to a separate file.
It is advisable to separate the logs: for storing general information and for storing error information.
Upon completion of the program, all invalid files must be moved to a separate folder.
The final statistics should be uploaded to a separate file.
Statistics:
- total total turnover for the year
- total turnover for all invoices
- total turnover for all orders
- total turnover for all checks
- record all payers in a separate file -
record all payees in a separate file

Acceptance criteria
- A working program.
- Clean and understandable code.
- Compliance with naming conventions for packages, classes, methods, variables.
- Java doc comments are required for services. Comments in English.
- A completed, concise and clear ReadMe file. The file must be filled in English.
- All the working code should be in the master branch. The number of other branches is not limited.
- The repository should not contain unnecessary files and folders (for example, idea, target, and others).
- There is a class diagram.
- There is a diagram of components (services).

Verification scenario
1. Launching the program
2. The program requests credits -> login and password input
3. The program asks for the folder path -> enter the folder path
4. The program is running and the results of the program are saved in a separate folder


Additional technical information on the project structure
Services:
- Authorization service.
- File reading and processing service.

Packages:
- classes for describing files
- classes for recording logs
- classes for parsing files
- classes for session description
- classes with utilitarian information
- exceptions
