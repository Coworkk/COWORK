truncate table projects cascade;
truncate table tasks cascade;
truncate table users cascade;
truncate table subtasks cascade;
truncate table notes cascade;
truncate table Role cascade;

insert into users(user_id, first_name, last_name, email) values
   ('f62f68e8-023f-4c67-9e87-7af2a111e5eb', 'john', 'doe', 'johndoe@email.com');

insert into projects(project_id, name, description) values
    (200, 'Smart home automation', 'Develop an integrated smart home system'),
    (201, 'df', 'fdf');

insert into Role(id , role_name )values
       (1,'ADMIN' );

insert into tasks( task_id, project_project_id, user_user_id, title, description, priority, status, start_date, due_date) values
   (300, 200, 'f62f68e8-023f-4c67-9e87-7af2a111e5eb', 'Develop voice control', 'Set up user voice assistant', 'HIGH', 'IN_PROGRESS', '2024-08-06 00:00:00', '2024-09-09 09:00:00'),
   (301, 200, 'f62f68e8-023f-4c67-9e87-7af2a111e5eb', 'Create automation scenarios', 'Set automated routines', 'LOW', 'NOT_STARTED', '2024-08-06 00:00:00', '2024-10-10 10:00:00'),
   (302, 201, 'f62f68e8-023f-4c67-9e87-7af2a111e5eb', 'Conduct system testing', 'Test home system functions', 'MEDIUM', 'IN_PROGRESS', '2024-08-06 00:00:00', '2024-11-11 11:00:00');

insert into subtasks(sub_task_id, task_task_id, title, description, status, start_date, due_date) values
   (400, 300, 'GG', 'GG', 'COMPLETED',  '2024-08-07 00:00:00', '2024-08-17 07:00:00'),
   (401, 300,  'GG', 'GG', 'IN_PROGRESS', '2024-08-07 00:00:00', '2024-08-18 08:00:00'),
   (402, 301,  'GG', 'GG', 'NOT_STARTED', '2024-08-07 00:00:00', '2024-08-19 09:00:00');

insert into notes(note_id, project_project_id, user_user_id, content) values
   (500, 200, 'f62f68e8-023f-4c67-9e87-7af2a111e5eb', 'GG'),
   (501, 200, 'f62f68e8-023f-4c67-9e87-7af2a111e5eb', 'GG'),
   (502, 201, 'f62f68e8-023f-4c67-9e87-7af2a111e5eb', 'GG');

insert into comments(comment_id, task_task_id) values
    (600, 300);