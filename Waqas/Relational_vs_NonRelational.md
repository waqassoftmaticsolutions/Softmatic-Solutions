# Relational Databases
 Relational databases store data in structured tables with rows and columns. Each table has a unique key that identifies records, and relationships between tables are established using foreign keys.
 ### Example
   1) MySQL
   2) Oracle
   3) Microsoft SQL Server

### When to use
  1) **Structured Data:** When the data is highly structured and relationships between entities are well-defined.
  2) **Complex Queries:** When you need to perform complex queries, joins, and transactions.
  3) **Consistent Schema:** When the schema is stable, and changes to the data structure are infrequent.

### Pros
  1) **ACID compliance:** Atomicity, Consistency, Isolation, and Durability (ACID) is a standard that guarantees the reliability of database transactions. The general principle is if one change fails, the whole transaction will fail, and the database will remain in the state it was in before the transaction was attempted.
  2) **Data accuracy:** Using primary and foreign keys allows you to ensure there is no duplicate information. This helps enforce data accuracy because there will never be repeated information
  3) **Normalization:** The process of normalization involves ensuring the data is organized in such a way that data anomalies are reduced or eliminated. This, in turn, reduces storage costs.
### Cons
  1) **Performance:** The performance of the database is tightly linked to the complexity of the tables—the number of them, as well as the amount of data in each table. As this increases, the time taken to perform queries increases too.
  2) **Scalability:** Horizontal scaling (scaling out) can be challenging and costly.
  3) **Rigid Schema:** Changing the schema can be complex and may require downtime.
  4) **Complexity:** Managing relationships, indexes, and constraints can be complex.


# Non Relational Databases
Non-relational databases do not store data in tables. Instead, they use different data models, such as key-value pairs, documents, wide-column stores, or graphs. Non-relational databases have been designed with the cloud in mind, making them great at horizontal scaling.
### Example
   1) MongoDB

### When to use
  1) **Unstructured or Semi-Structured Data:** When the data is unstructured, semi-structured, or rapidly changing.
  2) **Scalability:** When you need to handle large volumes of data and require horizontal scaling.
  3) **Flexible Schema:** When the schema is dynamic, and you need to accommodate changes without downtime.
  4) **High Throughput:** When performance and speed are more critical than data consistency.

### Pros
  1) **Scalability:** Easily scale horizontally by distributing data across multiple nodes.
  2) **Flexibility:** Dynamic schema allows for easy changes to the data structure.
  3) **Performance:** Optimized for specific use cases, such as fast read/write operations.
### Cons
  1) **Lack of Standardization:** No universal query language like SQ.
  2) **Acid** doesn't support acid properties

