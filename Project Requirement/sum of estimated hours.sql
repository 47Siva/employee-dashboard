SELECT SUM(
    CASE 
        WHEN oa.estimated_hours = '' THEN 0.0 
        ELSE EXTRACT(EPOCH FROM CAST(oa.estimated_hours AS interval)) / '3600.0' 
    END
) 
FROM overtime_analysis oa 
WHERE oa.attendance_date BETWEEN '26/08/2023' AND '26/08/2023'
