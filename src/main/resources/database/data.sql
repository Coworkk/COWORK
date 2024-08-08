truncate table projects cascade;
truncate table tasks cascade;
truncate table users cascade;
truncate table subtasks cascade;
truncate table notes cascade;

insert into users(user_id, first_name, last_name, email) values
   (100, 'john', 'doe', 'johndoe@email.com'),
   (102, 'jane', 'doe', 'janedoe@gmail.com');

insert into projects(project_id, name, description) values
    (200, 'Smart home automation', 'Develop an integrated smart home system'),
    (201, 'df', 'fdf');

insert into tasks(task_id, project_project_id, user_user_id, title, description, priority, status) values
   (300, 200, 100, 'Develop voice control', 'Set up user voice assistant', 'HIGH', 'IN_PROGRESS'),
   (301, 200, 100, 'Create automation scenarios', 'Set pre automated routines', 'LOW', 'NOT_STARTED'),
   (302, 201, 100, 'Conduct system testing', 'Test smart home system functionality', 'MEDIUM', 'IN_PROGRESS');

insert into subtasks(sub_task_id, task_task_id) values
   (400, 300),
   (402, 300);

insert into notes(note_id, project_project_id) values
   (500, 200),
   (502, 200);