ini adalah microservice BE dengan menggunakan java spring boot:

spesification dari project :
-Java versi 17 <br/>
-Spring boot versi 3.1.10 <br/>
-postgre sql <br/>

A. untuk menjalankan aplikasi jalankan script ini :
1. create database db_user_management;
2. create database db_cours_management;
3. CREATE TABLE db_user_management.public.student (
	id varchar(36) NOT NULL,
	"name" varchar(100) NOT NULL,
	address varchar(255) NOT NULL,
	phone_number varchar(20) NOT NULL,
	mother_name varchar(100) NOT NULL,
	father_name varchar(100) NOT NULL,
	birth_date date NULL,
	place_birth varchar(50) NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at timestamp NULL,
	CONSTRAINT student_pkey PRIMARY KEY (id)
);
4. CREATE TABLE db_user_management.public.teacher (
	id varchar(36) NOT NULL,
	"name" varchar(100) NOT NULL,
	address varchar(255) NOT NULL,
	phone_number varchar(20) NOT NULL,
	birth_date date NULL,
	place_birt varchar(50) NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at timestamp NULL,
	CONSTRAINT teacher_pkey PRIMARY KEY (id)
);
5. CREATE TABLE db_cours_management.public.registration (
	id varchar(36) NOT NULL,
	student_name varchar(100) NOT NULL,
	birth_date date NULL,
	place_birth varchar(100) NULL,
	phone_number varchar(20) NOT NULL,
	mother_name varchar(100) NOT NULL,
	father_name varchar(100) NOT NULL,
	address varchar(255) NULL,
	religion varchar(30) NULL,
	zip_code varchar(10) NULL,
	last_school varchar(100) NULL,
	is_active bool NULL,
	school_fees numeric NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	CONSTRAINT registration_pkey PRIMARY KEY (id)
);
6. CREATE TABLE db_cours_management.public.salary (
	id varchar(36) NOT NULL,
	teacher_id varchar(36) NOT NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at timestamp NULL,
	teacher_name varchar(100) NULL,
	salary numeric NULL,
	monthly_allowance numeric NULL,
	CONSTRAINT subjects_pkey PRIMARY KEY (id)
);

B. jalankan service : 
   1. running service-registry
   2. running gateway-service
   3. running user-management
   4. running course-management
   
