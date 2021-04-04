CREATE
STREAM sessionstream (
	type varchar,
  	anonymousID varchar,
  	loadTime bigint,
  	unloadTime bigint,
  	language varchar,
  	platform varchar,
  	port int,
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