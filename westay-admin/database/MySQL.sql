-- 用户表 (已有 id，不做修改)
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       gender TINYINT,
                       lastname VARCHAR(50) NOT NULL,
                       firstname VARCHAR(50) NOT NULL,
                       headUrl VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       phone VARCHAR(100) NOT NULL,
                       create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                       update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       points INT DEFAULT 0,
                       vip_user TINYINT UNSIGNED,
                       profile_picture_url VARCHAR(255),
                       status tinyint
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
                        update_date DATETIME,
                        create_date DATETIME,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 登录日志 (已有 id，不做修改)
CREATE TABLE login_logs (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            operation tinyint unsigned,
                            status tinyint unsigned NOT NULL,
                            user_agent varchar(500),
                            ip varchar(39),
                            creator_name varchar(50),
                            creator bigint,
                            create_date datetime,
                            key idx_status (status),
                            key idx_create_date (create_date)
);

-- 操作日志 (已有 id，不做修改)
CREATE TABLE operation_logs (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                operation            varchar(50) COMMENT '用户操作',
                                request_uri          varchar(200) COMMENT '请求URI',
                                request_method       varchar(20) COMMENT '请求方式',
                                request_params       text COMMENT '请求参数',
                                request_time         int unsigned NOT NULL COMMENT '请求时长(毫秒)',
                                user_agent           varchar(500) COMMENT '用户代理',
                                ip                   varchar(39) COMMENT '操作IP',
                                status               tinyint unsigned NOT NULL COMMENT '状态  0：失败   1：成功',
                                creator_name         varchar(50) COMMENT '用户名',
                                creator              bigint COMMENT '创建者',
                                create_date          datetime COMMENT '创建时间',
                                key idx_create_date (create_date)
);

-- 异常日志 (已有 id，不做修改)
CREATE TABLE error_logs (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            request_uri          varchar(200) COMMENT '请求URI',
                            request_method       varchar(20) COMMENT '请求方式',
                            request_params       text COMMENT '请求参数',
                            user_agent           varchar(500) COMMENT '用户代理',
                            ip                   varchar(39) COMMENT '操作IP',
                            error_info           text COMMENT '异常信息',
                            creator              bigint COMMENT '创建者',
                            create_date          datetime COMMENT '创建时间',
                            key idx_create_date (create_date)
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
INSERT INTO roles (name) VALUES ('游客'), ('普通用户'), ('VIP');


-- 初始化权限数据（根据实际需求调整）
INSERT INTO permissions (name) VALUES ('访问城市卡'), ('查看评论'), ('发表评论'), ('编辑个人信息'), ('签证咨询'), ('一对一客服');