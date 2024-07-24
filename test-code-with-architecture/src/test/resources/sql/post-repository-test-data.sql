insert into `users` (`id`, `email`, `nickname`, `address`, `certification_code`, `status`, `last_login_at`)
values (1, 'kok202@naver.com', 'kok202', 'Seoul', 'aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaaa', 'ACTIVE', 0);
insert into `users` (`id`, `email`, `nickname`, `address`, `certification_code`, `status`, `last_login_at`)
values (2, 'kok203@naver.com', 'kok203', 'Seoul', 'aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaab', 'PENDING', 0);
insert into `posts` (`id`, `content`, `created_at`, `modified_at`, `user_id`)
values (1, 'helloworld', 1678530673958, 1678530673958, 1);