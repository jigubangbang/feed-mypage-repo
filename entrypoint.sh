#!/bin/sh
echo "Starting Feed Service..."
java -jar /app/feed-service.jar &
FEED_PID=$!
echo "Feed Service started with PID: $FEED_PID"

echo "Starting Mypage Service..."
java -jar /app/mypage-service.jar &
MYPAGE_PID=$!
echo "Mypage Service started with PID: $MYPAGE_PID"

echo "All feed-mypage-repo services are running. Waiting for them to finish..."

wait $FEED_PID
wait $MYPAGE_PID

echo "All services have terminated. Exiting container."
