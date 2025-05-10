--1. Retrieve the names and salaries of all active employees.
select name, salary from employees where active=true;

--2. Write a query to retrieve the names and salaries of all employees, sorted by salary in descending order.
select name, salary from employees order by salary desc;

--3. Write a query to retrieve the names of employees along with the names of the departments they belong to.
select e.name as employee_name, d.name as department_name
	from employees e JOIN departments d 
	on e.department_id = d.id;

--4. Write a query to find the average salary of employees in each department.
select d.name as department, avg(e.salary) as avarage_salary
	from employees e JOIN departments d
	on e.department_id = d.id
	group by d.id;
-- Minor Improvement Suggested by ChatGPT
select d.name as department, avg(e.salary) as avarage_salary
	from employees e JOIN departments d
	on e.department_id = d.id
	group by d.name;

--5. Write a query to retrieve the names of employees whose salary is greater than the average salary of all employees.
select e1.name from employees e1
	where salary > (
		select avg(salary) from employees e2
	);

--6. Write a query to retrieve the names of employees who were hired before January 1, 2020.
select name from employees where hire_date < '2020-01-01';

--7. Write a query to increase the salary by 10% for all employees in a specific department (e.g., Engineering).
update employees e
	set salary = salary * 1.1
	where e.department_id = (
		select d.id from departments d where name='Engineering'
	);

--8. Write a query to find the number of employees in each department.
select d.name, count(e) 
	from employees e JOIN departments d
	on e.department_id = d.id
	group by d.name;
-- Minor Improvement Suggested by ChatGPT	
select d.name, count(e.id) 
	from employees e JOIN departments d
	on e.department_id = d.id
	group by d.name;

--9. Write a query to delete all employees who belong to the "Sales" department.
delete from employees e
	where department_id = (
		select d.id from departments d where d.name='Sales'
	);

--10. Write a query to retrieve the distinct job titles of employees who are currently active and earn more than 60,000.
select distinct(ep.role)
	from employees e JOIN employee_projects ep
	on ep.employee_id = e.id
	where e.active=true
	and e.salary > 60000;

--11. Write a query to find the names of employees who do not belong to any department.
select e.name from employees e where department_id IS NULL;

--12. Write a query to display each employee’s name along with the number of projects they are assigned to.
select e.name as employee_name, count(ep.employee_id) as project_count
	from employees e JOIN employee_projects ep
	on e.id = ep.employee_id
	group by e.name;
-- Minor Improvement Suggested by ChatGPT	
select e.name as employee_name, count(ep.employee_id) as project_count
	from employees e JOIN employee_projects ep
	on e.id = ep.employee_id
	group by e.name, e.id;

--13. Write a query to find departments that have more than 2 employees.
select d.name as department
	from departments d JOIN employees e
	on d.id = e.department_id
	group by (d.id)
	having  count(d.id) > 2;
-- Minor Improvement Suggested by ChatGPT	
SELECT d.name AS department
FROM departments d
JOIN employees e ON d.id = e.department_id
GROUP BY d.id, d.name
HAVING COUNT(e.department_id) > 2;

--14. Write a query to list each employee’s name along with a status based on their activity
SELECT e.name,
	CASE
		WHEN active=true THEN 'Active' 
		WHEN active=false THEN 'Inactive'
		ELSE 'Unknown'
	END
	as status
	from employees e;

--15. Write a query to find all employees who were hired in the last 6 months (from today).
select * from employees where hire_date > CURRENT_DATE - INTERVAL '6 months'

--16. Write a query to find employees who belong to the same department and display their names alongside the names of their colleagues (excluding themselves).
select e1.name as employee, e2.name as colleague 
	from employees e1 JOIN employees e2
	on e1.department_id = e2.department_id
	where e1.id != e2.id;

--17. Write a query to find the first 3 characters of the names of employees whose names start with the letter "J".
select substring(e.name, 1, 3) from employees e where e.name like 'F%';

--18. Write a query to find all departments that have at least one employee with a salary greater than 75,000.
select distinct(d.name) 
	from departments d JOIN employees e
	on d.id = e.department_id
	where e.salary > 75000;
-- With exists
SELECT d.name
FROM departments d
WHERE EXISTS (
  SELECT 1
  FROM employees e
  WHERE e.department_id = d.id
  AND e.salary > 75000
);


--19. Write a query to find each employee’s salary along with the average salary of their department, using a window function.
select e.salary, avg(e.salary) over (partition by e.department_id) as department_avg from employees e;

--20. Write a query to find the number of employees in each department who have a salary greater than 75,000.
select d.name, count(e.id) as hightly_paid_employees 
	from employees e join departments d
	on e.department_id = d.id
	where e.salary > 75000
	group by d.id,d.name

--21. Write a query to find the names of employees who have a higher salary than the average salary of their department.
select e1.name 
	from employees e1 JOIN 
	(select department_id as did, avg(salary) as department_avarage from employees group by department_id)
	on e1.department_id = did
	where e1.salary > department_avarage;
-- Minor improvement suggested by chat gpt
select e1.name 
	from employees e1 JOIN 
	(select department_id as did, avg(salary) as department_avarage from employees group by department_id) department_avg
	on e1.department_id = department_avg.did
	where e1.salary > department_avg.department_avarage;

--22. Write a query to find the names of employees who were hired in the same month (any year) as today.
select e.name from employees e where EXTRACT(month from e.hire_date) = EXTRACT (month from CURRENT_DATE);

--23. Write a query to find the departments that have more than one employee assigned.
select d.name 
	from departments d JOIN 
	(
		select e.department_id, count(e.department_id) as dep_emp_count
		from employees e 
		group by e.department_id 
	) department_counts
	on d.id=department_counts.department_id
	where department_counts.dep_emp_count > 1;
-- Minor Imporvement
select d.name 
	from departments d JOIN 
	(
		select e.department_id, count(e.department_id) as dep_emp_count
		from employees e 
		group by e.department_id 
		having count(e.department_id) > 1
	) department_counts
	on d.id=department_counts.department_id;
	
--24. Write a query to display each department’s name along with:
--The total number of employees in that department.
--The number of active employees in that department.
-- The number of employees earning more than 70,000 in that department.	
select d.name as department_name, count(e.id) as total_employees,
	sum(
		case
			when e.active=true then 1
			else 0
		end
	) as active_employees,
	sum(
		case
			when e.salary > 70000 then 1
			else 0
		end
	) as high_earners
	from departments d join employees e
	on d.id = e.department_id
	group by d.name,d.id; 

--25. Write a query to find the departments where the average salary is greater than the average salary of all employees.
select d.name as department_name
	from departments d join (
		select e.department_id, avg(e.salary) as das_avg from employees e group by e.department_id
	) das
	on d.id = das.department_id
	where das.das_avg > (select avg(e2.salary) from employees e2);

--26. Write a query to find the names of all employees who are assigned to at least one project but are not active.
select e.name  
	from employees e join employee_projects ep
	on ep.employee_id = e.id
	where e.active= false;
-- Minor improvement suggested by ChatGPT
select distinct e.name  
	from employees e join employee_projects ep
	on ep.employee_id = e.id
	where e.active= false;  

--27. Find the names of employees who are working on more than one project.
select e.name
	from employees e join (
		select ep.employee_id, count(ep.project_id) as project_count
		from employee_projects ep 
		group by ep.employee_id
		having count(ep.project_id) > 1
	) epd
	on e.id = epd.employee_id;

--28. For each employee, list their name along with a comma-separated list of project roles they have across all assigned projects.
select e.name, STRING_AGG(ep.role, ', ') as roles
	from employees e join employee_projects ep
	on e.id=ep.employee_id
	group by e.name;

--29. Write a query to find all pairs of employees who are in the same department but have different salaries. Avoid filtering out NULL values in salary or department.
select e1.name as employee1, e2.name as employee2, e1.department_id
	from employees e1 join employees e2
	on e1.id != e2.id
	where e1.department_id = e2.department_id
	and e1.salary is distinct from e2.salary;

--30. Find the departments that have the highest number of employees.
select d.name as deparment_name, count(d.id) as employee_count
	from departments d join employees e
	on d.id = e.department_id
	group by d.name
	order by employee_count desc
	limit 1;

--31. Find the employees who have worked in the same department but have different roles.
select e1.name as employee1_name, e2.name employee2_name, e1.department_id as department_id from
	(
		select e.id, e.name, e.department_id, ep.role  
		from employees e join employee_projects ep
		on e.id = ep.employee_id
	) e1
	join
	(
		select e.id, e.name, e.department_id, ep.role  
		from employees e join employee_projects ep
		on e.id = ep.employee_id
	) e2
	on e1.department_id = e2.department_id
	where e1.id != e2.id
	and e1.role != e2.role;
-- Optimized Query without duplicates 
SELECT e1.name AS employee1_name, e2.name AS employee2_name, e1.department_id
FROM employees e1
JOIN employee_projects ep1 ON e1.id = ep1.employee_id
JOIN employees e2 ON e1.department_id = e2.department_id
JOIN employee_projects ep2 ON e2.id = ep2.employee_id
WHERE e1.id != e2.id
AND ep1.role != ep2.role;

--32. Find employees who have worked in more than one project. List the employee name and the number of projects they are involved in.
select e.name as employee_name, count(ep.project_id) as project_count
	from employees e join employee_projects ep
	on e.id = ep.employee_id
	group by e.id,e.name
	having count(ep.project_id) > 1;

--33. Find the total number of active employees and total number of high earners (salary greater than 75,000) for each department.
select 
	d.name as department_name,
	sum(
		CASE 
			when e.active=true then 1
			else 0
		END
	) as active_employees_count,
	sum(
		CASE
			when e.salary > 75000 then 1
			else 0
		END	
	) as high_earners_count
	from employees e join departments d
	on e.department_id = d.id
	group by d.name;

--34. Find the departments that have more than one employee with a salary greater than 75,000.
select d.name 
	from departments d join 
	(
		select e.department_id, count(e.id) as he_count
		from employees e 
		where e.salary > 75000
		group by e.department_id
	) es
	on d.id = es.department_id
	where es.he_count > 1;

--35. Retrieve the names of employees whose name starts with the letter 'A' and contains more than 5 characters.
select e.name from employees e
	where e.name like 'A%' and CHAR_LENGTH(e.name) > 4;

--36. Increase the salary by 15% for all employees who work in the 'Marketing' department. Use an UPDATE statement with a JOIN.
update employees e
	set salary = salary * 1.15
	from departments d 
	where e.department_id = d.id
	and d.name = 'Marketing';

--37. Get the departments that have more than the average number of employees across all departments.
select d.name,count(e.department_id)
	from departments d join employees e
	on d.id = e.department_id
	group by (e.department_id,d.name)
	having count(e.department_id) > avg(e.department_id);
-- My query is incorrect here is the correct version
SELECT d.name, COUNT(e.department_id)
FROM departments d
JOIN employees e ON d.id = e.department_id
GROUP BY d.name
HAVING COUNT(e.department_id) > (
    SELECT AVG(department_count)
    FROM (
        SELECT COUNT(*) AS department_count
        FROM employees
        GROUP BY department_id
    ) AS avg_department_counts
);

--38. Write a query to return the employee names and their status based on their salary. If their salary is greater than 70000, display "High Earner". If their salary is between 50000 and 70000, display "Medium Earner". Otherwise, display "Low Earner".
select e.name,
	case
		when e.salary > 80000 then 'High Earner'
		when e.salary > 60000 then 'Mid Earer'
		else 'Low Earner'
	end as earner
from employees e;	

--39. Fetch the names of employees whose names end with the letter 'e', and convert those names to uppercase in the result.
select upper(e.name) as name_in_uppercase from employees e where e.name like '%e';

--40. Get the names of employees who were hired in the month of April, regardless of the year.
select e.name,EXTRACT(month from e.hire_date) from employees e where EXTRACT(month from e.hire_date) = 4;

--41. Write a query to find departments that have more than 2 employees with a salary greater than 70000.
select d.name from
	departments d join employees e
	on d.id = e.department_id
	where e.salary > 70000
	group by (d.name,d.id)
	having count(e.id) > 2;

--42. Write a query to display each employee's salary along with the maximum salary in their department.
select e.name, e.salary, max(e.salary) over(partition by e.department_id) as department_maximum from employees e;

--43. Write a query to list departments where:There are more than 2 employees, and The average salary in the department is above 70,000.
select d.name, count(e.id)
	from departments d join employees e
	on d.id = e.department_id
	group by d.name, d.id
	having count(e.id) > 2 and avg(e.salary) > 70000;

--44. Use a CTE to find the average salary for each department, and then return departments with an average salary greater than 70,000.
with avarage_department_salary as (
	select 
		e.department_id, avg(e.salary) as dept_avg , d.name
		from employees e join departments d 
		on e.department_id=d.id 
		group by (e.department_id,d.name)
)
select name from avarage_department_salary where dept_avg > 70000;

--45. Write a query using CTE that finds departments where the number of employees with a salary greater than 60,000 is greater than 3.
with department_high_earners as (
	select d.name, count(e.id) as
		from departments d join employees e
		on d.id = e.department_id
		where e.salary > 60000
		group by(d.name,d.id)
)
select dhe.name from department_high_earners dhe where count >= 3; 

--46. Use a CTE to list employees hired in the last 6 months and display their names and hire dates.	