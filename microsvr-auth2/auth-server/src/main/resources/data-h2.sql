-- ----------------------------
-- Table structure for e_mall_user
-- ----------------------------
DROP TABLE IF EXISTS e_mall_user;
CREATE TABLE e_mall_user (
  id bigint(20) NOT NULL,
  username varchar(20) NOT NULL,
  password varchar(100) NOT NULL,
  enabled bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
  client_id varchar(256) NOT NULL,
  resource_ids varchar(256) DEFAULT NULL,
  client_secret varchar(256) DEFAULT NULL,
  scope varchar(256) DEFAULT NULL,
  authorized_grant_types varchar(256) DEFAULT NULL,
  web_server_redirect_uri varchar(256) DEFAULT NULL,
  authorities varchar(256) DEFAULT NULL,
  access_token_validity int(11) DEFAULT NULL,
  refresh_token_validity int(11) DEFAULT NULL,
  additional_information varchar(4096) DEFAULT NULL,
  autoapprove varchar(256) DEFAULT NULL,
  PRIMARY KEY (client_id)
);


-- ----------------------------
-- Records of e_mall_user joe - password1; stanley - password2
-- ----------------------------
INSERT INTO e_mall_user VALUES ('1', 'joe', '$2a$04$Wx/0Es2chGQEADxJl2G9UexlAtIHDDSGGcYSP7SDuk3Jv9ipAHRo2', 1);
INSERT INTO e_mall_user VALUES ('2', 'stanley', '$2a$04$RjTLMr55Rhff8km5ekv/KOwJUIoqE6gDg4Jrl8N1hpQOLqhwdO9P6', 1);

-- ----------------------------
-- Records of oauth_client_details clientapp - abcd1234
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('clientapp', null, '$2a$04$zwL.1agunodQhXUk90nTWeNZysX2hhSp4Z2AEdHi4hCpzk7MwYWqK', 'user:profile:read,user:profile:write', 'password', 'http://localhost:9002/callback', null, '3600', '-1', null, 'false');
INSERT INTO `oauth_client_details` VALUES ('webapp', null, '$2a$04$zwL.1agunodQhXUk90nTWeNZysX2hhSp4Z2AEdHi4hCpzk7MwYWqK', 'user:profile:read,user:profile:write', 'authorization_code,password,refresh_token', 'http://localhost:9002/callback', null, '3600', '-1', null, 'false');

