-- 123456789
INSERT INTO `users`(`createdata`, `email`, `firstname`, `lastname`, `password`, `role`, `username`)
VALUES ('2020-01-01','admin@tut.by','Adminov','Admin','$2a$12$Ti6Fk2jvq7Aw.KzWba4BW.iPyNcGQrufIYJBsygEXGdnUD7ih.zFW','ADMIN','admin');
INSERT INTO `users`( `firstname`, `lastname`, `role`, `username`) VALUES ('anonymous','anonymous','GUEST',' anonymous');
INSERT INTO `users`(`createdata`, `email`, `firstname`, `lastname`, `password`, `role`, `username`)
VALUES ('2020-01-01','trader_test@tut.by','Kolobkov','Kolobok','$2a$12$Ti6Fk2jvq7Aw.KzWba4BW.iPyNcGQrufIYJBsygEXGdnUD7ih.zFW','TRADER','trader');


INSERT INTO `trader`(`name`) VALUES ('BMW');
INSERT INTO `trader`(`name`) VALUES ('Toyota');
INSERT INTO `trader`(`name`) VALUES ('Volkswagen ');

INSERT INTO `comment`(`approved`, `createdate`, `message`, `trader_id`, `user_id`, `mark`) VALUES (0,'2020-02-03','first commit',1,3,8);
INSERT INTO `comment`(`approved`, `createdate`, `message`, `trader_id`, `user_id`, `mark`) VALUES (1,'2020-02-03','second commit',1,3,7)