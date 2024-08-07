truncate table projects cascade;
truncate table tasks cascade;
truncate table users cascade;
truncate table subtasks cascade;
truncate table notes cascade;

insert into users(user_id) values
   (100);

insert into projects(project_id) values
    (200);

insert into tasks(task_id, project_project_id, user_user_id) values
   (300, 200, 100),
   (303, 200, 100);


insert into subtasks(sub_task_id, task_task_id) values
   (400, 300),
   (403, 300);

insert into notes(note_id, project_project_id) values
   (500, 200),
   (503, 200);