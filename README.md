# http-client-samples

![Java http calls](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/8h11cnqgx43lx8orlhsa.png)

A collection of java examples of http calls depending on OkHttpClient library

Here's a list of included examples:

GET

- simpleGetCall: Performs a simple http GET call to the specified url with the specified query parameter
- simpleGetCallTimeoutAware: Performs a simple http GET call to the specified url with the specified query parameter with a specified timeout expressed in millisecond. When the timeout occurs the call will be canceled.
- simpleUnsafeGetCallTimeoutAware: Performs a simple unsafe http GET call to the specified url with the specified query parameter with a specified timeout expressed in millisecond. When the timeout occurs the call will be canceled. It is unsafe beacause in case of an https url call, the certificates chain will not be checked. It is not a recommended usage, though sometimes it may be useful for troubleshooting purpose.
- simpleAsyncGetCall: Performs a simple asyncronous http GET call to the specified url.

POST

- simplePostCall: Performs a simple http POST call to the specified url with the specified json payload.

PUT

- simplePutCall: Performs a simple http PUT call to the specified url with the specified json payload.

DELETE

- simpleDeleteCall: Performs a simple http DELETE call to the specified url.
