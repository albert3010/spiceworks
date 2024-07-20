<p><a target="_blank" href="https://app.eraser.io/workspace/8rZ2i03AVHYkxpDXhgfg" id="edit-in-eraser-github-link"><img alt="Edit in Eraser" src="https://firebasestorage.googleapis.com/v0/b/second-petal-295822.appspot.com/o/images%2Fgithub%2FOpen%20in%20Eraser.svg?alt=media&amp;token=968381c8-a7e7-472a-8ed6-4a6626da5501"></a></p>

function

1. creates post , image , video supported 
2. get user profile posts 
3. get timeline  
4. like the post , reply the post multiple, nested(extended)
5. search tweets 
Non Functional

1. System should be highly available 
2. System should be able scale for large no of tweets
3. Latency to be minimum
4. Latency over consistency but eventual consistency


Storage estimates 

users : 1B users

DAU : 100 million 

avg per user daily tweet: 10

write posts : 100m* 10 => 1B tweets/day

avg no of follower per user: 100

avg per user daily read: 100 * 10 tweets => 1000 tweet read/user/day

Read post => 100 m * 1000 => 100B read => **1M/sec TPS**

Write 

per tweet size -> 100 character -> 1Kb *1B -> 1TB

Image -> (1B/10) _ 100Kb   -> 10TB _

Video ->(1B/100) 10Mb -> 100TB

bandwidth -> 1TB/1000 -> 1GB/second

Read

100 * write bandwidth -> 100GB/second



100B/10^5 = 1B/1000-> **1M/sec read / 10^6/100= 10000 machines**



**DB schema **

NoSql 

Content : this is good, @mention @Ni <link> 

this is good, id-mention ,  uuid-mention  <link> 

RichContent: [{

Text: ssas

link:

}, 

{

Text: mention

link: www...

}]

**Tweet table **

TweetId

userid

creationTime

content  

objectLinks 

lat 

log 



**Reply table **

replyId

TweetId

userId

parentRepyId (null)

content

time 

**Like Table **

TweetId

userId

time



**LikeCount table **

tweetId

TotalCount

dislikeId

love



### Right Way to Use Load Balancer and API Gateway
**Combined Usage in Modern Architectures:**

- **Front-end Load Balancer:** Placed at the edge to distribute incoming client traffic to multiple instances of the API Gateway, ensuring high availability and redundancy.
- **API Gateway:** Handles routing, security, rate limiting, and other API management tasks, forwarding requests to the appropriate backend services.
**Types:** Layer 4 (Transport Layer) and Layer 7 (Application Layer) load balancers.

**Client Uploads Video to S3:**

- The client uses the pre-signed URL to upload the video directly to S3.
- PUT https://bucket-name.s3.amazonaws.com/my_video.mp4?AWSAccessKeyId=AKIA... Content-Type: video/mp4 (binary data of the video file)


{ "upload_url": "https://bucket-name.s3.amazonaws.com/my_video.mp4?AWSAccessKeyId=AKIA...", "media_url": "https://bucket-name.s3.amazonaws.com/my_video.mp4" }

**Backend processes the media and stores metadata (e.g., URL, type, user) in its database.**



Client -> Backend (request pre-signed URL) Client <- Backend (return pre-signed URL) Client -> S3 (upload file using pre-signed URL) Client -> Backend (notify of upload completion) Backend -> Process Media (optional) Client -> Backend (create post with media URL)

** **



<!--- Eraser file: https://app.eraser.io/workspace/8rZ2i03AVHYkxpDXhgfg --->