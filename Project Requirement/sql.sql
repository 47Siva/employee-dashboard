CREATE TABLE overtime_analysis (
    "Job Code" TEXT,
    "Job Name" TEXT,
    "Phase Code" TEXT,
    "Phase Name" TEXT,
    "Project Code" TEXT,
    "Project Name" TEXT,
    "Estimated-Hours" TEXT,
    "Status" TEXT,
    "User ID" INT,
    "User Name" TEXT,
    "attendance-date" TEXT,
    "Job Hours" TEXT,
    "Job Count" DOUBLE PRECISION,
    ot1 TEXT,
    ot2 TEXT,
    "Clocked Hours" FLOAT(8),
    "Overtime_Hours" FLOAT(8),
    "department-name" TEXT,
    "designation" TEXT,
    "designation-name" TEXT,
    "organization-name" TEXT,
    "grade-name" TEXT,
    "section-name" TEXT,
    "category-name" TEXT,
    "branch-name" TEXT,
    "Cost Per Hour" FLOAT(8),
    "Cost Incurred" FLOAT(8),
    "ProjectID" TEXT,
    "max-end-date" DATE,
    "Project Status" TEXT,
    "HasProjectStarted" TEXT,
    "Active Project" FLOAT(8),
    "Created Datetime" TIMESTAMP,
    "Start Date Time" TIMESTAMP,
    "End Date Time" TIMESTAMP,
    "ProcessDate" TIMESTAMP,
    "FHSHS" TEXT,
    "Day" TEXT,
    "Overtime-Percent" TEXT
);

-- Alter table to rename columns one by one
ALTER TABLE overtime_analysis
  RENAME COLUMN "Job Code" TO job_code;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Job Name" TO job_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Phase Code" TO phase_code;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Phase Name" TO phase_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Project Code" TO project_code;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Project Name" TO project_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Estimated-Hours" TO estimated_hours;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Status" TO status;

ALTER TABLE overtime_analysis
  RENAME COLUMN "User ID" TO user_id;

ALTER TABLE overtime_analysis
  RENAME COLUMN "User Name" TO user_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "attendance-date" TO attendance_date;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Job Hours" TO job_hours;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Job Count" TO job_count;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Clocked Hours" TO clocked_hours;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Overtime_Hours" TO overtime_hours;

ALTER TABLE overtime_analysis
  RENAME COLUMN "department-name" TO department_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "designation" TO designation;

ALTER TABLE overtime_analysis
  RENAME COLUMN "designation-name" TO designation_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "organization-name" TO organization_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "grade-name" TO grade_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "section-name" TO section_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "category-name" TO category_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "branch-name" TO branch_name;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Cost Per Hour" TO cost_per_hour;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Cost Incurred" TO cost_incurred;

ALTER TABLE overtime_analysis
  RENAME COLUMN "ProjectID" TO project_id;

ALTER TABLE overtime_analysis
  RENAME COLUMN "max-end-date" TO max_end_date;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Project Status" TO project_status;

ALTER TABLE overtime_analysis
  RENAME COLUMN "HasProjectStarted" TO has_project_started;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Active Project" TO active_project;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Created Datetime" TO created_date_time;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Start Date Time" TO start_date_time;

ALTER TABLE overtime_analysis
  RENAME COLUMN "End Date Time" TO end_date_time;

ALTER TABLE overtime_analysis
  RENAME COLUMN "ProcessDate" TO process_date;

ALTER TABLE overtime_analysis
  RENAME COLUMN "FHSHS" TO fshhs;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Day" TO day;

ALTER TABLE overtime_analysis
  RENAME COLUMN "Overtime-Percent" TO overtime_percent;
