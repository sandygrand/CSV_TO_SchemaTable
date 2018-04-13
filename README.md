# CSV_TO_SchemaTable
This API reads the contents of the CSV file in /raw folder and then parses it for individual data.
It then sends it to server side script via an Asynchronous Background class 'BackgroundWorker'.
That class sends the data in key-value pairs to the PHP file on server.
The PHP file then performs the insert operation on to the schema table.

