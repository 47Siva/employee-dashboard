SELECT * FROM overtime_analysis

SELECT SUM(
    CASE 
        WHEN oa.estimated_hours = '' THEN 0.0 
        ELSE EXTRACT(EPOCH FROM CAST(oa.estimated_hours AS interval)) / '3600.0' 
    END
) 
FROM overtime_analysis oa 
WHERE oa.attendance_date BETWEEN '26/08/2023' AND '26/08/2023'

SELECT DISTINCT project_name
FROM overtime_analysis oa
WHERE oa.overtime_hours>0 AND TO_DATE(oa.attendance_date, 'DD/MM/YYYY') 
BETWEEN TO_DATE('26/08/2023','DD/MM/YYYY') AND TO_DATE('26/09/2023', 'DD/MM/YYYY')

SELECT DISTINCT user_name
FROM overtime_analysis oa
WHERE oa.overtime_hours>0 AND TO_DATE(oa.attendance_date, 'DD/MM/YYYY') 
BETWEEN TO_DATE('26/08/2023','DD/MM/YYYY') AND TO_DATE('26/09/2023', 'DD/MM/YYYY')