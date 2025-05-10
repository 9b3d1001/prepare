-- Departments
INSERT INTO departments (name) VALUES
('Engineering'), ('HR'), ('Finance'), ('Marketing');

-- Employees
INSERT INTO employees (name, department_id, salary, hire_date, active) VALUES
('Alice', 1, 90000, '2020-01-10', TRUE),
('Bob', 1, 80000, '2019-03-15', TRUE),
('Charlie', 2, 60000, '2021-06-01', TRUE),
('Diana', 3, 75000, '2018-11-20', FALSE),
('Eve', 4, 70000, '2022-05-05', TRUE),
('Frank', 1, 95000, '2017-08-30', TRUE);

-- Projects
INSERT INTO projects (name, budget, start_date, end_date) VALUES
('Project Apollo', 500000, '2023-01-01', '2023-12-31'),
('Project Gemini', 300000, '2022-06-01', '2023-06-01'),
('Project Mercury', 800000, '2021-01-01', '2023-06-30');

-- Employee-Project Assignments
INSERT INTO employee_projects (employee_id, project_id, role, allocation_percent) VALUES
(1, 1, 'Developer', 50),
(2, 1, 'Lead Developer', 100),
(1, 2, 'Developer', 50),
(3, 2, 'HR Support', 100),
(4, 3, 'Finance Analyst', 100),
(5, 1, 'Marketing Specialist', 100),
(6, 3, 'Architect', 100);

UPDATE employees
SET hire_date = '2025-04-10'
WHERE id = 1;