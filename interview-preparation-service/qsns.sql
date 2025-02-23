-- https://www.sql-practice.com/

1)--Display every patient's first_name. Order the list by the length of each name and then by alphabetically.
select first_name from patients order by len(first_name), first_name;


2)-- Show the total amount of male patients and the total amount of female patients in the patients table.
   --Display the two results in the same row.

SELECT (select count(gender) from patients where gender='F') as female_count,
         (select count(gender) from patients where gender='M') as male_count;
#
select
    sum(case when gender = 'M' then 1 end) as male_count,
    sum(case when gender = 'F' then 1 end) as female_count
from patients;

#
SELECT
    SUM(Gender = 'M') as male_count,
    SUM(Gender = 'F') AS female_count
FROM patients

3)--Show the city and the total number of patients in the city.Order from most to least patients and then by city name ascending.
select city,  count(*)  AS num_patients from patients
                group by city order by num_patients DESC, city asc;

4)--Show first name, last name and role of every person that is either patient or doctor.The roles are either "Patient" or "Doctor"
SELECT first_name, last_name, 'Patient' as role FROM patients
union all
select first_name, last_name, 'Doctor' from doctors;

5)--Show all patient's first_name, last_name, and birth_date who were born in the 1970s decade.
        -- Sort the list starting from the earliest birth_date.

select first_name, last_name ,birth_date,year(birth_date) as yr from patients
where year(birth_date) between 1970 and 1980 order by birth_date ASC
#
SELECT
    first_name,
    last_name,
    birth_date
FROM patients
WHERE
        birth_date >= '1970-01-01'
  AND birth_date < '1980-01-01'
ORDER BY birth_date ASC

5)--We want to display each patient's full name in a single column. Their last_name in all upper letters must appear first,
    -- then first_name in all lower case letters. Separate the last_name and first_name with a comma. Order the list by the first_name in decending order
EX: SMITH,jane
select CONCAT(Upper(last_name),',', LOWER(first_name)) from patients order by first_name DESC

SELECT
        UPPER(last_name) || ',' || LOWER(first_name) AS new_name_format
FROM patients
ORDER BY first_name DESC;

6)--Show all columns for patient_id 542's most recent admission_date.
--I wrote :SELECT * FROM admissions where patient_id = 542 and admission_date =
-- (select MAX(admission_date) from admissions where patient_id = 542 group by patient_id)
#
SELECT *
FROM admissions
WHERE patient_id = 542
GROUP BY patient_id
HAVING
        admission_date = MAX(admission_date);

#
SELECT *
FROM admissions
WHERE
        patient_id = '542'
  AND admission_date = (
    SELECT MAX(admission_date)
    FROM admissions
    WHERE patient_id = '542'
)

#
SELECT *
FROM admissions
WHERE patient_id = 542
ORDER BY admission_date DESC
    LIMIT 1

#
SELECT *
FROM admissions
GROUP BY patient_id
HAVING
        patient_id = 542
   AND max(admission_date)

7)--For each doctor, display their id, full name, and the first and last admission date they attended.
-- I Wrote:
select d.doctor_id, concat(d.first_name,' ',d.last_name) as fullName,
       MIN(admission_date) AS FIRST_ADMISSION_DATE,
       MAX(admission_date) AS LAST_ADMISSION_DATE
from doctors d
         join admissions a
              ON a.attending_doctor_id = d.doctor_id
group by fullName

8) --For every admission, display the patient's full name, their admission diagnosis, and their doctor's full
    -- name who diagnosed their problem.
---Multi join
select concat(p.first_name,' ',p.last_name) as patientName, diagnosis,
       concat(d.first_name,' ',d.last_name) as doctorName
from doctors d
         join admissions a
         JOIN patients p
              ON a.attending_doctor_id = d.doctor_id
                  AND a.patient_id = p.patient_id


9)--Display patient's full name,
-- height in the units feet rounded to 1 decimal,
-- weight in the unit pounds rounded to 0 decimals,
-- birth_date,
-- gender non abbreviated.

Convert CM to feet by dividing by 30.48.
Convert KG to pounds by multiplying by 2.205.
select
      concat(first_name, ' ', last_name) AS 'patient_name',
      ROUND(height / 30.48, 1) as 'height "Feet"',
      ROUND(weight * 2.205, 0) AS 'weight "Pounds"', birth_date,
      CASE
          WHEN gender = 'M' THEN 'MALE'
          ELSE 'FEMALE'
          END AS 'gender_type'
  from patients

10)--Show patient_id, first_name, last_name from patients whose does not have
    -- any records in the admissions table. (Their patient_id does not exist in any admissions.patient_id rows.)
-- I wrote:
select p.patient_id, first_name, last_name
from patients p
         Left join admissions a
                   ON p.patient_id = a.patient_id
where a.patient_id is null


#
SELECT
    patients.patient_id,
    first_name,
    last_name
from patients
where patients.patient_id not in (
    select admissions.patient_id
    from admissions
)


11)--Display a single row with max_visits, min_visits, average_visits where the
-- maximum, minimum and average number of admissions per day is calculated.
-- Average is rounded to 2 decimal places.
select
    max(number_of_visits) as max_visits,
    min(number_of_visits) as min_visits,
    round(avg(number_of_visits),2) as average_visits
from (
         select admission_date, count(*) as number_of_visits
         from admissions
         group by admission_date
     )
-------

