# UniqueIdGen
## Why do we need this ?

	·  Idempotency request handling
	·  For a service we may need to store data for each service to identify it uniquely


## Topics to Know for this ?

	· Hashing (MD5 , SHA1 and other algorithms )
	· UUID , GUID , Epoch timestamps 

## Functional Requirements  :

	·  To design a unique ID generator 
	·  Id should be sorted by time or random ? going with sorted 
	·  Random for services like Tiny URL 
	·  Sortable for other services 
	·  Size of Unique ID ? 64 bit or 128 bits , possibly 8 bytes (64 bits)
	·  Do we need to persist the Id in DB ?

## Non Functional Requirements : 

	·  Scalable
	·  Consistency or availability ? Highly available 
	· Authentication and authorization ? May be required 


## APIs :

	·  getUniqueId(api_key/token/securityContext)


## Algorithms :

### Option 1 : Counter/Timestamp from a single server
	· Counter  or local timestamp : works fine with single server, not scalable
	· Counter on application side / DB auto increment feature 
	· What if multiple users request at the same time ?  Make the counter atomic 
	· Will give conflicts for distributed servers
	· If we use auto increment , it will take 8 bytes just for a single value and also increase in latency

### Option 2 : Timestamp + Machine ID 

	· This can work 
	· Sortable 
	· Have to check on how many bits can be utilized?
	· Is it possible to do 40 bits timestamp + 24 bit machine ID ?

### Option 3 : MongoDB Object ID 
 
	· 96 bits object id which is unique 
	· More size than what we are expecting 

### Option 4 :  Using a central server like ZooKeeper 
	· What is zookeeper is down ? Is that a valid case ?
	· Assign some counters to each server in a range which they can return 
	· One problem is lets say server 1 has range 0 - 100 and server 2 has 101 - 200 , first request goes to server 1 return 1 , next to server 2 return 101 and now again to server 1 will return 2 . It is not in sorted order 


### Option 5:  UUID(128 bits ) : Alphanumeric
A standard UUID code contains 32 hex digits along with 4 “-” symbols, which makes its length equal to 36 characters.
Example : e58ed763-928c-4155-bee9-fdbaaadc15f3

Types of UUID : 

	1. Version 1 (date time and MAC address ) : can reveal mac address 
	2. Version 2 (date time , MAC address and DCE security version)
	3. Version 3 (from url ) 
	4. Version 4 : Random 
	5. Version 5 : same as 3 but with SHA1 

	· UUIDs are random 
	· Not sortable 
	· Can be used for design where randomness is required 
	· Size is more than what is expected (128 bits )
	

### Option 6:  ULID (128 bits )  : 48 bit timestamp for sorting and remaining bits for uniqueness 

### Option 7:  Ticket Server 
	· Centralized server which gives auto increment keys 
	· SPOF 
	

Option 8:  Twitter Snowflake ![image](https://github.com/Shivamjr7/UniqueIdGen/assets/29726341/ad1cb354-d39d-4655-a5ff-8dfd894b46c2)

