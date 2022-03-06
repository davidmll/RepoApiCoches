INSERT INTO usuarios (username,password,enabled) VALUES ("David","$2a$10$/P4np4JXZik5oUTcxMLuKuRU2XJs02N0hMY/RNMrJLisU38tv.hii",1);
INSERT INTO usuarios (username,password,enabled) VALUES ("admin","$2a$10$KAeFOm4XoQQCMTSPtgiDU.azCUpJZthq/fohSey8slT9EiPoOVO.G",1);

INSERT INTO roles (nombre) VALUES ("ROLE_USER");
INSERT INTO roles (nombre) VALUES ("ROLE_ADMIN");

INSERT INTO usuarios_roles (usuario_id,role_id)VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,role_id)VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id,role_id)VALUES (2,1);