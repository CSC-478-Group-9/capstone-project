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

CREATE TABLE CLICK_USER_SESSIONS AS
SELECT anonymousID           as K,
       AS_VALUE(anonymousID) as id,
       WINDOWEND             as EVENT_TS,
       count(*)              AS events
FROM sessionstream WINDOW TUMBLING (size 6 HOURS)
    GROUP BY anonymousID;

CREATE TABLE PAGE_VIEWS AS
    SELECT HREF,
           COUNT(ANONYMOUSID) as USERS
FROM SESSIONSTREAM
    WINDOW TUMBLING (size 30 SECONDS)
GROUP BY HREF
    EMIT CHANGES;