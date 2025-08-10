CREATE TABLE notification_task (
    id BIGINT PRIMARY KEY NOT NULL,
    chat_id VARCHAR(255) NOT NULL,
    notification_text TEXT NOT NULL,
    scheduled_time TIMESTAMP NOT NULL
);
