# metadata-api
Metadata API to create, update, delete and search meta data's.

1. Download the source code and do ./gradlew clean --refresh-dependencies to download the dependencies.
2. To run the code without any IDE (VsCode or IntelliJ) use ./gradlew bootRun which starts app in 8080 port.
3. This application uses Spring data JPA with hibernate and in memory database H2 to store and retrieve data.
4. endpoint /save method inserts the metadata into DB and takes in YAML format. 
5. endpoint /findByDescription takes in a query param and returns the records that match like the input param.
6. endpoint /findByTitle takes in a query param and returns matching record from DB. 

