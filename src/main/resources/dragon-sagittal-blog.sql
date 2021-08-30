CREATE TABLE `ds_user` (
  `user_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
  `role_id` bigint COMMENT '角色ID',
  `user_name` varchar(255) COMMENT '用户账户',
  `user_password` varchar(255) COMMENT '用户密码',
  `user_age` int COMMENT '用户年龄',
  `user_birth` datetime COMMENT '用户生日',
  `user_sex` tinyint COMMENT '用户性别',
  `user_authority` tinyint COMMENT '用户权限',
  `user_autograph` varchar(255) COMMENT '用户头像URL',
  `user_integral` double COMMENT '用户积分',
  `user_level` tinyint COMMENT '用户等级',
  `user_create_time` datetime COMMENT '创建时间',
  `user_update_time` datetime COMMENT '更新时间'
);

CREATE TABLE `ds_role` (
  `role_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '角色ID',
  `role` varchar(255) COMMENT '角色'
);

CREATE TABLE `ds_user_integral` (
  `integral_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '积分ID',
  `user_id` bigint COMMENT '用户ID',
  `integral` double COMMENT '积分',
  `integral_type` tinyint COMMENT '积分类型',
  `integral_description` varchar(255) COMMENT '积分描述',
  `integral_create_time` datetime COMMENT '积分创建时间'
);

CREATE TABLE `ds_user_fans` (
  `fans_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '粉丝ID',
  `fans_user_id` bigint COMMENT '粉丝用户ID',
  `user_id` bigint COMMENT '用户ID',
  `fans_create_time` datetime COMMENT '粉丝创建时间'
);

CREATE TABLE `ds_user_friends` (
  `friends_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '好友ID',
  `user_id` bigint  COMMENT '用户ID',
  `friends_user_id` bigint COMMENT '好友用户ID',
  `friends_create_time` datetime COMMENT '好友创建时间'
);

CREATE TABLE `ds_blog` (
  `blog_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '博文ID',
  `blog_title` varchar(255) COMMENT '博文标题',
  `blog_content` varchar(255) COMMENT '博文内容',
  `blog_release_time` datetime COMMENT '博文发布时间',
  `blog_create_time` datetime COMMENT '博文创建时间',
  `blog_praise_times` int COMMENT '博文点赞数',
  `blog_collection_times` int COMMENT '博文收藏数',
  `blog_read_times` int COMMENT '博文阅读数',
  `user_id` bigint COMMENT '用户ID',
  `recovery_state` boolean COMMENT '回收状态',
  `is_deleted` boolean COMMENT '逻辑删除状态'
);

CREATE TABLE `ds_label` (
  `label_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '标签ID',
  `label` varchar(255) COMMENT '标签'
);

CREATE TABLE `ds_blog_label` (
  `blog_label_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '博文标签ID',
  `label_id` bigint COMMENT '标签ID',
  `blog_id` bigint COMMENT '博文ID'
);

CREATE TABLE `ds_category` (
  `category_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '分类ID',
  `category` varchar(255) COMMENT '分类'
);

CREATE TABLE `ds_blog_category` (
  `blog_category_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '博文分类ID',
  `category_id` bigint COMMENT '分类ID',
  `blog_id` bigint COMMENT '博文ID'
);

CREATE TABLE `ds_blog_collection` (
  `collection_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '博文收藏ID',
  `blog_id` bigint COMMENT '博文ID',
  `user_id` bigint COMMENT '用户ID',
  `collection_create_time` datetime COMMENT '收藏创建时间'
);

CREATE TABLE `ds_blog_comment` (
  `comment_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '博文评论ID',
  `blog_id` bigint COMMENT '博文ID',
  `user_id` bigint COMMENT '用户ID',
  `comment_time` datetime COMMENT '评论时间',
  `comment_content` varchar(255) COMMENT '评论内容',
  `father_comment_id` bigint COMMENT '父级评论ID'
);

CREATE TABLE `ds_blog_update` (
  `update_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '博文更新ID',
  `blog_id` bigint COMMENT '博文ID',
  `update_time` datetime COMMENT '博文更新时间',
  `update_description` varchar(255) COMMENT '博文更新内容'
);

CREATE TABLE `ds_blog_recovery` (
  `recovery_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '博文回收ID',
  `blog_id` bigint COMMENT '博文ID',
  `recovery_time` datetime COMMENT '回收时间'
);

ALTER TABLE `ds_user` ADD FOREIGN KEY (`role_id`) REFERENCES `ds_role` (`role_id`);

ALTER TABLE `ds_user` ADD FOREIGN KEY (`user_id`) REFERENCES `ds_user_integral` (`user_id`);

ALTER TABLE `ds_user_fans` ADD FOREIGN KEY (`user_id`) REFERENCES `ds_user` (`user_id`);

ALTER TABLE `ds_user_friends` ADD FOREIGN KEY (`user_id`) REFERENCES `ds_user` (`user_id`);

ALTER TABLE `ds_blog` ADD FOREIGN KEY (`user_id`) REFERENCES `ds_user` (`user_id`);

ALTER TABLE `ds_blog_label` ADD FOREIGN KEY (`label_id`) REFERENCES `ds_label` (`label_id`);

ALTER TABLE `ds_blog_label` ADD FOREIGN KEY (`blog_id`) REFERENCES `ds_blog` (`blog_id`);

ALTER TABLE `ds_blog_category` ADD FOREIGN KEY (`category_id`) REFERENCES `ds_category` (`category_id`);

ALTER TABLE `ds_blog_category` ADD FOREIGN KEY (`blog_id`) REFERENCES `ds_blog` (`blog_id`);

ALTER TABLE `ds_user` ADD FOREIGN KEY (`user_id`) REFERENCES `ds_blog_collection` (`user_id`);

ALTER TABLE `ds_blog` ADD FOREIGN KEY (`blog_id`) REFERENCES `ds_blog_collection` (`blog_id`);

ALTER TABLE `ds_blog_comment` ADD FOREIGN KEY (`blog_id`) REFERENCES `ds_blog` (`blog_id`);

ALTER TABLE `ds_blog_comment` ADD FOREIGN KEY (`user_id`) REFERENCES `ds_user` (`user_id`);

ALTER TABLE `ds_blog_update` ADD FOREIGN KEY (`blog_id`) REFERENCES `ds_blog` (`blog_id`);

ALTER TABLE `ds_blog_recovery` ADD FOREIGN KEY (`blog_id`) REFERENCES `ds_blog` (`blog_id`);
