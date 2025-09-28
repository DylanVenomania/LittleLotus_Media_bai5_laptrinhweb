CREATE DATABASE LittleLotusMediaDB;
GO
USE LittleLotusMediaDB;

-- Bảng Users
CREATE TABLE Users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    email NVARCHAR(100) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    display_name NVARCHAR(100),
    avatar_url NVARCHAR(255),
    created_at DATETIME2 DEFAULT GETDATE()
);

-- Bảng Posts
CREATE TABLE Posts (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    caption NVARCHAR(255),
    image_url NVARCHAR(255),
    created_at DATETIME2 DEFAULT GETDATE(),
    user_id BIGINT NOT NULL,
    CONSTRAINT FK_Posts_Users FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Bảng Comments
CREATE TABLE Comments (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    content NVARCHAR(500),
    created_at DATETIME2 DEFAULT GETDATE(),
    user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL,
    CONSTRAINT FK_Comments_Users FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE NO ACTION,
    CONSTRAINT FK_Comments_Posts FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE
);





INSERT INTO Users (username, email, password, display_name, avatar_url)
VALUES 
('admin', 'admin@littlelotus.com', '123456', 'Admin User', '/images/admin.png'),
('hoa', 'hoa@littlelotus.com', '123456', N'Hoa Nguyễn', '/images/hoa.png');

INSERT INTO Posts (caption, image_url, user_id)
VALUES
(N'Ngày đầu tiên cùng LittleLotus 🌸', '/images/post1.jpg', 1),
(N'Trời hôm nay thật đẹp ☀️', '/images/post2.jpg', 2);

INSERT INTO Comments (content, user_id, post_id)
VALUES
(N'Tuyệt vời quá!', 2, 1),
(N'Ảnh đẹp quá bạn ơi 😍', 1, 2);
