truncate table projects cascade;
truncate table tasks cascade;
truncate table users cascade;
truncate table subtasks cascade;
truncate table notes cascade;

insert into users(user_id) values
   (100);

insert into projects(project_id) values
    (200),
    (201);

insert into tasks(task_id, project_project_id, user_user_id, title, description, priority, status) values
   (300, 200, 100, 'Develop voice control', 'Set up user voice assistant', 'HIGH', 'IN_PROGRESS'),
   (301, 201, 100, 'adcd', 'efgh', 'HIGH', 'IN_PROGRESS'),
   (302, 200, 100, 'Create automation scenarios', 'Set pre automated routines', 'LOW', 'NOT_STARTED');


insert into subtasks(sub_task_id, task_task_id) values
   (400, 300),
   (402, 300);

insert into notes(note_id, project_project_id) values
   (500, 200),
   (502, 200);