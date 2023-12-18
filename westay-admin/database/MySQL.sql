-- 用户表 (已有 id，不做修改)
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       gender TINYINT,
                       create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                       update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       points INT DEFAULT 0,
                       vip_user TINYINT UNSIGNED,
                       profile_picture_url VARCHAR(255)
);

-- 角色表 (已有 id，不做修改)
CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

-- 权限表 (已有 id，不做修改)
CREATE TABLE permissions (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(100) NOT NULL
);

-- 用户角色关系表 (添加 id)
CREATE TABLE user_roles (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT,
                            role_id BIGINT,
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- 角色权限关系表 (添加 id)
CREATE TABLE role_permissions (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  role_id BIGINT,
                                  permission_id BIGINT,
                                  FOREIGN KEY (role_id) REFERENCES roles(id),
                                  FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

-- Token 表 (已有 id，不做修改)
CREATE TABLE tokens (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        token VARCHAR(255) NOT NULL,
                        expire_date DATETIME,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 登录日志 (已有 id，不做修改)
CREATE TABLE login_logs (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT,
                            login_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                            ip_address VARCHAR(50),
                            FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 操作日志 (已有 id，不做修改)
CREATE TABLE operation_logs (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                user_id BIGINT,
                                operation VARCHAR(255),
                                operation_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 异常日志 (已有 id，不做修改)
CREATE TABLE error_logs (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT,
                            error_message TEXT,
                            error_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 文件上传 (已有 id，不做修改)
CREATE TABLE file_uploads (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT,
                              file_url VARCHAR(255),
                              upload_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 用户评论 (已有 id，不做修改)
CREATE TABLE user_reviews (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT,
                              review TEXT,
                              review_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 游客会话 (已有 id，不做修改)
CREATE TABLE guest_sessions (
                                session_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                access_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                ip_address VARCHAR(50),
                                city_views_count INT DEFAULT 0
);

-- 初始化角色数据
INSERT INTO roles (name) VALUES ('普通用户'), ('VIP');

-- 初始化权限数据（根据实际需求调整）
INSERT INTO permissions (name) VALUES ('查看页面'), ('编辑资料'), ('访问高级功能'), ('管理用户'), ('查看报告');
