truncate table projects cascade;
truncate table tasks cascade;
truncate table users cascade;

insert into users(user_id) values
   (100);

insert into projects(project_id) values
    (200);

insert into tasks(task_id, project_project_id, user_user_id) values
   (300, 200, 100);