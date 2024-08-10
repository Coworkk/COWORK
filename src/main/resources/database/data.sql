truncate table projects cascade;
truncate table tasks cascade;
truncate table users cascade;
truncate table subtasks cascade;
truncate table notes cascade;

insert into users(user_id, first_name, last_name, email) values
   (100, 'john', 'doe', 'johndoe@email.com'),
   (101, 'jane', 'doe', 'janedoe@email.com');

insert into projects(project_id, name, description) values
    (200, 'Smart home automation', 'Develop an integrated smart home system'),
    (201, 'df', 'fdf'),
    (202, 'dfdf', 'dfsdf');

insert into tasks(task_id, project_project_id, user_user_id, title, description, priority, status, start_date, due_date) values
   (300, 200, 100, 'Develop voice control', 'Set up user voice assistant', 'HIGH', 'IN_PROGRESS', '2024-08-06 00:00:00', '2024-09-09 09:00:00'),
   (301, 200, 100, 'Create automation scenarios', 'Set automated routines', 'LOW', 'NOT_STARTED', '2024-08-06 00:00:00', '2024-10-10 10:00:00'),
   (302, 201, 100, 'Conduct system testing', 'Test home system functions', 'MEDIUM', 'IN_PROGRESS', '2024-08-06 00:00:00', '2024-11-11 11:00:00');

insert into subtasks(sub_task_id, task_task_id) values
   (400, 300),
   (401, 301),
   (402, 300);

insert into notes(note_id, project_project_id, user_user_id) values
   (500, 200, 100),
   (502, 200, 100);