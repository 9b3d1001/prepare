CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    department_id INT,
    salary DECIMAL(10, 2),
    hire_date DATE,
    active BOOLEAN
);

CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);


CREATE TABLE projects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    budget DECIMAL(12, 2),
    start_date DATE,
    end_date DATE
);

CREATE TABLE employee_projects (
    employee_id INT REFERENCES employees(id),
    project_id INT REFERENCES projects(id),
    role VARCHAR(50),
    allocation_percent INT,
    PRIMARY KEY (employee_id, project_id)
);


ALTER TABLE employees
ADD CONSTRAINT fk_employees_department
FOREIGN KEY (department_id)
REFERENCES departments(id);