CREATE
STREAM sessionstream (
	type varchar,
  	anonymousID varchar,
  	loadTime varchar,
  	unloadTime varchar,
  	language varchar,
  	platform varchar,
  	port varchar,
  	referer varchar,
  	location varchar,
  	href varchar,
  	origin varchar,
  	title varchar,
  	endpoint varchar
) with (
	KAFKA_TOPIC = 'sessions-01',
  	VALUE_FORMAT = 'JSON'
);

CREATE TABLE PAGE_VIEWS AS
    SELECT HREF,
           COUNT(ANONYMOUSID) as USERS
FROM SESSIONSTREAM
GROUP BY HREF
    EMIT CHANGES;

CREATE TABLE DISTINCT_VIEWS AS
SELECT HREF,
       COUNT_DISTINCT(ANONYMOUSID) as USERS
FROM SESSIONSTREAM
GROUP BY HREF
    EMIT CHANGES;