CREATE TABLE IF NOT EXISTS profiles
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    name VARCHAR(255),
    status VARCHAR(50),
    role VARCHAR(50)
);
USE profile_db;

INSERT INTO profiles (email, name, status, role)
VALUES
    ('quanda@example.com', 'Quân Đặng', 'ACTIVE', 'USER'),
    ('nguyenvana@example.com', 'Nguyễn Văn A', 'ACTIVE', 'ADMIN'),
    ('tranthib@example.com', 'Trần Thị B', 'INACTIVE', 'USER'),
    ('leminhc@example.com', 'Lê Minh C', 'ACTIVE', 'USER'),
    ('phamthid@example.com', 'Phạm Thị D', 'ACTIVE', 'MANAGER');