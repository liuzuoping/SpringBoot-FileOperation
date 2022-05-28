# trms_data DDL
create schema if not exists trms_data collate utf8_general_ci;
use trms_data;
create table if not exists category
(
	id bigint auto_increment
		primary key,
	parent_id bigint null,
	is_parent tinyint(1) null,
	access int null,
	name varchar(100) null,
	last_update_time datetime null
);

create table if not exists category_group_access
(
	cid bigint null,
	group_id bigint null
);

create table if not exists group_user
(
	group_id bigint null,
	user_key varchar(100) null
);

create table if not exists report
(
	id bigint auto_increment
		primary key,
	cid bigint default 1 not null,
	uid varchar(100) not null,
	access int null,
	title varchar(100) null,
	description varchar(100) null,
	create_time datetime null,
	last_update_time datetime null,
	version varchar(100) null,
	size varchar(100) null,
	del_flag tinyint(1) default 0 not null comment '删除标识（0 否 1 是）',
	path varchar(500) null,
	cid0 bigint not null,
	version_lock int default 0 not null
);

create table if not exists report_group_access
(
	rid bigint null,
	group_id bigint null
);

create table if not exists suggestion
(
	id bigint auto_increment
		primary key,
	uid varchar(100) null,
	content text null,
	status int null,
	create_time datetime null
);

create table if not exists sys_group
(
	id bigint auto_increment
		primary key,
	name varchar(100) null,
	description varchar(100) null,
	last_update_time datetime null,
	is_exclusive tinyint(1) null
);
